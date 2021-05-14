package com.qa.enemybestiary.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(id = "homeTextBox")
	private WebElement homeTextBox;
	
	@FindBy(id = "homeToCatalogueBtn")
	private WebElement homeToCatalogueBtn;

	public WebElement getHomeTextBox() {
		return homeTextBox;
	}

	public WebElement getHomeToCatalogueBtn() {
		return homeToCatalogueBtn;
	}
	
	
}
