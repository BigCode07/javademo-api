package com.tuapp.authapi.domain.model;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String email;
    private final String passwordHash;
    private final boolean active;


public User(UUID id , String email, String passwordHash, boolean active){
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("El email es obligatorio");
        }
        if (passwordHash == null || passwordHash.isBlank()){
            throw new IllegalArgumentException("El hash de contraseña es obligatorio");
        }

        this.id = id;
        this.email = email.trim().toLowerCase();
        this.passwordHash = passwordHash;
        this.active = active;}

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public boolean isActive() { return active; }

    
}


