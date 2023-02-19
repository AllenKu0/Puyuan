package com.example.demo1.a1c;

import com.example.demo1.appuser.AppUser;
import com.example.demo1.appuser.AppUserRepository;
import com.example.demo1.base.StatusResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo1.my_utils.RequestConverter.recordedAtStrToTimestamp;

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
        var userDetails = (AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        AppUser appUser = (AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var a1cData = A1cEntity.builder()
            .appUser(appUser)
            .a1c(request.getA1c())
            .recorded_at(recordedAtStrToTimestamp(request.getRecorded_at()))
            .build();
        a1cRepository.save(a1cData);
        return StatusResponse.SUCCESS();
    }

    /**
     * 刪除多筆資料
     */
    public StatusResponse deleteA1cs(List<Long> ids) {
        var appUser = (AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        a1cRepository.deleteByIdsAndAppUser(ids, appUser);
        return StatusResponse.SUCCESS();
    }

    /**
     * 刪除單筆資料
     */
    public StatusResponse delete(Long id) {
        var appUser = (AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        a1cRepository.deleteByIdAndAppUser(id, appUser);
        return StatusResponse.SUCCESS();
    }
}
