package com.example.OnlineRecruitment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineRecruitment.Classes.ResponseMessage;
import com.example.OnlineRecruitment.Classes.graduateJob;
import com.example.OnlineRecruitment.Entities.Graduate;
import com.example.OnlineRecruitment.Entities.Job;
import com.example.OnlineRecruitment.Services.GraduateService;

@RestController
@CrossOrigin("http://localhost:4200")
public class GraduateController {
	@Autowired
	GraduateService graduateService;
	
	@PostMapping("/graduate")
	public ResponseEntity<?> saveGraduate(@RequestBody Graduate graduate) {
		//TODO: process POST request
		graduateService.saveGraduate(graduate);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Created"));
	}
	
	@GetMapping("/graduate/{id}")
	public Graduate getGraduateById(@PathVariable String id) {
		return graduateService.getGraduateById(id);
	}
	
	@GetMapping("/allgraduate")
	public List<Graduate> getallgraduate() {
		return graduateService.getAllGraduate();
	}
	
	@GetMapping("/existsgraduate/{roleId}")
	public boolean existGraduateById(@PathVariable String roleId) {
		return graduateService.checkGraduateExist(roleId);
	}
	
	@PostMapping("/graduatejobs")
	public ResponseEntity<?> saveJobOfGraduate(@RequestBody graduateJob graduate) {
		try {
			graduateService.addJob(graduate);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("error"));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("saved"));
	}
	
	@PutMapping("/updategraduate/{roleId}")
	public ResponseEntity<?> updateGraduate(@RequestBody Graduate graduate,
			@PathVariable String roleId) {
		try {
		graduateService.updateService(graduate,roleId);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("error"));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("saved"));
	}
	
	@DeleteMapping("/deletegraduate/{roleId}")
	public ResponseEntity<?> deleteGraduate(@PathVariable String roleId) {
		try { graduateService.deleteGraduate(roleId);}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("error"));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("saved"));
	}
	
//	@GetMapping("/graduatejobs/{roleId}")
//	public List<Job> getListOfJobsByGraduateById(@PathVariable String roleId){
//		return graduateService.getListofJobsofGraduateById(roleId);
//	}
	
	@GetMapping("/getstudentid/{roleId}")
	public Long getstudentId(@PathVariable String roleId) {
		return graduateService.getStudentId(roleId);
	}
}
