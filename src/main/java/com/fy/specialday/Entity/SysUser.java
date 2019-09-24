package com.fy.specialday.Entity;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysUser implements UserDetails {
    /**
    * 主键
    */
    private BigDecimal id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码
    */
    @JsonIgnore()
    private String password;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 手机
    */
    private String phone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 生日
    */
    private Date birthday;

    /**
    * 性别，男-1，女-2
    */
    private String sex;

    /**
    * 状态，启用-1，禁用-0
    */
    private String status;

    /**
    * 创建时间
    */
    private Date createtime;

    /**
    * 更新时间
    */
    private Date updatetime;
    /**
    * 最后登录时间
    */
    private Date lastlogintime;

    private List<SysRole> roles;

    private List<SysPermission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = permissions.stream()
                .filter(sysPermission -> StrUtil.isNotBlank(sysPermission.getPermission()))
                .map(sysPermission -> new SimpleGrantedAuthority(sysPermission.getPermission()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
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
        return "1".equals(status);
    }
}