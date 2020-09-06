package org.cyka.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

  private final WebClient loadBalancedClient;
  private final WebClient nonLoadBalancedClient;

  @GetMapping("/loadBalancedCall")
  public Mono<String> loadBalancedCall() {
    return HystrixCommands.from(callHelloEndpoint(loadBalancedClient))
        .fallback(Mono.just("service down"))
        .commandName("loadBalanceCall")
        .toMono();
  }

  @GetMapping("/nonLoadBalancedCall")
  public Mono<String> nonLoadBalancedCall() {
    return HystrixCommands.from(callHelloEndpoint(nonLoadBalancedClient))
        .fallback(Mono.just("service down"))
        .commandName("nonLoadBalanced")
        .toMono();
  }

  private Mono<String> callHelloEndpoint(WebClient client) {
    return client.get().uri("/hello").retrieve().bodyToMono(String.class);
  }

  public HelloController(
      @LoadBalanced WebClient.Builder loadBalancedBuilder,
      WebClient.Builder nonLoadBalancedBuilder) {
    this.loadBalancedClient = loadBalancedBuilder.baseUrl("http://webFlux-provider").build();
    this.nonLoadBalancedClient = nonLoadBalancedBuilder.baseUrl("http://localhost:8080").build();
  }
}
