package com.example.puyuan.Care;

import com.example.puyuan.appuser.AppUser;
import com.example.puyuan.base.StatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CareService {
    private final CareRepository repository;

    /**
     * 獲取關懷訊息
     */
    public Map<String, Object> fetchCares() {
        var appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 從資料庫撈取資料
        var cares = repository.findAllByAppUser(appUser).orElseThrow();

        // 回傳結果
        var response = new LinkedHashMap<String, Object>();
        response.put("status", "0"); //成功狀態
        response.put("cares", cares);
        return response;
    }

    /**
     * 儲存關懷訊息
     */
    public StatusResponse saveCare(CareRequest request) {
        var data = CareEntity.builder()
            .message(request.getMessage())
            .member_id(1) //根據規格書範例設值的
            .reply_id(2)  //根據規格書範例設值的
            .build();
        repository.save(data);
        return StatusResponse.SUCCESS();
    }
}
