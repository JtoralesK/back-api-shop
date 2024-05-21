package com.apifrontGroup.shopFrontApiRest.controllers.models.request;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String name;
    @NotNull(message = "lastName Required")
    private String lastName;
    @NotNull(message = "email Required")
    private String email;
    @NotNull(message = "profileImgUrl Required")
    private String profileImgUrl;
    @NotNull(message = "phoneNumber Required")
    private String phoneNumber;
    @NotNull(message = "country Required")
    private String country;
    @NotNull(message = "postalCode Required")
    private String postalCode;
    @NotNull(message = "city Required")
    private String city;
    @NotNull(message = "password Required")
    private String password;
    @NotNull(message = "gender_Id Required")
    private Long gender_Id;
    @NotNull(message = "role_Id Required")
    private Long role_Id;
}
