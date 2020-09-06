package org.cyka;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class WebFluxConsumerApplication {
  public static void main(String[] args) {
    SpringApplication.run(WebFluxConsumerApplication.class, args);
  }
}
