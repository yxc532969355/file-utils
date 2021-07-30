package totxt;

import java.io.File;

public class ToText {

	public static void main(String[] args) {

		File rootFile = new File("C:\\Users\\developer\\git\\GuangFaDocument");
//		File rootFile = new File("C:\\Users\\developer\\git\\N00001-Cubiebaojiaxunjiaxitong");
		
//		WordToText wordToText= new WordToText();
//		wordToText.transform(rootFile);		
//
//		PdfToText pdfToText= new PdfToText();
//		pdfToText.transform(rootFile);
		
		ExcelToText excelToText = new ExcelToText();
		excelToText.transform(rootFile);
	}
	

}
