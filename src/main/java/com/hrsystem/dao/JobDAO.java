package com.hrsystem.dao;

import java.util.List;

import com.hrsystem.model.Job;

public interface JobDAO {

	public boolean addJob(Job job);

	public List<Job> getAllJobs();

	public Job getJobById(int jobId);
	
	public boolean updateJob(Job job);
	
	public boolean deleteJob(int jobId);
	
	public List<Job> searchJobs(String search);

}
