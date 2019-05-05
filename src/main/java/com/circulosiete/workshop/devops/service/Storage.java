package com.circulosiete.workshop.devops.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Storage {

  private static final String DEFAULT_VALUE = "Extraño";
  private static final String SALUDO = "saludo";
  private final RedisTemplate redisTemplate;

  public Storage(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public String saludo() {
    return Optional.ofNullable(redisTemplate.opsForValue().get(SALUDO))
      .map(Object::toString)
      .orElseGet(() -> {
        redisTemplate.opsForValue().set(SALUDO, DEFAULT_VALUE);
        return DEFAULT_VALUE;
      });
  }

  public String guardarSaludo(String word) {

    System.out.println("Saludo Modificado api primero" + word);
    redisTemplate.opsForValue().set("saludoModificado", word);
    String saludoModificado = Optional.ofNullable(redisTemplate.opsForValue().get("saludoModificado"))
      .map(Object::toString)
      .orElseGet(() -> {
        redisTemplate.opsForValue().set("saludoModificado", word);
        return word;
      });
     
    return saludoModificado;
  }
}
