package totxt;

import java.io.File;

public abstract class AbstractXxxToText implements XxxToText{
	
	public void transform(File file) {
		this.clean(file);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.doTransform(file);
	}

}
