package com.example.puyuan.Diary;

import com.example.puyuan.Diary.request.DeleteDiaryRequest;
import com.example.puyuan.Diary.request.DietRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Slf4j
public class DiaryController {

    private DiaryService service;

    @GetMapping("/diary")
    public ResponseEntity<?> diary(@RequestParam String date) {
        return ResponseEntity.ok(service.getDiary(date));
    }
    @PostMapping("/diet")
    public ResponseEntity<?> diet(@RequestBody DietRequest request) {
        return ResponseEntity.ok(service.newDiet(request));
    }

    @DeleteMapping("/records")
    public ResponseEntity<?> records(@RequestBody DeleteDiaryRequest request) {
        return ResponseEntity.ok(service.records(request));
    }
}
