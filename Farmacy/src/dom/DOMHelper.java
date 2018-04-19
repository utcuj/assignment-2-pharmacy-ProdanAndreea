package dom;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class DOMHelper {
	
	public static Document getDocument(String path_to_file) {
		Document doc = null;
		
		try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				doc = db.parse(path_to_file);		
		} catch(Exception ex) {
			doc = null;
		}
		return doc;		
	}
	
	public static String getXMLContent(Document d) {
		String result = "";
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			StringWriter sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			DOMSource source = new DOMSource(d);
			tf.transform(source, sr);
			result = sw.toString();
			
		} catch(Exception ex) {
			result = "";
		}
		return result;
	}
	
	public static void saveXMLContent(Document d, String path_to_file) {
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource ds = new DOMSource(d);
			StreamResult sr = new StreamResult(path_to_file);
			tf.transform(ds, sr);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}

}
