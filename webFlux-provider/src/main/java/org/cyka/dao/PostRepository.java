package org.cyka.dao;

import org.cyka.model.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author long
 * @version 1.0.0
 * @since 2020/10/19 20:07
 */
@Repository
public interface PostRepository extends ReactiveCrudRepository<Post, Integer> {}
