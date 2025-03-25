package com.sprint.mission.sbblogsystem.controller;

import com.sprint.mission.sbblogsystem.dto.LoginRequest;
import com.sprint.mission.sbblogsystem.dto.UserRegisterRequest;
import com.sprint.mission.sbblogsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserRegisterRequest request){
        userService.register(request);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "회원가입이 완료되었습니다.");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        String token = userService.login(request);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
