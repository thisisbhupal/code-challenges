package com.mindex.challenge.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.exceptions.EmployeeResourceNotFoundException;
import com.mindex.challenge.response.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

	/**
	 * Creating new employee.
	 * @param employee the employee object to be created
	 * @return employee the employee object from database
	 */
    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

	/**
	 * Get employee by id.
	 * @param id the employee id
	 * @return employee the employee object 
	 * @throws EmployeeResourceNotFoundException if the employee object is {@code null}
	 */
    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new EmployeeResourceNotFoundException("Invalid employeeId: " + id);
        }

        return employee;
    }

	/**
	 * Update existing employee details.
	 * @param employee the employee object to be updated
	 * @return employee the employee object from database
	 */
    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

	/**
	 * Get employee by id.
	 * @param id the employee id
	 * @return ReportingStructure the 
	 */
	@Override
	public ReportingStructure reportingStructure(String id) {
		LOG.debug("Getting  reporting structure for the employee with id [{}]", id);
		Employee employee = read(id);
		int numberOfReports = getReportsCount(employee);
		return new ReportingStructure(employee, numberOfReports);
	}
	
	/**
	 * Recursive function to get all the reports till it reaches leaf node.
	 * @param employee the employee object
	 * @return numberOfReports total number of reports
	 */
	private int getReportsCount(Employee employee) {
		List<Employee> directReports = employee.getDirectReports();
		
		if(null == directReports) {
			return 0;
		}
		
		int reportsCount = directReports.size();
		for(Employee emp : directReports) {
			try {
				reportsCount += getReportsCount(read(emp.getEmployeeId()));
			} catch (EmployeeResourceNotFoundException ex) {
				LOG.debug("Exception occured : [{}]", ex.getMessage());
				/*
				 * Don't do anything, if employee does not exist
				 * consider them as a leaf node
				 * */
			}
		}
		return reportsCount;
	}
}
