package application;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFHighlighter;
import org.apache.pdfbox.util.PDFTextStripper;
public class HighlightApp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filename = "E:\\Downloads\\Documents\\Ha IR LSE.pdf";
		String xmlFilename = "E:\\Downloads\\Documents\\Connectors.xml";
		PDDocument pdfFile = PDDocument.load(filename);
		PDFHighlighter highlighter= new PDFHighlighter();
		Writer xmlOutput = new FileWriter(xmlFilename);
		highlighter.generateXMLHighlight(pdfFile, "Moreover", xmlOutput );
		PDFTextStripper textStripper = new PDFTextStripper();
		Pattern p = Pattern.compile("Moreover");
		Writer pdfInput = new StringWriter();
		textStripper.writeText(pdfFile, pdfInput);
		Matcher match = p.matcher(pdfInput.toString());
		
	}

}
