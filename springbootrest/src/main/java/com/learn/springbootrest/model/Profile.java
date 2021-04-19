package com.learn.springbootrest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="profiles")
public class Profile {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Profile(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password=password;
	}
	public Profile(String name, String password) {
		super();
		this.id = 0;
		this.name = name;
		this.password=password;
	}
	public Profile() {
		super();
		this.id = 0;
		this.name = "";
		this.password="";
	}
	
	@Override
	public String toString() {
		return "Profile [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
