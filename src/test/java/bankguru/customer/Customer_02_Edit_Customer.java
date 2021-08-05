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

public class Customer_02_Edit_Customer extends BaseTest {

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlName) {

		fakeData = FakerConfig.getFakeData();
		emailID = fakeData.getEmailAddress();

		email = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		city = fakeData.getCityName();
		customerName = fakeData.getFirstname() + " " + fakeData.getLastname();
		addr = fakeData.getAddress();
		dayofBirth = "03/02/1998";
		state = "Tan Binh";
		pin = "123456";
		mobileNumber = "0964123456";

		log.info("Pre-condition - Step 01: Open Browser: '" + browserName + "' and navigate to '" + urlName + "'");
		driver = getBrowserDriver(browserName, urlName);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 02: Get current Url");
		url = loginPage.getCurrentUrl(driver);

		log.info("Pre-condition - Step 03: Click to 'here' link");
		loginPage.clickToHereLink();
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		log.info("Pre-condition - Step 04: Enter to 'Email ID' textbox");
		registerPage.sendKeyToTextboxByName(driver, emailID, "emailid");

		log.info("Pre-condition - Step 05: Click to 'Submit' button");
		registerPage.clickToButtonByName(driver, "btnLogin");

		log.info("Pre-condition - Step 06: Get 'User ID' value");
		userID = registerPage.getUserIDValue();

		log.info("Pre-condition - Step 07: Get 'Password' value");
		passwordLogin = registerPage.getPasswordValue();

		log.info("Pre-condition - Step 08: Back to LoginPage");
		driver.get(url);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 09: Enter to 'UserID' textbox");
		loginPage.sendKeyToTextboxByName(driver, userID, "uid");

		log.info("Pre-condition - Step 10: Enter to 'Password' textbox");
		loginPage.sendKeyToTextboxByName(driver, passwordLogin, "password");

		log.info("Pre-condition - Step 11: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "btnLogin");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Pre-condition - Step 12: Verify login success message is displayed");
		verifyEquals(homePage.getSuccessMessageText(), "Manger Id :" + " " + userID);

		log.info("Pre-condition - Step 13: Click to 'New Customer' Link");
		homePage.openPageAtLeftSubMenuByText(driver, "New Customer");
		newCusomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		log.info("Pre-condition - Step 14: Input to 'Customer Name' textbox with value" + customerName);
		newCusomerPage.sendKeyToTextboxByName(driver, customerName, "name");

		log.info("Pre-condition - Step 15: Input to 'Date of Birth' textbox with value" + dayofBirth);
		newCusomerPage.sendKeyToTextboxByName(driver, dayofBirth, "dob");

		log.info("Pre-condition - Step 16: Input to 'Address' textbox with value" + addr);
		newCusomerPage.sendKeyToTextAreaByName(driver, addr, "addr");

		log.info("Pre-condition - Step 17: Input to 'City' textbox with value" + city);
		newCusomerPage.sendKeyToTextboxByName(driver, city, "city");

		log.info("Pre-condition - Step 18: Input to 'State' textbox with value" + state);
		newCusomerPage.sendKeyToTextboxByName(driver, state, "state");

		log.info("Pre-condition - Step 19: Input to 'PIN' textbox with value" + pin);
		newCusomerPage.sendKeyToTextboxByName(driver, pin, "pinno");

		log.info("Pre-condition - Step 20: Input to 'Mobile Number' textbox with value" + mobileNumber);
		newCusomerPage.sendKeyToTextboxByName(driver, mobileNumber, "telephoneno");

		log.info("Pre-condition - Step 21: Input to 'E-mail' textbox with value" + email);
		newCusomerPage.sendKeyToTextboxByName(driver, email, "emailid");

		log.info("Pre-condition - Step 22: Input to 'Password' textbox with value" + password);
		newCusomerPage.sendKeyToTextboxByName(driver, password, "password");

		log.info("Pre-condition - Step 23: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "sub");

		log.info("Pre-condition - Step 23: Verify success message is displayed with content 'Customer Registered Successfully!!!'");
		verifyTrue(newCusomerPage.isSuccessMessageDisplayed());

		log.info("Pre-condition - Step 24: Get 'Customer ID' text value");
		customerID = newCusomerPage.getCustomerID();

		log.info("Pre-condition - Step 25: Click to 'Edit Customer' label");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Edit Customer");

		log.info("Pre-condition - Step 26: Verify 'Edit Customer Form' heading is displayed");
		newCusomerPage.isHeadinglDisplayedByTextName(driver, "Edit Customer Form");

	}

	@Test
	public void TC_01_Edit_Customer_CustomerID_Canot_Be_Empty() {

		log.info("Edit_Customer_CustomerID cannot be empty - Step 01: Input blank value to 'CustomerID' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "cusid");

		log.info("Edit_Customer_CustomerID cannot be empty - Step 02: Press key TAB to 'CustomerID' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "cusid");

		log.info("Edit_Customer_CustomerID cannot be empty - Step 03: Verify warning message is displayed with 'Customer ID is required'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "14"), "Customer ID is required");

	}

	@Test
	public void TC_02_Edit_Customer_CustomerID_Must_Be_Numeric() {

		log.info("Edit_Customer_CustomerID cannot be numeric - Step 01:  Input numeric to 'CustomerID' textbox with value: 'anhtran123'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran123", "cusid");

		log.info("Edit_Customer_CustomerID cannot be numeric - Step 02: Verify warning message is displayed with 'Characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "14"), "Characters are not allowed");
	}

	@Test
	public void TC_03_Edit_Customer_CustomerID_Can_Not_Have_Special_Characters() {

		log.info("Edit_Customer_CustomerID cannot have special charater - Step 01:  Input to 'CustomerID' textbox with value: '123@#'");
		newCusomerPage.sendKeyToTextboxByName(driver, "123@#", "cusid");

		log.info("Edit_Customer_CustomerID cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "14"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_Edit_Customer_CustomerID_Valid_Customer_ID() {
		log.info("Edit_Customer_CustomerID Valid CustomerID  - Step 01: Input valid customerID to 'Customer ID' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, customerID, "cusid");

		log.info("Edit_Customer_CustomerID Valid CustomerID  - Step 02: Click to Submit button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Edit_Customer_CustomerID Valid CustomerID  - Step 03: Verify 'Edit Customer' heading is displayed");
		newCusomerPage.isHeadinglDisplayedByTextName(driver, "Edit Customer");

	}

	@Test
	public void TC_05_Edit_Customer_Address_Can_Not_Be_Empty() {

		log.info("Edit_Customer_Address cannot be empty - Step 01: Input blank value to 'Address' textarea");
		newCusomerPage.sendKeyToTextAreaByName(driver, "", "addr");

		log.info("Edit_Customer_Address cannot be empty - Step 02: Press key TAB to 'Address' textarea");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_NAME, Keys.TAB, "addr");

		log.info("Edit_Customer_Address cannot be empty - Step 03: Verify warning message is displayed with 'Address Field must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "3"), "Address Field must not be blank");
	}

	@Test
	public void TC_06_Edit_Customer_City_Can_Not_Be_Empty() {

		log.info("Edit_Customer_City cannot be empty - Step 01: Input blank value to 'City' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "city");

		log.info("Edit_Customer_City cannot be empty - Step 02: Press key TAB to 'City' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "city");

		log.info("Edit_Customer_City cannot be empty - Step 03: Verify warning message is displayed with 'City Field must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "4"), "City Field must not be blank");
	}

	@Test
	public void TC_07_Edit_Customer_City_Can_Not_Be_Numeric() {

		log.info("Edit_Customer_City cannot be numeric - Step 01:  Input numeric to 'City' textbox with value: 'anhtran123'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran123", "city");

		log.info("Edit_Customer_City cannot be numeric - Step 02: Verify warning message is displayed with 'Numbers are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "4"), "Numbers are not allowed");
	}

	@Test
	public void TC_08_Edit_Customer_City_With_Name_Can_Not_Have_Special_Characters() {

		log.info("Edit_Customer_City cannot have special charater - Step 01:  Input to 'City' textbox with value: 'anhtran@#'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran@#", "city");

		log.info("Edit_Customer_City cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "4"), "Special characters are not allowed");
	}

	@Test
	public void TC_09_Edit_Customer_With_State_Can_Not_Be_Empty() {

		log.info("Edit_Customer_State cannot be empty - Step 01: Input blank value to 'State' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "state");

		log.info("Edit_Customer_State cannot be empty - Step 02: Press key TAB to 'State' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "state");

		log.info("New_Customer_State cannot be empty - Step 03: Verify warning message is displayed with 'State must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "5"), "State must not be blank");
	}

	@Test
	public void TC_10_Edit_Customer_With_State_Can_Not_Be_Numeric() {

		log.info("Edit_Customer_State cannot be numeric - Step 01:  Input numeric to 'State' textbox with value: 'anhtran123'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran123", "state");

		log.info("Edit_Customer_State cannot be numeric - Step 02: Verify warning message is displayed with 'Numbers are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "5"), "Numbers are not allowed");
	}

	@Test
	public void TC_11_Edit_Customer_With_State_Can_Not_Have_Special_Characters() {

		log.info("New_Customer_State cannot have special charater - Step 01:  Input to 'State' textbox with value: 'anhtran@#'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran@#", "state");

		log.info("New_Customer_State cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "5"), "Special characters are not allowed");
	}

	@Test
	public void TC_12_Edit_Customer_With_Pin_Can_Not_Be_Empty() {

		log.info("Edit_Customer_Pin cannot be empty - Step 01: Input blank value to 'Pin' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "pinno");

		log.info("Edit_Customer_Pin cannot be empty - Step 02: Press key TAB to 'Pin' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "pinno");

		log.info("Edit_Customer cannot be empty - Step 03: Verify warning message is displayed with 'PIN Code must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "PIN Code must not be blank");
	}

	@Test
	public void TC_13_Edit_Customer_With_Pin_Must_Be_Numeric() {

		log.info("Edit_Customer_Pin must be numeric - Step 01:  Input numeric to 'Pin' textbox with value: '123pin'");
		newCusomerPage.sendKeyToTextboxByName(driver, "123pin", "pinno");

		log.info("Edit_Customer_Pin must be numeric - Step 02: Verify warning message is displayed with 'Characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "Characters are not allowed");
	}

	@Test
	public void TC_14_Edit_Customer_With_Pin_Can_Not_Have_Special_Characters() {

		log.info("Edit_Customer_Pin cannot have special charater - Step 01:  Input to 'Pin' textbox with value: '@#anh'");
		newCusomerPage.sendKeyToTextboxByName(driver, "@#anh", "pinno");

		log.info("Edit_Customer_Pin cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "Special characters are not allowed");
	}

	@Test
	public void TC_15_Edit_Customer_With_With_Pin_Must_Have_6_Digits() {

		log.info("Edit_Customer_Pin must have 6 digits - Step 01:  Input to 'Pin' textbox with value: '1234'");
		newCusomerPage.sendKeyToTextboxByName(driver, "1234", "pinno");

		log.info("Edit_Customer_Pin must have 6 digits - Step 02: Verify warning message is displayed with 'PIN Code must have 6 Digits'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "6"), "PIN Code must have 6 Digits");
	}

	@Test
	public void TC_15_Edit_Customer_With_Mobile_Number_Can_Not_Be_Empty() {

		log.info("Edit_Customerr_Mobile Number cannot be empty - Step 01: Input blank value to 'Mobile Number' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "telephoneno");

		log.info("Edit_Customer_Mobile Number cannot be empty - Step 02: Press key TAB to 'Mobile Number' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "telephoneno");

		log.info("Edit_Customer_Mobile Number cannot be empty - Step 03: Verify warning message is displayed with 'Mobile no must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "7"), "Mobile no must not be blank");
	}

	@Test
	public void TC_16_Edit_Customer_With_Mobile_Number_Can_Not_Have_First_Character_As_Blank() {

		log.info("Edit_Customer_Mobile Number cannot have first character as blank space - Step 01:  Input to 'Mobile Number' textbox with value: ' 123'");
		newCusomerPage.sendKeyToTextboxByName(driver, " 123", "telephoneno");

		log.info("Edit_Customer_Mobile Number cannot have first character as blank space - Step 02: Verify warning message is displayed with 'First character can not have space'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "7"), "First character can not have space");
	}

	@Test
	public void TC_17_Edit_Customer_With_Mobile_Number_Pin_Can_Not_Have_Special_Characters() {

		log.info("Edit_Customer_Mobile Numbercannot have special charater - Step 01:  Input to 'Mobile Number' textbox with value: '8888$3#'");
		newCusomerPage.sendKeyToTextboxByName(driver, "8888$3#", "telephoneno");

		log.info("Edit_Customer_Mobile Number cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "7"), "Special characters are not allowed");
	}

	@Test
	public void TC_18_Edit_Customer_With_Email_Can_Not_Be_Empty() {

		log.info("Edit_Customer_Email cannot be empty - Step 01: Input blank value to 'Email' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "emailid");

		log.info("Edit_Customer_Email cannot be empty - Step 02: Press key TAB to 'Email-ID must not be blank' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "emailid");

		log.info("Edit_Customer_Email cannot be empty - Step 03: Verify warning message is displayed with 'Email-ID must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "9"), "Email-ID must not be blank");
	}

	@Test
	public void TC_19_Edit_Customer_With_Email_Must_Be_In_Correct_Format() {

		log.info("Edit_Customer_Email must be in correct format - Step 01:  Input numeric to 'Email' textbox with value: 'anhtran@'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran@", "emailid");

		log.info("Edit_Customer_Pin must be in correct format - Step 02: Verify warning message is displayed with 'Email-ID is not valid'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "9"), "Email-ID is not valid");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	String url, emailID, userID, passwordLogin;
	String email, customerName, addr, state, city, pin, mobileNumber, password, dayofBirth, customerID;
	WebDriver driver;
	FakerConfig fakeData;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	CustomerPageObject newCusomerPage;
}
