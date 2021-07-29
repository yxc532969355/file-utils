package totxt;

import java.io.File;

public abstract class AbstractFileHandler implements FileHandler{

	@Override
	public void handle(File file) {
		if(preCheck(file)) {
			doHandle(file);
		}
	}

	
}
