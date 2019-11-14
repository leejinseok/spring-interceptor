package com.example.spring.util;


import com.example.spring.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieUtil {

  private final AppProperties appProperties;

  @Autowired
  public CookieUtil(AppProperties appProperties) {
    this.appProperties = appProperties;
  }

  public void add(HttpServletResponse resp, String name, String value, Boolean secure, Integer maxAge) {
    Cookie cookie = new Cookie(name, value);
    cookie.setSecure(secure);
    cookie.setHttpOnly(true);
    cookie.setMaxAge(maxAge);
    cookie.setDomain(appProperties.getDomain());
    cookie.setPath("/");
    resp.addCookie(cookie);
  }

  public void clear(HttpServletResponse resp, String name) {
    Cookie cookie = new Cookie(name, null);
    cookie.setMaxAge(0);
    cookie.setHttpOnly(true);
    cookie.setDomain(appProperties.getDomain());
    cookie.setPath("/");
    resp.addCookie(cookie);
  }
}

