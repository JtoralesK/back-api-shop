package com.apifrontGroup.shopFrontApiRest.repository;

import com.apifrontGroup.shopFrontApiRest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.name LIKE %:searchTerm% OR u.lastName LIKE %:searchTerm%")
    Page<User> findUsersByName(@Param("searchTerm") String searchTerm, Pageable pageable);

    Optional <User> findUserByEmail(String Email);
    @Query("SELECT COUNT(u) FROM User u where active=true ")
    Long countUsers();
}
