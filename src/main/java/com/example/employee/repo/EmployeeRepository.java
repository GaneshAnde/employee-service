package com.example.employee.repo;

import com.example.employee.domain.Employee;
import org.springframework.data.jpa.repository.*;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
  boolean existsByEmail(String email);
}
