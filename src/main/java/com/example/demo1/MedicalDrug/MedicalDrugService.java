package com.example.demo1.MedicalDrug;

import com.example.demo1.MedicalDrug.DrugInformation.DrugInformationEntity;
import com.example.demo1.MedicalDrug.DrugInformation.DrugInformationRepository;
import com.example.demo1.MedicalDrug.DrugInformation.DrugUsedRequest;
import com.example.demo1.appuser.AppUser;
import com.example.demo1.base.StatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo1.my_utils.RequestConverter.recordedAtStrToTimestamp;

@Service
@RequiredArgsConstructor
public class MedicalDrugService {

    private final DrugInformationRepository drugRepository;

    /**
     * 上傳藥物資訊
     */
    public StatusResponse uploadDrugUsed(DrugUsedRequest request) {
        var data = DrugInformationEntity.builder()
            .drugname(request.getName())
            .type(request.getType())
            .recorded_at(recordedAtStrToTimestamp(request.getRecorded_at()))
            .build();
        drugRepository.save(data);
        return StatusResponse.SUCCESS();
    }

    /**
     * 獲取所有藥物資訊
     */
    public Map<String, Object> fetchAllDrugUsed(boolean type) {
        var appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 從資料庫撈資料
        var drugUseds = drugRepository.findAllByTypeAndUser(type, appUser);

        var response = new LinkedHashMap<String, Object>();
        response.put("status", "0"); //操作成功狀態
        response.put("drug_useds", drugUseds);
        return response;
    }

    /**
     * 刪除多筆藥物資訊
     */
    public StatusResponse deleteDrugUseds(List<Long> ids) {
        var appUser = (AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        drugRepository.deleteByIdsAndUser(ids, appUser);
        return StatusResponse.SUCCESS();
    }
}
