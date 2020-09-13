package org.cyka.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cyka.dao.EmployeeRepository;
import org.cyka.model.Employee;
import org.cyka.service.IEmployeeService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Service
@Slf4j
@CacheConfig(cacheNames = "employee", cacheManager = "caffeineCacheManager")
public class EmployeeService implements IEmployeeService {
  private final EmployeeRepository employeeRepo;

  @CachePut(key = "#e.id")
  public void create(Employee e) {
    employeeRepo.save(e).subscribe();
  }

  @Cacheable(key = "#id")
  public Mono<Employee> findById(Integer id) {
    log.info("query database");
    return employeeRepo.findById(id);
  }

  @Cacheable(key = "#name", condition = "#name.length()>0")
  public Flux<Employee> findByName(String name) {
    return employeeRepo.findByName(name);
  }

  @Cacheable
  public Flux<Employee> findAll() {
    return employeeRepo.findAll().delayElements(Duration.ofMillis(1000), Schedulers.elastic());
  }

  @CachePut(key = "#e.id")
  public Mono<Employee> update(Employee e) {
    return employeeRepo.save(e);
  }

  @CacheEvict(key = "#id")
  public Mono<Void> delete(Integer id) {
    return employeeRepo.deleteById(id);
  }

  public EmployeeService(EmployeeRepository employeeRepo) {
    this.employeeRepo = employeeRepo;
  }
}
