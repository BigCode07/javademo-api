package com.tuapp.authapi.infrastructure.persistence;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity 
@Table  (name="users")
public class UserEntity {
    @Id 
    private UUID id;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (name = "password_hash", nullable = false)
    private String passwordHash;

    @Column (nullable = false)
    private boolean active;

    protected UserEntity(){

    }

    public UserEntity(UUID id, String email, String paswordHash, boolean active){
        this.id = id;
        this.email = email;
        this.passwordHash = paswordHash;
        this.active = active;
    }


    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public boolean isActive() { return active; }
}
