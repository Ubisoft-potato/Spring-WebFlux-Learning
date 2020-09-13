package org.cyka.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
}
