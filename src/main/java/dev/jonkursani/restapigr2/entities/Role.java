package dev.jonkursani.restapigr2.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static dev.jonkursani.restapigr2.entities.Permission.*;

@RequiredArgsConstructor
public enum Role {
    // ADMIN can do everything
    // Cili rol cfar permission ka e percaktoni ketu
    ADMIN(Set.of(ADMIN_READ, ADMIN_WRITE, MANAGER_READ, MANAGER_WRITE)),
    MANAGER(Set.of(MANAGER_READ, MANAGER_WRITE)),
    USER(Collections.emptySet()),
    EMPLOYEE(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = new java.util.ArrayList<>(getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList());

        // Spring security kerkon qe rolet te fillojne me ROLE_
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}