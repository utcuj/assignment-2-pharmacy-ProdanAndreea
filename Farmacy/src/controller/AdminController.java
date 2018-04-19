package controller;

import java.util.ArrayList;
import java.util.List;
import model.Medication;
import model.User;
import report.Report;
import report.ReportFactory;
import service.MedicationService;
import service.UserService;
import view.AdminView;

public class AdminController {
	
	private AdminView adminView;
	private UserService userService = new UserService();
	private ReportFactory reportFactory = new ReportFactory();
	private MedicationService medService = new MedicationService();
	private final static String ERROR_MSG = "Wrong input";
	
	public AdminController(AdminView adminView) {
		this.adminView = adminView;	
	}
	
	///////////////////////// CHEMIST /////////////////////////////////
	
	/* gets the users using dom and return back the data by calling a method from view to display it
	 * in this way AdminView will not know about User model
	*/
	public void btnShowChemistsClicked() {
		
		List<User> userList = userService.getChemists();
		List<Object[]> dataList = new ArrayList<>();
		
		for (int i = 0; i < userList.size(); i++){
			   String id = userList.get(i).getId();
			   String username = userList.get(i).getUsername();
			   String password = userList.get(i).getPassword();
			   String name = userList.get(i).getName();
			   
			   Object[] data = {id, username, password, name};	
			   dataList.add(data);
		}
		
		// tell the view to display the data from dataList
		adminView.displayChemists(dataList);	
	}
	
	/* button for adding a new chemist have been clicked */
	public void btnAddClicked() { 
		List<String> data = adminView.getChemistData(); 
		User user = new User(data.get(1), data.get(2), data.get(3)); // the id isn't needed
		user.setIsAdmin(false);
		userService.Add(user);
		
		btnShowChemistsClicked(); // to refresh the table content
	}

	public void btnUpdateChemistClicked() {
		List<String> data = adminView.getChemistData(); 
		User user = new User(data.get(0), data.get(1), data.get(2), data.get(3)); 
		user.setIsAdmin(false);
		userService.Update(user);
		
		btnShowChemistsClicked();	// to refresh the table content
	}
	
	public void btnDeleteChemistClicked() {
		List<String> data = adminView.getChemistData();
		String id = data.get(0);
		userService.Delete(id);
		btnShowChemistsClicked();	// to refresh the table content
	}
	
	//////////////////////MEDICATION /////////////////////////////////
	
	
	public void btnShowMedicationsClicked() {
		// get the medications
		List<Medication> medList = medService.getMedications();
		List<Object[]> dataList = new ArrayList<>();
		for (int i = 0; i < medList.size(); i++){
			   String id = medList.get(i).getId();
			   String name = medList.get(i).getName();
			   String ingredients = medList.get(i).getIngredients();
			   String manufacturer = medList.get(i).getManufacturer();
			   String quantity = medList.get(i).getQuantity();
			   String price = medList.get(i).getPrice();
			   
			   Object[] data = {id, name, ingredients, manufacturer, quantity, price};	
			   dataList.add(data);
		}
		// tell the view to display the data from dataList
		adminView.displayMedications(dataList);	
	}
	
	/* add a new medication */
	public void btnAddMedicationClicked() { 
		List<String> data = adminView.getMedicationData();
		Medication med = new Medication(data.get(1), data.get(2), data.get(3), data.get(4), data.get(5));
		
		int success = medService.addMedication(med);
		if (success == -1) { 
			adminView.displayError(ERROR_MSG);
		} else {
			btnShowMedicationsClicked();
		}	
	}
	
	public void btnUpdateMedicationClicked() {
		List<String> data = adminView.getMedicationData(); System.out.println(data.get(0) + " " + data.get(1) + " " + data.get(2) + " " + data.get(3) + " " + data.get(4) + " " + data.get(5));
		Medication med = new Medication(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5));
		int success = medService.updateMedication(med);
		if (success == -1) { 
			adminView.displayError(ERROR_MSG);
		} else {
			btnShowMedicationsClicked(); // to refresh the table content
		}	
	}
		
	public void btnDeleteMedicationClicked() {
		List<String> data = adminView.getMedicationData();
		String id = data.get(0);
		medService.deleteMedication(id);
		btnShowMedicationsClicked();	// to refresh the table content
	}
		
	public void btnOutOfStockClicked() {
		List<Medication> medList = medService.getMedicationsOutOfStock();
		List<Object[]> dataList = new ArrayList<>();
		for (int i = 0; i < medList.size(); i++){
			if (medList.get(i).getQuantity().equals("0")) {	
				   String id = medList.get(i).getId();
				   String name = medList.get(i).getName();
				   String ingredients = medList.get(i).getIngredients();
				   String manufacturer = medList.get(i).getManufacturer();
				   String quantity = medList.get(i).getQuantity();
				   String price = medList.get(i).getPrice();
				   
				   Object[] data = {id, name, ingredients, manufacturer, quantity, price};
				   dataList.add(data);
			}
		}
		
		// tell the view to display the data from dataList
		adminView.displayMedications(dataList);	
	}
		
	public void btnCSVClicked() {
		List<Medication> medList = medService.getMedications();
		List<List<String>> dataList = new ArrayList<>();
		for (int i = 0; i < medList.size(); i++){
			if (medList.get(i).getQuantity().equals("0")) {	
				   String id = medList.get(i).getId();
				   String name = medList.get(i).getName();
				   String ingredients = medList.get(i).getIngredients();
				   String manufacturer = medList.get(i).getManufacturer();
				   String quantity = medList.get(i).getQuantity();
				   String price = medList.get(i).getPrice();
				   
				   List<String> data = new ArrayList<String>();
				   data.add(id);
				   data.add(name);
				   data.add(ingredients);
				   data.add(manufacturer);
				   data.add(quantity);
				   data.add(price);
				   dataList.add(data);
			}
		}
		// generate the csv
		Report report = reportFactory.getReport("CSV");
		report.generateReport(dataList);		
	}

	
}
