package org.cyka.controller;

import lombok.RequiredArgsConstructor;
import org.cyka.dao.PostRepository;
import org.cyka.model.Post;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author long
 * @version 1.0.0
 * @since 2020/10/19 20:09
 */
@RestController()
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
class PostController {
  private final PostRepository posts;

  @GetMapping("")
  public Flux<Post> all() {
    return this.posts.findAll().log();
  }

  @PostMapping("")
  public Mono<Post> create(@RequestBody Post post) {
    return this.posts.save(post);
  }

  @GetMapping("/{id}")
  public Mono<Post> get(@PathVariable("id") Integer id) {
    return this.posts.findById(id);
  }

  @PutMapping("/{id}")
  public Mono<Post> update(@PathVariable("id") Integer id, @RequestBody Post post) {
    return this.posts
        .findById(id)
        .map(
            p -> {
              p.setTitle(post.getTitle());
              p.setContent(post.getContent());
              return p;
            })
        .flatMap(this.posts::save);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable("id") Integer id) {
    return this.posts.deleteById(id);
  }
}
