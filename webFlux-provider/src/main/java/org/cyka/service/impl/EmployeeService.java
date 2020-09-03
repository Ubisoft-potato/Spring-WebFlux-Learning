package org.cyka.service.impl;

import org.cyka.dao.EmployeeRepository;
import org.cyka.model.Employee;
import org.cyka.service.IEmployeeService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Service
public class EmployeeService implements IEmployeeService {
  private final EmployeeRepository employeeRepo;

  public void create(Employee e) {
    employeeRepo.save(e).subscribe();
  }

  public Mono<Employee> findById(Integer id) {
    return employeeRepo.findById(id);
  }

  public Flux<Employee> findByName(String name) {
    return employeeRepo.findByName(name);
  }

  public Flux<Employee> findAll() {
    return employeeRepo.findAll().delayElements(Duration.ofMillis(1000), Schedulers.elastic());
  }

  public Mono<Employee> update(Employee e) {
    return employeeRepo.save(e);
  }

  public Mono<Void> delete(Integer id) {
    return employeeRepo.deleteById(id);
  }

  public EmployeeService(EmployeeRepository employeeRepo) {
    this.employeeRepo = employeeRepo;
  }
}
