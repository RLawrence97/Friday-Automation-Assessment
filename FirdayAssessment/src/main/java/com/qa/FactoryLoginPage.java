package com.qa;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FactoryLoginPage {
	@FindBy(id = "j_username")
	private WebElement user;
	@FindBy(name = "j_password")
	private WebElement pass;
	
	public void login() {
		user.sendKeys("root");
		pass.sendKeys("root");
		pass.submit();
	}
}
