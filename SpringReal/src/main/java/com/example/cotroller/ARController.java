package com.example.cotroller;
	
	
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.text.ParseException;
	import java.util.ArrayList;
	import java.util.List;

	import javax.servlet.http.HttpServletRequest;

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.BeanUtils;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.data.domain.Page;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.ARUserMaster;
import com.example.model.User;
import com.example.model.UserMaster;
import com.example.service.ARService;
import com.example.service.UserService;
import com.example.util.constants;

	
	@Controller
	public class ARController {

		private static final Logger logger = LoggerFactory.getLogger(ARController.class);

	//	@Autowired(required = true)
	//	private EmailService emailService;
		@Autowired
	    private UserService userService;
		
		

		@Autowired(required = true)
		private ARService arService;
		
		//@Autowired
		//private CoPlanReportDlyBatch batch;
	/*
	 * @RequestMapping({ "/batchTest" }) public String batchTest(Model model) { long
	 * time=batch.init(); UserMaster um = new UserMaster(); model.addAttribute("um",
	 * um); model.addAttribute("time", time); return "index"; }
	 */

	
	  @RequestMapping(value = "/regUserForm", method = RequestMethod.GET) public
	  String regUserForm(Model model) {
	  logger.debug("Started CaseWorker Registration Form Display"); UserMaster um =
	  new UserMaster(); model.addAttribute("um", um); initForm(model);
	  logger.debug("Ended CaseWorker Registration Form Display"); return "userReg";
	  }
	  
	  @RequestMapping(value = "/loginForm", method = RequestMethod.GET) public
	  String index(Model model) { System.out.println("loading login form");
	  UserMaster um = new UserMaster(); model.addAttribute("um", um); return
	  "index"; }
	 

		public void initForm(Model model) {
			List<String> rolesList = new ArrayList();
			rolesList.add("Admin");
			rolesList.add("Case Worker");
			model.addAttribute("rolesList", rolesList);
		}

		/**
		 * This method is used to save user profile
		 * 
		 * @param um
		 * @param model
		 * @return String
		 * @throws ParseException 
		 */
		@RequestMapping(value = "/regUser", method = RequestMethod.POST)
		public String registerUser(@ModelAttribute("um") UserMaster um, Model model) throws ParseException {
			// call service layer
			
			//save user
			UserMaster master = arService.saveUser(um);

			if (master.getUserId() != null) {
				// store success msg
				model.addAttribute(constants.SUCCESS, constants.REG_SUCCESS);
			} else {
				// store error msg in model
				model.addAttribute(constants.ERROR, constants.REG_ERROR);
			}
			logger.info("rendering to view with register status");
			initForm(model);
			return "userReg";
		}

		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public String loginCheck(@ModelAttribute("um") UserMaster um, Model model) {
			String view = "";
			// call service layer
			UserMaster master = arService.findActiveUserByEmailAndPwd(um.getEmail(), um.getPwd());
			if (master != null) {
				// Valid User
				System.out.println(master.getActiveSw()+" "+constants.SW_Y);
				System.out.println(master.getActiveSw()!=constants.SW_Y);
				if(!constants.SW_Y.equals(master.getActiveSw())){
					view="index";
					model.addAttribute(constants.ERROR, "user in inactive state");
				}
				else {
					if("Admin".equals(master.getUserRole())) 
						view = "dashboardAdmin";
					else 
						view="dashboardCaseWorker";
					
				}
				
			} 
			else {
				// In Valid User
				view = "index";
				model.addAttribute(constants.ERROR, constants.INVALID_USER);
			}
			return view;
		}

		@RequestMapping(value = "/viewCaseWorkers")
		public String viewAllCaseWorkers(@RequestParam(name="cpn",defaultValue="1") String pageNo, Model model) {
	        System.out.println("ARController.viewAllCaseWorkers()");
			Integer currentPageNo = 1;
			List<UserMaster> users = new ArrayList();

			if (null != pageNo && !"".equals(pageNo)) {
				currentPageNo = Integer.parseInt(pageNo);
			}

			Page<ARUserMaster> page = arService.findAllUsers(currentPageNo - 1, constants.PAGE_SIZE);
			int totalPages = page.getTotalPages();
			List<ARUserMaster> entities = page.getContent();

			for (ARUserMaster entity : entities) {
				UserMaster um = new UserMaster();
				BeanUtils.copyProperties(entity, um);
				users.add(um);
			}

			model.addAttribute("cpn", pageNo);
			model.addAttribute("tp", totalPages);
			model.addAttribute("caseWorkers", users);
		
			return "viewCaseWorkers";
		}

		@RequestMapping(value = "/listCaseWorkers")
		public String listAllCaseWorkers(Model model) {
	System.out.println("ARController.viewAllCaseWorkers()");
		
			List<UserMaster> users = new ArrayList();

			// call service method
			users = arService.findAllUsers();
			

		

		
			model.addAttribute("caseWorkers", users);
			
			return "listCaseWorkers";
		}

		@RequestMapping(value = "/regUserForm/checkEmail")
		public @ResponseBody String checkUniqueEmail(@RequestParam(name = "email") String email)  {
			System.out.println("EMail entered : " + email);
			UserMaster um=null;
			um= arService.findByEmail(email);
			return (um==null)?constants.UNIQUE:constants.DUPLICATE;
		}
		
		@RequestMapping("/deleteCaseWorker")
		public String deleteCaseWorker(@RequestParam("uid")String uid,@RequestParam("cpn")String cpn) {
			   
			
			UserMaster um=arService.findById(Integer.parseInt(uid));
			
			//set user activeSw 
			um.setActiveSw("N");
			 um=arService.update(um);
			 
			
			return "redirect:viewCaseWorkers?cpn="+cpn;
			
		}
		@RequestMapping("/activateCaseWorker")
		public String activateCaseWorker(@RequestParam("uid")String uid,@RequestParam("cpn")String cpn) {
			   
			
			UserMaster um=arService.findById(Integer.parseInt(uid));
			
			//set user activeSw 
			um.setActiveSw("Y");
			 um=arService.update(um);
			 
			
			return "redirect:viewCaseWorkers?cpn="+cpn;
			
		}
		@RequestMapping("/deleteCaseWorker1")
		public String deleteCaseWorker1(@RequestParam("uid")String uid) {
			   
			
			UserMaster um=arService.findById(Integer.parseInt(uid));
			
			//set user activeSw 
			um.setActiveSw("N");
			 um=arService.update(um);
			 
			
			return "redirect:listCaseWorkers";
			
		}
		@RequestMapping("/activateCaseWorker1")
		public String activateCaseWorker1(@RequestParam("uid")String uid) {
			   
			
			UserMaster um=arService.findById(Integer.parseInt(uid));
			
			//set user activeSw 
			um.setActiveSw("Y");
			 um=arService.update(um);
			 
			
			return "redirect:listCaseWorkers";
			
		}
		
		@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
		public String updateUser(@ModelAttribute("userModel") UserMaster um, Model model) throws ParseException {
			// call service layer
			
			System.out.println(um);
			UserMaster master = arService.saveUser(um);

			if (master.getUserId() != null) {
				// store success msg
				model.addAttribute(constants.SUCCESS, constants.UPDATE_SUCCESS);
			} else {
				// store error msg
				model.addAttribute(constants.ERROR, constants.UPDATE_ERROR);
			}
			initForm(model);
			return "updateUserReg";
		}
		@RequestMapping("/editCaseWorker")
		public String editUser(@RequestParam("uid") String uid,Model model) {
			
			UserMaster um=arService.findById(Integer.parseInt(uid));
			if(um!=null)
			model.addAttribute("userModel",um);
			initForm(model);
			return "updateUserReg";
		
			
		}
		
		@RequestMapping("/forgotPwdForm")
		public String forgotPasswordForm() {
		return "forgotPwd";	
		}
		/**
	 * this method for Forgot password
	 * 
	 * @param model
	 * @param req
	 * @return String
	 * @throws Exception
	 *//*
		 * @RequestMapping("/forgotPwd") public String forgotPwd(Model
		 * model,HttpServletRequest req) throws Exception { UserMaster um=null; //get
		 * request scope variable String email=req.getParameter("email"); //call service
		 * method um= arService.findByEmail(email); //checking availability of record
		 * if(um!=null) { //checking whether record is active or not
		 * if(um.getActiveSw().equals("Y")) { //send original password to user mail
		 * if(um.getActiveSw().equals("Y")) { String text=getLoginEmailBody(um);
		 * 
		 * emailService.sendEmail(um.getEmail(), ARConstants.EMAIL_FROM,
		 * "Recovering Password", text); } model.addAttribute(ARConstants.
		 * SUCCESS,"password sent to your mail, please check it. And Login <a href='/loginFrom'>here</a>"
		 * ); } else { model.addAttribute(ARConstants.ERROR, "user in inactive state");
		 * } } else { model.addAttribute(ARConstants.ERROR, "wrong mail id"); } return
		 * "forgotPwd";
		 */
		//}
		
	/*
	 * public String getLoginEmailBody(UserMaster um) throws Exception { String
	 * fileName = "login_mail_template.txt"; FileReader fr = new
	 * FileReader(fileName); BufferedReader br = new BufferedReader(fr); String line
	 * = br.readLine(); StringBuilder mailBody = new StringBuilder(""); while (line
	 * != null) {
	 * 
	 * // Processing mail body content if (line.contains("USER_NAME")) { line =
	 * line.replace("USER_NAME", um.getFirstName() + " " + um.getLastName()); }
	 * 
	 * if (line.contains("USER_PWD")) { line = line.replace("USER_PWD",
	 * um.getPwd()); } if (line.contains("USER_LINK")) { line =
	 * line.replace("USER_LINK",
	 * "<a href='http://localhost:4040/HIS/loginForm'>RI HIS</a>"); }
	 * 
	 * // Appending processed line to StringBuilder mailBody.append(line);
	 * 
	 * // reading next line line = br.readLine(); }
	 * 
	 * 
	 * fr.close(); br.close();
	 * 
	 * // Returning mail body content return mailBody.toString(); }
	 */
		@RequestMapping(value="/appUserForm", method = RequestMethod.GET)
		public String appUserForm(Model model) {
			User user=new User();
			model.addAttribute("appUser",user);
			return "applicationReg";
		}
		@RequestMapping(value="/appUserReg", method= RequestMethod.POST)
		public String appUserReg(@ModelAttribute("appUser")User user,Model model) throws ParseException {
			user=userService.saveUser(user);
			if(user!=null) {
				model.addAttribute(constants.SUCCESS,"User Registered Successfully with APPID:"+user.getUserId());
			}
			else
				model.addAttribute(constants.ERROR,"User Registration Failed check ur details ");
		return "applicationReg";
	    }
		

	}


	
	
	
	
	
	


