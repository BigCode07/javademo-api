package com.tuapp.authapi.application.port.out;

import java.util.Optional;

import com.tuapp.authapi.domain.model.User;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
