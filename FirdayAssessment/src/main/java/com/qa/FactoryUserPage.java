package com.qa;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FactoryUserPage {
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/div[2]")
	private WebElement username;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/h1")
	private WebElement fullName;
	
	@FindBy(linkText = "Configure")
	private WebElement config;
	
	public String returnUser() {
		int a = username.getText().indexOf(":") + 2;
		return username.getText().substring(a);
	}
	
	public void clickConfig() {
		config.click();
	}

	public Object returnFullName() {
		return fullName.getText().trim();
	}
}
