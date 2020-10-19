package org.cyka.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cyka.dao.EmployeeRepository;
import org.cyka.model.Employee;
import org.cyka.service.IEmployeeService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
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
    return employeeRepo.findAll();
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
