package model;

public class Medication {
	
	private String id;
	private String name;
	private String ingredients;
	private String manufacturer;
	private String quantity;
	private String price;
	
	public Medication(String id, String name, String ingredients, String manufacturer, String quantity, String price) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Medication(String name, String ingredients, String manufacturer, String quantity, String price) {
		this.name = name;
		this.ingredients = ingredients;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
		this.price = price;
	}

	public Medication() {}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getQuantity() {
		return quantity;
	}
	
	
	public float sell(int quantity) {
		int remainedStock =  Integer.parseInt(this.quantity) - quantity;
		if (remainedStock < 0) { // not sufficient stock
			return -1;
		} else {
			// update the stock for the medication
			this.quantity = Integer.toString(remainedStock);
			
			float totalPrice = calcTotalPrice(quantity, Float.parseFloat(price));
			return totalPrice;
		}
	}
	
	public float calcTotalPrice(int quantity, float price) {
		return quantity * price;
	}
	
	
	
	

}
