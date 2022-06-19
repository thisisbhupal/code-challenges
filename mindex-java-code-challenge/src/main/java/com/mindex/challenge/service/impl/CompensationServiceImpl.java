package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.exceptions.CompensationResourceNotFoundException;
import com.mindex.challenge.exceptions.EmployeeResourceNotFoundException;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;

@Service
public class CompensationServiceImpl implements CompensationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompensationServiceImpl.class);
	
	@Autowired
	CompensationRepository compensationRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * Creating compensation with employee details.
	 * @param compensation the compensation details to be created
	 * @return compensation the compensation details from database
	 */
	@Override
	public Compensation create(Compensation compensation) {
		LOGGER.debug("Creating Compensation [{}]", compensation);
		
		Employee employee = employeeService.create(compensation.getEmployee());
		compensation.setEmployee(employee);
		compensationRepository.insert(compensation);

        return compensation;
	}

	/**
	 * Get compensation details by employee id.
	 * @param id the employee id
	 * @return compensation the compensation details from database
	 * @throws EmployeeResourceNotFoundException if the employee object is {@code null}
	 */
	@Override
	public Compensation read(String id) {
		LOGGER.debug("Reading Compensation for employee with the id [{}]", id);
		
		Employee employee = employeeService.read(id);
		Compensation compensation = compensationRepository.findByEmployee(employee);
		if(null == compensation) {
			throw new CompensationResourceNotFoundException("There is no compensation data for the employee :" + employee.getEmployeeId());
		}
		
		return compensation;
	}

}
