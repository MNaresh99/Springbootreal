package com.example.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service("arService")
public class ARServiceImpl implements ARService {

	@Autowired(required = true)
	private ARUserMasterDao arUserMasterDao;

	@Autowired(required = true)
	private EmailService emailService;

	/**
	 * This method is used to insert user record
	 * @throws ParseException 
	 */
	@Override
	public UserMaster saveUser(UserMaster um) throws ParseException {
		ARUserMaster entity = new ARUserMaster();

		um.setActiveSw("Y");
		um.setCreatedBy("Admin");

		// copying data from model to entity
		BeanUtils.copyProperties(um, entity);
System.out.println(entity);
		// Encrypting User Password
		String encryptedPwd = PasswordService.encrypt(um.getPwd());
		entity.setPwd(encryptedPwd);
     
		Date date=new SimpleDateFormat("dd/MM/yyyy").parse(um.getDob());
		entity.setDob(date);
		
		ARUserMaster savedEntity = arUserMasterDao.save(entity);
System.out.println("saved "+savedEntity);
		// Sending Email with Pwd
		if (savedEntity != null) {
			String text;
			try {
				text = getRegEmailBody(um);
				emailService.sendEmail(um.getEmail(), ARConstants.EMAIL_FROM, ARConstants.EMAIL_SUBJECT, text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// setting generated pk value to model
		um.setUserId(savedEntity.getUserId());

		return um;
	}

	@Override
	public List<UserMaster> findAllUsers() {
		List<UserMaster> users = new ArrayList<UserMaster>();
		List<ARUserMaster> entities = arUserMasterDao.findAll();
		for (ARUserMaster entity : entities) {
			UserMaster master = new UserMaster();
			BeanUtils.copyProperties(entity, master);
			users.add(master);
		}
		return users;
	}

	@Override
	public Page<ARUserMaster> findAllUsers(int pageNo, int pageSize) {
		System.out.println("ARServiceImpl.findAllUsers()");
		Pageable pageble = new PageRequest(pageNo, pageSize);
		List<UserMaster> users = new ArrayList<UserMaster>();
		Page<ARUserMaster> pages = arUserMasterDao.findAll(pageble);
		System.out.println("returning pages to controller");
		return pages;
	}

	@Override
	public UserMaster findById(Integer userId) {
		
		ARUserMaster entity=arUserMasterDao.findById(userId).get();
		
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		String date=df.format(entity.getDob());
		UserMaster um=new UserMaster();
		
		BeanUtils.copyProperties(entity, um);
		um.setDob(date);
		um.setPwd(PasswordService.decrypt(um.getPwd()));
		System.out.println(um.getPwd());
		return um;
	}

	@Override
	public UserMaster update(UserMaster um) {
		ARUserMaster entity=new ARUserMaster();
		um.setPwd(PasswordService.encrypt(um.getPwd()));
		BeanUtils.copyProperties(um, entity);
		Date date;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(um.getDob());
			entity.setDob(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		entity=arUserMasterDao.save(entity);
		BeanUtils.copyProperties(entity, um);
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		String date1=df.format(entity.getDob());
		um.setDob(date1);
		um.setPwd(PasswordService.decrypt(um.getPwd()));
		return um;
	}

	@Override
	public UserMaster findActiveUserByEmailAndPwd(String email, String pwd) {
		UserMaster um = null;
		String encryptedPwd = PasswordService.encrypt(pwd);
		ARUserMaster arUserMaster = arUserMasterDao.findActiveUserByEmailAndPwd(email, encryptedPwd);
		if(arUserMaster!=null) {
			um=new UserMaster();
		BeanUtils.copyProperties(arUserMaster, um);
		}
		return um;
	}

	

	public String getRegEmailBody(UserMaster um) throws Exception {
		String fileName = "Registration_Email_Template.txt";
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		StringBuilder mailBody = new StringBuilder("");
		while (line != null) {

			// Processing mail body content
			if (line.contains("USER_NAME")) {
				line = line.replace("USER_NAME", um.getFirstName() + " " + um.getLastName());
			}

			if (line.contains("APP_USER_EMAIL")) {
				line = line.replace("APP_USER_EMAIL", um.getEmail());
			}

			if (line.contains("APP_URL")) {
				line = line.replace("APP_URL", "<a href='http://localhost:4040/HIS/loginForm'>RI HIS</a>");
			}

			if (line.contains("APP_USER_PWD")) {
				line = line.replace("APP_USER_PWD", um.getPwd());
			}

			// Appending processed line to StringBuilder
			mailBody.append(line);

			// reading next line
			line = br.readLine();
		}
		

		fr.close();
		br.close();

		// Returning mail body content
		return mailBody.toString();
	}
	@Override
	public UserMaster findByEmail(String email)  {
		System.out.println("ARServiceImpl.findByEmail()");
		UserMaster um=null;
		ARUserMaster entity = arUserMasterDao.findByEmail(email);
		
		if(entity!=null) {
			um=new UserMaster();
			BeanUtils.copyProperties(entity, um);
			um.setPwd(PasswordService.decrypt(um.getPwd()));
			
			
		}
		return um;
	}


}
