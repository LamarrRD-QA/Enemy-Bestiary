package com.qa.enemybestiary.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CataloguePage {
	
	@FindBy(id = "enemyTable")
	private WebElement enemyTable;
	
	@FindBy(id = "addEnemyBtn")
	private WebElement addEnemyBtn;
	
	@FindBy(id = "addEnemyModal")
	private WebElement addEnemyModal;
	
	@FindBy(id = "updateEnemyModal")
	private WebElement updateEnemyModal;
	
	@FindBy(id = "deleteEnemyModal")
	private WebElement deleteEnemyModal;
	
	@FindBy(id = "notif")
	private WebElement notification;
	
	public void fillAndSubmitAddEnemy() {
		addEnemyModal.findElement(By.id("addEnemyName")).sendKeys("Kitty");
		addEnemyModal.findElement(By.id("addEnemyLevel")).sendKeys("20");
		
		Select addEnemyLocations = new Select(addEnemyModal.findElement(By.id("addEnemyLocations")));
		addEnemyLocations.selectByVisibleText("Stardust Fields");
		addEnemyLocations.selectByVisibleText("Chucklehuck Woods");
		
		addEnemyModal.findElement(By.id("addEnemyCoins")).sendKeys("20");
		addEnemyModal.findElement(By.id("addEnemyExp")).sendKeys("20");
		addEnemyModal.findElement(By.id("addEnemyHP")).sendKeys("20");
		addEnemyModal.findElement(By.id("addEnemyPow")).sendKeys("20");
		addEnemyModal.findElement(By.id("addEnemyDef")).sendKeys("20");
		addEnemyModal.findElement(By.id("addEnemySpeed")).sendKeys("20");
		addEnemyModal.findElement(By.id("addEnemyStun")).sendKeys("40.56");
		addEnemyModal.findElement(By.id("addEnemyBurn")).sendKeys("20.89");
		addEnemyModal.findElement(By.id("addEnemyStatDown")).sendKeys("60.79");
		
		Select addEnemyItemOne = new Select(addEnemyModal.findElement(By.id("addEnemyItemOne")));
		Select addEnemyItemTwo = new Select(addEnemyModal.findElement(By.id("addEnemyItemTwo")));
		
		addEnemyItemOne.selectByVisibleText("Nut");
		addEnemyItemTwo.selectByVisibleText("Mushroom");
		
		addEnemyModal.findElement(By.id("addEnemyChanceItemOne")).sendKeys("30.79");
		addEnemyModal.findElement(By.id("addEnemyChanceItemTwo")).sendKeys("21.56");
		addEnemyModal.findElement(By.id("addEnemySubmit")).click();
		
		
	}

	public WebElement getEnemyTable() {
		return enemyTable;
	}

	public WebElement getAddEnemyBtn() {
		return addEnemyBtn;
	}

	public WebElement getAddEnemyModal() {
		return addEnemyModal;
	}

	public WebElement getUpdateEnemyModal() {
		return updateEnemyModal;
	}

	public WebElement getDeleteEnemyModal() {
		return deleteEnemyModal;
	}

	public WebElement getNotification() {
		return notification;
	}

}
