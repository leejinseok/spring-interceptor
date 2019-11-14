package com.example.spring.interceptor;

import com.example.spring.config.JwtProperties;
import com.example.spring.exception.NoJwtException;
import com.example.spring.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter {

  private final JwtUtil jwtUtil;
  private final JwtProperties jwtProperties;

  public JwtInterceptor(JwtProperties jwtProperties, JwtUtil jwtUtil) {
    this.jwtProperties = jwtProperties;
    this.jwtUtil = jwtUtil;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    for (Cookie cookie : request.getCookies()) {
      String name = cookie.getName();
      String value = cookie.getValue();

      if (name .equals(jwtProperties.getName()) && !value.isEmpty()) {
        request.setAttribute("session", jwtUtil.decodeToken(value));
        return true;
      }
    }

    throw new NoJwtException("JWT not provided");
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    request.removeAttribute("session");
  }
}
