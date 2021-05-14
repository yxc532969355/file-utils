package dytt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import http.ApacheHttp;

public class GetDytt {

	static String outbase = "C:\\Users\\developer\\git\\file-utils\\dytt_output\\";
	static int start = 298;
	static int MAXMVCOUNT = 10000;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//cleanOutput();
		
		String urlBase = "https://www.ygdy8.net/html/gndy/oumei/";
		String htmlName = "";
		for (int i = start; i <= MAXMVCOUNT; i++) {
			htmlName = "list_7_"+i+".html";
			String str = ApacheHttp.doGet(urlBase + htmlName);
			boolean hasNext = parseStr(str, i+"");
			if(!hasNext) {
				break;
			}
		}
	}

	private static void cleanOutput() {
		File output  = new File(outbase);
		File[] files = output.listFiles();
 		for(File f : files) {
			f.delete();
		}
	}

	private static boolean parseStr(String str, String htmlName) throws IOException {
		Scanner scanner = null;
		PrintWriter pr = null;
		try {
			scanner = new Scanner(str);
			pr = new PrintWriter(
					new File("C:\\Users\\developer\\git\\file-utils\\dytt_output\\" + paddingStr(htmlName) + ".html"),
					"gb2312");
			while (scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				if (nextLine.contains("您的访问出错了")) {
					return false;
				} else {
					pr.println(nextLine);
				}
			}

			return true;
		} finally {
			pr.close();
			scanner.close();
	    }
	}
	
	static String paddingStr(String htmlName) {
		
		String temp = htmlName;
		
		while(temp.length()<4) {
			temp = "0"+temp;
		}
		return temp;
		
	}

}
