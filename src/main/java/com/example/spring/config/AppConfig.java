package com.example.spring.config;

import com.example.spring.domain.User;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Profile("dev")
  public ApplicationRunner applicationRunner() {
    return new ApplicationRunner() {

      @Autowired
      UserRepository userRepository;

      @Autowired
      BCryptPasswordEncoder bCryptPasswordEncoder;

      @Override
      public void run(ApplicationArguments args) {

        User user = User.builder()
          .username("sonaky47@naver.com")
          .password(bCryptPasswordEncoder.encode("1111"))
          .build();

        userRepository.save(user);
      }
    };
  }
}
