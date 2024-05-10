package com.project.bookbell.dto.security;

import com.project.bookbell.dto.UserAccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record BoardPrincipal(

        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String phone_number,
        String address,
        String email,

        String nickname,

        Map<String,Object> oAuth2Attributes


) implements UserDetails, OAuth2User {



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2Attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() { return true;}
    @Override
    public boolean isEnabled() {return true;}

    @Override
    public String getName() {
        return username;
    }



    public enum RoleType {
        USER("ROLE_USER");

        private final String name;

        RoleType(String name){
            this.name = name;
        }
    }
}
