package com.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name = "USER_MASTER")

public class UserBO {

	
	private String userId;
	private String surName;    
	private Integer SSN;	
	private String firstName;	
	private String lastName;
	private String email;	
	private Date dob;	
	private String activeSw;	
	private Timestamp createdDate;	
	private Timestamp updatedDate;	
	private String createdBy;	
	private String updatedBy;

	@Id
	@GenericGenerator(name = "sequence_dep_id", strategy = "com.his.util.SsnIdGenerator")
	@GeneratedValue(generator = "sequence_dep_id") 
	@Column(name = "USER_ID")
   public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "USER_FIRSTNAME")
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "USER_LASTNAME")
	public String getLastName() {
		return lastName;
	}
	@Column(name="USER_SURNAME")
	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="USER_SSN")
	public Integer getSSN() {
		return SSN;
	}

	public void setSSN(Integer sSN) {
		SSN = sSN;
	}
	@Column(name = "USER_EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "USER_DOB")
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "USER_ACTIVE_SW")
	public String getActiveSw() {
		return activeSw;
	}

	public void setActiveSw(String activeSw) {
		this.activeSw = activeSw;
	}
	@CreationTimestamp
	@Column(name = "USER_CREATED_DT")
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	@UpdateTimestamp
	@Column(name = "USER_UPDATED_DT")
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Column(name = "USER_CREATEDBY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "UPDATEDBY")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "UserBO [userId=" + userId + ", surName=" + surName + ", SSN=" + SSN + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", dob=" + dob + ", activeSw=" + activeSw
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + "]";
	}

	

}
