package com.amazon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.base.TestBase;
import com.amazon.util.TestUtil;


public class AmazonTask extends TestBase {
 

	TestUtil testUtil = new TestUtil();
	String sheetName = "Sheet1";
	
	
	public AmazonTask() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	@Test(priority=1, dataProvider="getLoginData")
	public void login(String username, String password) throws InterruptedException {
		
		System.out.println("Username= " +username+ "Password= "+password);
		Thread.sleep(2500);
				
		// Login disabled because Amazon uses link verification mechanism	
		
		
		/*
		// login details
		driver.findElement(By.cssSelector("#ap_email")).sendKeys(username);
		Thread.sleep(2500);
		driver.findElement(By.cssSelector("#continue")).click();
		Thread.sleep(2500);
		driver.findElement(By.cssSelector("#ap_password")).sendKeys(password);
		Thread.sleep(2500);		
		driver.findElement(By.cssSelector("#signInSubmit")).click();
		Thread.sleep(5500);
		*/
	}
	
	
	// -- This will read the login credentials --
	@DataProvider
	public Object[][] getLoginData()
	{
		Object data[][] = TestUtil.getLoginData(sheetName);
		return data;
	}
	
	// -- this method will get all the advertisement's title --
	@Test(priority = 2)
	public void getAdvertisement() {
		
		List<WebElement> adBox = driver.findElements(By.xpath("//div[contains(@id,'desktop-grid-')]"));
		
		//System.out.println(adBox.size());
		for(int i=1; i <= adBox.size();) {
			
			String header = driver.findElement(By.xpath("//div[@id='desktop-grid-"+ i +"']/div/div[1]/h2")).getText();
			System.out.println("Advertisement No."+ i +" is- "+header);
			i++;
		}
		
	}
	
	//-- This will store all the carousel images --
	@Test(priority=3)
	public void saveCarousel() throws IOException {
		
		WebElement listParent = driver.findElement(By.cssSelector("ol.a-carousel"));
		
		List<WebElement> Carousellists = listParent.findElements(By.tagName("li"));
		//System.out.println("Lenth is: "+Carousellists.size());
		//String[] imageList = new String[Carousellists.size()];
	
	
	
		for(int i=1; i<=Carousellists.size(); i++) {
		 
			String url = driver.findElement(By.xpath("//ol[@class='a-carousel']/li[" + i + "]/div/div/div/div/span/a/img")).getAttribute("src");
			 URL imageURL = new URL(url);
			 
			 BufferedImage saveImage = ImageIO.read(imageURL);
			 File outputfile = new File("Advertisement-"+i+".png");
			 
			 //saving image files
			 ImageIO.write(saveImage, "png", outputfile);
		} 
	
		
	}
	
}
