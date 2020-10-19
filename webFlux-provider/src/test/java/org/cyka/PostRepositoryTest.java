package org.cyka;

import org.cyka.dao.PostRepository;
import org.cyka.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.data.relational.core.query.Criteria.where;

/**
 * @author long
 * @version 1.0.0
 * @since 2020/10/19 20:17
 */
@DataR2dbcTest
public class PostRepositoryTest {
  @Autowired DatabaseClient client;
  @Autowired PostRepository posts;

  @Test
  public void testDatabaseClientExisted() {
    assertNotNull(client);
  }

  @Test
  public void testPostRepositoryExisted() {
    assertNotNull(posts);
  }

  @Test
  public void testInsertAndQuery() {
    this.client
        .insert()
        .into("posts")
        // .nullValue("id", Integer.class)
        .value("title", "testTitle")
        .value("content", "testContent")
        .then()
        .block(Duration.ofSeconds(5));

    this.client
        .select()
        .from(Post.class)
        .matching(where("title").like("test%"))
        .fetch()
        .one()
        .log()
        .as(StepVerifier::create)
        .consumeNextWith(p -> assertEquals("testTitle", p.getTitle()))
        .verifyComplete();
  }
}
