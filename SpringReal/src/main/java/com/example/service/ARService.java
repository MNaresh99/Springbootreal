package com.example.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.model.ARUserMaster;
import com.example.model.UserMaster;



public interface ARService {

	public UserMaster saveUser(UserMaster um)throws ParseException ;

	public List<UserMaster> findAllUsers();

	public Page<ARUserMaster> findAllUsers(int pageNo,int pageSize);

	public UserMaster findById(Integer userId);

	// updating and deleting
	public UserMaster update(UserMaster um);

	public UserMaster findActiveUserByEmailAndPwd(String email, String pwd);

	
	public UserMaster findByEmail(String email);
	
}
