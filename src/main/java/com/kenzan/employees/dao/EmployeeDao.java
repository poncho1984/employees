package com.kenzan.employees.dao;

import com.kenzan.employees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

    Employee findByIdAndStatusTrue(Long id);
}
