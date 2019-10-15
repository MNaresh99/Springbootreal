package com.example.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name = "AR_USER_MASTER")

public class ARUserMaster {

	@Id
	@GeneratedValue
	@Column(name = "AR_USER_ID")
	private Integer userId;

	@Column(name = "AR_USER_FIRSTNAME")
	private String firstName;

	@Column(name = "AR_USER_LASTNAME")
	private String lastName;

	@Column(name = "AR_USER_EMAIL")
	private String email;

	@Column(name = "AR_USER_PWD")
	private String pwd;

	@Column(name = "AR_USER_DOB")
	private Date dob;

	@Column(name = "AR_USER_PHNO")
	private String phno;

	@Column(name = "AR_USER_ACTIVE_SW")
	private String activeSw;

	@Column(name = "AR_USER_ROLE")
	private String userRole;

	@CreationTimestamp
	@Column(name = "AR_USER_CREATED_DT")
	private Timestamp createdDate;

	@UpdateTimestamp
	@Column(name = "AR_USER_UPDATED_DT")
	private Timestamp updatedDate;

	@Column(name = "AR_USER_CREATEDBY")
	private String createdBy;

	@Column(name = "UPDATEDBY")
	private String updatedBy;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getActiveSw() {
		return activeSw;
	}

	public void setActiveSw(String activeSw) {
		this.activeSw = activeSw;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "ARUserMaster [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", pwd=" + pwd + ", dob=" + dob + ", phno=" + phno + ", activeSw=" + activeSw + ", userRole="
				+ userRole + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + "]";
	}

}
