package model;

public class User {
	
	private String id;
	private String name;
	private String username;
	private String password;
	private boolean isAdmin;
	
	public User() {}
	
	public User(String id, String name, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	public User(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}


	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getIsAdmin() {
		if (isAdmin == true) {
			return "true";
		} else {
			return "false";
		}
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;		
	}
	
	public void setIsAdmin(String isAdmin) {
		if (isAdmin.equals("true")) {
			this.isAdmin = true;
		} else this.isAdmin = false;	
	}
	

}
