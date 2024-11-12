package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtils {
	
	public static String generateBrandNewEmail() {
		return new Date().toString().replaceAll("\\s","").replaceAll("\\:","")+"@email.com";
	}
	
    public static boolean compareTwoScreenshots(String actualImagePath,String expectedImagePath) throws IOException {
		
		BufferedImage acutualBImg = ImageIO.read(new File(actualImagePath));
		BufferedImage expectedBImg = ImageIO.read(new File(expectedImagePath));
		
		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);
		
		return imgDifference.hasDiff();
		
	}
	

}
