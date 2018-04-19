package controller;

import java.util.ArrayList;
import java.util.List;
import model.Medication;
import service.MedicationService;
import view.ChemistView;

public class ChemistController {
	
	private ChemistView chemistView;
	private MedicationService medService = new MedicationService();
	private static final String ERROR_MSG_STOCK = "Insufficient stock";
	private static final String ERROR_MSG = "Wrong input";
	
	public ChemistController(ChemistView chemistView) {
		this.chemistView = chemistView;
	}
	
	public void btnSearchMedByNameClicked() {	
		// get the searched medication from the specific field from view
		String medName = chemistView.getSearchField(); 
		// get the medications with that name
		Medication med = medService.getMedicationByName(medName);
		if (med == null) { // if the returned value is null, tell the view to display an error message
			chemistView.displayError("The medication doesn't exist");
		} else { // if not tell the view to display the medication's data
			List<Object[]> dataList = new ArrayList<>();
			Object[] data = {med.getId(), med.getName(), med.getIngredients(), med.getManufacturer(), med.getQuantity(), med.getPrice()};
			dataList.add(data);
			chemistView.displayMedications(dataList);
		}		
	}
	
	public void btnSearchMedByIngredientsClicked() {
		String searchedIngredients = chemistView.getSearchField();
		Medication med = medService.getMedicationByIngredients(searchedIngredients);
		if (med == null) {
			chemistView.displayError("Nothing found");
		} else {
			List<Object[]> dataList = new ArrayList<>();
			Object[] data = {med.getId(), med.getName(), med.getIngredients(), med.getManufacturer(), med.getQuantity(), med.getPrice()};
			dataList.add(data);
			chemistView.displayMedications(dataList);
		}	
	}
	
	public void showMedications() {
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
		chemistView.displayMedications(dataList);
	}
	
	
	public void btnSellClicked() {
		String[] data = chemistView.getSellData();
		
		float totalPrice = medService.sell(data[0], data[1]);
		if (totalPrice != -1) {
			showMedications();
		} else {
			chemistView.displayError(ERROR_MSG_STOCK);
		}
	}
	

	public void btnPriceEnterPressed() {
		String[] data = chemistView.getSellData();
		
		float total = medService.calcTotalPrice(data[1], data[2]);
		if (total != -1) {
			chemistView.setTotalField(Float.toString(total));
		} else {
			chemistView.displayError(ERROR_MSG);
		}
			
	}
	
	public void RowDataSelected() {
		chemistView.getRowData();
	}

	
	
	
	
}
