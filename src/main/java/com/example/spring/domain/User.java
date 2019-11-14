package com.example.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor @Builder
@Getter @Setter
public class User {

  public User() {}
  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 30)
  private String username;

  @Column(length = 100)
  private String password;
}
