package org.cyka.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@LoadBalancerClients({
  @LoadBalancerClient(
      value = "webFlux-provider",
      configuration = CustomLoadBalancerConfiguration.class)
})
public class LoadBalanceWebClientConfiguration {

  /**
   * loadBalanced webclient
   *
   * @return loadBalanced webclient
   */
  @Bean
  @LoadBalanced
  public WebClient.Builder loadBalancedWebClientBuilder() {
    return WebClient.builder();
  }
  /**
   * non loadBalanced webclient as the priamry
   *
   * @return non loadBalanced webclient
   */
  @Primary
  @Bean
  WebClient.Builder webClient() {
    return WebClient.builder();
  }
}
