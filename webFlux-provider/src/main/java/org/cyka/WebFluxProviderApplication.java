package org.cyka;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WebFluxProviderApplication {
  public static void main(String[] args) {
    new SpringApplicationBuilder(WebFluxProviderApplication.class) // configuration class
        .main(WebFluxProviderApplication.class) // just main class, not for config
        .bannerMode(Banner.Mode.CONSOLE)
        .web(WebApplicationType.REACTIVE)
        .logStartupInfo(true)
        .registerShutdownHook(true)
        .build()
        .run(args);
  }
}
