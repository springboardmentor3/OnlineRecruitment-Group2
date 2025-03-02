package com.example.OnlineRecruitment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineRecruitment.Classes.ResponseMessage;
import com.example.OnlineRecruitment.Entities.Appointment;
import com.example.OnlineRecruitment.Services.AppointmentService;

@RestController
@CrossOrigin("http://localhost:4200")
public class AppointmentControllers {
	
	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/saveappointment/{applicantId}")
	public ResponseEntity<?>saveAppointment(@PathVariable String applicantId,
			@RequestBody Appointment appointment ){
			try {
			appointmentService.saveAppointment(applicantId,appointment);
			}
			catch(Exception e) {
				return  ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseMessage("error"));
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Created"));
	}
	
	@GetMapping("/getAppointmentAllNotSet/{roleId}")
	public List<Appointment> getAlltheAppointmentwhichisNotSet(@PathVariable String roleId){
		return appointmentService.getAllAppointmentNotSet(roleId);
	}
	
	@GetMapping("/getAppointmentAllSet/{roleId}")
	public List<Appointment> getAlltheAppointmentwhichisSet(@PathVariable String roleId){
		System.out.println(roleId);
		System.out.println(appointmentService.getAllAppointmentSet(roleId));
		return appointmentService.getAllAppointmentSet(roleId);
	}
	
	@DeleteMapping("/rejectAppointment/{applicantId}")
	public ResponseEntity<?> rejectAppointment(@PathVariable String applicantId) {
		try { 
		appointmentService.rejectAppointment(applicantId);
		}
		catch(Exception e) {
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("error"));
		}	 
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("error"));
	}
	
	@GetMapping("/getResume/{applicantId}")
	public ResponseEntity<byte[]> resumedownload(@PathVariable String applicantId) {
	    byte[] file = appointmentService.download(applicantId);
	    System.out.println("this is runned");
	    String filename = "resume.pdf"; // adjust the filename as needed
	    return ResponseEntity.ok()
	           .contentType(MediaType.valueOf("application/pdf"))
	           .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
	           .body(file);
	}
}
