package com.qa.enemybestiary.selenium.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.enemybestiary.selenium.pages.HomePage;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HomeTest {

	@LocalServerPort
	int randomPort;
	
	private static final String URL = "http://localhost:";
	
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.setHeadless(true); // we won't have the window popping up 
		driver = new ChromeDriver(option);
		driver.manage().window().setSize(new Dimension(1920, 1080));
	}
	
	@After
	public void tearDown() {
		driver.close();
	}
	
	@Test
	public void checkTitle() {
		driver.get(String.format("%s%d", URL, randomPort));
		
		assertEquals("Homepage", driver.getTitle());
	}
	
	@Test
	public void checkTextBox() {
		driver.get(String.format("%s%d", URL, randomPort));
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		
		String homeText = home.getHomeTextBox().getText();
		assertTrue(homeText.contains("fan page dedicated to the enemies"));
		
	}
	
	@Test
	public void checkButton() {
		driver.get(String.format("%s%d", URL, randomPort));
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		
		home.getHomeToCatalogueBtn().click();
		assertEquals("Catalogue", driver.getTitle());
	}
}
