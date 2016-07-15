package com.hrsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hrsystem.model.Assignment;
import com.hrsystem.model.Employee;
import com.hrsystem.model.Job;
import com.hrsystem.util.DBUtil;

public class AssignmentDAOImpl implements AssignmentDAO {

	private Connection connection;

	public AssignmentDAOImpl() {
		this.connection = DBUtil.getConnection();
	}

	@Override
	public Employee loadEmployeeInfo(int employeeId) {
		Employee employee = new Employee();

		try {
			String sqlQuery = "select * from employee where employeeid=?";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();
			pStatement.setInt(1, employeeId);
			ResultSet rs = pStatement.executeQuery();

			if (rs.next()) {

				employee.setEmployeeId(rs.getInt("employeeid"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setSsn(rs.getString("ssn"));
				employee.setInitMiddle(rs.getString("initmiddle"));
				employee.setLocation(rs.getString("location"));
				employee.setSalaryType(rs.getString("salarytype"));

				// System.out.println(employee);
			}

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return employee;
	}

	@Override
	public List<Job> loadMapJob(int employeeId) {

		List<Job> jobs = new ArrayList<Job>();

		try {
			String sqlQuery = "select * from job j where j.jobid not in (select ja.jobid from jobassignment ja where ja.status = 1 and ja.employeeid = ?)";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();
			pStatement.setInt(1, employeeId);

			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				Job job = new Job();

				job.setJobId(rs.getInt("jobid"));
				job.setJobNumber(rs.getString("jobnumber"));
				// job.setJobName(rs.getString("name"));
				// job.setDescription(rs.getString("description"));

				// System.out.println(job);
				jobs.add(job);
			}

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return jobs;
	}

	@Override
	public Job loadJobInfo(int jobId) {
		Job job = new Job();

		try {
			String sqlQuery = "select * from job where jobid=?";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();
			pStatement.setInt(1, jobId);
			ResultSet rs = pStatement.executeQuery();

			if (rs.next()) {
				job.setJobId(rs.getInt("jobid"));
				job.setJobNumber(rs.getString("jobnumber"));
				job.setJobName(rs.getString("name"));
				job.setDescription(rs.getString("description"));
			}

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return job;
	}

	@Override
	public boolean addAssignment(Assignment assignment) {
		boolean actionResult = false;

		try {
			String sqlQuery = "insert into jobassignment (employeeid,jobid,status,createddate) values (?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();

			pStatement.setInt(1, assignment.getEmployeeId());
			pStatement.setInt(2, assignment.getJobId());
			pStatement.setInt(3, assignment.getStatus());
			pStatement.setTimestamp(4,
					new java.sql.Timestamp(assignment.getCreatedDate().getTime()));

			pStatement.executeUpdate();
			actionResult = true;

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return actionResult;
	}

	@Override
	public List<Assignment> getAllJobAssignment(int status) {
		List<Assignment> jobAssignments = new ArrayList<Assignment>();

		try {
			String sqlQuery = "select ja.assignmentid, ja.employeeid, j.jobid, e.firstname, e.lastname, e.initmiddle, j.jobnumber, ja.status from employee e, jobassignment ja, job j where e.employeeid = ja.employeeid and j.jobid = ja.jobid and status = ?";

			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();
			pStatement.setInt(1, status);

			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				Assignment assignment = new Assignment();

				assignment.setAssignmentId(rs.getInt("assignmentid"));

				assignment.setEmployeeId(rs.getInt("employeeid"));
				assignment.setFirstName(rs.getString("firstname"));
				assignment.setLastName(rs.getString("lastname"));
				assignment.setInitMiddle(rs.getString("initmiddle"));

				assignment.setJobId(rs.getInt("jobid"));
				assignment.setJobNumber(rs.getString("jobnumber"));

				assignment.setStatus(rs.getInt("status"));

				jobAssignments.add(assignment);

			}

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return jobAssignments;

	}

	@Override
	public Assignment getJobAssignment(int assignmentId) {

		Assignment assignment = new Assignment();

		try {
			String sqlQuery = "select ja.assignmentid, ja.employeeid, e.ssn, e.firstname, e.lastname, e.initmiddle, e.location, e.salarytype, j.jobid, j.jobnumber, j.name, j.description, ja.status  from employee e, jobassignment ja, job j where e.employeeid = ja.employeeid and j.jobid = ja.jobid and ja.assignmentid = ?";

			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();
			pStatement.setInt(1, assignmentId);

			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {

				assignment.setAssignmentId(rs.getInt("assignmentid"));

				assignment.setEmployeeId(rs.getInt("employeeid"));
				assignment.setSsn(rs.getString("ssn"));
				assignment.setFirstName(rs.getString("firstname"));
				assignment.setLastName(rs.getString("lastname"));
				assignment.setInitMiddle(rs.getString("initmiddle"));
				assignment.setLocation(rs.getString("location"));
				assignment.setSalaryType(rs.getString("salarytype"));

				assignment.setJobId(rs.getInt("jobid"));
				assignment.setJobNumber(rs.getString("jobnumber"));
				assignment.setJobName(rs.getString("name"));
				assignment.setDescription(rs.getString("description"));

				assignment.setStatus(rs.getInt("status"));

				// System.out.println(job);
			}

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return assignment;
	}

	@Override
	public boolean updateAssignment(Assignment assignment) {
		boolean actionResult = false;

		try {
			String sqlQuery = "update jobassignment ja set ja.jobid = ?, updateddate= ? where ja.assignmentid = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();

			pStatement.setInt(1, assignment.getJobId());
			pStatement.setTimestamp(2,
					new java.sql.Timestamp(assignment.getUpdatedDate().getTime()));
			pStatement.setInt(3, assignment.getAssignmentId());

			pStatement.executeUpdate();
			actionResult = true;

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return actionResult;

	}

	@Override
	public boolean deleteAssignment(int assignmentId) {
		boolean actionResult = false;

		try {
			String sqlQuery = "delete from jobassignment where assignmentid = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();
			pStatement.setInt(1, assignmentId);

			pStatement.executeUpdate();
			actionResult = true;

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return actionResult;

	}

	@Override
	public List<Assignment> getAssignmentByEmpId(int employeeId) {

		List<Assignment> jobAssignments = new ArrayList<Assignment>();
		try {
			String sqlQuery = "select ja.assignmentid, ja.employeeid, e.ssn, e.firstname, e.lastname, e.initmiddle, e.location, e.salarytype, j.jobid, j.jobnumber, j.name, j.description, ja.status  from employee e, jobassignment ja, job j where e.employeeid = ja.employeeid and j.jobid = ja.jobid and ja.employeeid = ?";

			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();
			pStatement.setInt(1, employeeId);

			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				Assignment assignment = new Assignment();
				assignment.setAssignmentId(rs.getInt("assignmentid"));

				assignment.setEmployeeId(rs.getInt("employeeid"));
				assignment.setSsn(rs.getString("ssn"));
				assignment.setFirstName(rs.getString("firstname"));
				assignment.setLastName(rs.getString("lastname"));
				assignment.setInitMiddle(rs.getString("initmiddle"));
				assignment.setLocation(rs.getString("location"));
				assignment.setSalaryType(rs.getString("salarytype"));

				assignment.setJobId(rs.getInt("jobid"));
				assignment.setJobNumber(rs.getString("jobnumber"));
				assignment.setJobName(rs.getString("name"));
				assignment.setDescription(rs.getString("description"));

				assignment.setStatus(rs.getInt("status"));
				jobAssignments.add(assignment);
				// System.out.println(job);
			}

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return jobAssignments;
	}

	@Override
	public List<Assignment> getAssignmentByJobId(int jobId) {
		List<Assignment> jobAssignments = new ArrayList<Assignment>();

		try {
			String sqlQuery = "select ja.assignmentid, ja.employeeid, e.ssn, e.firstname, e.lastname, e.initmiddle, e.location, e.salarytype, j.jobid, j.jobnumber, j.name, j.description, ja.status  from employee e, jobassignment ja, job j where e.employeeid = ja.employeeid and j.jobid = ja.jobid and ja.jobId = ?";

			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();
			pStatement.setInt(1, jobId);

			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				Assignment assignment = new Assignment();
				assignment.setAssignmentId(rs.getInt("assignmentid"));

				assignment.setEmployeeId(rs.getInt("employeeid"));
				assignment.setSsn(rs.getString("ssn"));
				assignment.setFirstName(rs.getString("firstname"));
				assignment.setLastName(rs.getString("lastname"));
				assignment.setInitMiddle(rs.getString("initmiddle"));
				assignment.setLocation(rs.getString("location"));
				assignment.setSalaryType(rs.getString("salarytype"));

				assignment.setJobId(rs.getInt("jobid"));
				assignment.setJobNumber(rs.getString("jobnumber"));
				assignment.setJobName(rs.getString("name"));
				assignment.setDescription(rs.getString("description"));

				assignment.setStatus(rs.getInt("status"));
				jobAssignments.add(assignment);
				// System.out.println(job);
			}

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return jobAssignments;
	}

}
