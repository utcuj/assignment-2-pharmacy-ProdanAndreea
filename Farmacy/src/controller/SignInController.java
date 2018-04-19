package controller;

import service.UserService;
import view.AdminView;
import view.ChemistView;
import view.SignInView;

public class SignInController {
	
	private SignInView signInView;
	private UserService userService = new UserService();
	private AdminView adminView = new AdminView();
	private ChemistView chemistView = new ChemistView();
	
	public SignInController(SignInView signInView) {
		this.signInView = signInView;
	}
	
	public void btnSignInClicked() {
		String[] data = signInView.getSignInData();
		String givenUsername = data[0];
		String givenPassword = data[1];
		String type = userService.signIn(givenUsername, givenPassword);
		if (type != null) { // user found
			// close this window and open the user's specific window					
			if (type.equals("true")) {
				adminView.start();
				signInView.close();					
			} else if (type.equals("false")) {
				chemistView.start();
				signInView.close();
			}
		} else {
			signInView.displayError("The user doesn't exist");
		}
	}

}
