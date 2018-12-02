package com.kenzan.employees.services;

import com.kenzan.employees.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO findById(Long id);

    Long createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> findAll();

    void editEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);
}
