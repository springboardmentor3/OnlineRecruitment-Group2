package com.example.OnlineRecruitment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineRecruitment.Classes.Email;
import com.example.OnlineRecruitment.Entities.Role;
import com.example.OnlineRecruitment.Entities.User;

import com.example.OnlineRecruitment.Services.UserService;

import jakarta.validation.Valid;



@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/user")
	public String saveUser(@Valid @RequestBody User user){
		try {
		System.out.println("the enter to backend");
		userService.saveUser(user);
		}
		catch(Exception e) {
			System.out.println("the exception occured"+e.getMessage());;
		}
		return "Created";
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable Integer id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/alluser")
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}
	
	@DeleteMapping("/userdelete/{id}")
	public String userDeleteById(@PathVariable Integer id) {
		userService.deleteUser(id);
		return "User Deleted";
	}
	@PutMapping("/userupdate/{id}")
	public String userUpdateById(@PathVariable Integer id,@Valid @RequestBody User user) {
		
		userService.updateUserById(id, user);
		return "Updated";
	}
	
	@PostMapping("/userlogin")
	public boolean checkUserexist(@RequestBody Email email) {
		try {
		System.out.println(email.toString());
		return userService.checkUserexist(email);
		}
		catch(Exception e) {
			System.out.println("the Exception occured"+e.getMessage());
			return false;
		}
	}
	
	
	
}
