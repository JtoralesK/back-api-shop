package com.apifrontGroup.shopFrontApiRest.controllers;

import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ChangedEntityStatusResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.UserResponse;
import com.apifrontGroup.shopFrontApiRest.entity.User;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.UserRequest;
import com.apifrontGroup.shopFrontApiRest.error.UserNotFoundException;
import com.apifrontGroup.shopFrontApiRest.service.UserService;
import com.apifrontGroup.shopFrontApiRest.utils.PaginationUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("users/all")
    Map<String,java.lang.Object>findAllUsers
            (
             @RequestParam(defaultValue = "0") int offset,
             @RequestParam(defaultValue = "5") int limit
            ){
        Pageable pageable = PageRequest.of(offset, limit);
        Page<User> userPage = userService.findAllUsers(pageable);
        return PaginationUtil.createPageResponse(userPage);
    }
    @GetMapping("users/all/filter")
    Map<String,java.lang.Object>findAllUsersByName
            (
                    @RequestParam(defaultValue = "0") int offset,
                    @RequestParam(defaultValue = "5") int limit,
                    @RequestParam String searchTerm
            ){
        Pageable pageable = PageRequest.of(offset, limit);
        Page<User> userPage = userService.findUsersByName(searchTerm,pageable);
        return PaginationUtil.createPageResponse(userPage);
    }
    @GetMapping("users/{id}")
     User findUserById(@PathVariable long id) throws UserNotFoundException {
        return userService.findUserById(id);
    }


    @PutMapping("users/{id}")
     UserResponse updateUser(@PathVariable long id, @RequestBody UserRequest user){
        return userService.updateUser(id,user);
    }
    @DeleteMapping("users/{id}")
    ChangedEntityStatusResponse deleteUser(@PathVariable long id) throws UserNotFoundException{
        return userService.changeUserState(id);
    }
    @GetMapping("users/count")
    Long getUsersCount()  {
        return userService.getTotalUsersActive();
    }

}
