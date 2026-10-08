package com.tuapp.authapi.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tuapp.authapi.application.port.out.UserRepository;
import com.tuapp.authapi.domain.model.User;

@Component 
public class JpaUserRepositoryAdapter implements  UserRepository{

    private final SpringDataUserRepository springDataRepository;

    public JpaUserRepositoryAdapter(SpringDataUserRepository springDataUserRepository){
        this.springDataRepository = springDataUserRepository;
    }

    @Override 
    public Optional<User> findByEmail(String email){
        return springDataRepository.findByEmail(email).map(this::toDomain);
    }

     private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.isActive());
    }
}
