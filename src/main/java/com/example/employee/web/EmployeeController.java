package com.example.employee.web;

import com.example.employee.domain.Employee;
import com.example.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private final EmployeeService service;

  public EmployeeController(EmployeeService service) {
    this.service = service;
  }

  @GetMapping
  public List<Employee> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public Employee get(@PathVariable Long id) {
    System.out.println(id);
    return service.get(id);
  }

  @PostMapping
  public ResponseEntity<Employee> create(@RequestBody @Valid EmployeeDTO dto) {
    Employee e = new Employee(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getDateOfJoining());
    Employee saved = service.create(e);
    return ResponseEntity.created(URI.create("/api/employees/" + saved.getId())).body(saved);
  }

  @PutMapping("/{id}")
  public Employee update(@PathVariable Long id, @RequestBody @Valid EmployeeDTO dto) {
    Employee e = new Employee(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getDateOfJoining());
    e.setId(id);
    return service.update(id, e);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
