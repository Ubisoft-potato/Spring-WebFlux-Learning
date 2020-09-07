package org.cyka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GateWaySecurityConfig {

  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
    http.authorizeExchange()
        .pathMatchers("/resource")
        .hasAuthority("SCOPE_resource.read")
        .anyExchange()
        .authenticated()
        .and()
        .oauth2ResourceServer()

        .jwt();
    return http.build();
  }
}
