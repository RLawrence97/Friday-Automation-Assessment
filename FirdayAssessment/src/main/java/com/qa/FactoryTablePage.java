package com.qa;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FactoryTablePage {

	
	public void clickUser(String arg1, WebDriver driver) {
		List<WebElement> matts = driver.findElements(By.linkText(arg1));
		for (WebElement m : matts) {
			if (m.getAttribute("href").equalsIgnoreCase("http://localhost:8080/securityRealm/user/" + arg1 + "/")){
				m.click();
			}
		}
	}

	public boolean isThere(String arg1, WebDriver driver) {
		if (!driver.findElements(By.linkText(arg1)).isEmpty()){
			return true;
		}
		return false;
	}

}
