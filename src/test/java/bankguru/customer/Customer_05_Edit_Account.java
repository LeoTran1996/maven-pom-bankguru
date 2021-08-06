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

public class Customer_05_Edit_Account extends BaseTest {

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
		customerPage = PageGeneratorManager.getNewCustomerPage(driver);

		log.info("Pre-condition - Step 14: Input to 'Customer Name' textbox with value" + customerName);
		customerPage.sendKeyToTextboxByName(driver, customerName, "name");

		log.info("Pre-condition - Step 15: Input to 'Date of Birth' textbox with value" + dayofBirth);
		customerPage.sendKeyToTextboxByName(driver, dayofBirth, "dob");

		log.info("Pre-condition - Step 16: Input to 'Address' textbox with value" + addr);
		customerPage.sendKeyToTextAreaByName(driver, addr, "addr");

		log.info("Pre-condition - Step 17: Input to 'City' textbox with value" + city);
		customerPage.sendKeyToTextboxByName(driver, city, "city");

		log.info("Pre-condition - Step 18: Input to 'State' textbox with value" + state);
		customerPage.sendKeyToTextboxByName(driver, state, "state");

		log.info("Pre-condition - Step 19: Input to 'PIN' textbox with value" + pin);
		customerPage.sendKeyToTextboxByName(driver, pin, "pinno");

		log.info("Pre-condition - Step 20: Input to 'Mobile Number' textbox with value" + mobileNumber);
		customerPage.sendKeyToTextboxByName(driver, mobileNumber, "telephoneno");

		log.info("Pre-condition - Step 21: Input to 'E-mail' textbox with value" + email);
		customerPage.sendKeyToTextboxByName(driver, email, "emailid");

		log.info("Pre-condition - Step 22: Input to 'Password' textbox with value" + password);
		customerPage.sendKeyToTextboxByName(driver, password, "password");

		log.info("Pre-condition - Step 23: Click to 'Submit' button");
		customerPage.clickToButtonByName(driver, "sub");

		log.info("Pre-condition - Step 23: Verify success message is displayed with content 'Customer Registered Successfully!!!'");
		verifyTrue(customerPage.isSuccessMessageDisplayed());

		log.info("Pre-condition - Step 24: Get 'Customer ID' text value");
		customerID = customerPage.getCustomerID();

		log.info("Pre-condition - Step 25: Click to 'New Account' label");
		customerPage.openPageAtLeftSubMenuByText(driver, "New Account");

		log.info("Pre-condition - Step 26: Verify 'Add new account form Customer Form' heading is displayed");
		customerPage.isHeadinglDisplayedByTextName(driver, "Add new account form");

		log.info("Pre-condition - Step 27: Input valid customerID to 'Customer ID' textbox");
		customerPage.sendKeyToTextboxByName(driver, customerID, "cusid");

		log.info("Pre-condition - Step 28: Input to 'Initial_Deposit' textbox with valid value");
		customerPage.sendKeyToTextboxByName(driver, inideposit, "inideposit");

		log.info("Pre-condition - Step 29: Click to 'Submit' button");
		customerPage.clickToButtonByName(driver, "button2");

		log.info("Pre-condition - Step 30: Verify success message is displayed");
		verifyTrue(customerPage.isHeadinglDisplayedByTextName(driver, "Account Generated Successfully!!!"));

		log.info("Pre-condition - Step 31: Get 'Account ID'");
		accountNo = customerPage.getAccountID();

		log.info("Pre-condition - Step 32: Click to 'Edit Account' label");
		customerPage.openPageAtLeftSubMenuByText(driver, "Edit Account");

		log.info("Pre-condition - Step 33: Verify 'Edit Account Form' heading");
		customerPage.isHeadinglDisplayedByTextName(driver, "Edit Account Form");

	}

	@Test
	public void TC_01_Edit_Account_AccountNo_Canot_Be_Empty() {

		log.info("Edit_Account_AccountNo cannot be empty - Step 01: Input blank value to 'AccountNo' textbox");
		customerPage.sendKeyToTextboxByName(driver, "", "accountno");

		log.info("Edit_Account_AccountNo cannot be empty - Step 02: Press key TAB to 'AccountNo' textbox");
		customerPage.sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "accountno");

		log.info("Edit_Account_AccountNo cannot be empty - Step 03: Verify warning message is displayed with 'Account Number must not be blank'");
		verifyEquals(customerPage.getWarningMessageByID(driver, "2"), "Account Number must not be blank");

	}

	@Test
	public void TC_02_Edit_Account_AccountNo_Must_Be_Numeric() {

		log.info("Edit_Account_AccountNo cannot be numeric - Step 01:  Input numeric to 'AccountNo' textbox with value: 'anhtran123'");
		customerPage.sendKeyToTextboxByName(driver, "anhtran123", "accountno");

		log.info("Edit_Account_AccountNo cannot be numeric - Step 02: Verify warning message is displayed with 'Characters are not allowed'");
		verifyEquals(customerPage.getWarningMessageByID(driver, "2"), "Characters are not allowed");
	}

	@Test
	public void TC_03_Edit_Account_AccountNo_Can_Not_Have_Special_Characters() {

		log.info("Edit_Account_AccountNo cannot have special charater - Step 01:  Input to 'AccountNo' textbox with value: '123@#'");
		customerPage.sendKeyToTextboxByName(driver, "123@#", "accountno");

		log.info("Edit_Account_AccountNo cannot have special charater - Step 02: Verify warning message is displayed with 'Special characters are not allowed'");
		verifyEquals(customerPage.getWarningMessageByID(driver, "2"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_Edit_Account_AccountNo_Valid_AccountNo() {
		log.info("Edit_Account_AccountNo Valid AccountNo  - Step 01: Input valid AccountNo to 'AccountNo' textbox");
		customerPage.sendKeyToTextboxByName(driver, accountNo, "accountno");

		log.info("Edit_Account_AccountNo Valid AccountNo  - Step 02: Click to Submit button");
		customerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Edit_Account_AccountNo Valid AccountNo  - Step 03: Verify 'Edit Account Entry Formr' heading is displayed");
		customerPage.isHeadinglDisplayedByTextName(driver, "Edit Account Entry Form");

		log.info("Edit_Account_AccountNo Valid AccountNo  - Step 04: Verify 'CustomerID' and 'Balance' displayed correct");
		verifyEquals(customerPage.getCustomerIDAttributeValue(), customerID);
		verifyEquals(customerPage.getBalanceIDAttributeValue(), inideposit);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	String url, emailID, userID, passwordLogin;
	String email, customerName, addr, state, city, pin, mobileNumber, password, dayofBirth, customerID, inideposit, accountNo;
	WebDriver driver;
	FakerConfig fakeData;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	CustomerPageObject customerPage;
}
