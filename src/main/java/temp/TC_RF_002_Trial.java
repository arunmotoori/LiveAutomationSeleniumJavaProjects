//package temp;
//
//import java.time.Duration;
//import java.util.Properties;
//
//import javax.mail.BodyPart;
//import javax.mail.Flags;
//import javax.mail.Folder;
//import javax.mail.Session;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.search.FlagTerm;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store;
//
//public class TC_RF_002_Trial {
//
//	public static void main(String[] args) throws InterruptedException {
//		
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.manage().window().maximize();
//		driver.get("https://www.amazon.in/");
//		
//		driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();
//		driver.findElement(By.xpath("//span[contains(text(),'Need help')]")).click();
//		driver.findElement(By.id("auth-fpp-link-bottom")).click();
//		String email = "tutorialsninjavideos@gmail.com";
//		String appPassCode = "fudc omti ftgs bqjj";
//		driver.findElement(By.id("ap_email")).sendKeys(email);
//		driver.findElement(By.id("continue")).click();
//	
//		// Gmail IMAP settings
//        String host = "imap.gmail.com";
//        String port = "993";
//        String username = email;
//        String password = appPassCode;  // Not recommended, use OAuth2 for better security
//       
//        try {
//            // Mail server connection properties
//            Properties properties = new Properties();
//            properties.put("mail.store.protocol", "imaps");
//            properties.put("mail.imap.host", host);
//            properties.put("mail.imap.port", port);
//            properties.put("mail.imap.ssl.enable", "true");
//           
//            // Connect to the mail server
//            Session emailSession = Session.getDefaultInstance(properties);
//            Store store = emailSession.getStore("imaps");
//            store.connect("imap.gmail.com", username, password); // replace email password with App password
//            
//            // Open the inbox folder
//            Folder inbox = store.getFolder("INBOX");
//            inbox.open(Folder.READ_ONLY);
//            
//            // Search for unread emails
//            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
//            
//            boolean found = false;
//            for (int i = messages.length - 1; i >= 0; i--) {
//            	
//            	Message message = messages[i];
//            
//                if (message.getSubject().contains("amazon.in: Password recovery")) {
//                    found = true;
//                    System.out.println("Email Subject: " + message.getSubject());
//                    System.out.println("Email From: " + message.getFrom()[0].toString());
//                    System.out.println("Email Body: " + getTextFromMessage(message));
//                    break;
//                }
//            }
//
//            if (!found) {
//                System.out.println("No confirmation email found.");
//            }
//
//            // Close the store and folder objects
//            inbox.close(false);
//            store.close();
//
//        }catch(Exception e) {
//        	e.printStackTrace();
//        }
//       
//	}
//
//	 private static String getTextFromMessage(Message message) throws Exception {
//	        String result = "";
//	        if (message.isMimeType("text/plain")) {
//	            result = message.getContent().toString();
//	        } else if (message.isMimeType("text/html")) {
//	            result = message.getContent().toString();
//	        } else if (message.isMimeType("multipart/*")) {
//	            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
//	            result = getTextFromMimeMultipart(mimeMultipart);
//	        }
//	        return result;
//    }
//	 
//	 private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
//	        StringBuilder result = new StringBuilder();
//	        int count = mimeMultipart.getCount();
//	        for (int i = 0; i < count; i++) {
//	            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
//	            if (bodyPart.isMimeType("text/plain")) {
//	                result.append(bodyPart.getContent());
//	            } else if (bodyPart.isMimeType("text/html")) {
//	                result.append(bodyPart.getContent());
//	            } else if (bodyPart.getContent() instanceof MimeMultipart) {
//	                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
//	            }
//	        }
//	        return result.toString();
//	 }
//
//}
