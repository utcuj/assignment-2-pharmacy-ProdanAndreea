package validator;

/*
 * validates int, float, double
 */

public class NumberValidator implements Validator<String>{
	
	public void validate(String number) throws IllegalArgumentException {
		String message;
		if (!number.matches("^[-+]?[0-9]*\\.?[0-9]+$")) {
			message = "Wrong number format";
			//JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException(message);	
		}
	}

}
