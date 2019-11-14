package com.example.spring.util;

import com.example.spring.config.JwtProperties;
import com.example.spring.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {

  private final JwtProperties jwtProperties;

  private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
  private final int plusDays = 3;

  @Autowired
  public JwtUtil(JwtProperties jwtProperties) {
    this.jwtProperties = jwtProperties;
  }

  public String generateToken(User user) {
    Claims claims = Jwts.claims().setSubject(user.getUsername());
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime expireTime = currentTime.plusDays(plusDays);

    return Jwts.builder()
      .setClaims(claims)
      .setIssuer(jwtProperties.getIssuer())
      .setIssuedAt(localDateTimeToDate(currentTime))
      .setExpiration(localDateTimeToDate(expireTime))
      .signWith(signatureAlgorithm, generateSigingKey())
      .compact();
  }

  public User decodeToken(String token) {
    Jws<Claims> claims = generateClaims(token);
    User user = new User();
    user.setUsername(claims.getBody().getSubject());
    return user;
  }

  private Key generateSigingKey() {
    byte[] secretByte = DatatypeConverter.parseBase64Binary(jwtProperties.getKey());
    return new SecretKeySpec(secretByte, signatureAlgorithm.getJcaName());
  }

  private Jws<Claims> generateClaims(String token) {
    return Jwts.parser()
      .setSigningKey(generateSigingKey())
      .parseClaimsJws(token);
  }

  private Date localDateTimeToDate(LocalDateTime time) {
    return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
  }
}
