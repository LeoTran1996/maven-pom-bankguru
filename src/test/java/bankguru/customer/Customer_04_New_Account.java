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

public class Customer_04_New_Account extends BaseTest {

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
		dob = "03021998";
		state = "Thanh Xuan";
		pin = "152111";
		mobileNumber = "0964456456";
		inideposit = "500000";

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

		log.info("Pre-condition - Step 15: Input to 'Date of Birth' textbox with value" + dob);
		newCusomerPage.sendKeyToTextboxByName(driver, dob, "dob");

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

		log.info("Pre-condition - Step 25: Click to 'New Account' label");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "New Account");

		log.info("Pre-condition - Step 26: Verify 'Add new account form Customer Form' heading is displayed");
		newCusomerPage.isHeadinglDisplayedByTextName(driver, "Add new account form");

	}

	@Test
	public void TC_01_New_Account_CustomerID_Canot_Be_Empty() {

		log.info("New_Account_CustomerID cannot be empty - Step 01: Input blank value to 'CustomerID' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "cusid");

		log.info("New_Account_CustomerID cannot be empty - Step 02: Press key TAB to 'CustomerID' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "cusid");

		log.info("New_Account_CustomerID cannot be empty - Step 03: Verify warning message is displayed with 'Customer ID is required'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "14"), "Customer ID is required");

	}

	@Test
	public void TC_02_New_Account_CustomerID_Must_Be_Numeric() {

		log.info("New_Account_CustomerID cannot be numeric - Step 01:  Input numeric to 'CustomerID' textbox with value: 'anhtran123'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran123", "cusid");

		log.info("New_Account_CustomerID cannot be numeric - Step 02: Verify warning message is displayed with 'Characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "14"), "Characters are not allowed");
	}

	@Test
	public void TC_03_Delete_Customer_CustomerID_Can_Not_Have_Special_Characters() {

		log.info("New_Account_CustomerID cannot have special charater - Step 01:  Input to 'CustomerID' textbox with value: '123@#'");
		newCusomerPage.sendKeyToTextboxByName(driver, "123@#", "cusid");

		log.info("New_Account_CustomerID cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "14"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_New_Account_CustomerID_Can_Not_Have_First_Character_As_Blank() {

		log.info("New_Account_CustomerID cannot have first character as blank space - Step 01:  Input to 'CustomerID' textbox with value: ' 123456'");
		newCusomerPage.sendKeyToTextboxByName(driver, " 123456", "cusid");

		log.info("New_Account_CustomerID cannot have first character as blank space - Step 02: Verify warning message is displayed with 'First character can not have space'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "14"), "First character can not have space");
	}

	@Test
	public void TC_05_New_Account_CustomerID_Can_Not_Have_Blank_Space() {

		log.info("New_Account_CustomerID cannot have blank space - Step 01:  Input to 'CustomerID' textbox with value: '123 34'");
		newCusomerPage.sendKeyToTextboxByName(driver, "123 34", "cusid");

		log.info("New_Account_CustomerID cannot have blank space - Step 02: Verify warning message is displayed with 'Characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "14"), "Characters are not allowed");
	}

	@Test
	public void TC_06_New_Account_Initial_Deposit_Canot_Be_Empty() {

		log.info("New_Account_Initial_Deposit cannot be empty - Step 01: Input blank value to 'Initial_Deposit' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "", "inideposit");

		log.info("New_Account_Initial_Deposit cannot be empty - Step 02: Press key TAB to 'Initial_Deposit' textbox");
		newCusomerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "inideposit");

		log.info("New_Account_Initial_Deposit cannot be empty - Step 03: Verify warning message is displayed with 'Initial Deposit must not be blank'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "19"), "Initial Deposit must not be blank");

	}

	@Test
	public void TC_07_New_Account_Initial_Deposit_Must_Be_Numeric() {

		log.info("New_Account_Initial_Deposit cannot be numeric - Step 01:  Input numeric to 'Initial_Deposit' textbox with value: 'anhtran123'");
		newCusomerPage.sendKeyToTextboxByName(driver, "anhtran123", "inideposit");

		log.info("New_Account_Initial_Deposit cannot be numeric - Step 02: Verify warning message is displayed with 'Characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "19"), "Characters are not allowed");
	}

	@Test
	public void TC_08_Delete_Customer_Initial_Deposit_Can_Not_Have_Special_Characters() {

		log.info("New_Account_Initial_Deposit cannot have special charater - Step 01:  Input to 'Initial_Deposit' textbox with value: '123@#'");
		newCusomerPage.sendKeyToTextboxByName(driver, "123@#", "inideposit");

		log.info("New_Account_Initial_Deposit cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "19"), "Special characters are not allowed");
	}

	@Test
	public void TC_09_New_Account_Initial_Deposit_Can_Not_Have_First_Character_As_Blank() {

		log.info("New_Account_Initial_Deposit cannot have first character as blank space - Step 01:  Input to 'Initial_Deposit' textbox with value: ' 123456'");
		newCusomerPage.sendKeyToTextboxByName(driver, " 123456", "inideposit");

		log.info("New_Account_Initial_Deposit cannot have first character as blank space - Step 02: Verify warning message is displayed with 'First character can not have space'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "19"), "First character can not have space");
	}

	@Test
	public void TC_10_New_Account_Initial_Deposit_Can_Not_Have_Blank_Space() {

		log.info("New_Account_Initial_Deposit cannot have blank space - Step 01:  Input to 'Initial_Deposit' textbox with value: '123 34'");
		newCusomerPage.sendKeyToTextboxByName(driver, "123 34", "inideposit");

		log.info("New_Account_Initial_Deposit cannot have blank space - Step 02: Verify warning message is displayed with 'Characters are not allowed'");
		verifyEquals(newCusomerPage.getWarningMessageByID(driver, "19"), "Characters are not allowed");
	}

	@Test
	public void TC_11_New_Account_Create_A_New_Account() {
		log.info("New_Account_CustomerID Create a new account - Step 01: Input valid customerID to 'Customer ID' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, customerID, "cusid");

		log.info("New_Account_CustomerID Create a new account - Step 02: Input to 'Initial_Deposit' textbox with valid value");
		newCusomerPage.sendKeyToTextboxByName(driver, inideposit, "inideposit");

		log.info("New_Account_CustomerID Create a new account - Step 03: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "button2");

		log.info("New_Account_CustomerID Create a new account - Step 04: Verify success message is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Account Generated Successfully!!!"));

		log.info("New_Account_CustomerID Create a new account - Step 05: Get 'Account ID'");
		accountID = newCusomerPage.getAccountID();

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	String url, emailID, userID, passwordLogin;
	String email, customerName, addr, state, city, pin, mobileNumber, password, dob, customerID, inideposit, accountID;
	WebDriver driver;
	FakerConfig fakeData;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	CustomerPageObject newCusomerPage;
}
