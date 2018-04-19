package dom;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import model.Medication;

public class MedicationDOM {
	
	private static final String FILE_PATH = "src\\data\\medications.xml";

	public void Add(Medication med) {
		String nextId;
		try{
			Document doc = DOMHelper.getDocument(FILE_PATH);
			Element medications = doc.getDocumentElement();
			
			// create medication tag
			Element medicationEl = doc.createElement("medication");
			
			// create id tag
			Element idEl = doc.createElement("id");
			// calculate the next id
			NodeList nl = doc.getElementsByTagName("medication"); 
			nextId = Integer.toString(nl.getLength() + 1);
			// add it
			idEl.appendChild(doc.createTextNode(nextId));
			medicationEl.appendChild(idEl);
			
			// create name tag
			Element nameEl = doc.createElement("name");
			nameEl.appendChild(doc.createTextNode(med.getName()));
			medicationEl.appendChild(nameEl);	
			
			// create ingredients tag 
			Element ingredientEl = doc.createElement("ingredients");
			ingredientEl.appendChild(doc.createTextNode(med.getIngredients()));
			medicationEl.appendChild(ingredientEl);
			
			// create manufacturer tag
			Element manufacturerEl = doc.createElement("manufacturer");
			manufacturerEl.appendChild(doc.createTextNode(med.getManufacturer()));
			medicationEl.appendChild(manufacturerEl);
			
			// create quantity tag
			Element quantityEl = doc.createElement("quantity");
			quantityEl.appendChild(doc.createTextNode(med.getQuantity()));
			medicationEl.appendChild(quantityEl);
			
			// create price tag
			Element priceEl = doc.createElement("price");
			priceEl.appendChild(doc.createTextNode(med.getPrice()));
			medicationEl.appendChild(priceEl);
					
			medications.appendChild(medicationEl);
			
			// write to file
			DOMHelper.saveXMLContent(doc, FILE_PATH);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
				
	public void Delete(String id) {
		try{
			Document doc = DOMHelper.getDocument(FILE_PATH);
			NodeList nl = doc.getElementsByTagName("medication");
			for(int i=0; i<nl.getLength(); i++) {
				Element medEl = (Element) nl.item(i);
				if(medEl.getElementsByTagName("id").item(0).getTextContent().equals(id)) {
					medEl.getParentNode().removeChild(medEl);
				}
			}
			// write to file
			DOMHelper.saveXMLContent(doc, FILE_PATH);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	public void Update(Medication med) {
		try{
			Document doc = DOMHelper.getDocument(FILE_PATH);
			NodeList nl = doc.getElementsByTagName("medication");
			for(int i=0; i<nl.getLength(); i++) {
				Element medEl = (Element) nl.item(i);
				if(medEl.getElementsByTagName("id").item(0).getTextContent().equals(med.getId())) {
					medEl.getElementsByTagName("name").item(0).setTextContent(med.getName());
					medEl.getElementsByTagName("ingredients").item(0).setTextContent(med.getIngredients());
					medEl.getElementsByTagName("manufacturer").item(0).setTextContent(med.getManufacturer());	
					medEl.getElementsByTagName("quantity").item(0).setTextContent(med.getQuantity());	
					medEl.getElementsByTagName("price").item(0).setTextContent(med.getPrice());	
				}
			}
			// write to file
			DOMHelper.saveXMLContent(doc, FILE_PATH);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}


	public List<Medication> getMedications() {
		List<Medication> medList = new ArrayList<Medication>();
		try{
			Document doc = DOMHelper.getDocument(FILE_PATH);
			NodeList nl = doc.getElementsByTagName("medication");
			
			for(int i=0; i<nl.getLength(); i++) {
				Element medEl = (Element) nl.item(i);
				
				String id = medEl.getElementsByTagName("id").item(0).getTextContent();  
				String name = medEl.getElementsByTagName("name").item(0).getTextContent(); 
				String ingredients = medEl.getElementsByTagName("ingredients").item(0).getTextContent();  
				String manufacturer = medEl.getElementsByTagName("manufacturer").item(0).getTextContent(); 
				String quantity = medEl.getElementsByTagName("quantity").item(0).getTextContent();
				String price = medEl.getElementsByTagName("price").item(0).getTextContent();				
						 
				Medication med = new Medication(id, name, ingredients, manufacturer, quantity, price);  
				medList.add(med);		
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return medList;
	}
	
	public Medication getMedicationByName(String medName) { 
		Medication med = null;
		try{ 
			Document doc = DOMHelper.getDocument(FILE_PATH);  
			NodeList nl = doc.getElementsByTagName("medication");			
			
			boolean found = false;
			for(int i=0; i<nl.getLength() && found == false; i++) { 
				Element medEl = (Element) nl.item(i);
				
				if (medEl.getElementsByTagName("name").item(0).getTextContent().equals(medName)) {  
					found = true;
					
					String id = medEl.getElementsByTagName("id").item(0).getTextContent();  
					String name = medEl.getElementsByTagName("name").item(0).getTextContent(); 
					String ingredients = medEl.getElementsByTagName("ingredients").item(0).getTextContent();  
					String manufacturer = medEl.getElementsByTagName("manufacturer").item(0).getTextContent(); 
					String quantity = medEl.getElementsByTagName("quantity").item(0).getTextContent();
					String price = medEl.getElementsByTagName("price").item(0).getTextContent();				
	
					med = new Medication(id, name, ingredients, manufacturer, quantity, price);   
				}
			}
								
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return med;
	}

	
	public Medication getMedicationByIngredients(String searchedIngredients) { 
		Medication med = null;
		try{ 
			Document doc = DOMHelper.getDocument(FILE_PATH);  
			NodeList nl = doc.getElementsByTagName("medication");			
			
			boolean found = false;
			for(int i=0; i<nl.getLength() && found == false; i++) { 
				Element medEl = (Element) nl.item(i);
				
				if (medEl.getElementsByTagName("ingredients").item(0).getTextContent().contains(searchedIngredients)) {  
					found = true;
					
					String id = medEl.getElementsByTagName("id").item(0).getTextContent();  
					String name = medEl.getElementsByTagName("name").item(0).getTextContent(); 
					String ingredients = medEl.getElementsByTagName("ingredients").item(0).getTextContent();  
					String manufacturer = medEl.getElementsByTagName("manufacturer").item(0).getTextContent(); 
					String quantity = medEl.getElementsByTagName("quantity").item(0).getTextContent();
					String price = medEl.getElementsByTagName("price").item(0).getTextContent();				
	
					med = new Medication(id, name, ingredients, manufacturer, quantity, price);   
				}
			}
								
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return med;
	}
	
}
