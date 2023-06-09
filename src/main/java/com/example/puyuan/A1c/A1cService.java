package com.example.puyuan.A1c;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.AppUser.AppUserRepository;
import com.example.puyuan.Base.StatusResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class A1cService {
    private A1cRepository a1cRepository;
    private AppUserRepository userRepository;

    /**
     * 獲取使用者的糖化血色素資料
     */
    public Map<String, Object> fetch() {
        var userDetails = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userData = userRepository.findById(userDetails.getId()).orElseThrow();
        var result = new LinkedHashMap<String, Object>();
        result.put("status", StatusResponse.RC.SUCCESS.getCode());
        result.put("a1cs", userData.getA1cs());
        return result;
    }

    /**
     * 使用者上傳糖化血色素新資料到資料庫
     */
    public StatusResponse upload(A1cRequest request) {
        AppUserEntity appUser = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var a1cData = A1cEntity.builder()
            .appUser(appUser)
            .a1c(request.getA1c())
            .recorded_at(request.getRecorded_at())
            .build();
        a1cRepository.save(a1cData);
        return StatusResponse.SUCCESS();
    }

    /**
     * 刪除多筆資料
     */
    public StatusResponse deleteA1cs(List<Long> ids) {
        var appUser = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (a1cRepository.deleteByIdsAndAppUser(ids, appUser) > 0) {
            return StatusResponse.SUCCESS();
        }
        throw new RuntimeException("[糖化血色素]刪除多筆資料操作失敗");
    }

    /**
     * 刪除單筆資料
     */
    public StatusResponse delete(Long id) {
        var appUser = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(a1cRepository.deleteByIdAndAppUser(id, appUser) > 0) {
            return StatusResponse.SUCCESS();
        }
        throw new RuntimeException("[糖化血色素]刪除多筆資料操作失敗");
    }
}
