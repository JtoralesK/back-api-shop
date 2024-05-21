package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.config.JwtService;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.AuthResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.AuthenticationRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.UserRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ErrorResponse;
import com.apifrontGroup.shopFrontApiRest.entity.Gender;
import com.apifrontGroup.shopFrontApiRest.entity.Role;
import com.apifrontGroup.shopFrontApiRest.entity.User;
import com.apifrontGroup.shopFrontApiRest.repository.GenderRepository;
import com.apifrontGroup.shopFrontApiRest.repository.RoleRepository;
import com.apifrontGroup.shopFrontApiRest.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public AuthResponse register(UserRequest user) throws Exception {
        AuthResponse authResponse = new AuthResponse();
        if(userRepository.findUserByEmail(user.getEmail()).isPresent()){
            authResponse.setErrorResponse(ErrorResponse.builder().error(true).messageError("There is already an user with this email "+user.getEmail()).build());
            return authResponse;
        }
        try{

            User newUser = User.builder()
                    .city(user.getCity())
                    .name(user.getName())
                    .email(user.getEmail())
                    .country(user.getCountry())
                    .postalCode(user.getPostalCode())
                    .lastName(user.getLastName())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .phoneNumber(user.getPhoneNumber())
                    .profileImgUrl(user.getProfileImgUrl())
                    .build();
            setRoleAndGenre(user,newUser);
            newUser.setActive(true);
            User userCreated = userRepository.save(newUser);
            var jwtToken = jwtService.generateToken(newUser);
            authResponse.setToken(jwtToken);
            authResponse.setUser(userCreated);
        }catch (Exception err){
            authResponse.setErrorResponse(ErrorResponse.builder().error(true).messageError(err.getMessage()).build());
        }

        return authResponse;
    }
    public void setRoleAndGenre (UserRequest user, User newUser)throws Exception{
        Gender gender = genderRepository.findById(user.getGender_Id())
                .orElseThrow(() -> new EntityNotFoundException("Gender with ID " + user.getGender_Id() + " not found"));

        Role role = roleRepository.findById(user.getRole_Id())
                .orElseThrow(() -> new EntityNotFoundException("Role with ID " + user.getRole_Id() + " not foundd"));
        newUser.setRole(role);
        newUser.setGender(gender);
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request)throws Exception {
        AuthResponse authResponse = new AuthResponse();
        try{
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
            if(auth.isAuthenticated()){
                var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
                var  jwtToken = jwtService.generateToken(user);
                authResponse.setUser(user);
                authResponse.setToken(jwtToken);
            }else{
                authResponse.setErrorResponse(ErrorResponse.builder().error(true).messageError("Email o contraseña incorrecta").build());
            }

        }catch(AuthenticationException err){
            authResponse.setErrorResponse(ErrorResponse.builder().
                    error(true).
                    messageError("Email o contraseña incorrecta")
                    .build());
        }


        return authResponse;
    }


}
