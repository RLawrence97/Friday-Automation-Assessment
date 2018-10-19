package com.qa;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FactoryAddPage {
	@FindBy(id = "username")
	private WebElement user;
	@FindBy(name = "password1")
	private WebElement pass;
	@FindBy(name = "password2")
	private WebElement confirmPass;
	@FindBy(name = "fullname")
	private WebElement fullName;
	@FindBy(name = "email")
	private WebElement mail;
	@FindBy(id = "yui-gen1-button")
	private WebElement button;
	
	public void inputDetails(String incUser, String incPass, String incCPass, String incName) {
		user.sendKeys(incUser);
		pass.sendKeys(incPass);
		confirmPass.sendKeys(incCPass);
		fullName.sendKeys(incName);
		mail.sendKeys(incUser + "@Test.com");
	}
	public void submitDetails() {
		button.click();
	}
}
