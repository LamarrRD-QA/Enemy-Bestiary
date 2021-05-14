package com.qa.enemybestiary.selenium.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.enemybestiary.selenium.pages.CataloguePage;

@ContextConfiguration
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CatalogueTest {

	@LocalServerPort
	int randomPort;

	private static final String URL = "http://localhost:";
	private static final String subURL = "/catalogue.html";

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
//		option.setHeadless(true);
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void checkTitle() {
		driver.get(String.format("%s%d%s", URL, randomPort, subURL));

		assertEquals("Catalogue", driver.getTitle());
	}

	@Test
	public void checkEnemyTableSuccess() {
		driver.get(String.format("%s%d%s", URL, randomPort, subURL));

		CataloguePage catalogue = PageFactory.initElements(driver, CataloguePage.class);

		assertTrue(catalogue.getEnemyTable().isDisplayed());
	}

	// TODO - Implement fail case tests
//	@Test
//	public void checkEnemyTableFailure() {
//		driver.get(String.format("%s%d%s", URL, randomPort, subURL));
//		
//		WebElement listEnemyNotFoundAlert = driver.findElement(By.id("listEnemyNotFoundAlert"));
//		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(listEnemyNotFoundAlert));
//		assertTrue(listEnemyNotFoundAlert.isDisplayed());
//	}

	@Test
	public void checkAddEnemyButton() {
		driver.get(String.format("%s%d%s", URL, randomPort, subURL));

		CataloguePage catalogue = PageFactory.initElements(driver, CataloguePage.class);

//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		catalogue.getAddEnemyBtn().click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(catalogue.getAddEnemyModal()));
		driver.switchTo().activeElement();

		assertTrue(catalogue.getAddEnemyModal().isDisplayed());
	}

	// Test current 
	@Test
	public void checkAddEnemyToTable() {
		driver.get(String.format("%s%d%s", URL, randomPort, subURL));

		CataloguePage catalogue = PageFactory.initElements(driver, CataloguePage.class);
		
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", catalogue.getAddEnemyBtn());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		catalogue.getAddEnemyBtn().click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(catalogue.getAddEnemyModal()));
		driver.switchTo().activeElement();
		catalogue.fillAndSubmitAddEnemy();
		
		
		String message = catalogue.getNotification().findElement(By.className("toast-body")).getText();
		wait.until(ExpectedConditions.visibilityOf(catalogue.getNotification()));
		assertTrue(catalogue.getNotification().isDisplayed());
		assertTrue(message.contains("created"));
		
		
	}
}
