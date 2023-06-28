package com.example.puyuan.Friend;


import com.example.puyuan.Friend.request.FriendDeleteRequest;
import com.example.puyuan.Friend.request.FriendSendRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friend")
@RequiredArgsConstructor
public class FriendController {


    private final FriendService service;

    @Operation(summary = "查詢邀請碼")

    @GetMapping("/code")
    public ResponseEntity<?> code() {
        return ResponseEntity.ok(service.code());
    }

    @Operation(summary = "控糖團列表")
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(service.list());
    }

    @Operation(summary = "取得控糖邀請")
    @GetMapping("/requests")
    public ResponseEntity<?> request() {
        return ResponseEntity.ok(service.request());
    }

    @Operation(summary = "送出控糖邀請")
    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody FriendSendRequest request) {
        return ResponseEntity.ok(service.send(request));
    }

    @Operation(summary = "接受控糖團邀請")
    @GetMapping("/{friend_invite_id}/accept")
    public ResponseEntity<?> accept(@PathVariable("friend_invite_id") Long id) {
        return ResponseEntity.ok(service.accept(id));
    }

    @Operation(summary = "拒絕控糖團邀請")
    @GetMapping("/{friend_invite_id}/refuse")
    public ResponseEntity<?> refuse(@PathVariable("friend_invite_id") Long id) {
        return ResponseEntity.ok(service.refuse(id));
    }

    @Operation(summary = "刪除控糖團邀請")
    @GetMapping("/{friend_invite_id}/remove")
    public ResponseEntity<?> remove(@PathVariable("friend_invite_id") Long id) {
        return ResponseEntity.ok(service.removeByFriendId(id));
    }

    @Operation(summary = "刪除更多好友")
    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(@RequestBody FriendDeleteRequest ids) {
        return ResponseEntity.ok(service.remove(ids));
    }

    @Operation(summary = "控糖團結果")
    @GetMapping("/results")
    public ResponseEntity<?> results() {
        return ResponseEntity.ok(service.results());
    }
}
