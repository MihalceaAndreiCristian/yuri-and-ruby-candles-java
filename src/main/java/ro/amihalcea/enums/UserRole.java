package ro.amihalcea.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {

    GUEST,
    ADMIN;


    public static SimpleGrantedAuthority getAuthorities(UserRole role){
        return new SimpleGrantedAuthority(role.name());
    }
}
