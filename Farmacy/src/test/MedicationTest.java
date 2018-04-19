package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Medication;
import service.MedicationService;

public class MedicationTest {

	private MedicationService medService = new MedicationService();
	
	@Test
	public void testAddMedication() {
		// test the quantity exception
		Medication med = new Medication(null, null, null, "12aa", "10");
		int i = medService.addMedication(med);
		assertEquals(i, -1);
		
		// test the price exception
		med = new Medication(null, null, null, "12", "10aa");
		i = medService.addMedication(med);
		assertEquals(i, -1);
	}
	
	@Test
	public void calcTotalPrice() {
		float tot = new Medication().calcTotalPrice(10, (float) 4.2); 
		assertEquals(tot, 42, 0.0001);
	}
	
	@Test
	public void testSell() {
		Medication med = new Medication(null, null, null, "100", "10");
		// if there is sufficient stock
		float tot = med.sell(8);
		assertEquals(med.getQuantity(), "92");
		assertEquals(tot, 80, 0.0001);
		// if there isn't
		tot = med.sell(1000);
		assertEquals(tot, -1, 0.0001);		
	}
	

}
