package com.example.model;

public class User {
   private String userId;
	private String phno;
	private String SSN;
	private String firstName;
	private String lastName;
	private String email;
	private String dob;
	private String surName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	@Override
	public String toString() {
		return "User [phno=" + phno + ", SSN=" + SSN + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", dob=" + dob + ", surName=" + surName + "]";
	}

	

}
