package com.hrsystem.dao;

import java.util.List;

import com.hrsystem.model.Assignment;
import com.hrsystem.model.Employee;
import com.hrsystem.model.Job;

public interface AssignmentDAO {

	public Employee loadEmployeeInfo(int employeeId);

	public List<Job> loadMapJob(int employeeId);

	public Job loadJobInfo(int jobId);

	public boolean addAssignment(Assignment assignment);

}
