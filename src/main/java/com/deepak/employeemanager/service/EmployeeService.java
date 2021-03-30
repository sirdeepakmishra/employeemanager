package com.deepak.employeemanager.service;


import com.deepak.employeemanager.exception.EmptyDataBaseException;
import com.deepak.employeemanager.exception.UserNotFoundException;
import com.deepak.employeemanager.model.Employee;
import com.deepak.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    public List<Employee> findEmployees() {
       /* Supplier<Employee> s = () -> new Employee();
        List<String> list1 = (List<String>) Arrays.asList(employeeRepo.findAll());*/
        return  employeeRepo.findAll();


    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User By id " + id + " was not found!"));
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);

    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }


    public void deleteEmployeeById(Long id) {

        employeeRepo.deleteById(id);
    }
}
