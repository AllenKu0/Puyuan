package com.example.puyuan.Notify;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.Base.StatusResponse;
import com.example.puyuan.Diary.DiaryRepository;
import com.example.puyuan.Diary.response.Location;
import com.example.puyuan.Measurement.BloodPressure.BloodPressureRepository;
import com.example.puyuan.Measurement.BloodSugar.BloodSugarRepository;
import com.example.puyuan.Measurement.Weight.WeightRepository;
import com.example.puyuan.Notify.news.NewsEntity;
import com.example.puyuan.Notify.news.NewsRepository;
import com.example.puyuan.Notify.notification.NotificationEntity;
import com.example.puyuan.Notify.notification.NotificationRepository;
import com.example.puyuan.Notify.notification.request.NotificationRequest;
import com.example.puyuan.Notify.share.ShareEntity;
import com.example.puyuan.Notify.share.ShareRepository;
import com.example.puyuan.Notify.share.request.ShareRequest;
import com.example.puyuan.Notify.news.response.NewsResponse;
import com.example.puyuan.Notify.share.response.ShareLocationResponse;
import com.example.puyuan.Notify.share.response.ShareResponse;
import com.example.puyuan.Notify.share.response.ShareUserResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotifyService {
    private NewsRepository newsRepository;
    private ShareRepository shareRepository;
    private BloodSugarRepository bloodSugarRepository;
    private BloodPressureRepository bloodPressureRepository;
    private WeightRepository weightRepository;
    private DiaryRepository diaryRepository;
    private NotificationRepository notificationRepository;


    public StatusResponse notification(NotificationRequest request) {
        var userDetails = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var notifyData = NotificationEntity.builder()
                .member_id(userDetails.getId().intValue())
                .reply_id(userDetails.getId().intValue())
                .message(request.getMessage())
                .build();

        notificationRepository.save(notifyData);

        return StatusResponse.SUCCESS();
    }
    public Map<String,Object> news() {
        var response = new LinkedHashMap<String,Object>();
        var newsDataList = new ArrayList<>();
        var count = newsRepository.count();
        if (count == 0) {
            var newsNull = NewsResponse
                    .builder()
                    .id(999L)
                    .member_id(999)
                    .group(999)
                    .title("999")
                    .message("999")
                    .pushed_at(LocalDateTime.now())
                    .created_at(LocalDateTime.now())
                    .updated_at(LocalDateTime.now())
                    .build();
            newsDataList.add(newsNull);
            response.put("status", "0");
            response.put("message", "ok");
            response.put("news", newsDataList);
        } else {
            List<NewsEntity> newsData = newsRepository.findAll();
            newsDataList.add(newsData);
            response.put("status", StatusResponse.RC.SUCCESS.getCode());
            response.put("message", "ok");
            response.put("news", newsDataList);
        }
        return response;
    }

    public StatusResponse share(ShareRequest request) {
        var userDetail = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ShareEntity shareData = new ShareEntity();
        shareData.builder()
                .fid(userDetail.getId())
                .dataType(request.getType())
                .relation_type(request.getRelation_type())
                .appUser(userDetail)
                .build();

        shareRepository.save(shareData);
        return StatusResponse.SUCCESS();
    }

    public Map<String,Object> type(Integer request) {
        var userDetails = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var response = new LinkedHashMap<String,Object>();
        var typeData = new ArrayList<>();
        var checkData = shareRepository.findAllByDataTypeAndAppUser(request,userDetails);
        var pressureData = bloodPressureRepository.findFirstByAppUserOrderByRecordedAtDesc(userDetails);
        var sugarData = bloodSugarRepository.findFirstByAppUserOrderByRecordedAtDesc(userDetails);
        var weightData = weightRepository.findFirstByAppUserOrderByRecordedAtDesc(userDetails);
        var diaryData = diaryRepository.findFirstByAppUserOrderByRecordedAtDesc(userDetails);
        var notificationData = notificationRepository.findFirstByAppUserOrderByCreatedAtDesc(userDetails);

        if(checkData.isEmpty()){
            var shareNull = ShareResponse
                    .builder()
                    .id(99L)
                    .user_id(99)
                    .sugar(99)
                    .timeperiod(99)
                    .weight(99)
                    .body_fat(99)
                    .bmi(99)
                    .systolic(99)
                    .diastolic(99)
                    .pulse(99)
                    .meal(99)
                    .tag(new ArrayList<>())
                    .image(new ArrayList<>())
                    .location(ShareLocationResponse
                            .builder()
                            .lat("999")
                            .lng("999")
                            .build())
                    .relation_type(99)
                    .relation_id(99)
                    .message("999")
                    .type(99)
                    .url("99")
                    .recorded_at(LocalDateTime.now())
                    .created_at(LocalDateTime.now())
                    .user(ShareUserResponse
                            .builder()
                            .id(99L)
                            .account("999")
                            .name("999")
                            .build())
                    .build();
            typeData.add(shareNull);
            response.put("status", "0");
            response.put("message", "ok");
            response.put("records", typeData);
        } else {
            int maxIndex = checkData.size();
            for (int i=0;i<maxIndex;i++) {
                var share = ShareResponse.builder()
                        .id(userDetails.getId())
                        .user_id(userDetails.getId().intValue())
                        .sugar(i < sugarData.size() ? sugarData.get(i).getSugar():0)
                        .timeperiod(i < sugarData.size() ? sugarData.get(i).getTimeperiod():0)
                        .weight(i < weightData.size() ? weightData.get(i).getWeight() : 0)
                        .body_fat(i < weightData.size() ? weightData.get(i).getBody_fat() : 0)
                        .bmi(i < weightData.size() ? weightData.get(i).getBmi() : 0)
                        .systolic(i < pressureData.size() ? pressureData.get(i).getSystolic().intValue() : 0)
                        .diastolic(i < pressureData.size() ? pressureData.get(i).getDiastolic().intValue() : 0)
                        .pulse(i < pressureData.size() ? pressureData.get(i).getPulse().intValue() : 0)
                        .meal(i < diaryData.size() ? diaryData.get(i).getMeal() : 0)
                        .tag(new ArrayList<>())
                        .image(new ArrayList<>())
//                        .tag(i < diaryData.size() ? diaryData.get(i).getTag() : new ArrayList<>())
//                        .image(i < diaryData.size() ? Collections.singletonList(diaryData.get(i).getImage().toString()) : new ArrayList<>())
                        .location(ShareLocationResponse.builder()
                                .lat(i < diaryData.size() ? diaryData.get(i).getLat() : "")
                                .lng(i < diaryData.size() ? diaryData.get(i).getIng() : "")
                                .build())
                        .relation_type(i < diaryData.size() ? diaryData.get(i).getImage() : 0)
                        .relation_id(i < notificationData.size() ? notificationData.get(i).getMember_id() : 0)
                        .message(i < notificationData.size() ? notificationData.get(i).getMessage() : "")
                        .type(request)
                        .url("0")
                        .recorded_at(i < diaryData.size() ? diaryData.get(i).getRecorded_at() : LocalDateTime.now())
                        .created_at(i < diaryData.size() ? diaryData.get(i).getCreated_at() : LocalDateTime.now())
                        .user(ShareUserResponse.builder()
                                .id(userDetails.getId())
                                .account(userDetails.getEmail())
                                .name(userDetails.getAccount() != null ? userDetails.getAccount() : "")
                                .build())
                        .build();
                typeData.add(share);
            }
            response.put("status", "0");
            response.put("message", "ok");
            response.put("records", typeData.toArray(new ShareResponse[0]));
        }
        return response;
    }
}
