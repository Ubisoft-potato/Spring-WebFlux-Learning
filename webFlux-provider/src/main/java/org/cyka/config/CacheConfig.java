package org.cyka.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
@EnableCaching
public class CacheConfig {
  @Bean
  public CaffeineCacheManager caffeineCacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    Caffeine<Object, Object> caffeine =
        Caffeine.newBuilder()
            .maximumSize(5000L)
            .recordStats()
            .expireAfterAccess(Duration.ofSeconds(30))
            .expireAfterWrite(3 * 60, TimeUnit.SECONDS);
    cacheManager.setCaffeine(caffeine);
    return cacheManager;
  }

  @Bean
  @Primary
  public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
    RedisCacheConfiguration employeeCacheConfig =
        RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(600))
            .serializeKeysWith(fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(fromSerializer(new Jackson2JsonRedisSerializer<>(Object.class)))
            .disableCachingNullValues();
    return RedisCacheManager.builder(factory)
        .transactionAware()
        .withCacheConfiguration("employee", employeeCacheConfig)
        .build();
  }
}
