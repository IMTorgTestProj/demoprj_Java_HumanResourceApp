package com.hrsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<Job> getAllJobs() {
		List<Job> jobs = new ArrayList<Job>();

		try {
			String sqlQuery = "select * from job";
			Statement statement = connection.prepareStatement(sqlQuery);

			ResultSet rs = statement.executeQuery(sqlQuery);

			while (rs.next()) {
				Job job = new Job();

				job.setJobId(rs.getInt("jobid"));
				job.setJobNumber(rs.getString("jobnumber"));
				job.setJobName(rs.getString("name"));
				job.setDescription(rs.getString("description"));

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
	public Job getJobById(int jobId) {
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

				// System.out.println(job);
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
}
