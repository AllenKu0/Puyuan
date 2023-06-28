package com.example.puyuan.Measurement;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.Diary.DiaryRepository;
import com.example.puyuan.Measurement.BloodPressure.BloodPressureEntity;
import com.example.puyuan.Measurement.BloodPressure.BloodPressureRepository;
import com.example.puyuan.Measurement.BloodPressure.BloodPressureRequest;
import com.example.puyuan.Measurement.BloodSugar.BloodSugarEntity;
import com.example.puyuan.Measurement.BloodSugar.BloodSugarRepository;
import com.example.puyuan.Measurement.BloodSugar.BloodSugarRequest;
import com.example.puyuan.Measurement.Weight.WeightEntity;
import com.example.puyuan.Measurement.Weight.WeightRepository;
import com.example.puyuan.Measurement.Weight.WeightRequest;
import com.example.puyuan.Base.StatusResponse;
import com.example.puyuan.Measurement.request.TimeRequest;
import com.example.puyuan.Measurement.response.LastResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeasurementService {

    private final BloodSugarRepository bloodSugarRepository;
    private final BloodPressureRepository bloodPressureRepository;
    private final WeightRepository weightRepository;
    private final DiaryRepository diaryRepository;

    /**
     * 上傳血糖測量結果
     */
    public StatusResponse uploadBloodSugar(BloodSugarRequest request) {
        var data = BloodSugarEntity.builder()
            .sugar(request.getSugar())
            .timeperiod(request.getTimeperiod())
            .recorded_at(request.getRecorded_at())
            .build();
        bloodSugarRepository.save(data);
        return StatusResponse.SUCCESS();
    }

    /**
     * 上傳血壓測量結果
     */
    public StatusResponse uploadBloodPressure(BloodPressureRequest request) {
        var data = BloodPressureEntity.builder()
            .systolic(request.getSystolic())
            .diastolic(request.getDiastolic())
            .pulse(request.getPulse())
            .recorded_at(request.getRecorded_at())
            .build();
        bloodPressureRepository.save(data);
        return StatusResponse.SUCCESS();
    }

    /**
     * 上傳體重測量結果
     */
    public StatusResponse uploadWeight(WeightRequest request) {
        var data = WeightEntity.builder()
            .weight(request.getWeight())
            .body_fat(request.getBody_fat())
            .bmi(request.getBmi())
            .recorded_at(request.getRecorded_at())
            .build();
        weightRepository.save(data);
        return StatusResponse.SUCCESS();
    }

    public Map<String, Object> lastUpload() {
        var response = new LinkedHashMap<String,Object>();

        var appUser = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var bloodPressureLast = bloodPressureRepository.findLatestByAppUserOrderByRecorded(appUser);
        var bloodSugarLast = bloodSugarRepository.findLatestByAppUserOrderByRecorded(appUser);
        var weightLast = weightRepository.findLatestWeightEntityByAppUser(appUser);
        var dietLast = diaryRepository.findLatestByAppUserOrderByRecorded(appUser);

        var lastResponse = LastResponse.builder()
                .blood_pressure(bloodPressureLast != null ? bloodPressureLast.getRecorded_at() : null)
                .blood_sugar(bloodSugarLast != null ? bloodSugarLast.getRecorded_at() : null)
                .weight(weightLast != null ? weightLast.getRecorded_at() : null)
                .diet(dietLast != null ? dietLast.getRecorded_at() : null)
                .build();

        response.put("status","0");
        response.put("last_upload",lastResponse);

        return response;
    }

    public Map<String,Object> records(TimeRequest request) {

        var response = new LinkedHashMap<String,Object>();

        var appUser = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var pressureData = bloodPressureRepository.findLatestByAppUserOrderByRecorded(appUser);
        var sugarData = bloodSugarRepository.findLatestByAppUserOrderByRecorded(appUser);
        var weightData = weightRepository.findLatestWeightEntityByAppUser(appUser);

        var pressureResponse = new LinkedHashMap<String,Object>();
        var sugarResponse = new LinkedHashMap<String,Object>();
        var weightsResponse = new LinkedHashMap<String,Object>();

        pressureResponse.put("systolic",pressureData.getSystolic());
        pressureResponse.put("diastolic",pressureData.getDiastolic());
        pressureResponse.put("pulse",pressureData.getPulse());
        sugarResponse.put("sugar",sugarData.getSugar());
        weightsResponse.put("weight",weightData.getWeight());

        response.put("status","0");
        response.put("message","ok");
        response.put("blood_sugars",sugarResponse);
        response.put("blood_pressures",pressureResponse);
        response.put("weights",weightsResponse);

        return response;
    }
}
