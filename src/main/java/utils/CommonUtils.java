package utils;

import java.util.Date;

public class CommonUtils {
	
	public static String generateBrandNewEmail() {
		return new Date().toString().replaceAll("\\s","").replaceAll("\\:","")+"@email.com";
	}
	

}
