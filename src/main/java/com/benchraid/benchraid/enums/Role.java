package com.benchraid.benchraid.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    EMPLOYEE, EMPLOYER;

    @Override
    public String getAuthority() {
        return name();
    }
}
