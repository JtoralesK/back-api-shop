package com.apifrontGroup.shopFrontApiRest.controllers.models.response;

import com.apifrontGroup.shopFrontApiRest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private User user;
    private ErrorResponse errorResponse;
}
