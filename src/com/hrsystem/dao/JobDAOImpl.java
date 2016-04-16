package com.hrsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hrsystem.model.Job;
import com.hrsystem.util.DBUtil;

public class JobDAOImpl implements JobDAO {

	private Connection connection;

	public JobDAOImpl() {
		connection = DBUtil.getConnection();
	}

	@Override
	public boolean addJob(Job job) {

		boolean actionResult = false;

		try {
			String sqlQuery = "insert into job (jobnumber,name,description,createddate) values(?,?,?,?);";
			PreparedStatement pStatement = connection.prepareStatement(sqlQuery);

			pStatement.clearParameters();

			pStatement.setString(1, job.getJobNumber());
			pStatement.setString(2, job.getJobName());
			pStatement.setString(3, job.getDescription());
			pStatement.setTimestamp(4, new java.sql.Timestamp(job.getCreatedDate().getTime()));

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

}
