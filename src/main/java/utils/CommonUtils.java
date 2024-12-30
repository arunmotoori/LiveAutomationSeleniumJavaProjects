package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtils {

	public static String generateBrandNewEmail() {
		return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "") + "@email.com";
	}

	public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) {
		BufferedImage acutualBImg = null;
		BufferedImage expectedBImg = null;
		try {
			acutualBImg = ImageIO.read(new File(System.getProperty("user.dir")+actualImagePath));
			expectedBImg = ImageIO.read(new File(System.getProperty("user.dir")+expectedImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);

		return imgDifference.hasDiff();

	}

	public static Properties loadProperties() {

		Properties prop = new Properties();
		try {
			FileReader fr = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties");
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	public static void setProperties(String propertyKey, String propertyValue, Properties prop) {

		prop.setProperty(propertyKey, propertyValue);
		FileWriter fr = null;
		try {
			fr = new FileWriter(System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties");
			prop.store(fr, "");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getTextFromMessage(Message message) throws Exception {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("text/html")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	public static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
		StringBuilder result = new StringBuilder();
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result.append(bodyPart.getContent());
			} else if (bodyPart.isMimeType("text/html")) {
				result.append(bodyPart.getContent());
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
			}
		}
		return result.toString();
	}

	public static WebDriver takeScreenshot(WebDriver driver, String pathToBCopied) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(srcScreenshot, new File(System.getProperty("user.dir") + pathToBCopied));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	public static String takeScreenshotAndReturnPath(WebDriver driver, String pathToBCopied) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		String destScreenshotPath = System.getProperty("user.dir") + pathToBCopied;
		try {
			FileHandler.copy(srcScreenshot, new File(destScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destScreenshotPath;
	}

	public static String validEmailRandomizeGenerator() {

		String[] validEmails = { "amotooricap1@gmail.com", "amotooricap2@gmail.com", "amotooricap3@gmail.com",
				"amotooricap4@gmail.com", "amotooricap5@gmail.com", "amotooricap6@gmail.com", "amotooricap7@gmail.com",
				"amotooricap8@gmail.com" };

		Random random = new Random();
		int index = random.nextInt(validEmails.length);

		return validEmails[index];
	}

	public static Object[][] getTestData(MyXLSReader xls_received, String testName, String sheetName) {

		MyXLSReader xls = xls_received;

		String testCaseName = testName;

		String testDataSheet = sheetName;

		int testStartRowNumber = 1;

		while (!(xls.getCellData(testDataSheet, 1, testStartRowNumber).equals(testCaseName))) {

			testStartRowNumber++;

		}

		int columnStartRowNumber = testStartRowNumber + 1;
		int dataStartRowNumber = testStartRowNumber + 2;

		int rows = 0;
		while (!(xls.getCellData(testDataSheet, 1, dataStartRowNumber + rows).equals(""))) {

			rows++;

		}

		// Total number of columns in the required test
		int columns = 1;

		while (!(xls.getCellData(testDataSheet, columns, columnStartRowNumber).equals(""))) {

			columns++;

		}

		Object[][] obj = new Object[rows][1];

		HashMap<String, String> map = null;

		// Reading the data in the test
		for (int i = 0, row = dataStartRowNumber; row < dataStartRowNumber + rows; row++, i++) {

			map = new HashMap<String, String>();

			for (@SuppressWarnings("unused")
			int j = 0, column = 1; column < columns; column++, j++) {

				String key = xls.getCellData(testDataSheet, column, columnStartRowNumber);

				String value = xls.getCellData(testDataSheet, column, row);

				map.put(key, value);

			}

			obj[i][0] = map;

		}

		return obj;

	}

	public static ExtentReports getExtentReport() {

		ExtentReports extentReport = new ExtentReports();

		File extentReportFile = new File(System.getProperty("user.dir") + "\\reports\\TNExtentReport.html");

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		ExtentSparkReporterConfig sparkConfig = sparkReporter.config();
		sparkConfig.setReportName("Tutorials Ninja Test Automation Results");
		sparkConfig.setDocumentTitle("TNER Results");

		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Selenium WebDriver Version", "4.24.0");

		return extentReport;

	}

}
