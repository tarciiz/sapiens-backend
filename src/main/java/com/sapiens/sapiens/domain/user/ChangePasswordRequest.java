package com.sapiens.sapiens.domain.user;

public record ChangePasswordRequest(String email, String password, String newPassword) {

}
