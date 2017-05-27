package hightlighting;

import java.io.File;
import java.io.FileInputStream;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;

public class App {

  static void annotateExample(String fileName) throws Exception {
    PDDocument pdDoc = null;
    File file = new File(fileName);

    if (!file.isFile()) {
      System.err.println("File " + fileName + " does not exist.");
      return;
    }
    
    PDFParser parser = new PDFParser(new FileInputStream(file));

    parser.parse();
    pdDoc = new PDDocument(parser.getDocument());
    
    PDFTextAnnotator pdfAnnotator = new PDFTextAnnotator("UTF-8"); // create new annotator
    pdfAnnotator.setLineSeparator(" "); // kinda depends on what you want to match
    pdfAnnotator.initialize(pdDoc);
    pdfAnnotator.highlight(pdDoc, "international");
    
    
    pdDoc.save("E:\\Downloads\\Documents\\Ha IR LSE3.pdf");
    try {
      if (parser.getDocument() != null) {
        parser.getDocument().close();
      }
      if (pdDoc != null) {
        pdDoc.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]) throws Exception {
    annotateExample("E:\\Downloads\\Documents\\Ha IR LSE2.pdf");
  }

}