package org.cyka.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Employee {
  @Id Integer id;
  String name;
  Long salary;
}
