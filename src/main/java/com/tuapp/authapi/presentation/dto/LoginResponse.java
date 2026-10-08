package com.tuapp.authapi.presentation.dto;

public record LoginResponse(String accessToken, String tokenType) {
}