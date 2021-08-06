package bankguru.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.CustomerPageObject;
import pageObject.PageGeneratorManager;
import pageObject.RegisterPageObject;
import pageUI.BasePageUI;
import utilities.FakerConfig;

public class Customer_10_Change_Password extends BaseTest {

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlName) {

		log.info("Pre-condition - Step 01: Open Browser: '" + browserName + "' and navigate to '" + urlName + "'");
		driver = getBrowserDriver(browserName, urlName);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 02: Get current Url");
		url = loginPage.getCurrentUrl(driver);

		log.info("Pre-condition - Step 03: Click to 'here' link");
		loginPage.clickToHereLink();
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		fakeData = FakerConfig.getFakeData();
		emailID = fakeData.getEmailAddress();
		newPassword = "anhtran@123";

		log.info("Pre-condition - Step 04: Enter to 'Email ID' textbox");
		registerPage.sendKeyToTextboxByName(driver, emailID, "emailid");

		log.info("Pre-condition - Step 05: Click to 'Submit' button");
		registerPage.clickToButtonByName(driver, "btnLogin");

		log.info("Pre-condition - Step 06: Get 'User ID' value");
		userID = registerPage.getUserIDValue();

		log.info("Pre-condition - Step 07: Get 'Password' value");
		password = registerPage.getPasswordValue();

		log.info("Pre-condition - Step 08: Back to LoginPage");
		driver.get(url);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 09: Enter to 'UserID' textbox");
		loginPage.sendKeyToTextboxByName(driver, userID, "uid");

		log.info("Pre-condition - Step 10: Enter to 'Password' textbox");
		loginPage.sendKeyToTextboxByName(driver, password, "password");

		log.info("Pre-condition - Step 11: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "btnLogin");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Pre-condition - Step 12: Verify login success message is displayed");
		verifyEquals(homePage.getSuccessMessageText(), "Manger Id :" + " " + userID);

		log.info("Pre-condition - Step 13: Click to 'Change Password' label");
		homePage.openPageAtLeftSubMenuByText(driver, "Change Password");
		cusomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		log.info("Pre-condition - Step 14: Verify 'Change Password' heading is displayed");
		cusomerPage.isHeadinglDisplayedByTextName(driver, "Change Password");
	}

	@Test
	public void TC_01_Change_Password_Old_Password_Can_Not_Be_Empty() {

		log.info("Change_Password_Old_Password cannot be empty - Step 01: Input blank value to 'Old Password' textbox");
		cusomerPage.sendKeyToTextboxByName(driver, "", "oldpassword");

		log.info("Change_Password_Old_Password cannot be empty - Step 02: Press key TAB to 'Old Password' textbox");
		cusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "oldpassword");

		log.info("Change_Password_Old_Password cannot be empty - Step 03: Verify warning message is displayed with 'Old Password must not be blank'");
		verifyEquals(cusomerPage.getWarningMessageByID(driver, "20"), "Old Password must not be blank");
	}

	@Test
	public void TC_02_Change_Password_New_Password_Can_Not_Be_Empty() {

		log.info("Change_Password_New_Password cannot be empty - Step 01: Input blank value to 'New Password' textbox");
		cusomerPage.sendKeyToTextboxByName(driver, "", "newpassword");

		log.info("Change_Password_New_Password cannot be empty - Step 02: Press key TAB to 'New Password' textbox");
		cusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "oldpassword");

		log.info("Change_Password_New_Password cannot be empty - Step 03: Verify warning message is displayed with 'Old Password must not be blank'");
		verifyEquals(cusomerPage.getWarningMessageByID(driver, "21"), "New Password must not be blank");
	}

	@Test
	public void TC_03_Change_Password_New_Password_Must_Have_One_Numeric_Value() {

		log.info("Change_Password_New_Password must have one numeric value - Step 01:  Input to 'New Password' textbox with value: 'anhtran@!'");
		cusomerPage.sendKeyToTextboxByName(driver, "anhtran@!", "newpassword");

		log.info("Change_Password_New_Password must have one numeric value - Step 02: Verify warning message is displayed with 'Enter at-least one numeric value'");
		verifyEquals(cusomerPage.getWarningMessageByID(driver, "21"), "Enter at-least one numeric value");
	}

	@Test
	public void TC_04_Change_Password_New_Password_Must_Have_One_Special_Character() {

		log.info("Change_Password_New_Password must have one special chatacter - Step 01:  Input to 'New Password' textbox with value: 'anhtran123'");
		cusomerPage.sendKeyToTextboxByName(driver, "anhtran123", "newpassword");

		log.info("Change_Password_New_Password must have one special chatacter - Step 02: Verify warning message is displayed with 'Enter at-least one special character'");
		verifyEquals(cusomerPage.getWarningMessageByID(driver, "21"), "Enter at-least one special character");
	}

	@Test
	public void TC_05_Change_Password_New_Password_Cannot_Have_Password_With_String_Password() {

		log.info("Change_Password_New_Password cannot have password with string 'password' - Step 01:  Input to 'New Password' textbox with value: 'anhtran@!password'");
		cusomerPage.sendKeyToTextboxByName(driver, "anhtran123@password", "newpassword");

		log.info("Change_Password_New_Password cannot have password with string 'password' - Step 02: Verify warning message is displayed with 'Choose a difficult Password'");
		verifyEquals(cusomerPage.getWarningMessageByID(driver, "21"), "Choose a difficult Password");
	}

	@Test
	public void TC_06_Change_Password_Confirm_Password_Must_Be_Matched() {

		log.info("Change_Password_Confirm_Password must be matched - Step 01:  Input to 'New Password' textbox with value: 'anhtran@123'");
		cusomerPage.sendKeyToTextboxByName(driver, "anhtran@123", "newpassword");

		log.info("Change_Password_Confirm_Password must be matched - Step 02:  Input to 'Confirm Password' textbox with value: 'anhtran@456'");
		cusomerPage.sendKeyToTextboxByName(driver, "anhtran@456", "confirmpassword");

		log.info("Change_Password_Confirm_Password must be matched - Step 03: Verify warning message is displayed with 'Passwords do not Match'");
		verifyEquals(cusomerPage.getWarningMessageByID(driver, "22"), "Passwords do not Match");
	}

	@Test
	public void TC_07_Change_Password_With_Matched_Password() {

		log.info("Change_Password with matched password - Step 01: Input to 'Old' textbox with valid old password");
		cusomerPage.sendKeyToTextboxByName(driver, password, "oldpassword");

		log.info("Change_Password with matched password - Step 02: Input to 'New Password' textbox with valid new password: 'anhtran@123'");
		cusomerPage.sendKeyToTextboxByName(driver, newPassword, "newpassword");

		log.info("Change_Password with matched password - Step 03: Input to 'Confirm Password' textbox with valid new confirm password: 'anhtran@123'");
		cusomerPage.sendKeyToTextboxByName(driver, newPassword, "confirmpassword");

		log.info("Change_Password with matched password - Step 04: Click to 'Submit' button");
		cusomerPage.clickToButtonByName(driver, "sub");

		log.info("Change_Password with matched password - Step 05: Verify alertext 'Password is Changed' is displayed and accept to alert");
		verifyEquals(cusomerPage.getAlertText(driver), "Password is Changed");
		cusomerPage.acceptAlert(driver);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Change_Password with matched password - Step 06: Enter to 'UserID' textbox");
		loginPage.sendKeyToTextboxByName(driver, userID, "uid");

		log.info("Change_Password with matched password - Step 07: Enter to 'Password' textbox");
		loginPage.sendKeyToTextboxByName(driver, newPassword, "password");

		log.info("Change_Password with matched password - Step 08: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "btnLogin");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Change_Password with matched password - Step 09: Verify login success message is displayed");
		verifyEquals(homePage.getSuccessMessageText(), "Manger Id :" + " " + userID);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	String url, emailID, userID, password, newPassword;
	WebDriver driver;
	FakerConfig fakeData;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	CustomerPageObject cusomerPage;
}
