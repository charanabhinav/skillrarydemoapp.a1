package genericLibraries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	
	public class ListenerImplementation {

	}

	/**
	 * this class contains reusable methods of java
	 * @author abhi
	 * @return
	 */
	public int generateRandomNum(int limit) {
		Random random = new Random();
		return random.nextInt(limit);
		
	}
	
	/**
	 * this method generates current time
	 * @return
	 */
	public String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_mm_yy_hh_mm_ss");
		return sdf.format(date) ;
		
	}
	

}
