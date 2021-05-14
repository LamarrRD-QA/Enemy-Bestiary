package com.qa.enemybestiary.selenium.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.enemybestiary.selenium.pages.WebsiteHeader;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HeaderTest {

	@LocalServerPort
	int randomPort;

	private static final String URL = "http://localhost:";

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
//		option.setHeadless(true);
		driver = new ChromeDriver(option);
		driver.manage().window().setSize(new Dimension(1920, 1080));
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void checkImgBannerSuccess() {
		driver.get(String.format("%s%d/profile.html", URL, randomPort));
		WebsiteHeader header = PageFactory.initElements(driver, WebsiteHeader.class);

		String src = header.getHeaderBanner().getAttribute("src");

		assertTrue(src.contains("header_banner"));
	}

	@Test
	public void checkImgBannerFailure() {
		driver.get(String.format("%s%d/profile.html", URL, randomPort));
		WebsiteHeader header = PageFactory.initElements(driver, WebsiteHeader.class);

		String alt = header.getHeaderBanner().getAttribute("alt");

		assertEquals("Mario & Luigi Enemy Bestiary", alt);
	}

	@Test
	public void checkSearchSuccess() {
		driver.get(String.format("%s%d", URL, randomPort));

		WebsiteHeader header = PageFactory.initElements(driver, WebsiteHeader.class);

		header.getEnemySearch().sendKeys("beanie");
		header.getEnemySearchButton().click();

		assertEquals("Profile", driver.getTitle());
		assertTrue(driver.findElement(By.id("imgNameCard")).isDisplayed());
	}
	
	@Test
	public void checkSearchFailure() {
		driver.get(String.format("%s%d", URL, randomPort));

		WebsiteHeader header = PageFactory.initElements(driver, WebsiteHeader.class);

		header.getEnemySearch().sendKeys("meanie");
		header.getEnemySearchButton().click();

		assertEquals("Profile", driver.getTitle());
		
		WebElement noEnemyAlert = driver.findElement(By.id("noEnemyAlert"));
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(noEnemyAlert));
		assertTrue(noEnemyAlert.isDisplayed());
	}
}
