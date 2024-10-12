package com.sapiens.sapiens.domain.user;

public enum UserRole {
    ADMIN("ADMIN"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT"),
    CORDINATOR("CORDINATOR"),
    GUARDIAN("GUARDIAN"),
    SUPERADMIN("SUPERADMIN");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
