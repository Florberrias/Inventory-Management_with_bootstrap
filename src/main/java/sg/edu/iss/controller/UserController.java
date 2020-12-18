package sg.edu.iss.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.edu.iss.model.RoleType;
import sg.edu.iss.model.User;
import sg.edu.iss.service.UserService;
import sg.edu.iss.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setUserService(UserServiceImpl userviceimpl) {
		this.uservice = userviceimpl;
	}
//................................................LOGIN/HOME/LOGOUT...................................................
	@RequestMapping(value="/login")
	public String login(Model model)
	{
		model.addAttribute("user",new User());
		return "login";
	}
	
	@RequestMapping(value="/authenticate",method = RequestMethod.POST)
	public String authenticate(@ModelAttribute("user") User user,Model model, HttpSession session) 
	{
		
		if(uservice.login(user.getUserName(), user.getPassword())!=null)
		{   User authUser = uservice.login(user.getUserName(), user.getPassword());
			String au_username = authUser.getUserName();
			String au_password= authUser.getPassword();
			if(au_username.equals(user.getUserName()) && au_password.equals(user.getPassword()))
				{
				
				if(authUser.getRoleType()==RoleType.ADMIN)
					{
						session.setAttribute("session", "admin");
						return "adminmain";
					}
				
				else if(authUser.getRoleType()==RoleType.MECHANIC)
					{
						session.setAttribute("session", "mechanic");
						return "mechanicmain";
					}
				
				else {
					model.addAttribute("error","Please enter correct username and password");
					return "forward:/user/login";}
				}

			else {
				model.addAttribute("error","Please enter correct username and password");
				return "forward:/user/login";
			}
			
		}
		else {
			model.addAttribute("error","Please enter correct username and password");
			return "forward:/user/login";
		}
	}
	
	@RequestMapping(value = "/home/admin")
	public String adminMain() {
		return "adminmain";
	}
	
	@RequestMapping(value = "/home/mechanic")
	public String mechanicMain() {
		return "mechanicmain";
	}
	
	@RequestMapping(value="/logout")
	public String logout(Model model, HttpSession session)
	{
		session.invalidate();
		return "logout";
	}
	
//....................................................CRUD...............................................
	@RequestMapping(value="/list")
	public String listUsers(Model model)
	
	{
		model.addAttribute("users", uservice.findAllUsers());
		return "users";
	}
	

	@RequestMapping(value = "/add")
	public String addUser(Model model)
	{
		model.addAttribute("user", new User());
		return "userform";
	}
	
	//for new user
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult,  Model model) 
	{   
		if (bindingResult.hasErrors())
		{
			
			return "userform";
		}
		
		if(uservice.findUserbyUserName(user.getUserName())!=null)
		{   
			model.addAttribute("duplicate", "The username already exists");
			return "userform";
			
		}
		if(uservice.findUserbyEmailAddress(user.getEmail())!=null)
		{
			model.addAttribute("duplicate", "The email already exists");
			return "userform";
		}
		else {
			uservice.saveUser(user);
			return "forward:/user/list";
		}
	}
	
    //for editing
	@RequestMapping(value = "/edit/{id}")
	public String editUser(@PathVariable("id") Integer id, Model model)
	{
		
		model.addAttribute("user", uservice.findUserbyId(id));
		return "userform1";
		
	}
	
	//for saving the edited
	@RequestMapping(value = "/save1", method = RequestMethod.POST)
	public String saveeditedUser(@ModelAttribute("user") @Valid User user,BindingResult bindingResult,  Model model) 
	{   
	    User previoususer=uservice.findUserbyId(user.getUserId());
	    User newuser=(User)model.getAttribute("user");
	    
		if (bindingResult.hasErrors())
		{
			
			return "userform1";
		}
		if(!previoususer.getEmail().equalsIgnoreCase(newuser.getEmail()))
		{
			if(uservice.findUserbyEmailAddress(user.getEmail())!=null)
			{
				model.addAttribute("duplicate", "The email already exists");
				return "userform1";
			}
			else 
			{
				
				uservice.saveUser(user);
				return "forward:/user/list";
			}
		}
		if(!previoususer.getUserName().equalsIgnoreCase(newuser.getUserName()))
		{
			if(uservice.findUserbyUserName(user.getUserName())!=null)
			{
				
				model.addAttribute("duplicate", "The username already exists");
				return "userform1";
			}
			else 
			{
				
				uservice.saveUser(user);
				return "forward:/user/list";
			}
		}
		else 
		{
			uservice.saveUser(user);
			return "forward:/user/list";
		}
	}
	


	@RequestMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {
		
		uservice.deleteUser(uservice.findUserbyId(id));  
		return "forward:/user/list";
	}
	
}
