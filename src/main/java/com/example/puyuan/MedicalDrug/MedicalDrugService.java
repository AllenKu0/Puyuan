package com.example.puyuan.MedicalDrug;

import com.example.puyuan.MedicalDrug.DrugInformation.DrugInformationEntity;
import com.example.puyuan.MedicalDrug.DrugInformation.DrugInformationRepository;
import com.example.puyuan.MedicalDrug.DrugInformation.DrugUsedRequest;
import com.example.puyuan.MedicalDrug.MedicalInformation.MedicalInfoRequest;
import com.example.puyuan.MedicalDrug.MedicalInformation.MedicalInformationEntity;
import com.example.puyuan.MedicalDrug.MedicalInformation.MedicalInformationRepository;
import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.Base.StatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MedicalDrugService {

    private final DrugInformationRepository drugInfoRepository;
    private final MedicalInformationRepository medicalInfoRepository;

    /**
     * 上傳藥物資訊
     */
    public StatusResponse uploadDrugUsed(DrugUsedRequest request) {
        var data = DrugInformationEntity.builder()
            .drugname(request.getName())
            .type(request.getType())
            .recorded_at(request.getRecorded_at())
            .build();
        drugInfoRepository.save(data);
        return StatusResponse.SUCCESS();
    }

    /**
     * 獲取所有藥物資訊
     */
    public Map<String, Object> fetchAllDrugUsed(boolean type) {
        var appUser = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 從資料庫撈資料
        var drugUseds = drugInfoRepository.findAllByTypeAndUser(type, appUser);

        var response = new LinkedHashMap<String, Object>();
        response.put("status", "0"); //操作成功狀態
        response.put("drug_useds", drugUseds);
        return response;
    }

    /**
     * 刪除多筆藥物資訊
     */
    public StatusResponse deleteDrugUseds(List<Long> ids) {
        var appUser = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(drugInfoRepository.deleteByIdsAndUser(ids, appUser) > 0) {
            return StatusResponse.SUCCESS();
        }
        throw new RuntimeException("刪除多筆藥物資訊操作失敗");
    }

    /**
     * 部分設定(更新)就醫資訊
     */
    public StatusResponse updateMedicalInfo(
        MedicalInfoRequest request
    ) {
        var appUser = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var data = medicalInfoRepository.findByAppUser(appUser)
            .orElse(new MedicalInformationEntity()); //如果找不到使用者資料則創建新資料
        BeanUtils.copyProperties(request, data);
        medicalInfoRepository.save(data);
        return StatusResponse.SUCCESS();
    }

    /**
     * 獲取就醫資訊
     */
    public Map<String, Object> fetchMedicalInfo() {
        var appUser = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //從資料庫撈取資料
        var medical_info = medicalInfoRepository.findByAppUser(appUser)
            .orElse(new MedicalInformationEntity());

        //設定回傳結果
        var response = new LinkedHashMap<String, Object>();
        response.put("status", 0); //成功操作狀態
        response.put("medical_info", medical_info);
        return response;
    }
}
