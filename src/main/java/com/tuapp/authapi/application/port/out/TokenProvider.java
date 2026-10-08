package com.tuapp.authapi.application.port.out;

import com.tuapp.authapi.domain.model.User;

public interface TokenProvider {
    String generate(User user);
}
