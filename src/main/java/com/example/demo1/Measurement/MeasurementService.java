package com.example.demo1.Measurement;

import com.example.demo1.Measurement.BloodPressure.BloodPressureEntity;
import com.example.demo1.Measurement.BloodPressure.BloodPressureRepository;
import com.example.demo1.Measurement.BloodPressure.BloodPressureRequest;
import com.example.demo1.Measurement.BloodSugar.BloodSugarEntity;
import com.example.demo1.Measurement.BloodSugar.BloodSugarRepository;
import com.example.demo1.Measurement.BloodSugar.BloodSugarRequest;
import com.example.demo1.Measurement.Weight.WeightEntity;
import com.example.demo1.Measurement.Weight.WeightRepository;
import com.example.demo1.Measurement.Weight.WeightRequest;
import com.example.demo1.base.StatusResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeasurementService {

    private final BloodSugarRepository bloodSugarRepository;
    private final BloodPressureRepository bloodPressureRepository;
    private final WeightRepository weightRepository;

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
}
