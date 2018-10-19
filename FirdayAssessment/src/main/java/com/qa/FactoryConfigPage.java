package com.qa;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FactoryConfigPage {
	
	@FindBy(name = "_.fullName")
	private WebElement userInputField;
	
	@FindBy(id = "yui-gen2-button")
	private WebElement saveButton;

	public void saveChanges() {
		saveButton.click();
	}

	public void updateFullName(String arg1) {
		userInputField.clear();
		userInputField.sendKeys(arg1);
	}

}
