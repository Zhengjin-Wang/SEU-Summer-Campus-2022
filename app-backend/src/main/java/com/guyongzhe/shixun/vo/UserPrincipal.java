package com.guyongzhe.shixun.vo;

import com.guyongzhe.shixun.pojo.InspectEmployee;
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
 * <p>
 * 自定义User
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 15:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    /**
     * 实体类
     */
    InspectEmployee user;

    /**
     * 用户职能
     */
    List<String> roles;

    /**
     * 用户权限列表
     */
    private Collection<? extends GrantedAuthority> authorities;


    public static UserPrincipal create(InspectEmployee user, List<String> roles, List<String> authorityList) {

        return new UserPrincipal(user, roles, authorityList.stream().
                map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getWorkNo();
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
