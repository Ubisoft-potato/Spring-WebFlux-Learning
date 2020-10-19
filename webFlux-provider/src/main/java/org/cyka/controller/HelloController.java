package org.cyka.controller;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public Publisher<String> sayHello() {
    return Flux.<String>generate(sink -> sink.next("hello\n")).take(20);
  }
}
