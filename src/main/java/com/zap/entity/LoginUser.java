package com.zap.entity;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 14:33
 */

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private Person person;

    private List<String> permissions;

    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    public LoginUser(Person person, List<String> list) {
        this.person = person;
        this.permissions=list;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //将用户权限信息封装
        if(authorities!=null){
            return authorities;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
