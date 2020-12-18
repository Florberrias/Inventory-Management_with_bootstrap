package sg.edu.iss.service;

import java.util.ArrayList;

import sg.edu.iss.model.User;

public interface UserService {
	 public boolean saveUser(User user);
	 public ArrayList<User> findAllUsers();
	 public void deleteUser(User user);
	 public User findUserbyId(Integer id);
	 public User findUserbyUserName(String username);
	 public User findUserbyEmailAddress (String email);
	 public User login(String username,String password);
}
