package sg.edu.iss.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.model.User;

import sg.edu.iss.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository urepo;


	@Override
	public boolean saveUser(User user) {
		if(urepo.save(user)!=null) 
			return true; 
		else return false;
		
	}

	@Override
	public ArrayList<User> findAllUsers() {
		return (ArrayList<User>) urepo.findAll();
	
	}

	@Transactional
	@Override
	public void deleteUser(User user) {
		
	urepo.delete(user);
	
	}

	@Override
	@Transactional
	public User findUserbyId(Integer id) {

		return urepo.findById(id).get();
	}

	@Override
	public User findUserbyUserName(String username) {
		return urepo.findByUserName(username);
	}

	@Override
	public User findUserbyEmailAddress(String email) {
		
		return urepo.findByEmail(email);
	}

	@Override
	public User login(String username, String password) {
		User user = urepo.findByUsernameAndPassword(username,password);
		return user;
	
	}
	
	

}
