package com.example.spring.interceptor;

import com.example.spring.config.JwtProperties;
import com.example.spring.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

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
      log.info(cookie.getName(), cookie.getValue());
      if (cookie.getName().equals(jwtProperties.getName())) {
        String value = cookie.getValue();
        request.setAttribute("session", jwtUtil.decodeToken(value));
      }
    }

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
  }
}
