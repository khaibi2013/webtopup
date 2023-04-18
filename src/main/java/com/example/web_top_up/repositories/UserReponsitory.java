package com.example.web_top_up.repositories;

import com.example.web_top_up.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserReponsitory extends JpaRepository<UserEntity,Long> {


    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity findByEmail(String email);

    @Query(value = "select u.id from users u where u.email = :email", nativeQuery = true)
    Long findIdByEmail(String email);

}
