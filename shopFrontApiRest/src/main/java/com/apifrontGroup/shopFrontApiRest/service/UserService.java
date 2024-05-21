package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ChangedEntityStatusResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.UserResponse;
import com.apifrontGroup.shopFrontApiRest.entity.User;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.UserRequest;
import com.apifrontGroup.shopFrontApiRest.error.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAllUsers(Pageable firstPage);
    UserResponse updateUser(Long id,UserRequest user);
    ChangedEntityStatusResponse changeUserState(Long id)throws UserNotFoundException;
    User findUserById(Long id) throws UserNotFoundException;
    Page<User> findUsersByName(String searchTerm, Pageable pageable);
    Long getTotalUsersActive();
}
