package com.roomflow.domain.user.repository;

import com.roomflow.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();


    Optional<User> findByLoginId(String loginId);
}
