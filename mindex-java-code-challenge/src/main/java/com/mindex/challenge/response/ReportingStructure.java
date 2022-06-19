package com.mindex.challenge.response;

import com.mindex.challenge.data.Employee;

public class ReportingStructure {

	private Employee employee;
	private int numberOfReports;
	
	public ReportingStructure(Employee employee2, int numberOfReports2) {
		this.employee = employee2;
		this.numberOfReports = numberOfReports2;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getNumberOfReports() {
		return numberOfReports;
	}
	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}	
}
