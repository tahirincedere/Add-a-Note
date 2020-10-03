package com.tahir.notalma;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tahir.entity.Note;
import com.tahir.entity.User;
import com.tahir.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userservice;

	
	
	@RequestMapping(value = "/controlUser", method = RequestMethod.POST)
	public ResponseEntity<String> controlUser(@RequestBody User user,HttpServletRequest request) {

		User userm=userservice.getFindByIdUsernameAndPass(user);
		if(userm!=null)
		
		{
			request.getSession().setAttribute("user",userm);

    		return new ResponseEntity<>("OK", HttpStatus.OK);

		}
		return new ResponseEntity<>("ERROR", HttpStatus.OK);
		
		
		

		
				
		}
		
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value ="status",required = false)String status,Model model) {

		if(status!=null)
		{
			System.out.println(status);
			if(status.equals("ok"))
			{
				model.addAttribute("status","�yeli�iniz ba�ar�yla tamamland�");
			}
			else
				model.addAttribute("status","Hata Tekrar Deneyiniz");
				
		}
		
		
		
		
		
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {

		return "register";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(Model model,HttpServletRequest request) {

		request.getSession().setAttribute("user",null);
		return "redirect:/login";
	}

	@RequestMapping(value = "/reg/{key}", method = RequestMethod.GET)
	public String regOk(@PathVariable("key") String key,Model model) {

		
		if(userservice.getFindByKey(key))
		{
			return "redirect:/login?status=ok";
		}
		return "redirect:/login?status=error";
	}
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user, HttpServletRequest request) {

		int status = control(user);
		if (status != 0) {
			return new ResponseEntity<>(status +"", HttpStatus.OK);
		}
		
           if(userservice.insert(user).equals(1l))
           {
        		return new ResponseEntity<>("OK", HttpStatus.CREATED);
        		
           }
	
		return new ResponseEntity<>("ERROR", HttpStatus.CREATED);
	}

	private int control(User user) {
		if (!user.getPass2().equals(user.getPassword())) {
			return 1;
		}
		return 0;

	}

}
