//package totxt;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.converter.WordToTextConverter;
//import org.apache.poi.hwpf.extractor.WordExtractor;
//import org.apache.poi.poifs.filesystem.FileMagic;
//import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//
//public class WordToText2 {
//
//	public static void main(String[] args) {
//
//		File rootFile = new File("C:\\Users\\developer\\git\\GuangFaDocument\\接口文档");
//
//		Util.iterateFiles(rootFile, new FileHandler() {
//
//			@Override
//			public void handle(File file) {
//				boolean notNormalFile = file.getName().startsWith("~");
//				
//				boolean isWord = file.getName().endsWith("doc") || file.getName().endsWith("docx");
//				if (!isWord || notNormalFile) {
//					return;
//				}
//
//				try {
//					BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
//					String text = null;
//
//					if (FileMagic.valueOf(is) == FileMagic.OLE2) {
//						WordExtractor ex = new WordExtractor(is);
//						text = ex.getText();
//						ex.close();
//
//					} else if (FileMagic.valueOf(is) == FileMagic.OOXML) {
//						XWPFDocument doc = new XWPFDocument(is);
//						XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
//						text = extractor.getText();
//						extractor.close();
//					}
//					
//					File outFile;
//					
//					if(file.getName().endsWith(".doc")) {
//						 outFile = new File(file.getAbsolutePath().replace(".doc", ".doc.xxxToText.txt"));
//					}else {
//						 outFile = new File(file.getAbsolutePath().replace(".docx", ".docx.xxxToText.txt"));
//					}
////					BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
//					FileWriter fileWriter = new FileWriter(outFile);
//					fileWriter.write(text);
//					fileWriter.close();
//					System.out.println(text);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
////				try {
////					InputStream is = new FileInputStream(file);
////					HWPFDocument wordDocument = new HWPFDocument(is);
////					WordToTextConverter converter = new WordToTextConverter(
////							DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
////					// 对HWPFDocument进行转换
////					converter.processDocument(wordDocument);
////					Writer writer = new FileWriter(new File(
////							file.getPath() + "/" + file.getName().replaceAll("docx", "txt").replaceAll("doc", "txt")));
////					Transformer transformer = TransformerFactory.newInstance().newTransformer();
////					transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
////					// 是否添加空格
////					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
////					transformer.setOutputProperty(OutputKeys.METHOD, "text");
////					transformer.transform(new DOMSource(converter.getDocument()), new StreamResult(writer));
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//
////		        WordExtractor extractor = new WordExtractor(); // 利用WordExtractor解析Word文档—读取Word文档中的文本
////		        try {
////		            String content = extractor.extractText(new FileInputStream(file));
////		            // 用于测试当前正在转换哪个文档
////		            System.out.println(file.getName() + "....." + content.length());
////	                BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()
////	                        + "/" + file.getName().replaceAll("docx", "txt").replaceAll("doc", "txt")));
////	                writer.write(content);
////	                writer.close();
////		        } catch (Exception e) {
////		            e.printStackTrace();
////		        }
//
//			}
//		});
//
//	}
//
//}
