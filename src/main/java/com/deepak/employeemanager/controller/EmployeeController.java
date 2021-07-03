package com.deepak.employeemanager.controller;

import com.deepak.employeemanager.model.Employee;
import com.deepak.employeemanager.model.JWTRequest;
import com.deepak.employeemanager.model.JWTResponse;
import com.deepak.employeemanager.service.EmployeeService;
import com.deepak.employeemanager.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin("*")
@EnableWebMvc
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;
    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public EmployeeController(EmployeeService employeeService, JWTUtility jwtUtility, AuthenticationManager authenticationManager) {
        this.employeeService = employeeService;
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping(value = "/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = employeeService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        return new JWTResponse(token);
    }


    @PostMapping(value = "/token", consumes = "application/json")
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) {
        System.out.println("jwtRequest");
        return null;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<?>> findEmployees() {
        return new ResponseEntity<>(employeeService.findEmployees(), HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        LOGGER.info("new employee created : " + newEmployee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        LOGGER.info("employee by id " + updateEmployee.getId() + " has been updated!");
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        LOGGER.info("employee by id " + id + " has been deleted!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
