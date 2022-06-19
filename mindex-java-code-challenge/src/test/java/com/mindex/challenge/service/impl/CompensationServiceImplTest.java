package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationCreateUrl;
    private String compensationUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
    	compensationCreateUrl = "http://localhost:" + port + "/compensation";
    	compensationUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateRead() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("Saraha");
        testEmployee.setLastName("Johnson");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(120000.5f);
        testCompensation.setEffectiveDate(new Date());
        // Create Compensation checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationCreateUrl, testCompensation, Compensation.class).getBody();
        assertNotNull(createdCompensation);
        assertCompensationEquivalence(testCompensation, createdCompensation);

        // Read Compensation checks
        Compensation readCompensation = restTemplate.getForEntity(compensationUrl, Compensation.class, createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertNotNull(readCompensation);
        assertEquals(createdCompensation.getEmployee().getEmployeeId(), readCompensation.getEmployee().getEmployeeId());
        assertCompensationEquivalence(createdCompensation, readCompensation);

    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
    	assertNotNull(actual.getEmployee());
        assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
        assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName());
        assertEquals(expected.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
        assertEquals(expected.getEmployee().getPosition(), actual.getEmployee().getPosition());
        assertEquals(expected.getSalary(), actual.getSalary(), 0);
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}
