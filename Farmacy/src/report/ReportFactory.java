package report;

public class ReportFactory {

	public Report getReport(String reportType) {
		if (reportType == null) {
			return null;
		}
		
		if (reportType.equalsIgnoreCase("PDF")) {
			return new PDFReport();
		} else if (reportType.equalsIgnoreCase("CSV")) {
			return new CSVReport();
		}
		
		return null;
	}
}
