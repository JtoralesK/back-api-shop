package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.controllers.models.response.AuthResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.AuthenticationRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.UserRequest;

public interface AuthService {
    AuthResponse register (UserRequest request) throws Exception;
    AuthResponse authenticate(AuthenticationRequest request) throws Exception;
}
