package io.pivotal.literx;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a
 *     href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux
 *     Javadoc</a>
 */
public class Part01Flux {

  // ========================================================================================

  // TODO Return an empty Flux
  // 创建一个空Flux
  Flux<String> emptyFlux() {
    return Flux.empty(); // TO BE REMOVED
  }

  // ========================================================================================

  // TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a
  // collection
  // 不使用集合或者数组生成一个包含2个值（"foo","bar"）的Flux
  Flux<String> fooBarFluxFromValues() {
    return Flux.just("foo", "bar"); // TO BE REMOVED
  }

  // ========================================================================================

  // TODO Create a Flux from a List that contains 2 values "foo" and "bar"
  // 使用一个包含"foo"和"bar"的list创建Flux
  Flux<String> fooBarFluxFromList() {
    return Flux.fromIterable(Arrays.asList("foo", "bar")); // TO BE REMOVED
  }

  // ========================================================================================

  // TODO Create a Flux that emits an IllegalStateException
  // 创建发送IllegalStateException的FLux
  Flux<String> errorFlux() {
    return Flux.error(new IllegalStateException()); // TO BE REMOVED
  }

  // ========================================================================================

  // TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
  // 创建一个从0到9每隔100ms依次递增的Flux
  Flux<Long> counter() {
    return Flux.interval(Duration.ofMillis(100)).take(10); // TO BE REMOVED
  }
}
