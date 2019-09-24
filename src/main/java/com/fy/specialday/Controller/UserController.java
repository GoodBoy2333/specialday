package com.fy.specialday.Controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import com.fy.specialday.ApiCommon.ApiResponse;
import com.fy.specialday.Entity.SysUser;
import com.fy.specialday.Service.SysUserService;
import com.fy.specialday.Util.Jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈用户访问控制类〉
 *
 * @author fangyan
 * @create 2019/8/21
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    Snowflake snowflake;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    String tokenHead;

    @PostMapping("/login")
    public ApiResponse login(@RequestParam String username, @RequestParam String password) {
        String token;
        SysUser userDetails = new SysUser();
        //密码需要客户端加密后传递
        try {
//            也可以使用passwordEncoder.matches匹配密码
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            BeanUtil.copyProperties(authentication.getPrincipal(), userDetails);
            token = jwtTokenUtil.generateToken(userDetails);
//            更新登录时间
            sysUserService.updateByPrimaryKeySelective(new SysUser().setId(userDetails.getId()).setLastlogintime(new Date()));
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
            throw e;
        }
        if (token == null) {
            throw new BadCredentialsException("用户名或密码不正确");
        }
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("user", userDetails);
        return ApiResponse.success(tokenMap);
    }

    @PostMapping
    public ApiResponse register(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getUsername())) {
            return ApiResponse.fail("用户名为空");
        }
        if (StringUtils.isEmpty(sysUser.getPassword())) {
            return ApiResponse.fail("密码为空");
        }
        if (StringUtils.isEmpty(sysUser.getEmail())) {
            return ApiResponse.fail("邮箱为空");
        }
        List<SysUser> sysUserByDataBase = sysUserService.findByUsernameOrEmail(sysUser.getUsername(), sysUser.getEmail());
        if (sysUserByDataBase.size() != 0) {
            return ApiResponse.fail("该用户名或邮箱已注册");
        }
        sysUser.setId(BigDecimal.valueOf(snowflake.nextId()));
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encodePassword);
        sysUser.setStatus("1");
        sysUser.setCreatetime(new Date());
        sysUserService.insert(sysUser);
        return ApiResponse.success("注册成功");
    }

    @GetMapping("/check/{usernameoremail}")
    public ApiResponse repeat(@PathVariable("usernameoremail") String usernameoremail) {
        List<SysUser> sysUserByDataBase = sysUserService.findByUsernameOrEmail(usernameoremail, usernameoremail);
        if (sysUserByDataBase != null) {
            return ApiResponse.fail("已注册");
        }
        return ApiResponse.success("未注册");
    }
}
