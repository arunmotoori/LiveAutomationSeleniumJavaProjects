package temp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class VerifySessionPersistence {
    public static void main(String[] args) {
       
        // Path to Chrome's User Data directory
        String userDataDir = "C:\\Users\\arunm\\AppData\\Local\\Google\\Chrome\\User Data"; // Replace with actual path

        // Chrome profile you want to use
        String profileName = "Profile 2"; // Replace with your actual profile name

        // ChromeOptions to specify the user profile
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + userDataDir);  // Path to User Data
        options.addArguments("profile-directory=" + profileName);  // Specify profile

        // Step 2: Log in and close the browser
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open login page (Replace with your app's URL)
            driver.get("https://tutorialsninja.com/demo/");  // Change to your app URL
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
       
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 3: Close the browser without logging out
            driver.quit();
            System.out.println("Browser closed without logging out.");
        }

        // Step 4: Reopen the browser to verify session persistence
        WebDriver driver2 = new ChromeDriver(options);

        try {
            // Re-open the app URL (should retain the session from the previous session)
            driver2.get("https://tutorialsninja.com/demo/");  // Change to your app URL
            driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

          
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the second browser instance
            driver2.quit();
            System.out.println("Test completed!");
        }
    }
}
