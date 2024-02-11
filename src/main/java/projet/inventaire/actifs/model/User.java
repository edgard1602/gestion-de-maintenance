package projet.inventaire.actifs.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    // Getters and setters for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters and setters for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getters and setters for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters and setters for enabled


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // Getters and setters for authorities
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    // Implementation of UserDetails methods...
    @Override
    public boolean isAccountNonExpired() {
        return true; // Mettez votre logique d'expiration de compte ici
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Mettez votre logique de compte verrouillé ici
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Mettez votre logique d'expiration des informations d'identification ici
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

