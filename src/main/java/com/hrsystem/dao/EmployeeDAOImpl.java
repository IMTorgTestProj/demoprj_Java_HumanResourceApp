package com.hrsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hrsystem.model.Employee;
import com.hrsystem.util.DBUtil;
import com.hrsystem.util.HRUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	private Connection connection;

	public EmployeeDAOImpl() {
		this.connection = DBUtil.getConnection();
	}

	@Override
	public boolean addEmployee(Employee employee) {
		boolean actionResult = false;

		try {
			String sqlQuery = "insert into employee (firstname, lastname, initmiddle, ssn, location, salarytype, createddate) values (?,?,?,?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();

			pStatement.setString(1, employee.getFirstName());
			pStatement.setString(2, employee.getLastName());
			pStatement.setString(3, employee.getInitMiddle());
			pStatement.setString(4, employee.getSsn());
			pStatement.setString(5, employee.getLocation());
			pStatement.setString(6, employee.getSalaryType());

			pStatement.setTimestamp(7, new java.sql.Timestamp(employee.getCreatedDate().getTime()));

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
	public List<String> loadLocation() {
		List<String> locations = new ArrayList<String>();

		try {
			String sqlQuery = "select * from location";
			Statement statement = connection.prepareStatement(sqlQuery);

			ResultSet rs = statement.executeQuery(sqlQuery);

			while (rs.next()) {
				String locationName = rs.getString("name");
				locations.add(locationName);
			}

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return locations;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();

		try {
			String sqlQuery = "select * from employee";
			Statement statement = connection.prepareStatement(sqlQuery);

			ResultSet rs = statement.executeQuery(sqlQuery);

			while (rs.next()) {

				Employee employee = new Employee();

				employee.setEmployeeId(rs.getInt("employeeid"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setSsn(rs.getString("ssn"));
				employee.setInitMiddle(rs.getString("initmiddle"));
				employee.setLocation(rs.getString("location"));
				employee.setSalaryType(rs.getString("salarytype"));

				// System.out.println(employee);
				employees.add(employee);
			}

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
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
	public boolean deleteEmployee(int employeeId) {
		boolean actionResult = false;

		try {
			String sqlQuery = "delete from employee where employeeId=?";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();
			pStatement.setInt(1, employeeId);

			int count = pStatement.executeUpdate();

			if (count == 1) {
				String updateQuery = "update jobassignment ja set status = ?, updateddate= ? where ja.employeeid = ? and status = ?";
				PreparedStatement pStatement1 = connection.prepareStatement(updateQuery);

				pStatement1.clearParameters();

				pStatement1.setInt(1, HRUtil.Status.Inactive);
				pStatement1.setTimestamp(2, new java.sql.Timestamp((new Date()).getTime()));
				pStatement1.setInt(3, employeeId);
				pStatement1.setInt(4, HRUtil.Status.Active);

				pStatement1.executeUpdate();
			}

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
	public boolean updateEmployee(Employee employee) {
		boolean actionResult = false;

		try {
			String sqlQuery = "update employee set firstname=?, lastname=?, initmiddle=?, ssn=?, location=?, salarytype = ?, updateddate=? where employeeid = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();

			pStatement.setString(1, employee.getFirstName());
			pStatement.setString(2, employee.getLastName());
			pStatement.setString(3, employee.getInitMiddle());
			pStatement.setString(4, employee.getSsn());
			pStatement.setString(5, employee.getLocation());
			pStatement.setString(6, employee.getSalaryType());
			pStatement.setTimestamp(7, new java.sql.Timestamp(employee.getUpdatedDate().getTime()));
			pStatement.setInt(8, employee.getEmployeeId());

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
	public List<Employee> searchEmployees(String search) {
		List<Employee> employees = new ArrayList<Employee>();

		try {
			String sqlQuery = "select * from employee where ssn like '%" + search + "%'";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			ResultSet rs = pStatement.executeQuery(sqlQuery);

			while (rs.next()) {
				Employee employee = new Employee();

				employee.setEmployeeId(rs.getInt("employeeid"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setSsn(rs.getString("ssn"));
				employee.setInitMiddle(rs.getString("initmiddle"));
				employee.setLocation(rs.getString("location"));
				employee.setSalaryType(rs.getString("salarytype"));

				// System.out.println(employee);
				employees.add(employee);

			}

		} catch (SQLException ex) {
			System.out.println("SQL Exception : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			ex.printStackTrace();
		}

		return employees;
	}

}
