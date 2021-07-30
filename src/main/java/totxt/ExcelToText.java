
package totxt;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
 


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
 

public class ExcelToText extends AbstractXxxToText{

	@Override
	public void clean(File rootFile) {
		Util.iterateFiles(rootFile, new AbstractFileHandler() {
			
			@Override
			public boolean preCheck(File file) {
				boolean isOldTxt = file.getName().endsWith("xls.xxxToText.txt")||file.getName().endsWith("xlsx.xxxToText.txt");
				return isOldTxt;
			}
			
			@Override
			public void doHandle(File file) {
				file.delete();
			}
		});		
	}

	@Override
	public void doTransform(File file) {
		
		Util.iterateFiles(file, new AbstractFileHandler() {
			
			@Override
			public boolean preCheck(File file) {
				
				boolean notNormalFile = file.getName().startsWith("~");
				
				boolean isWord = file.getName().endsWith("xls") || file.getName().endsWith("xlsx");
				
				if (!isWord || notNormalFile) {
					return false;
				}

				return true;
			}

			@Override
			public void doHandle(File file) {
				try {
					
					FileInputStream fileInputStream = new FileInputStream(file);
					Workbook workbook = null;
					if (file.getName().endsWith("xls")) {
			            workbook = new HSSFWorkbook(fileInputStream);
			        } else if (file.getName().endsWith("xlsx")) {
			            workbook = new XSSFWorkbook(fileInputStream);
			        }	
					StringBuilder text = new StringBuilder("");
		            int sheetSize = workbook.getNumberOfSheets();
		            for(int i=0;i<sheetSize;i++) {
			            Sheet sheet = workbook.getSheetAt(i);

		            	text = text.append("===================sheet").append(i+"_"+sheet.getSheetName()).append("=====================\r\n");

			            Iterator<Row> rows = sheet.iterator();
			            while(rows.hasNext()) {
			            	Row row = rows.next();
			            	Iterator<Cell> cells = row.iterator();
			            	StringBuilder tempString = new StringBuilder("");
			            	while(cells.hasNext()) {
			            		Cell cell = cells.next();
			            		try {
									tempString.append(cell.getStringCellValue()).append(",");
								} catch (Exception e) {
									try {
										tempString.append(cell.getNumericCellValue()+"").append(",");
									} catch (Exception e1) {
										// ignore
									}
								}
			            	}
			            	text = text.append(tempString).append("\r\n");
			            }
		            }

					
					

					File outFile;
					
					if(file.getName().endsWith(".xls")) {
						 outFile = new File(file.getAbsolutePath().replace(".xls", ".xls.xxxToText.txt"));
					}else {
						 outFile = new File(file.getAbsolutePath().replace(".xlsx", ".xlsx.xxxToText.txt"));
					}
					FileWriter fileWriter = new FileWriter(outFile);
					fileWriter.write(text.toString());
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
	}

	}


