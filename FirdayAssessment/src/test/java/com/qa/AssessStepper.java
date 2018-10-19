package com.qa;

import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssessStepper {
	
	WebDriver driver = null;
	static ExtentReports report = null;
	ExtentTest test;
	
	static int count = 0;
	
	@Before
	public void setup() {
		if (count == 0) {
			report = new ExtentReports(Constant.REPORTLOCATION, true);
		}
		if (count < 5) {
			test = report.startTest("Creating a user, test " + count);
		} else if (count < 8) {
			test = report.startTest("Verifying User, test " + count);
		} else {
			test = report.startTest("Updating User, test " + count);
		}
		System.setProperty("webdriver.chrome.driver","C:/Users/Admin/Desktop/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Constant.JENKINS);
	    driver.manage().window().maximize();
	    FactoryLoginPage flp = PageFactory.initElements(driver, FactoryLoginPage.class);
	    flp.login();
	    count++;
	}
	
	@After
	public void teardown() {
		report.endTest(test);
		report.flush();
		driver.close();
	}
	
	@Given("^that you are on the create UserScreen$")
	public void that_you_are_on_the_create_UserScreen() throws Throwable {
	    driver.get(Constant.JENKINSADD);
	    test.log(LogStatus.INFO, "Create User page loaded");
	    Thread.sleep(1000);
	}

	@When("^the User details are entered on the Create UserScreen$")
	public void the_User_details_are_entered_on_the_Create_UserScreen() throws Throwable {
		test.log(LogStatus.INFO, "User: ");
		FactoryAddPage faadpa = PageFactory.initElements(driver, FactoryAddPage.class);
		faadpa.inputDetails("test", "test", "test", "testy McTestface");
	}

	@When("^the details are submitted on the Create UserScreen$")
	public void the_details_are_submitted_on_the_Create_UserScreen() throws Throwable {
		FactoryAddPage faadpa = PageFactory.initElements(driver, FactoryAddPage.class);
		faadpa.submitDetails();
		test.log(LogStatus.INFO, "Submitting");
	}

	@Then("^the Username should be visible on the UsersScreen$")
	public void the_Username_should_be_visible_on_the_UsersScreen() throws Throwable {
		FactoryTablePage ftpbtw = PageFactory.initElements(driver, FactoryTablePage.class);
		if (ftpbtw.isThere("test", driver)) {
			test.log(LogStatus.PASS, "User test located");
		} else {
			test.log(LogStatus.FAIL, "User test not found");
		}
		assertEquals("Is test there?",true, ftpbtw.isThere("test", driver));
	}

	@When("^the User details \"([^\"]*)\" username, \"([^\"]*)\" password, \"([^\"]*)\" confirm Password, and \"([^\"]*)\" Fullname are entered on the Create UserScreen$")
	public void the_User_details_username_password_confirm_Password_and_Fullname_are_entered_on_the_Create_UserScreen(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		test.log(LogStatus.INFO, "User: " + arg1);
		FactoryAddPage faadpa = PageFactory.initElements(driver, FactoryAddPage.class);
		faadpa.inputDetails(arg1, arg2, arg3, arg4);
	}

	@Then("^the \"([^\"]*)\" username should be visible on the UsersScreen$")
	public void the_username_should_be_visible_on_the_UsersScreen(String arg1) throws Throwable {
		FactoryTablePage ftpbtw = PageFactory.initElements(driver, FactoryTablePage.class);
		if (ftpbtw.isThere(arg1, driver)) {
			test.log(LogStatus.PASS, "User " + arg1 + " located");
		} else {
			test.log(LogStatus.FAIL, "User " + arg1 + " not found");
		}
		assertEquals("Is user " + arg1+ "present?", true, ftpbtw.isThere(arg1, driver));
	}

	@Given("^the \"([^\"]*)\" username is visible on the UsersScreen$")
	public void the_username_is_visible_on_the_UsersScreen(String arg1) throws Throwable {
		test.log(LogStatus.INFO, "Looking for user: " + arg1);
		driver.get(Constant.JENKINSTABLE);
	    FactoryTablePage ftpbtw = PageFactory.initElements(driver, FactoryTablePage.class);
	    if (ftpbtw.isThere(arg1, driver)) {
			test.log(LogStatus.PASS, "User " + arg1 + " located");
		} else {
			test.log(LogStatus.FAIL, "User " + arg1 + " not found");
		}
		assertEquals("Is user " + arg1+ "present?", true, ftpbtw.isThere(arg1, driver));
	    Thread.sleep(1000);
	}

	@When("^the \"([^\"]*)\" username is clicked on the UserScreen$")
	public void the_username_is_clicked_on_the_UserScreen(String arg1) throws Throwable {
		FactoryTablePage ftpbtw = PageFactory.initElements(driver, FactoryTablePage.class);
		if (ftpbtw.isThere(arg1, driver)) {
			ftpbtw.clickUser(arg1, driver);
			test.log(LogStatus.INFO, "User  " + arg1 + " clicked");
		}
	}

	@Then("^the User Profile should display the \"([^\"]*)\" username on the ProfileScreen$")
	public void the_User_Profile_should_display_the_username_on_the_ProfileScreen(String arg1) throws Throwable {
		FactoryUserPage fup = PageFactory.initElements(driver, FactoryUserPage.class);
		if (fup.returnUser().equals(arg1)) {
			test.log(LogStatus.PASS, "Successfully located username " + arg1);
		} else {
			test.log(LogStatus.FAIL, "Failed to locate username " + arg1);
		}
		assertEquals("Profile displays username " + arg1, arg1 ,fup.returnUser());
	}

	@Given("^the \"([^\"]*)\" Username's profile page has been loaded$")
	public void the_Username_s_profile_page_has_been_loaded(String arg1) throws Throwable {
		test.log(LogStatus.INFO, "Updating user: " + arg1);
		driver.get(Constant.JENKINSUSERSTEM + arg1 + "/");
	    Thread.sleep(1000);
	}

	@Given("^the configure button has been clicked on the profile page$")
	public void the_configure_button_has_been_clicked_on_the_profile_page() throws Throwable {
	    FactoryUserPage fup = PageFactory.initElements(driver, FactoryUserPage.class);
	    fup.clickConfig();
	    test.log(LogStatus.INFO, "Configure clicked");
	}

	@When("^I change the old FullName on the Configure Page to a new FullName \"([^\"]*)\"$")
	public void i_change_the_old_FullName_on_the_Configure_Page_to_a_new_FullName(String arg1) throws Throwable {
		FactoryConfigPage fcp = PageFactory.initElements(driver, FactoryConfigPage.class);
		fcp.updateFullName(arg1);
		test.log(LogStatus.INFO, "Updating full name to: " + arg1);
	}

	@When("^I save the changes to the Configure Page$")
	public void i_save_the_changes_to_the_Configure_Page() throws Throwable {
		FactoryConfigPage fcp = PageFactory.initElements(driver, FactoryConfigPage.class);
		fcp.saveChanges();
		test.log(LogStatus.INFO, "Saving Changes");
	}

	@Then("^the Configure Page should show the NewFullName \"([^\"]*)\"$")
	public void the_Configure_Page_should_show_the_NewFullName(String arg1) throws Throwable {
		FactoryUserPage fup = PageFactory.initElements(driver, FactoryUserPage.class);
		if (fup.returnFullName().equals(arg1)) {
			test.log(LogStatus.PASS, "Successfully updated name to " + arg1);
		} else {
			test.log(LogStatus.FAIL, "User update failed!");
		}
		assertEquals("Has name changed to " + arg1 + "?", arg1, fup.returnFullName());
	}
}
