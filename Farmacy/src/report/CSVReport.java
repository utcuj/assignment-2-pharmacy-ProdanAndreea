package report;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CSVReport implements Report {
	
private static final String FILE_PATH = "src\\medications.csv" ;
	
	public void generateReport(List<List<String>> dataList) {
		String csvFile = FILE_PATH;
		try {
	        FileWriter writer = new FileWriter(csvFile);
	        
	        //for header
	        CSVUtils.writeLine(writer, Arrays.asList("Id", "Name", "Ingredients", "Manufacturer", "Quantity", "Price"));
			
	       // for (Developer d : developers) {
	        for(List<String> data: dataList) { 
	
	            CSVUtils.writeLine(writer, data, '|');
	
				//try custom separator and quote. 
				//CSVUtils.writeLine(writer, list, '|', '\"');
	        }
	
	        writer.flush();
	        writer.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "ERROR writting the csv file");
			System.out.println(e.getMessage());
		}
	}

}
