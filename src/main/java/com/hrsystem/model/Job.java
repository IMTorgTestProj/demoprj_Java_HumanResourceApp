package com.hrsystem.model;

import java.util.Date;

public class Job {

	private int jobId;
	private String jobNumber;
	private String jobName;
	private String description;
	private Date createdDate;
	private Date updatedDate;

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "Job [jobId=" + jobId + ", jobNumber=" + jobNumber + ", jobName=" + jobName + ", description=" + description + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
