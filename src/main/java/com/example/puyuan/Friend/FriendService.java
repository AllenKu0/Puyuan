package com.example.puyuan.Friend;


import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.AppUser.AppUserRepository;
import com.example.puyuan.Base.StatusResponse;
import com.example.puyuan.Friend.request.FriendDeleteRequest;
import com.example.puyuan.Friend.request.FriendSendRequest;
import com.example.puyuan.Friend.response.*;
import com.example.puyuan.UserSet.UserInformation.UserSetEntity;
import com.example.puyuan.UserSet.UserInformation.UserSetRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendService {
    private final FriendRepository friendRepository;

    private final UserSetRepository userSetRepository;

    private final AppUserRepository appUserRepository;

    /**
     * 取德邀請碼
     * @return
     */
    public Map<String,Object> code() {
        var userDetails = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userData = appUserRepository.findById(userDetails.getId()).orElseThrow();
        var response = new LinkedHashMap<String,Object>();
        response.put("status", StatusResponse.RC.SUCCESS.getCode());
        response.put("message","ok");
        response.put("invite_code",userData.getUserSet().getInviteCode());
        return response;
    }

    /**
     * 取得控糖團列表
     * @return
     */
    public Map<String,Object> list() {
        var response = new LinkedHashMap<String,Object>();
        var userDetail = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var dataList = new ArrayList<>();
        //本帳號的好友
        var friendDataList = friendRepository.findByStatusAndUserId(1, userDetail.getId()).orElseThrow();

        //朋友ID
        List<Long> friendIds = friendDataList.stream()
                        .map(FriendEntity::getRelationId)
                        .collect(Collectors.toList());

        //朋友的UserSet
        var friendUserSetList = userSetRepository.findByAppUser_IdIn(friendIds).orElseThrow();
        for (var friend : friendDataList) {
            //找出朋友的UserSet，可能有多個，找第一個
            var friendData = friendUserSetList.stream()
                    .filter(fd -> fd.getAppUser().getId().equals(friend.getUserId()))
                    .findFirst()
                    .orElse(null);

            var friendResponseList = FriendListResponse.builder()
                    .id(friendData.getId())
                    .relation_type(friend.getFriend_type())
                    .name(friendData.getName())
                    .build();

            dataList.add(friendResponseList);
        }
        response.put("status",StatusResponse.RC.SUCCESS.getCode());
        response.put("friends",dataList);

        return response;
    }


    /**
     * 取得控糖團邀請
     * @return
     */
    public Map<String,Object> request() {

        var response = new LinkedHashMap<String,Object>();
        var userDetail = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var dataList = new ArrayList<>();

        var inviterList = friendRepository.findByStatusAndUserId(0,userDetail.getId()).orElseThrow();

        //拿朋友id
        List<Long> inviters = inviterList.stream()
                .map(FriendEntity::getRelationId)
                .collect(Collectors.toList());

        //拿朋友UserSet
        var friendDataList = userSetRepository.findByAppUser_IdIn(inviters).orElseThrow();

        for (var inviterData : inviterList){
            inviterData.setRead(true);
            friendRepository.save(inviterData);
            var friendRequestResponse = FriendRequestResponse.builder()
                    .id(inviterData.getId())
                    .user_id(inviterData.getUserId())
                    .read(1)
                    .relation_id(inviterData.getRelationId())
                    .type(inviterData.getFriend_type())
                    .status(inviterData.getStatus())
                    .created_at(inviterData.getCreated_at())
                    .updated_at(inviterData.getUpdated_at())
                    .user(getFriendUserSetResponse(friendDataList,inviterData))
                    .build();

            dataList.add(friendRequestResponse);
        }
        response.put("status",StatusResponse.RC.SUCCESS.getCode());
        response.put("message","ok");
        response.put("requests",dataList);

        return response;
    }


    public StatusResponse send(FriendSendRequest request) {
        var userDetail =  (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //寄送者
        var userData = userSetRepository.findByEmail(userDetail.getEmail()).orElseThrow();
        //被邀請者
        var friendData = userSetRepository.findByInviteCode(request.getInvite_code()).orElseThrow();
        //是否有傳送邀請了
        var inviteRepeat = friendRepository.findByUserIdAndRelationId(userData.getAppUser().getId(),friendData.getAppUser().getId());
        //是否有傳送邀請了且沒被拒絕了或以接受
        var isSendInvite = friendRepository.findByUserIdAndRelationId(userData.getAppUser().getId(),friendData.getAppUser().getId())
                //代表 B 使用者還沒有拒絕 A 使用者的邀請
                .map(friend -> friend.getStatus() != 2) // status = 2 代表已拒絕邀請
                .orElse(false);
        if(isSendInvite || inviteRepeat.isPresent() || userData.getAppUser().getId().equals(friendData.getAppUser().getId())){
            return StatusResponse.FAILED();
        }
        //沒寄送過直接創建
        var friendRelation = FriendEntity.builder()
                .userId(userData.getAppUser().getId())
                .relationId(friendData.getAppUser().getId())
                .friend_type(request.getType())
                .status(0)
                .read(false)
                .build();

        friendRepository.save(friendRelation);
        return StatusResponse.SUCCESS();
    }

    public StatusResponse accept(Long id) {
        //找有邀請的然後沒接受的
        var acceptInvite = friendRepository.findByIdAndStatus(id,0).orElseThrow();
        acceptInvite.setStatus(1);
        friendRepository.save(acceptInvite);
        return StatusResponse.SUCCESS();

    }

    public StatusResponse refuse(Long id) {
        var refuseInvite = friendRepository.findByIdAndStatus(id,0).orElseThrow();
//        refuseInvite.setStatus(2);
//        friendRepository.save(refuseInvite);
        friendRepository.delete(refuseInvite);
        return StatusResponse.SUCCESS();
    }

    public StatusResponse removeByFriendId(Long id) {
        friendRepository.findByRelationIdAndStatus(id,2)
                .ifPresent(friendEntities -> {
                    friendRepository.delete(friendEntities);
                });
        return StatusResponse.SUCCESS();
    }

    @Transactional
    public StatusResponse remove(FriendDeleteRequest ids) {

        var userDetails = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userId = userDetails.getId();

        for (var friendId : ids.getIds()) {
            //刪除邀請人
            friendRepository.findByUserIdAndRelationIdAndStatus(userId,friendId.longValue(),1)
                    .ifPresent(inviter -> {
                        friendRepository.delete(inviter);
                    });

            //刪除被邀請人
            friendRepository.findByUserIdAndRelationIdAndStatus(friendId.longValue(),userId,1)
                    .ifPresent(inviter -> {
                        friendRepository.delete(inviter);
                    });
        }
        return StatusResponse.SUCCESS();
    }

    public Map<String,Object> results() {

        var response = new LinkedHashMap<String,Object>();
        var dataList = new ArrayList<>();
        var userDetails = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userData = appUserRepository.findById(userDetails.getId()).orElseThrow();
        var userFriendList = friendRepository.findByUserIdAndStatus(userData.getId(),1).orElseThrow();

        var friendIds = userFriendList.stream()
                .map(FriendEntity::getRelationId)
                .collect(Collectors.toList());

        var friendUserSetList = userSetRepository.findByIdIn(friendIds).orElseThrow();

        for (var userFriend : userFriendList) {
            var friendResult = FriendResultResponse.builder()
                    .id(userFriend.getId())
                    .userId(userFriend.getUserId())
                    .relationId(userFriend.getRelationId())
                    .type(userFriend.getFriend_type())
                    .status(userFriend.getStatus())
                    .read(userFriend.getRead())
                    .created_at(userFriend.getCreated_at())
                    .updated_at(userFriend.getUpdated_at())
                    .relation(getFriendUserSet(friendUserSetList, userFriend))
                    .build();

            dataList.add(friendResult);
        }
        response.put("status",0);
        response.put("message","ok");
        response.put("results",dataList);

        return response;
    }

    private FriendInfoResponse getFriendUserSet(List<UserSetEntity> userSetEntities,FriendEntity inviteData){
        //朋友的UserSet
        var friendUserSet = userSetEntities.stream()
                .filter(fd -> fd.getAppUser().getId().equals(inviteData.getRelationId()))
                .findFirst()
                .orElse(null);
        var account = appUserRepository.findById(friendUserSet.getAppUser().getId()).orElseThrow();

        var response = FriendInfoResponse.builder()
                .id(friendUserSet.getId())
                .name(friendUserSet.getName())
                .account(account.getAccount())
                .email(friendUserSet.getEmail())
                .phone(friendUserSet.getPhone())
                .gender(friendUserSet.getGender())
                .birthday(friendUserSet.getBirthday())
                .status(friendUserSet.getStatus())
                .group(friendUserSet.getGroups())
                .height(friendUserSet.getHeight())
                .badge(friendUserSet.getBadge())
                .updated_at(friendUserSet.getUpdated_at())
                .created_at(friendUserSet.getCreated_at())
                .build();

        return response;
    }
    private FriendUserSetResponse getFriendUserSetResponse(List<UserSetEntity> userSetEntities,FriendEntity inviteData) {
        var inviteUserSet = userSetEntities.stream()
                .filter(fd -> fd.getId().equals(inviteData.getRelationId()))
                .findFirst()
                .orElse(null);

        return FriendUserSetResponse.builder()
                .id(inviteData.getId())
                .name(inviteUserSet.getName())
                .account(inviteUserSet.getEmail())
                .build();
    }



}
