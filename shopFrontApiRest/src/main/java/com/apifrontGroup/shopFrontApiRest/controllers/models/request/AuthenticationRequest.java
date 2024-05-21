package com.apifrontGroup.shopFrontApiRest.controllers.models.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {
    @NotNull(message = "email Required")
    private String email;
    @NotNull(message = "password Required")
    private String password;
}
