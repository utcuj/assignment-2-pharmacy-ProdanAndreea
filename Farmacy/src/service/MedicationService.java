package service;

import java.util.List;
import dom.MedicationDOM;
import model.Medication;
import validator.NumberValidator;
import validator.ShortValidator;
import validator.Validator;

public class MedicationService {
	
	private MedicationDOM medicationDOM = new MedicationDOM();
	private Validator<String> numberValidator = new NumberValidator();
	private Validator<String> shortValidator = new ShortValidator();
	
	public MedicationService() {}
	
	// get the mediactions and put them in a lsit of objects
	public List<Medication> getMedications() {
		List<Medication> medList = medicationDOM.getMedications();	
		return medList;
	}
	
	
	public Medication getMedicationByName(String medName) {
		Medication med = medicationDOM.getMedicationByName(medName);
		return med;
	}
	
	public Medication getMedicationByIngredients(String searchedIngredients) {
		return medicationDOM.getMedicationByIngredients(searchedIngredients);
	}
	
	public int addMedication(Medication med) {		
		try {		
			numberValidator.validate(med.getPrice());
			numberValidator.validate(med.getQuantity());
		} catch (Exception e) {
			return -1;
		}
		medicationDOM.Add(med);
		return 0;
	}
	
	public int updateMedication(Medication med) {
		try {
			shortValidator.validate(med.getId());
			numberValidator.validate(med.getPrice());
			numberValidator.validate(med.getQuantity());
		} catch (Exception e) {
			return -1;
		}
		medicationDOM.Update(med);
		return 0;
	}
	
	public int deleteMedication(String id) {
		try {
			shortValidator.validate(id);
		} catch (Exception e) {
			return -1;
		}
		medicationDOM.Delete(id);
		return 0;
	}
	
	public List<Medication> getMedicationsOutOfStock() {
		List<Medication> medList = medicationDOM.getMedications();
		return medList;
	}
	
	public float sell(String medName, String quantity) {	
		try {
			numberValidator.validate(quantity);
		} catch (Exception e) {
			return -1;
		}
		
		int quantityInt = Integer.parseInt(quantity);
		
		Medication med = medicationDOM.getMedicationByName(medName);
		float totalPrice = med.sell(quantityInt);
		
		// update in xml
		medicationDOM.Update(med);
		
		return totalPrice;	
	}
	
	public float calcTotalPrice(String quantity, String price) {
		try {
			numberValidator.validate(quantity);
			numberValidator.validate(price);
		} catch (Exception e) {
			return -1;
		}	
		
		int quantityInt = Integer.parseInt(quantity);
		float priceFloat = Float.parseFloat(price);
		
		return new Medication().calcTotalPrice(quantityInt, priceFloat);
	}
	
	
	

}
