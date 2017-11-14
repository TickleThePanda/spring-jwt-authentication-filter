package uk.co.ticklethepanda.spring.auth.jwt;

import java.util.List;

public class JwtPayload {
    private List<String> roles;

    private JwtPayload(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
