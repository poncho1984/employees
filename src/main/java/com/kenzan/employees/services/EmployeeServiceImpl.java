package com.kenzan.employees.services;

import com.kenzan.employees.EmployeeDTO;
import com.kenzan.employees.dao.EmployeeDao;
import com.kenzan.employees.exceptions.EmployeeNotFoundException;
import com.kenzan.employees.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeDao.findByIdAndStatusTrue(id);
        if (employee != null) {
            return convertEntityToDTO(employee);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Long createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeDao.save(convertDTOToEntity(employeeDTO));
        return employee.getId();
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeDao.findAll();
        return employees.stream().filter(employee -> employee.isStatus()).map(employeeEntity -> convertEntityToDTO(employeeEntity)).collect(Collectors.toList());
    }

    @Override
    public void editEmployee(EmployeeDTO employeeDTO) {
        Employee existingEmployee = getEmployeeById(employeeDTO.getId());

        existingEmployee.setFirstName(employeeDTO.getFirstName());
        existingEmployee.setMiddleInitial(employeeDTO.getMiddleInitial());
        existingEmployee.setLastName(employeeDTO.getLastName());
        existingEmployee.setDateOfBirth(employeeDTO.getDateOfBirth());
        existingEmployee.setDateOfEmployment(employeeDTO.getDateOfEmployment());

        employeeDao.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setStatus(false);
        employeeDao.save(existingEmployee);
    }

    private Employee getEmployeeById(Long id) {
        Employee existingEmployee = employeeDao.findOne(id);
        if (existingEmployee == null) {
            throw new EmployeeNotFoundException("The request employee don´t exist.");
        }
        return existingEmployee;
    }

    private EmployeeDTO convertEntityToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setMiddleInitial(employee.getMiddleInitial());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setDateOfEmployment(employee.getDateOfEmployment());
        return employeeDTO;
    }
    

    private Employee convertDTOToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleInitial(employeeDTO.getMiddleInitial());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setDateOfEmployment(employeeDTO.getDateOfEmployment());
        return employee;
    }
}
