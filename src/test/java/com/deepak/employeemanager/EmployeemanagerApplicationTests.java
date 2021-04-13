package com.deepak.employeemanager;

//import org.junit.jupiter.api.Test;

import com.deepak.employeemanager.controller.EmployeeController;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.*;

@SpringBootTest
public class EmployeemanagerApplicationTests {
    private static final Logger LOG=LoggerFactory.getLogger(EmployeemanagerApplicationTests.class);

    @Autowired
    private EmployeeController employeeController;

    @BeforeMethod
    void beforeMethod() {
        LOG.info("before method");
        System.out.println("before method!!");
    }
    @BeforeTest
    void beforeTest() {
        LOG.info("before test");
        System.out.println("before test!!");
    }

    @AfterTest
    void afterTest() {
        LOG.info("after test");
        System.out.println("after test!");
    }

    @AfterMethod
    void afterMethod() {
        LOG.info("after method");
        System.out.println("after method!");
    }

    @Test
    void contextLoads()  {
        Assertions.assertThat(employeeController).isNotNull();

    }

    @Test
    void getEmployeesTest() {
        System.out.println("get employees test!!");
    }

}
