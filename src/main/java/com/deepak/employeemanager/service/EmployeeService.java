package com.deepak.employeemanager.service;


import com.deepak.employeemanager.exception.UserNotFoundException;
import com.deepak.employeemanager.model.Employee;
import com.deepak.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    /*Authentication*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Get the user form Database
        return new User("admin", "password", new ArrayList<>());
    }

    public List<Employee> findEmployees() {
        return Optional.of(employeeRepo.findAll())
                .orElseThrow(() -> new IllegalStateException("Couldn't find any employee!"));
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
        Consumer<Employee> employeeToUpdate = updateObject -> employeeRepo.save(employee);
        Optional.ofNullable(findEmployeeById(employee.getId())).ifPresent(employeeToUpdate);
        return employee;
    }

    public void deleteEmployeeById(Long id) {
        Consumer<Employee> employeeToDelete = deleteObject -> employeeRepo.deleteById(id);
        Optional.ofNullable(findEmployeeById(id)).ifPresent(employeeToDelete);
    }

}
