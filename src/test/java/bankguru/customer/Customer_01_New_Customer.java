package bankguru.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.NewCustomerPageObject;
import pageObject.PageGeneratorManager;
import pageObject.RegisterPageObject;
import pageUI.BasePageUI;
import utilities.FakerConfig;

public class Customer_01_New_Customer extends BaseTest {

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

		log.info("Pre-condition - Step 04: Enter to 'Email ID' textbox");
		registerPage.enterToEmailTextbox(emailID);

		log.info("Pre-condition - Step 05: Click to 'Submit' button");
		registerPage.clickToSubmitButtonAtRegisterPage();

		log.info("Pre-condition - Step 06: Get 'User ID' value");
		userID = registerPage.getUserIDValue();

		log.info("Pre-condition - Step 07: Get 'Password' value");
		password = registerPage.getPasswordValue();

		log.info("Pre-condition - Step 08: Back to LoginPage");
		driver.get(url);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 09: Enter to 'UserID' textbox");
		loginPage.enterToUserIDTextbox(userID);

		log.info("Pre-condition - Step 10: Enter to 'Password' textbox");
		loginPage.enterToPasswordTextbox(password);

		log.info("Pre-condition - Step 11: Click to 'Login' button");
		loginPage.clickToLoginButton();
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Pre-condition - Step 12: Verify login success message is displayed");
		verifyEquals(homePage.getSuccessMessageText(), "Manger Id :" + " " + userID);
	}

	@Test
	public void TC_01_New_Customer_With_Name_Can_Not_Be_Empty() {

		log.info("New_Customer_Name cannot be empty - Step 01: Click to 'New Customer' Link");
		homePage.openPageAtLeftSubMenuByText(driver, "New Customer");
		newCusomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		log.info("New_Customer_Name cannot be empty - Step 02: Input blank value to 'Customer Name' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "name");

		log.info("New_Customer_Name cannot be empty - Step 03: Press key TAB to 'Customer Name' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "name");

		log.info("New_Customer_Name cannot be empty - Step 04: Verify warning message is displayed with 'Customer name must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, ""), "Customer name must not be blank");
	}

	@Test
	public void TC_02_New_Customer_With_Name_Can_Not_Be_Numeric() {

		log.info("New_Customer_Name cannot be numeric - Step 01:  Input numeric to 'Customer Name' textbox with value: 'anhtran123'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran123", "name");

		log.info("New_Customer_Name cannot be numeric - Step 02: Verify warning message is displayed with 'Numbers are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, ""), "Numbers are not allowed");
	}

	@Test
	public void TC_03_New_Customer_With_Name_Can_Not_Have_Special_Characters() {

		log.info("New_Customer_Name cannot have special charater - Step 01:  Input to 'Customer Name' textbox with value: 'anhtran@#'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran@#", "name");

		log.info("New_Customer_Name cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, ""), "Special characters are not allowed");
	}

	@Test
	public void TC_04_New_Customer_With_Name_Can_Not_Have_First_Character_As_Blank() {

		log.info("New_Customer_Name cannot have first character as blank space - Step 01:  Input to 'Customer Name' textbox with value: ' anhtran'");
		newCusomerPage.sendKeyToTextboxByName(driver, " anhtran", "name");

		log.info("New_Customer_Name cannot have first character as blank space - Step 02: Verify warning message is displayed with 'First character can not have space'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, ""), "First character can not have space");
	}

	@Test
	public void TC_05_New_Customer_With_Address_Can_Not_Be_Empty() {

		log.info("New_Customer_Address cannot be empty - Step 01: Input blank value to 'Address' textarea");
		newCusomerPage.sendKeyToTextAreaByName(driver, "", "addr");

		log.info("New_Customer_Address cannot be empty - Step 02: Press key TAB to 'Address' textarea");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_NAME, Keys.TAB, "addr");

		log.info("New_Customer_Address cannot be empty - Step 03: Verify warning message is displayed with 'Address Field must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "3"), "Address Field must not be blank");
	}

	@Test
	public void TC_06_New_Customer_With_Address_Can_Not_Have_First_Character_As_Blank() {

		log.info("New_Customer_Address cannot be empty - Step 01: Input to 'Address' textarea with value: ' anhtran1996'");
		newCusomerPage.sendKeyToTextAreaByName(driver, " anhtran1996", "addr");

		log.info("New_Customer_Address cannot be empty - Step 02: Verify warning message is displayed with 'First character can not have space'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "3"), "First character can not have space");
	}

	@Test
	public void TC_07_New_Customer_With_City_Can_Not_Be_Empty() {

		log.info("New_Customer_City cannot be empty - Step 01: Input blank value to 'City' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "city");

		log.info("New_Customer_Name cannot be empty - Step 02: Press key TAB to 'City' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "city");

		log.info("New_Customer_Name cannot be empty - Step 03: Verify warning message is displayed with 'City Field must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "4"), "City Field must not be blank");
	}

	@Test
	public void TC_08_New_Customer_With_City_Can_Not_Be_Numeric() {

		log.info("New_Customer_City cannot be numeric - Step 01:  Input numeric to 'City' textbox with value: 'anhtran123'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran123", "city");

		log.info("New_Customer_City cannot be numeric - Step 02: Verify warning message is displayed with 'Numbers are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "4"), "Numbers are not allowed");
	}

	@Test
	public void TC_09_New_Customer_With_Name_Can_Not_Have_Special_Characters() {

		log.info("New_Customer_City cannot have special charater - Step 01:  Input to 'City' textbox with value: 'anhtran@#'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran@#", "city");

		log.info("New_Customer_City cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "4"), "Special characters are not allowed");
	}

	@Test
	public void TC_10_New_Customer_With_City_Can_Not_Have_First_Character_As_Blank() {

		log.info("New_Customer_City cannot have first character as blank space - Step 01:  Input to 'City' textbox with value: ' anhtran'");
		newCusomerPage.sendKeyToTextboxByName(driver, " anhtran", "city");

		log.info("New_Customer_City cannot have first character as blank space - Step 02: Verify warning message is displayed with 'First character can not have space'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "4"), "First character can not have space");
	}

	@Test
	public void TC_11_New_Customer_With_State_Can_Not_Be_Empty() {

		log.info("New_Customer_State cannot be empty - Step 01: Input blank value to 'State' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "state");

		log.info("New_Customer_State cannot be empty - Step 02: Press key TAB to 'State' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "state");

		log.info("New_Customer_State cannot be empty - Step 03: Verify warning message is displayed with 'State must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "5"), "State must not be blank");
	}

	@Test
	public void TC_12_New_Customer_With_State_Can_Not_Be_Numeric() {

		log.info("New_Customer_State cannot be numeric - Step 01:  Input numeric to 'State' textbox with value: 'anhtran123'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran123", "state");

		log.info("New_Customer_State cannot be numeric - Step 02: Verify warning message is displayed with 'Numbers are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "5"), "Numbers are not allowed");
	}

	@Test
	public void TC_13_New_Customer_With_State_Can_Not_Have_Special_Characters() {

		log.info("New_Customer_State cannot have special charater - Step 01:  Input to 'State' textbox with value: 'anhtran@#'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran@#", "state");

		log.info("New_Customer_State cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "5"), "Special characters are not allowed");
	}

	@Test
	public void TC_14_New_Customer_With_State_Can_Not_Have_First_Character_As_Blank() {

		log.info("New_Customer_State cannot have first character as blank space - Step 01:  Input to 'State' textbox with value: ' anhtran'");
		newCusomerPage.sendKeyToTextboxByName(driver, " anhtran", "state");

		log.info("New_Customer_State cannot have first character as blank space - Step 02: Verify warning message is displayed with 'First character can not have space'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "5"), "First character can not have space");
	}

	@Test
	public void TC_15_New_Customer_With_Pin_Can_Not_Be_Empty() {

		log.info("New_Customer_Pin cannot be empty - Step 01: Input blank value to 'Pin' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "pinno");

		log.info("New_Customer_Pin cannot be empty - Step 02: Press key TAB to 'Pin' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "pinno");

		log.info("New_Customer_Pin cannot be empty - Step 03: Verify warning message is displayed with 'PIN Code must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "PIN Code must not be blank");
	}

	@Test
	public void TC_16_New_Customer_With_Pin_Must_Be_Numeric() {

		log.info("New_Customer_Pin must be numeric - Step 01:  Input numeric to 'Pin' textbox with value: '123pin'");
		newCusomerPage.sendKeyToTextboxByName(driver, "123pin", "pinno");

		log.info("New_Customer_Pin must be numeric - Step 02: Verify warning message is displayed with 'Characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "Characters are not allowed");
	}

	@Test
	public void TC_17_New_Customer_With_Pin_Can_Not_Have_Special_Characters() {

		log.info("New_Customer_Pin cannot have special charater - Step 01:  Input to 'Pin' textbox with value: '@#anh'");
		newCusomerPage.sendKeyToTextboxByName(driver, "@#anh", "pinno");

		log.info("New_Customer_Pin cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "Special characters are not allowed");
	}

	@Test
	public void TC_18_New_Customer_With_Pin_Can_Not_Have_First_Character_As_Blank() {

		log.info("New_Customer_Pin cannot have first character as blank space - Step 01:  Input to 'Pin ' textbox with value: ' 123'");
		newCusomerPage.sendKeyToTextboxByName(driver, " 123", "pinno");

		log.info("New_Customer_Pin cannot have first character as blank space - Step 02: Verify warning message is displayed with 'First character can not have space'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "First character can not have space");
	}

	@Test
	public void TC_19_New_Customer_With_Pin_Can_Not_Have_Blank_Space() {

		log.info("New_Customer_Pin cannot have blank space - Step 01:  Input to 'Pin' textbox with value: '123 34'");
		newCusomerPage.sendKeyToTextboxByName(driver, "123 34", "pinno");

		log.info("New_Customer_Pin cannot have blank space - Step 02: Verify warning message is displayed with 'Characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "Characters are not allowed");
	}

	@Test
	public void TC_19_New_Customer_With_Pin_Must_Have_6_Digits() {

		log.info("New_Customer_Pin must have 6 digits - Step 01:  Input to 'Pin' textbox with value: '1234'");
		newCusomerPage.sendKeyToTextboxByName(driver, "1234", "pinno");

		log.info("New_Customer_Pin must have 6 digits - Step 02: Verify warning message is displayed with 'PIN Code must have 6 Digits'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "PIN Code must have 6 Digits");
	}

	@Test
	public void TC_20_New_Customer_With_Pin_Can_Not_Have_First_Character_As_Blank() {

		log.info("New_Customer_Pin cannot have first character as blank space - Step 01:  Input to 'State' textbox with value: ' 123'");
		newCusomerPage.sendKeyToTextboxByName(driver, " 123", "pinno");

		log.info("New_Customer_Pin cannot have first character as blank space - Step 02: Verify warning message is displayed with 'First character can not have space'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "First character can not have space");
	}

	@Test
	public void TC_21_New_Customer_With_Mobile_Number_Can_Not_Be_Empty() {

		log.info("New_Customer_Mobile Number cannot be empty - Step 01: Input blank value to 'Mobile Number' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "telephoneno");

		log.info("New_Customer_Mobile Number cannot be empty - Step 02: Press key TAB to 'Mobile Number' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "telephoneno");

		log.info("New_Customer_Mobile Number cannot be empty - Step 03: Verify warning message is displayed with 'Mobile no must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "7"), "Mobile no must not be blank");
	}

	@Test
	public void TC_22_New_Customer_With_Mobile_Number_Can_Not_Have_First_Character_As_Blank() {

		log.info("New_Customer_Mobile Number cannot have first character as blank space - Step 01:  Input to 'Mobile Number' textbox with value: ' 123'");
		newCusomerPage.sendKeyToTextboxByName(driver, " 123", "telephoneno");

		log.info("New_Customer_Mobile Number cannot have first character as blank space - Step 02: Verify warning message is displayed with 'First character can not have space'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "7"), "First character can not have space");
	}

	@Test
	public void TC_23_New_Customer_With_Mobile_Number_Can_Not_Have_Blank_Space() {

		log.info("New_Customer_Mobile Number cannot have blank space - Step 01:  Input to 'Mobile_Number' textbox with value: '123 34'");
		newCusomerPage.sendKeyToTextboxByName(driver, "123 34", "telephoneno");

		log.info("New_Customer_Mobile Number cannot have blank space - Step 02: Verify warning message is displayed with 'Characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "7"), "Characters are not allowed");
	}

	@Test
	public void TC_24_New_Customer_Mobile_Number_Pin_Can_Not_Have_Special_Characters() {

		log.info("New_Customer_Mobile Numbercannot have special charater - Step 01:  Input to 'Mobile Number' textbox with value: '8888$3#'");
		newCusomerPage.sendKeyToTextboxByName(driver, "8888$3#", "telephoneno");

		log.info("New_Customer_Mobile Number cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "7"), "Special characters are not allowed");
	}

	@Test
	public void TC_25_New_Customer_With_Email_Can_Not_Be_Empty() {

		log.info("New_Customer_Email cannot be empty - Step 01: Input blank value to 'Email' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "emailid");

		log.info("New_Customer_Email cannot be empty - Step 02: Press key TAB to 'Email-ID must not be blank' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "emailid");

		log.info("New_Customer_Email cannot be empty - Step 03: Verify warning message is displayed with 'Email-ID must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "9"), "Email-ID must not be blank");
	}

	@Test
	public void TC_26_New_Customer_With_Email_Must_Be_In_Correct_Format() {

		log.info("New_Customer_Email must be in correct format - Step 01:  Input numeric to 'Email' textbox with value: 'anhtran@'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran@", "emailid");

		log.info("New_Customer_Pin must be in correct format - Step 02: Verify warning message is displayed with 'Email-ID is not valid'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "9"), "Email-ID is not valid");
	}

	@Test
	public void TC_27_New_Customer_With_Email_Can_Not_Have_Blank_Space() {

		log.info("New_Customer_Email cannot have blank space - Step 01:  Input to 'Email' textbox with value: 'atran99 @gmai'");
		newCusomerPage.sendKeyToTextboxByName(driver, "atran99 @gmail", "emailid");

		log.info("New_Customer_Email cannot have blank space - Step 02: Verify warning message is displayed with 'Email-ID is not valid'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "9"), "Email-ID is not valid");
	}

	@Test
	public void TC_28_New_Customer_With_All_Fields_Are_As_Requirement() {

		log.info("New_Customer Verify all fields label - Step 01: Verify all fields are displayed as requirment");
		verifyTrue(homePage.isFieldLablelDisPlayedByName(driver, "Customer Name"));
		verifyTrue(homePage.isFieldLablelDisPlayedByName(driver, "Gender"));
		verifyTrue(homePage.isFieldLablelDisPlayedByName(driver, "Date of Birth"));
		verifyTrue(homePage.isFieldLablelDisPlayedByName(driver, "Address"));
		verifyTrue(homePage.isFieldLablelDisPlayedByName(driver, "City"));
		verifyTrue(homePage.isFieldLablelDisPlayedByName(driver, "State"));
		verifyTrue(homePage.isFieldLablelDisPlayedByName(driver, "PIN"));
		verifyTrue(homePage.isFieldLablelDisPlayedByName(driver, "Mobile Number"));
		verifyTrue(homePage.isFieldLablelDisPlayedByName(driver, "E-mail"));
		verifyTrue(homePage.isFieldLablelDisPlayedByName(driver, "Password"));

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	String url, emailID, userID, password;
	WebDriver driver;
	FakerConfig fakeData;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewCustomerPageObject newCusomerPage;
}
