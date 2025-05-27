package com.vinnet.repository;

import com.vinnet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    @Query("SELECT MAX(u.userId) FROM User u")
    Integer findMaxUserId();
} //
