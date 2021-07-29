package totxt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordToText extends AbstractXxxToText{

	@Override
	public void clean(File rootFile) {
		
		Util.iterateFiles(rootFile, new AbstractFileHandler() {
			
			@Override
			public boolean preCheck(File file) {
				boolean isOldTxt = file.getName().endsWith("doc.xxxToText.txt")||file.getName().endsWith("docx.xxxToText.txt");
				return isOldTxt;
			}
			
			@Override
			public void doHandle(File file) {
				file.delete();
			}
		});
	}

	
	
	@Override
	public void doTransform(File rootFile) {
		
		Util.iterateFiles(rootFile, new AbstractFileHandler() {
			
			@Override
			public boolean preCheck(File file) {
				
				boolean notNormalFile = file.getName().startsWith("~");
				
				boolean isWord = file.getName().endsWith("doc") || file.getName().endsWith("docx");
				
				if (!isWord || notNormalFile) {
					return false;
				}

				return true;
			}

			@Override
			public void doHandle(File file) {
				try {
					BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
					String text = null;

					if (FileMagic.valueOf(is) == FileMagic.OLE2) {
						WordExtractor ex = new WordExtractor(is);
						text = ex.getText();
						ex.close();

					} else if (FileMagic.valueOf(is) == FileMagic.OOXML) {
						XWPFDocument doc = new XWPFDocument(is);
						XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
						text = extractor.getText();
						extractor.close();
					}
					
					File outFile;
					
					if(file.getName().endsWith(".doc")) {
						 outFile = new File(file.getAbsolutePath().replace(".doc", ".doc.xxxToText.txt"));
					}else {
						 outFile = new File(file.getAbsolutePath().replace(".docx", ".docx.xxxToText.txt"));
					}
					FileWriter fileWriter = new FileWriter(outFile);
					fileWriter.write(text);
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
	}

}
