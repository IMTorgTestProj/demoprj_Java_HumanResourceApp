package com.hrsystem.dao;

import java.util.List;

import com.hrsystem.model.Employee;

public interface EmployeeDAO {

	public List<String> loadLocation();

	public boolean addEmployee(Employee employee);

	public List<Employee> getAllEmployees();

}
