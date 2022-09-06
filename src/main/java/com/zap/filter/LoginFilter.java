package com.zap.filter;

import com.zap.config.RedisService;
import com.zap.dao.AuthorityMapper;
import com.zap.dao.UserMapper;
import com.zap.entity.Authority;
import com.zap.entity.LoginUser;
import com.zap.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 16:16
 */

@Component
@Slf4j
public class LoginFilter extends OncePerRequestFilter {


    @Autowired
    RedisService redisService;

    @Autowired
    AuthorityMapper authorityMapper;

    private static String LOGIN_REDIS_KEY="login:";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取token信息
        String token = request.getHeader("token");
        //判断token信息是否存在
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }

        //解析token获取用户ID
        Integer perId = JwtUtils.getMemberIdByJwtToken(request);
        LoginUser loginUser = (LoginUser) redisService.get(LOGIN_REDIS_KEY+perId);

        //判断用户的登录状态
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }

        //将用户信息放入SecurityContextHolder中供过滤器链使用
        //TODO 用户权限信息查询封装到认证信息中
        Authority authority = authorityMapper.selectById(loginUser.getPerson().getId());
        List<SimpleGrantedAuthority> authorityList = Arrays.stream(authority.getAuthority().split(",")).distinct().map(item -> {
            return new SimpleGrantedAuthority(item);
        }).collect(Collectors.toList());
        log.info("权限集合为:{}",authorityList);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,authorityList);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request,response);

    }
}
