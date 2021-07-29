package totxt;

import java.io.File;

public class Util {

	public static void iterateFiles(File file,FileHandler fileHandler) {
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				iterateFiles(f, fileHandler);
			}
		}else {
			fileHandler.handle(file);
		}
	}

}
