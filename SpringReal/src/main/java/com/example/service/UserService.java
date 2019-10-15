package com.example.service;

import java.text.ParseException;
import java.util.List;

import com.example.model.User;



public interface UserService {

	public User saveUser(User user)throws ParseException ;
    
	/*public List<User> findAllUsers();

	public Page<User> findAllUsers(int pageNo,int pageSize);

	public User findById(Integer userId);

	// updating and deleting
	public User update(User um);

	public User findActiveUserByEmailAndPwd(String email, String pwd);

	
	public User findByEmail(String email);*/
	
}
