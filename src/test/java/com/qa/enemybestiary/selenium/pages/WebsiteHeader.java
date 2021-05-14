package com.qa.enemybestiary.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebsiteHeader {
	@FindBy(id = "headerBanner")
	private WebElement headerBanner;
	
	@FindBy(id = "enemySearch")
	private WebElement enemySearch;
	
	@FindBy(id = "enemySearchButton")
	private WebElement enemySearchButton;

	public WebElement getHeaderBanner() {
		return headerBanner;
	}

	public WebElement getEnemySearch() {
		return enemySearch;
	}

	public WebElement getEnemySearchButton() {
		return enemySearchButton;
	}
	
	
}
