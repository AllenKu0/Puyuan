package com.example.puyuan.Notify;

import com.example.puyuan.Notify.notification.request.NotificationRequest;
import com.example.puyuan.Notify.share.request.ShareRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotifyController {

    private NotifyService service;


    @PostMapping("/notification")
    public ResponseEntity<?> notification(@RequestBody NotificationRequest message) {
        return ResponseEntity.ok(service.notification(message));
    }

    @GetMapping("/news")
    @Operation(summary = "最新消息")
    public ResponseEntity<?> news(){
        return ResponseEntity.ok(service.news());
    }

    @PostMapping("/share")
    @Operation(summary = "分享")
    public ResponseEntity<?> share(@RequestBody ShareRequest request){
        return ResponseEntity.ok(service.share(request));
    }

    @PostMapping("/share/{type}")
    @Operation(summary = "查看分享")
    public ResponseEntity<?> share(@PathVariable Integer type){
        return ResponseEntity.ok(service.type(type));
    }
}
