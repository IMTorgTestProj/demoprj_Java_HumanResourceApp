package com.hrsystem.dao;

import java.util.List;

import com.hrsystem.model.Job;

public interface JobDAO {

	public boolean addJob(Job job);
	public List<Job> getAllJobs();
	

}
