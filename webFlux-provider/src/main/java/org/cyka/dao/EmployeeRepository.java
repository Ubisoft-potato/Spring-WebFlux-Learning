package org.cyka.dao;

import org.cyka.model.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {
  @Query("{ 'name': ?0 }")
  Flux<Employee> findByName(String name);
}
