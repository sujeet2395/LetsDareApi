package com.learn.springbootrest.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private String name;
	private String username;
	private String first_name;
	private String last_name;
	private String email_id;
	private String passwd;
	private String mobile_no;
    private boolean active;
    private String roles;
	public User() {
		
	}
	public User(String name, String username, String first_name, String last_name,
			String email_id, String passwd, String mobile_no) {
		this.name=name;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email_id = email_id;
		this.passwd = passwd;
		this.mobile_no = mobile_no;
		this.roles="USER";
	}

	public User(User user) {
		this.name=user.name;
		this.username = user.username;
		this.first_name = user.first_name;
		this.last_name = user.last_name;
		this.name = user.name;
		this.email_id = user.email_id;
		this.passwd = user.passwd;
		this.mobile_no = user.mobile_no;
		this.roles=user.roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email_id == null) ? 0 : email_id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
		      return true;
	    if (!(o instanceof User))
	      return false;
	    User user = (User) o;
	    return Objects.equals(this.id, user.id) && Objects.equals(this.first_name, user.first_name) && Objects.equals(this.last_name, user.last_name) 
	        && Objects.equals(this.email_id, user.email_id) && Objects.equals(this.username, user.username) && Objects.equals(this.passwd, user.passwd)
	        && Objects.equals(this.mobile_no, user.mobile_no);
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", email_id=" + email_id + ", mobile_no=" + mobile_no + ", active="
				+ active + ", roles=" + roles + "]";
	}
	
}
