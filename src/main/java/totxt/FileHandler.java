package totxt;

import java.io.File;

public interface FileHandler {
	
   boolean preCheck(File file);	
	
   void handle(File file);

   void doHandle(File file);
   
}
