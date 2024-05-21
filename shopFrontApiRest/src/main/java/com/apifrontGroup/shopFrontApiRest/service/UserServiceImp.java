package com.apifrontGroup.shopFrontApiRest.service;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ChangedEntityStatusResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.UserRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ErrorResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.apifrontGroup.shopFrontApiRest.entity.*;
import com.apifrontGroup.shopFrontApiRest.error.UserNotFoundException;
import com.apifrontGroup.shopFrontApiRest.repository.GenderRepository;
import com.apifrontGroup.shopFrontApiRest.repository.RoleRepository;
import com.apifrontGroup.shopFrontApiRest.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Page<User> findAllUsers(Pageable firstPage) {
        return userRepository.findAll(firstPage);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest user) {
        UserResponse userResponse = new UserResponse();
        try{
            User userFound =findUserById(id);
            setRoleAndGenre(user,userFound);
            updateUserFiles(user,userFound);
            userResponse.setUser(userRepository.save(userFound));

        }catch (Exception err){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError(true);
            errorResponse.setMessageError(err.getMessage());
            userResponse.setErrorResponse(errorResponse);
        }
        return userResponse;
    }
    public void updateUserFiles(UserRequest user, User userFound){
        if(Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())){
            userFound.setName(user.getName());
        }
        if(Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())){
            userFound.setLastName(user.getLastName());
        }
        if(Objects.nonNull(user.getPhoneNumber()) && !"".equalsIgnoreCase(user.getPhoneNumber())) {
            userFound.setPhoneNumber(user.getPhoneNumber());
        }
        if(Objects.nonNull(user.getCountry()) && !"".equalsIgnoreCase(user.getCountry())){
            userFound.setCountry(user.getCountry());
        }
        if(Objects.nonNull(user.getPostalCode()) && !"".equalsIgnoreCase(user.getPostalCode())){
            userFound.setPostalCode(user.getPostalCode());
        }
        if(Objects.nonNull(user.getCity()) && !"".equalsIgnoreCase(user.getCity())){
            userFound.setCity(user.getCity());
        }
        if(Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())){
            userFound.setPassword(user.getPassword());
        }
    }

    public void setRoleAndGenre (UserRequest user, User newUser)throws Exception{
        if(Objects.nonNull(user.getGender_Id())){
            Gender gender = genderRepository.findById(user.getGender_Id())
                    .orElseThrow(() -> new EntityNotFoundException("Gender with ID " + user.getGender_Id() + " not found"));
            newUser.setGender(gender);
        }
       if(Objects.nonNull(user.getRole_Id())){
           Role role = roleRepository.findById(user.getRole_Id())
                   .orElseThrow(() -> new EntityNotFoundException("Role with ID " + user.getRole_Id() + " not found"));
           newUser.setRole(role);
       }

    }
    @Override
    public ChangedEntityStatusResponse changeUserState(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("Error, User not found");
        }
        user.get().setActive(!user.get().isActive());
        userRepository.save(user.get());
        ChangedEntityStatusResponse response = new ChangedEntityStatusResponse();
        response.setId(user.get().getId());
        response.setMessage("Este usuario ha cambiado su estado correctamente");
        return response;
    }

    @Override
    public User findUserById(Long id)throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("Error, User not found");
        }
        return user.get();
    }

    @Override
    public Page<User> findUsersByName(String searchTerm, Pageable pageable) {
        return userRepository.findUsersByName(searchTerm,pageable);
    }

    @Override
    public Long getTotalUsersActive() {
        return userRepository.countUsers();
    }


}
