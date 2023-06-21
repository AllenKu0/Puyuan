package com.example.puyuan.Diary;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.Base.StatusResponse;
import com.example.puyuan.Diary.request.DeleteDiaryRequest;
import com.example.puyuan.Diary.response.DiaryResponse;
import com.example.puyuan.Diary.request.DietRequest;
import com.example.puyuan.Diary.response.Location;
import com.example.puyuan.Measurement.BloodPressure.BloodPressureEntity;
import com.example.puyuan.Measurement.BloodPressure.BloodPressureRepository;
import com.example.puyuan.Measurement.BloodPressure.BloodPressureRequest;
import com.example.puyuan.Measurement.BloodSugar.BloodSugarEntity;
import com.example.puyuan.Measurement.BloodSugar.BloodSugarRepository;
import com.example.puyuan.Measurement.BloodSugar.BloodSugarRequest;
import com.example.puyuan.Measurement.Weight.WeightEntity;
import com.example.puyuan.Measurement.Weight.WeightRepository;
import com.example.puyuan.Measurement.Weight.WeightRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryService {
    private DiaryRepository diaryRepository;
    private BloodPressureRepository bloodPressureRepository;
    private BloodSugarRepository bloodSugarRepository;
    private WeightRepository weightRepository;

    public Map<String,Object> newDiet(DietRequest request) {
        var data = DiaryDietEntity.builder()
                .description(request.getDescription())
                .meal(request.getMeal())
                .tag(request.getTag())
                .image(request.getImage())
                .lat(String.valueOf(request.getLat()))
                .ing(String.valueOf(request.getIng()))
                .recorded_at(request.getRecorded_at())
                .build();

        diaryRepository.save(data);
        var response = new LinkedHashMap<String, Object>();
        response.put("status", 0); //成功操作狀態
        response.put("message", "");
        response.put("image_url","");
        return response;
    }

    public Map<String, Object> getDiary(String date){

        var response = new LinkedHashMap<String,Object>();
        var diary = new ArrayList<>();
        var appUser = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LocalDateTime startTime = LocalDateTime.parse(date + "00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endTime = LocalDateTime.parse(date + "23:59:59",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<BloodPressureEntity> bloodPressureEntities = bloodPressureRepository.findLatestByRecordedAtBetweenAndAppUser(startTime,endTime,appUser);
        List<BloodSugarEntity> bloodSugarEntities = bloodSugarRepository.findLatestByRecordedAtBetweenAndAppUser(startTime,endTime,appUser);
        List<WeightEntity> weightEntities = weightRepository.findLatestByRecordedAtBetweenAndAppUser(startTime,endTime,appUser);
        List<DiaryDietEntity> diaryDietEntities = diaryRepository.findLatestByRecordedAtBetweenAndAppUser(startTime,endTime,appUser);

        if(!bloodPressureEntities.isEmpty()){
            var bloodPressure_response = DiaryResponse.builder()
                    .id(appUser.getId())
                    .user_id(appUser.getId().intValue())
                    .pulse(bloodPressureEntities.get(0).getPulse())
                    .diastolic(bloodPressureEntities.get(0).getDiastolic())
                    .systolic(bloodPressureEntities.get(0).getSystolic())
                    .recorded_at(bloodPressureEntities.get(0).getRecorded_at())
                    .type("blood_pressure")
                    .build();
            diary.add(bloodPressure_response);
        }
        if(!bloodSugarEntities.isEmpty()) {
            var bloodSugarRequest = DiaryResponse.builder()
                    .id(appUser.getId())
                    .user_id(appUser.getId().intValue())
                    .sugar(bloodSugarEntities.get(0).getSugar())
                    .timeperiod(bloodSugarEntities.get(0).getTimeperiod())
                    .type("blood_sugar")
                    .build();

            diary.add(bloodSugarRequest);
        }

        if(!weightEntities.isEmpty()) {
            var weightRequest = DiaryResponse.builder()
                    .id(appUser.getId())
                    .user_id(appUser.getId().intValue())
                    .weight(weightEntities.get(0).getWeight())
                    .body_fat(weightEntities.get(0).getBody_fat())
                    .bmi(weightEntities.get(0).getBmi())
                    .type("weight")
                    .build();
            diary.add(weightRequest);
        }
        if(!diaryDietEntities.isEmpty()) {
            var dietRequest = DiaryResponse.builder()
                    .description(diaryDietEntities.get(0).getDescription())
                    .image(Collections.singletonList(diaryDietEntities.get(0).getImage().toString()))
                    .location(new Location(diaryDietEntities.get(0).getLat(),diaryDietEntities.get(0).getIng()))
                    .meal(diaryDietEntities.get(0).getMeal())
//                    .tag(diaryDietEntities.get(0).getTag())
                    .type("diary_diet")
                    .build();
            diary.add(dietRequest);
        }
        response.put("status", StatusResponse.RC.SUCCESS.getCode());
        response.put("message","ok");
        response.put("diary",diary);
        return response;
    }

    public StatusResponse records(DeleteDiaryRequest request) {
        var appUser = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (request.getDeleteObjects() != null) {
            for (DeleteDiaryRequest.DeleteObject deleteObject: request.getDeleteObjects()) {
                //blood pressure id check
                if (deleteObject.getBloodPressures() != null) {
                    for (Long id : deleteObject.getBloodSugars()) {
                        if (!bloodPressureRepository.existsByIdAndAppUser(id, appUser)) {
                            throw new RuntimeException("Blood_pressures指定的 id 不存在");
                        }
                    }
                }
                //sugar id check
                if (deleteObject.getBloodSugars() != null) {
                    for (Long id : deleteObject.getBloodSugars()) {
                        if (!bloodSugarRepository.existsByIdAndAppUser(id, appUser)) {
                            throw new RuntimeException("Blood_sugars指定的 id 不存在");
                        }
                    }
                }

                //weight id check
                if (deleteObject.getWeights() != null) {
                    for (Long id : deleteObject.getWeights()) {
                        if(!weightRepository.existsByIdAndAppUser(id,appUser)) {
                            throw new RuntimeException("Weights 指定的 id 不存在");
                        }
                    }
                }

                if (deleteObject.getDiets() != null) {
                    for (Long id : deleteObject.getDiets()) {
                        if(!diaryRepository.existsByIdAndAppUser(id,appUser)) {
                            throw new RuntimeException("Diets 指定的 id 不存在");
                        }
                    }
                }

                bloodPressureRepository.deleteByIdAndAppUser(deleteObject.getBloodPressures(),appUser);
                bloodSugarRepository.deleteByIdAndAppUser(deleteObject.getBloodSugars(),appUser);
                weightRepository.deleteByIdAndAppUser(deleteObject.getWeights(),appUser);
                diaryRepository.deleteByIdAndAppUser(deleteObject.getDiets(),appUser);
            }
        }
        return StatusResponse.SUCCESS();
    }

}
