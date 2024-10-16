package temp;

import java.util.Date;

public class GenerateEmailDemo {

	public static void main(String[] args) {
		
		Date date = new Date();
		String dateString = date.toString();
		String noSpaceDateString = dateString.replaceAll("\\s","");
		String noSpaceAndnoColonsDateString = noSpaceDateString.replaceAll("\\:","");
		String emailWithTimeStamp = noSpaceAndnoColonsDateString+"@gmail.com";
		System.out.println(emailWithTimeStamp);

	}

}
