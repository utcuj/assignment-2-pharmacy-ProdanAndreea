package service;

import java.util.List;

import dom.UserDOM;
import model.User;

public class UserService {
	
	private UserDOM userDOM = new UserDOM();
	
	public List<User> getChemists() {
		List<User> userList = userDOM.getChemists();
		return userList;
	}
	
	public void Add(User user) {
		userDOM.Add(user);
	}
	
	public void Update(User user) {
		userDOM.Update(user);
	}
	
	public void Delete(String id) {		
		userDOM.Delete(id);
	}
	
	public String signIn(String givenUsername, String givenPassword) {
		User user = userDOM.getUser(givenUsername, givenPassword);
		if (user == null) {
			return null;
		}
		return user.getIsAdmin();
	}
	
	
	
	
}
