package com.sapiens.sapiens.domain.user;

public record RegisterRequest(String name, String email, String password, UserRole role) {

}
