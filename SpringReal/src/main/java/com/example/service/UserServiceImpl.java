package com.example.service;

import java.text.ParseException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.UserBO;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.util.DateFormater;


@Service("userService")
public class UserServiceImpl implements UserService {
	/*@Autowired
	private SSNClient client;*/
    @Autowired
	private UserRepository userDao;
	@Override
	public User saveUser(User user) throws ParseException {
		// TODO Auto-generated method stub
		UserBO bo=null;
		User user1=null;
		//hit webservice
	/*IndvDetailResponse res=	client.getSsnUser(user);
	System.out.println("response "+res);
		if(res!=null) {
			IndvDetailType type=res.getIndvDetail();
			user.setSSN(type.getSsn());
			user.setDob(type.getDob());
			user.setFirstName(type.getFirstName());
			user.setLastName(type.getLastName());
			user.setSurName(type.getSurname());
			}*/
		if(user!=null) {
			bo=new UserBO();
			BeanUtils.copyProperties(user, bo);
			bo.setDob(DateFormater.strintToDate(user.getDob()));
			bo.setSSN(Integer.parseInt(user.getSSN()));
			
			bo=userDao.save(bo);
		}
		if(bo!=null) {
			user1=new User();
			BeanUtils.copyProperties(bo, user1);
			user1.setDob(DateFormater.dateToString(bo.getDob()));
			user1.setSSN(bo.getSSN().toString());
		}
		
		return user1;
	}
	

}
