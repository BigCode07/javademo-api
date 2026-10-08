package com.tuapp.authapi.infrastructure.config;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.tuapp.authapi.application.port.out.PasswordHasher;
import com.tuapp.authapi.infrastructure.persistence.SpringDataUserRepository;
import com.tuapp.authapi.infrastructure.persistence.UserEntity;

@Component
@Profile("dev")
public class DevUserSeeder implements CommandLineRunner {

    private static final String EMAIL = "admin@tuapp.com";
    private static final String PASSWORD = "Admin123!";

    private final SpringDataUserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public DevUserSeeder(SpringDataUserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail(EMAIL).isEmpty()) {
            userRepository.save(new UserEntity(
                    UUID.randomUUID(), EMAIL, passwordHasher.hash(PASSWORD), true));
        }
    }
}