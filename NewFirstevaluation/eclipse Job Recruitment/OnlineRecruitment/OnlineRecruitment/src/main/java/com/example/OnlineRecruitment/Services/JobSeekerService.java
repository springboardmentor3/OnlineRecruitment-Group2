package com.example.OnlineRecruitment.Services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.OnlineRecruitment.Classes.FileUtil;
import com.example.OnlineRecruitment.Entities.Appointment;
import com.example.OnlineRecruitment.Entities.Job;
import com.example.OnlineRecruitment.Entities.JobSeeker;
import com.example.OnlineRecruitment.Repositories.JobRepository;
import com.example.OnlineRecruitment.Repositories.JobSeekerRepository;
@Service
public class JobSeekerService {

	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmployerService employerService;
	
	@Autowired
	private JobService jobService;
	@Autowired
	private UserService userService;
	

	public boolean createJobSeeker(JobSeeker jobSeeker) {
		String gradId = jobSeeker.getGraduate().getRoleId().getRoleId();
		String jobId = jobSeeker.getJob().getJobId().toString();

		Appointment appointment = new Appointment();
		
		String applicantId = gradId.concat(jobId);
		
		appointment.setApplicantId(applicantId);
		
		jobSeeker.setAppointment(appointment);
		
	
			
		 
		jobSeekerRepository.save(jobSeeker);
		
		String roleStringId = jobService.getRoleIdbYjobId(jobSeeker.getJob().getJobId());
		
		JobSeeker jobseeker2 = jobSeekerRepository.getAllJobseekerByemployeeId(roleStringId).get(0);
		
		String employeeEmail = userService.findByEmailRoleId(roleStringId);
		
		Job job2 = jobService.getJobsByIntegerId(Integer.valueOf(jobId));
		
		//this is for the graduate
		sendEmailtoGraduate(jobSeeker.getEmail(),jobSeeker,roleStringId,job2);
		// this is for the employer
		sendEmailtoEmployer(employeeEmail,jobseeker2,job2);
		
		return true;
	}
	
	private void sendEmailtoGraduate(String email,JobSeeker jobSeeker,String roleStringId,Job job) {
        SimpleMailMessage message = new SimpleMailMessage();
        String text = "Your Application Id is :"+jobSeeker.getAppointment().getApplicantId()+
        		"Company Name:"+ employerService.getEmployerById(roleStringId).getCompanyName()+
        		"JobName:"+job.getJobName()+"Applicant Name:"+jobSeeker
        		.getFullName()+"Job Type:"+job.getJobType()+"email:"+jobSeeker.getEmail();
        
        message.setTo(email);
        message.setSubject("Job Application Submitted");
        message.setText(text);
        javaMailSender.send(message);
    }
	
	private void sendEmailtoEmployer(String email,JobSeeker jobSeeker,Job job) {
		 SimpleMailMessage message = new SimpleMailMessage();
		 String text = "The Application Id is :"+jobSeeker.getAppointment().getApplicantId()+
				 "The Job Id :"+job.getJobId()+"The Job Type:"+job.getJobType()+
				 "The Job Name:"+job.getJobName()+"The Student Details \n"+"Name:"+
				 jobSeeker.getFullName()+ "Phone:"+jobSeeker.getPhone()+"Email:"+jobSeeker.getEmail()+"CGPA:"+
				 jobSeeker.getCgpa()+"Year Of Passing :"+jobSeeker.getYearOfPassing()+"KeySkills:"+jobSeeker.getKeySkill()+
				 "Project:"+jobSeeker.getProject();
		 	message.setTo(email);
	        message.setSubject("Job Application Submitted");
	        message.setText(text);
	        javaMailSender.send(message);
		 
	}

	public List<JobSeeker> getalljobseeker() {
		// TODO Auto-generated method stub
		return jobSeekerRepository.findAll();
	}

	public List<JobSeeker> getalljobseekerbyemployeeId(String employeeId) {
		// TODO Auto-generated method stub
		return jobSeekerRepository.getAllJobseekerByemployeeId(employeeId);
	}

	public List<JobSeeker> getAlljobsByStudentId(String studentId) {
		// TODO Auto-generated method stub
		return jobSeekerRepository.getAllJobSeekerByStudentId(studentId);
	}

	public String uploadResume(MultipartFile file,String applicationId) {
		// TODO Auto-generated method stub
		JobSeeker jobseeker = jobSeekerRepository.getByAppointmentId(applicationId);
		
		try {
			jobseeker.setResume(FileUtil.compressFile(file.getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jobSeekerRepository.save(jobseeker);
		return "file uploaded";
	}

	public byte[] download(String applicationId) {
		// TODO Auto-generated method stub
		JobSeeker jobseeker = jobSeekerRepository.getByAppointmentId(applicationId);
		
		return jobseeker.getResume();
	}
	
	
	
	
	
}
