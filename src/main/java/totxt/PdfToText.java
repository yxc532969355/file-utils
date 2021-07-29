package totxt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class PdfToText extends AbstractXxxToText{
	
	@Override
	public void clean(File rootFile) {
		
		Util.iterateFiles(rootFile, new AbstractFileHandler() {
			
			@Override
			public boolean preCheck(File file) {
				boolean isOldTxt = file.getName().endsWith("pdf.xxxToText.txt");
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
				
				boolean isPdf = file.getName().endsWith(".pdf");
				
				if (!isPdf || notNormalFile) {
					return false;
				}

				return true;
			}

			@Override
			public void doHandle(File file) {
				try {

				       // 是否排序
			        boolean sort = false;
			        File pdfFile= file;
			        // 输入文本文件名称
			        String textFile = null;
			        // 编码方式
			        String encoding = "UTF-8";
			        // 开始提取页数
			        int startPage = 1;
			        // 结束提取页数
			        int endPage = Integer.MAX_VALUE;
			        // 文件输入流，生成文本文件
			        Writer output = null;
			        // 内存中存储的PDF Document
			        PDDocument document = null;

		            //注意参数是File。
		            document = PDDocument.load(pdfFile);

//			            // 以原来PDF的名称来命名新产生的txt文件
//			            textFile=fileStr.replace(".pdf",".txt");
					
		            File outFile = new File(file.getAbsolutePath().replace(".pdf", ".pdf.xxxToText.txt"));

		            // 文件输入流，写入文件倒textFile
		            output = new OutputStreamWriter(new FileOutputStream(outFile));
		            // PDFTextStripper来提取文本
		            PDFTextStripper stripper = null;
		            stripper = new PDFTextStripper();
		            // 设置是否排序
//			            stripper.setSortByPosition(sort);
		            // 设置起始页
		            stripper.setStartPage(startPage);
		            // 设置结束页
		            stripper.setEndPage(endPage);
		            // 调用PDFTextStripper的writeText提取并输出文本
		            stripper.writeText(document, output);
		            
		            output.close();
		            document.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

}
