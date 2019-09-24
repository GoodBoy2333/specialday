package com.fy.specialday.Config.Security;

import com.fy.specialday.Common.Status;
import com.fy.specialday.Config.Filter.JwtAuthenticationTokenFilter;
import com.fy.specialday.Entity.SysUser;
import com.fy.specialday.Service.SysUserService;
import com.fy.specialday.Util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 〈安全配置〉
 *
 * @author fangyan
 * @create 2019/8/20
 * @since 1.0.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    SysUserService sysUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated()
                .anyRequest()
                .access("@rbacAuthorityService.hasPermission(request,authentication)");
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling().accessDeniedHandler((httpServletRequest, httpServletResponse, e) ->
                ResponseUtil.renderJson(httpServletResponse, Status.ACCESS_DENIED)
        ).authenticationEntryPoint((httpServletRequest, httpServletResponse, e) ->
                ResponseUtil.renderJson(httpServletResponse, Status.ACCESS_JWT_DENIED));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();
        //阿里数据监控
        ignoring.antMatchers("/druid/**");
        // 允许对于网站静态资源的无授权访问
        ignoring.antMatchers(HttpMethod.GET, "/favicon.ico", "/**/*.css", "/**/*.js");

        //测试资源
        ignoring.antMatchers(HttpMethod.GET, "/**/resource/photo/**");

        //用户登录与注册
        ignoring.antMatchers(HttpMethod.POST,"/user/login", "/user");
        //跨域请求会先进行一次options请求
        ignoring.antMatchers(HttpMethod.OPTIONS);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            SysUser sysUser = sysUserService.findOneByUsernameOrEmailAndPassword(username, "", "");
            if (sysUser != null) {
                return sysUser;
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
