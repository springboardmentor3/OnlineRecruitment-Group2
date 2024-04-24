package com.example.OnlineRecruitment.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;
	@NotEmpty(message = "Invalid RoleTitle")
	private String roleTitle;
	@NotEmpty(message = "Invalid RoleDescription")
	private String roleDescription;
	
	@OneToOne(mappedBy = "role")
	@JsonIgnore
	private User user;
	
	public Role() {
		super();
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleTitle() {
		return roleTitle;
	}
	public void setRoleTitle(String roleTitle) {
		this.roleTitle = roleTitle;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role(Integer roleId, String roleTitle, String roleDescription, User user) {
		super();
		this.roleId = roleId;
		this.roleTitle = roleTitle;
		this.roleDescription = roleDescription;
		this.user = user;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleTitle=" + roleTitle + ", roleDescription=" + roleDescription
				+ ", user=" + user + "]";
	}
}
