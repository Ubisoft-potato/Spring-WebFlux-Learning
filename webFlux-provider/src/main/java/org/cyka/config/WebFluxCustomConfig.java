package org.cyka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
@Slf4j
public class WebFluxCustomConfig implements WebFluxConfigurer {
  @Override
  public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
    log.info("Server Http Readers: {}", configurer.getReaders());
    log.info("Server Http Writers: {}", configurer.getWriters());
    configurer.defaultCodecs().maxInMemorySize(512 * 1024);
    configurer.defaultCodecs().enableLoggingRequestDetails(true);
  }

}
