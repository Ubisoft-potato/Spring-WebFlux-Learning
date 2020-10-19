package org.cyka.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author long
 * @version 1.0.0
 * @since 2020/10/19 20:05
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("posts")
public class Post {

  @Id
  @Column("id")
  private Integer id;

  @Column("title")
  private String title;

  @Column("content")
  private String content;
}
