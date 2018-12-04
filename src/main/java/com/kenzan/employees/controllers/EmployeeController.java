package com.kenzan.employees.controllers;

import com.kenzan.employees.EmployeeDTO;
import com.kenzan.employees.ExceptionDTO;
import com.kenzan.employees.exceptions.EmployeeNotFoundException;
import com.kenzan.employees.services.EmployeeService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static final String USER = "poncho";
    private static final String PASSWORD = "P0nchO";
    private static final int AUTH_USER_INDEX = 0;
    private static final int AUTH_PASS_INDEX = 1;

    @RequestMapping(value = {"/employee/{id}"}, method = RequestMethod.GET)
    @Description(value = "returns specific Employee by id")
    public @ResponseBody
    EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @RequestMapping(value = {"/employees"}, method = RequestMethod.GET)
    @Description(value = "returns all Employees that are active")
    public @ResponseBody
    List<EmployeeDTO> getEmployees() {
        return employeeService.findAll();
    }

    @RequestMapping(value = {"/employee"}, method = RequestMethod.POST)
    @Description(value = "creates new employee")
    public
    @ResponseBody
    Long createEmployee(@RequestBody final EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @RequestMapping(value = {"/employee/{id}"}, method = RequestMethod.PUT)
    @Description(value = "edit an existing employee")
    public
    @ResponseBody
    void editEmployee(@RequestBody final EmployeeDTO employeeDTO, @PathVariable Long id) {
        employeeDTO.setId(id);
        employeeService.editEmployee(employeeDTO);
    }

    @RequestMapping(value = {"/employee/{id}"}, method = RequestMethod.DELETE)
    @Description(value = "make an existing employee inactive")
    public
    @ResponseBody
    ResponseEntity deleteEmployee(@PathVariable Long id, HttpServletRequest request) {
        if (!validateAuthorizationHeader(request)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        employeeService.deleteEmployee(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler({EmployeeNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionDTO employeeNotFoundExceptionHandler(Exception e) {
        return new ExceptionDTO(e.getMessage());
    }

    private Boolean validateAuthorizationHeader(HttpServletRequest request) {
        try {
            String encodedAuthorizationHeader = request.getHeader("Authorization");
            encodedAuthorizationHeader = encodedAuthorizationHeader.replace("Basic ", "");
            final byte[] decodedBytes
                    = Base64.decodeBase64(encodedAuthorizationHeader.getBytes());
            final String pair = new String(decodedBytes);
            final String[] userDetails = pair.split(":", 2);
            return userDetails[AUTH_USER_INDEX].equals(USER) && userDetails[AUTH_PASS_INDEX].equals(PASSWORD);
        } catch (Exception ex) {
            return false;
        }
    }
}
