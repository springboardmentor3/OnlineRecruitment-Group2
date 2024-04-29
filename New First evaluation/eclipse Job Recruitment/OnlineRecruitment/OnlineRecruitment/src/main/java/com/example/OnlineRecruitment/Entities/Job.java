package com.example.OnlineRecruitment.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Job {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer jobId;
	@NotEmpty(message = "Invalid Job Name")
	private String jobName;
	@Max(value = 2147483647,message = "max salary can be 2147483647")
	private Integer jobSalary;
	
	@NotEmpty(message = "Invalid Job Type")
	private String jobType;
	
	@NotEmpty(message = "Invalid Job Description")
	private String jobDescription;
	
	@Max(value = 2147483647,message = "max vacancy can be 2147483647")
	private Integer jobVacancy;
	
	public Job() {
		super();
	}
	public Job(Integer jobId, String jobName, Integer jobSalary, String jobType, String jobDescription,
			Integer jobVacancy) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobSalary = jobSalary;
		this.jobType = jobType;
		this.jobDescription = jobDescription;
		this.jobVacancy = jobVacancy;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Integer getJobSalary() {
		return jobSalary;
	}
	public void setJobSalary(Integer jobSalary) {
		this.jobSalary = jobSalary;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public Integer getJobVacancy() {
		return jobVacancy;
	}
	public void setJobVacancy(Integer jobVacancy) {
		this.jobVacancy = jobVacancy;
	}
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobName=" + jobName + ", jobSalary=" + jobSalary + ", jobType=" + jobType
				+ ", jobDescription=" + jobDescription + ", jobVacancy=" + jobVacancy + "]";
	}
}
