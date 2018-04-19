package dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;

import model.User;

public class UserDOM {
	
	private static final String FILE_PATH = "src\\data\\users.xml";

	public void Add(User user) {
		String nextId;
		try{
			Document doc = DOMHelper.getDocument(FILE_PATH);
			Element users = doc.getDocumentElement();
			
			// create user tag
			Element userEl = doc.createElement("user");
			
			// create id tag
			Element idEl = doc.createElement("id");
			// calculate the next id
			NodeList nl = doc.getElementsByTagName("user"); 
			nextId = Integer.toString(nl.getLength() + 1);
			// add it
			idEl.appendChild(doc.createTextNode(nextId));
			userEl.appendChild(idEl);
			
			// create name tag
			Element nameEl = doc.createElement("name");
			nameEl.appendChild(doc.createTextNode(user.getName()));
			userEl.appendChild(nameEl);
			
			// create username tag
			Element usernameEl = doc.createElement("username");
			usernameEl.appendChild(doc.createTextNode(user.getUsername()));
			userEl.appendChild(usernameEl);
			
			// create password tag
			Element passwordEl = doc.createElement("password");
			passwordEl.appendChild(doc.createTextNode(user.getPassword()));
			userEl.appendChild(passwordEl);
			
			// create password tag
			Element isAminEl = doc.createElement("isAdmin");
			isAminEl.appendChild(doc.createTextNode(user.getIsAdmin()));
			userEl.appendChild(isAminEl);
			
			users.appendChild(userEl);
			
			// write to file
			DOMHelper.saveXMLContent(doc, FILE_PATH);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void Delete(String id) {
		try{
			Document doc = DOMHelper.getDocument(FILE_PATH);
			NodeList nl = doc.getElementsByTagName("user");
			for(int i=0; i<nl.getLength(); i++) {
				Element userEl = (Element) nl.item(i);
				if(userEl.getElementsByTagName("id").item(0).getTextContent().equals(id)) {
					userEl.getParentNode().removeChild(userEl);
				}
			}
			// write to file
			DOMHelper.saveXMLContent(doc, FILE_PATH);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	public void Update(User user) {
		try{
			Document doc = DOMHelper.getDocument(FILE_PATH);
			NodeList nl = doc.getElementsByTagName("user");
			for(int i=0; i<nl.getLength(); i++) {
				Element userEl = (Element) nl.item(i);
				if(userEl.getElementsByTagName("id").item(0).getTextContent().equals(user.getId())) {
					userEl.getElementsByTagName("name").item(0).setTextContent(user.getName());
					userEl.getElementsByTagName("username").item(0).setTextContent(user.getUsername());
					userEl.getElementsByTagName("password").item(0).setTextContent(user.getPassword());	
				}
			}
			// write to file
			DOMHelper.saveXMLContent(doc, FILE_PATH);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	/* return a list containing the chemists */
	public List<User> getChemists() {
		List<User> userList = new ArrayList<User>();
		try{
			Document doc = DOMHelper.getDocument(FILE_PATH);
			NodeList nl = doc.getElementsByTagName("user");
	
			for(int i=0; i<nl.getLength(); i++) {
				Element userEl = (Element) nl.item(i);	

				if (userEl.getElementsByTagName("isAdmin").item(0).getTextContent().equals("false")) { // add the user to the list just if it is a chemist
					String id = userEl.getElementsByTagName("id").item(0).getTextContent();
					String name = userEl.getElementsByTagName("name").item(0).getTextContent();
					String username = userEl.getElementsByTagName("username").item(0).getTextContent();
					String password = userEl.getElementsByTagName("password").item(0).getTextContent();
							
					User user = new User(id, name, username, password); System.out.println(id);
					userList.add(user);	
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return userList;
	}
	
	
	public User getUser(String givenUsername, String givenPassword) {
		User user = null;
		try{
			Document doc = DOMHelper.getDocument(FILE_PATH);
			NodeList nl = doc.getElementsByTagName("user");

			for(int i=0; i<nl.getLength(); i++) {
				Element userEl = (Element) nl.item(i);	
				String username = userEl.getElementsByTagName("username").item(0).getTextContent();
				String password = userEl.getElementsByTagName("password").item(0).getTextContent();
				if (username.equals(givenUsername) && password.equals(givenPassword)) {
					String id = userEl.getElementsByTagName("id").item(0).getTextContent();
					String name = userEl.getElementsByTagName("name").item(0).getTextContent();
					
					user = new User(id, name, username, password); System.out.println(id);	
					String isAdmin =  userEl.getElementsByTagName("isAdmin").item(0).getTextContent();
					user.setIsAdmin(isAdmin);
					return user;
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
