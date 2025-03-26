package dev.jonkursani.restapigr2.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"), // get
    ADMIN_WRITE("admin:write"), // post, put, delete
    MANAGER_READ("manager:read"), // get
    MANAGER_WRITE("manager:write"), // post, put, delete

    EMPLOYEE_READ("employee:read"), // get
    EMPLOYEE_WRITE("employee:write"); // post, put, delete

    @Getter
    private final String permission;
}