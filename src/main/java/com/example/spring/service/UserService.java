package com.example.spring.service;

import com.example.spring.domain.User;
import com.example.spring.exception.DataNotFoundException;
import com.example.spring.repository.UserRepository;
import com.example.spring.util.CookieUtil;
import com.example.spring.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;


  @Autowired
  public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, JwtUtil jwtUtil) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userRepository = userRepository;
    this.jwtUtil = jwtUtil;
  }

  public User register(String username, String password) {
    String encoded = bCryptPasswordEncoder.encode(password);
    return userRepository.save(new User(username, encoded));
  }

  public String login(String username, String password) throws BadCredentialsException {
    User user = userRepository.findByUsername(username).orElseThrow(() -> new BadCredentialsException(username + " is not registered"));
    if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Password not matched");
    }

    return jwtUtil.generateToken(user);
  }
}
