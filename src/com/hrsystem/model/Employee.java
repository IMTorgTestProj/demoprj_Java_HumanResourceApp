package com.hrsystem.model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	private int employeeId;
	private String ssn;
	private String firstName;
	private String lastName;
	private String initMiddle;
	private String location;
	private String salaryType;

	private Date createdDate;
	private Date updatedDate;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getInitMiddle() {
		return initMiddle;
	}

	public void setInitMiddle(String initMiddle) {
		this.initMiddle = initMiddle;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(String salaryType) {
		this.salaryType = salaryType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", ssn=" + ssn + ", firstName=" + firstName + ", lastName="
				+ lastName + ", initMiddle=" + initMiddle + ", location=" + location + ", salaryType=" + salaryType
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
