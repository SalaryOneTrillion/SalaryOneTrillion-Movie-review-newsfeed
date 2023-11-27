package com.sparta.salaryonetrillionmoviereviewnewsfeed.security;


import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.entity.User;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.entity.UserRoleEnum;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        UserRoleEnum role = user.getRole();
        String authority = role.getAuthority();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    //계정이 만료되었는지(true : 만료 x)
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    //계정이 잠겼는지
    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    //비밀번호가 만료되었는지
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    // 계정이 사용가능한지
    @Override
    public boolean isEnabled() {

        return true;
    }

}
