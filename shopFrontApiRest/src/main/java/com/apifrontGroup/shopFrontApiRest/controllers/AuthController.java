package com.apifrontGroup.shopFrontApiRest.controllers;

import com.apifrontGroup.shopFrontApiRest.controllers.models.response.AuthResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.AuthenticationRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.UserRequest;
import com.apifrontGroup.shopFrontApiRest.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest request)throws Exception{
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthenticationRequest request)throws Exception{
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
