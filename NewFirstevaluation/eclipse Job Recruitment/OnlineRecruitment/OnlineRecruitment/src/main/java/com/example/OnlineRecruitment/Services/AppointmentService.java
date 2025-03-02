package com.example.OnlineRecruitment.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.OnlineRecruitment.Classes.FileUtil;
import com.example.OnlineRecruitment.Entities.Appointment;
import com.example.OnlineRecruitment.Repositories.AppointmentRepository;
@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void saveAppointment(String applicantId, Appointment appointment) {
		// TODO Auto-generated method stub
		Appointment appoint = appointmentRepository.getById(applicantId);
		appoint.setDate(appointment.getDate());
		appoint.setLocation(appointment.getLocation());
		appoint.setSet(true);
		appointmentRepository.save(appoint);
		String email = appoint.getJobSeeker().getEmail();
		String JobName = appoint.getJobSeeker().getJob().getJobName();
		String JobDescription = appoint.getJobSeeker().getJob().getJobDescription();
		String text = "Your Application/appointmentId is : "+applicantId+" "+"Appointment Date is :"+appointment.getDate()+" "+
				 "Location :"+appointment.getLocation()+" "+"Job Name :"+JobName+" "+"Job Description :"+JobDescription;
		sendEmail(email,text,"Appointment Scheduled AND Location");
		
	}
	
	 private void sendEmail(String email,String text,String Subject) {
	
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(email);
	        message.setSubject(Subject);
	        message.setText(text);
	        javaMailSender.send(message);
	    }

	public List<Appointment> getAllAppointmentNotSet(String roleId) {
		return appointmentRepository.getAllAppointmentNotSet(roleId);
		
	}

	public List<Appointment> getAllAppointmentSet(String roleId) {
		System.out.println(roleId);
		return appointmentRepository.getAllAppointmentSet(roleId);
	}

	public String rejectAppointment(String applicantId) {
		Appointment appoint = appointmentRepository.getById(applicantId);
		System.out.println(appoint.getJobSeeker().getEmail());
		appointmentRepository.delete(appoint);
		String text = "This Email is regarding that Your Application :"+" "+appoint.getApplicantId()+" "+
		"is rejected by the Employer Due to not satisfied requirement to the Company";
		String Subject ="Appointment Rejected"; 
		sendEmail(appoint.getJobSeeker().getEmail(),text,Subject);
		return "deleted";
	}

	public byte[] download(String applicantId) {
		Appointment appoint = appointmentRepository.getById(applicantId);
		
		return FileUtil.decompressFile(appoint.getJobSeeker().getResume());
	}
	    
}
