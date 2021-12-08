package com.companyportal.app.service;

import java.util.ArrayList;
import java.util.List;



import com.companyportal.app.entity.Employee;

public interface EmployeeService {
	
public void saveOrUpdateEmployee(Employee employee);

public List<Employee> getEmployeesData();


public Employee getEmployeeById(int employeeId);

public List<Employee> searchEmployeebyName(String employeeName);


void deleteEmployee(Employee employee);




}