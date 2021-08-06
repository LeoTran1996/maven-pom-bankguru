package bankguru.customer;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.CustomerPageObject;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.PageGeneratorManager;
import pageObject.RegisterPageObject;
import utilities.FakerConfig;

public class Customer_11_Payment extends BaseTest {

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

		emailEdit = fakeData.getEmailAddress();
		cityEdit = fakeData.getCityName();
		addrEdit = fakeData.getAddress();
		stateEdit = "Thanh Xuan";
		pinEdit = "888888";
		mobileNumberEdit = "0987193165";

		inideposit = "50000";

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

	}

	@Test
	public void Tc_01_Create_New_Customer_And_Check_Created_Successfully() {

		log.info("Create_New_Customer - Step 01: Click to 'New Customer' Link");
		homePage.openPageAtLeftSubMenuByText(driver, "New Customer");
		newCusomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		log.info("Create_New_Customer - Step 02: Input to 'Customer Name' textbox with value" + customerName);
		newCusomerPage.sendKeyToTextboxByName(driver, customerName, "name");

		log.info("Create_New_Customer - Step 03: Input to 'Date of Birth' textbox with value" + dayofBirth);
		newCusomerPage.sendKeyToTextboxByName(driver, dayofBirth, "dob");

		log.info("Create_New_Customer - Step 04: Input to 'Address' textbox with value" + addr);
		newCusomerPage.sendKeyToTextAreaByName(driver, addr, "addr");

		log.info("Create_New_Customer - Step 06: Input to 'City' textbox with value" + city);
		newCusomerPage.sendKeyToTextboxByName(driver, city, "city");

		log.info("Create_New_Customer - Step 07: Input to 'State' textbox with value" + state);
		newCusomerPage.sendKeyToTextboxByName(driver, state, "state");

		log.info("Create_New_Customer - Step 08: Input to 'PIN' textbox with value" + pin);
		newCusomerPage.sendKeyToTextboxByName(driver, pin, "pinno");

		log.info("Create_New_Customer - Step 09: Input to 'Mobile Number' textbox with value" + mobileNumber);
		newCusomerPage.sendKeyToTextboxByName(driver, mobileNumber, "telephoneno");

		log.info("Create_New_Customer - Step 10: Input to 'E-mail' textbox with value" + email);
		newCusomerPage.sendKeyToTextboxByName(driver, email, "emailid");

		log.info("Create_New_Customer - Step 11: Input to 'Password' textbox with value" + password);
		newCusomerPage.sendKeyToTextboxByName(driver, password, "password");

		log.info("Create_New_Customer - Step 12: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "sub");

		log.info("Create_New_Customer - Step 13: Verify success message is displayed with content 'Customer Registered Successfully!!!'");
		verifyTrue(newCusomerPage.isSuccessMessageDisplayed());

		log.info("Create_New_Customer - Step 14: Get 'Customer ID' text value");
		customerID = newCusomerPage.getCustomerID();

		log.info("Create_New_Customer - Step 15: Verify New Customer information");
		verifyEquals(newCusomerPage.getInformationByText(driver, "Customer Name"), customerName);
		verifyEquals(newCusomerPage.getInformationByText(driver, "Address"), addr);
		verifyEquals(newCusomerPage.getInformationByText(driver, "City"), city);
		verifyEquals(newCusomerPage.getInformationByText(driver, "State"), state);
		verifyEquals(newCusomerPage.getInformationByText(driver, "Pin"), pin);
		verifyEquals(newCusomerPage.getInformationByText(driver, "Mobile No."), mobileNumber);
		verifyEquals(newCusomerPage.getInformationByText(driver, "Email"), email);

	}

	@Test
	public void Tc_02_Edit_Customer_Account_And_Check_Edited_Successfully() {

		log.info("Edit_Customer_Account - Step 01: Click to 'Edit Customer' Link");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Edit Customer");

		log.info("Edit_Customer_Account - Step 02: Input valid CustomerID");
		newCusomerPage.sendKeyToTextboxByName(driver, customerID, "cusid");

		log.info("Edit_Customer_Account - Step 03: Click to Submitt button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Edit_Customer_Account - Step 04: Input new information for Edit Customer");
		newCusomerPage.sendKeyToTextAreaByName(driver, addrEdit, "addr");
		newCusomerPage.sendKeyToTextboxByName(driver, cityEdit, "city");
		newCusomerPage.sendKeyToTextboxByName(driver, stateEdit, "state");
		newCusomerPage.sendKeyToTextboxByName(driver, pinEdit, "pinno");
		newCusomerPage.sendKeyToTextboxByName(driver, mobileNumberEdit, "telephoneno");
		newCusomerPage.sendKeyToTextboxByName(driver, emailEdit, "emailid");

		log.info("Edit_Customer_Account - Step 05: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "sub");

		log.info("Edit_Customer_Account - Step 06: Verify login success message is displayed");
		verifyTrue(newCusomerPage.isEditedCustomerSuccessMessageDisplayed());

		log.info("Edit_Customer_Account - Step 06: Verify Edit New Customer information");

		verifyEquals(newCusomerPage.getInformationByText(driver, "Address"), addrEdit);
		verifyEquals(newCusomerPage.getInformationByText(driver, "City"), cityEdit);
		verifyEquals(newCusomerPage.getInformationByText(driver, "State"), stateEdit);
		verifyEquals(newCusomerPage.getInformationByText(driver, "Pin"), pinEdit);
		verifyEquals(newCusomerPage.getInformationByText(driver, "Mobile No."), mobileNumberEdit);
		verifyEquals(newCusomerPage.getInformationByText(driver, "Email"), emailEdit);
	}

	@Test
	public void Tc_03_Add_New_Account_And_Check_Created_Successfully() {

		log.info("New_Account Create a new account - Step 01: Click to 'New Account' Link");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "New Account");

		log.info("New_Account Create a new account - Step 02: Input valid customerID to 'Customer ID' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, customerID, "cusid");

		log.info("New_Account Create a new account - Step 03: Input to 'Initial_Deposit' textbox with valid value");
		newCusomerPage.sendKeyToTextboxByName(driver, inideposit, "inideposit");

		log.info("New_Account Create a new account - Step 04: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "button2");

		log.info("New_Account Create a new account - Step 05: Verify success message is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Account Generated Successfully!!!"));

		log.info("New_Account Create a new account - Step 06: Verify 'Current Amount'");
		verifyEquals(newCusomerPage.getInformationByText(driver, "Current Amount"), inideposit);

		log.info("New_Account Create a new account - Step 07: Get 'Account ID'");
		accountID = newCusomerPage.getInformationByText(driver, "Account ID");

	}

	@Test
	public void Tc_04_Edit_Account_With_Type_Of_Account_Is_Current() {

		log.info("Edit_Account with Type is Current - Step 01: Click to 'Edit Account' Link");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Edit Account");

		log.info("Edit_Account with Type is Current - Step 02: Input valid AccountID to 'Account No'");
		newCusomerPage.sendKeyToTextboxByName(driver, accountID, "accountno");

		log.info("Edit_Account with Type is Current - Step 03: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Edit_Account with Type is Current - Step 04: Select 'Current' option in 'Type of Account'");
		newCusomerPage.selectCurrentInDropDown(driver, "Current");

		log.info("Edit_Account with Type is Current - Step 05: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Edit_Account with Type is Current - Step 06: Verify success message is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Account details updated Successfully!!!"));

		log.info("Edit_Account with Type is Current - Step 07: Verify 'Account Type' is changed to 'Current'");
		verifyEquals(newCusomerPage.getInformationByText(driver, "Account Type"), "Current");

	}

	@Test
	public void Tc_05_Transfer_Money_To_Current_Account() {

		log.info("Transfer_Money to Current Account - Step 01: Click to 'Deposit' in left Menu");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Deposit");

		log.info("Transfer_Money to Current Account - Step 02: Input valid AccountID to 'Account No' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, accountID, "accountno");

		log.info("Transfer_Money to Current Account - Step 03: Input valid Amount to 'Amount' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "5000", "ammount");

		log.info("Transfer_Money to Current Account - Step 04: Input valid Description to 'Description' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "giay", "desc");

		log.info("Transfer_Money to Current Account - Step 05: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Transfer_Money to Current Account - Step 06: Verify tranfer message success");
		verifyEquals(newCusomerPage.getTranfersSuccessMessageText(), "Transaction details of Deposit for Account " + accountID);

		log.info("Transfer_Money to Current Account - Step 07: Verify Current Balance after transfer");
		verifyEquals(newCusomerPage.getInformationByText(driver, "Current Balance"), "55000");

	}

	@Test
	public void Tc_06_Withdrawal_Money_From_Current_Account_And_Check_Account_Balance() {

		log.info("Withdrawal_Money from Current Account - Step 01: Click to 'Withdrawal' in left Menu");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Withdrawal");

		log.info("Withdrawal_Money from Current Account - Step 02: Verify 'Amount Withdrawal Form' heading is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Amount Withdrawal Form"));

		log.info("Withdrawal_Money from Current Account - Step 03: Input valid AccountID to 'Account No' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, accountID, "accountno");

		log.info("Withdrawal_Money from Current Account - Step 04: Input valid Amount to 'Amount' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "15000", "ammount");

		log.info("Withdrawal_Money from Current Account - Step 05: Input valid Description to 'Description' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "nothing", "desc");

		log.info("Withdrawal_Money from Current Account - Step 06: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Withdrawal_Money from Current Account - Step 07: Verify tranfer message success");
		verifyEquals(newCusomerPage.getTranfersSuccessMessageText(), "Transaction details of Withdrawal for Account " + accountID);

		log.info("Withdrawal_Money from Current Account - Step 08: Verify Current Balance after transfer");
		verifyEquals(newCusomerPage.getInformationByText(driver, "Current Balance"), "40000");

	}

	@Test
	public void Tc_07_Transfer_Money_To_Another_Account_And_Check_Amount() {

		log.info("Transfer_Money_To_Another_Account add another account - Step 01: Click to 'New Account' Link");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "New Account");

		log.info("Transfer_Money_To_Another_Account add another account - Step 02: Input valid customerID to 'Customer ID' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, customerID, "cusid");

		log.info("Transfer_Money_To_Another_Account add another account - Step 03: Input to 'Initial_Deposit' textbox with valid value");
		newCusomerPage.sendKeyToTextboxByName(driver, inideposit, "inideposit");

		log.info("Transfer_Money_To_Another_Account add another account - Step 04: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "button2");

		log.info("Transfer_Money_To_Another_Account add another account - Step 05: Verify success message is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Account Generated Successfully!!!"));

		log.info("Transfer_Money_To_Another_Account add another account - Step 06: Get 'Account ID'");
		accountID2 = newCusomerPage.getInformationByText(driver, "Account ID");

		log.info("Transfer_Money_To_Another_Account - Step 07: Click to 'Fund Transfer' Link");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Fund Transfer");

		log.info("Transfer_Money_To_Another_Account - Step 08: Verify 'Fund transfer' heading is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Fund transfer"));

		log.info("Transfer_Money_To_Another_Account - Step 09: Input valid AccountID 'Payers account no'");
		newCusomerPage.sendKeyToTextboxByName(driver, accountID, "payersaccount");

		log.info("Transfer_Money_To_Another_Account - Step 10: Input valid AccountID 'Payees account no'");
		newCusomerPage.sendKeyToTextboxByName(driver, accountID2, "payeeaccount");

		log.info("Transfer_Money_To_Another_Account - Step 11: Input valid value to 'Amount'");
		newCusomerPage.sendKeyToTextboxByName(driver, "10000", "ammount");

		log.info("Transfer_Money_To_Another_Account - Step 12: Input valid Description to 'Description' textbox");
		newCusomerPage.sendKeyToTextboxByName(driver, "nothing", "desc");

		log.info("Transfer_Money_To_Another_Account - Step 13: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Transfer_Money_To_Another_Account - Step 14: Verify 'Fund Transfer Details' heading is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Fund Transfer Details"));

		log.info("Transfer_Money_To_Another_Account - Step 15: Verify 'Amount' is correct");
		verifyEquals(newCusomerPage.getInformationByText(driver, "Amount"), "10000");

	}

	@Test
	public void Tc_08_Check_Current_Account_Balance() {
		log.info("Check_Current_Account_Balance - Step 01: Click to 'Balance Enquiry' Link");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Balance Enquiry");

		log.info("Check_Current_Account_Balance - Step 02: Verify 'Balance Enquiry Form' heading is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Balance Enquiry Form"));

		log.info("Check_Current_Account_Balance - Step 03: Input valid AccountID to 'Account No'");
		newCusomerPage.sendKeyToTextboxByName(driver, accountID, "accountno");

		log.info("Check_Current_Account_Balance - Step 04: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Check_Current_Account_Balance - Step 05: Verify 'Balance Details' heading is displayed");
		verifyEquals(newCusomerPage.getBalanceSuccessMessageText(), "Balance Details for Account " + accountID);

		log.info("Check_Current_Account_Balance - Step 06: Verify 'Balance' is correct");
		verifyEquals(newCusomerPage.getInformationByText(driver, "Balance"), "30000");
	}

	@Test
	public void Tc_09_Delete_Account_And_Check_Delete_Succesfully() {
		log.info("Delete_Account - Step 01: Click to 'Delete Account' label in the left Menu");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Delete Account");

		log.info("Delete_Account - Step 02: Verify 'Delete Account Form' heading is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Delete Account Form"));

		log.info("Delete_Account - Step 03: Input valid AccountID to 'Account No'");
		newCusomerPage.sendKeyToTextboxByName(driver, accountID, "accountno");

		log.info("Delete_Account - Step 04: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Delete_Account - Step 05: Accept and verify Alert");
		newCusomerPage.acceptAlert(driver);
		verifyEquals(newCusomerPage.getAlertText(driver), "Account Deleted Sucessfully");
		newCusomerPage.acceptAlert(driver);
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Delete_Account - Step 06: Click to 'Edit Account' label in the left Menu");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Edit Account");

		log.info("Delete_Account - Step 07: Verify 'Edit Account Form' heading is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Edit Account Form"));

		log.info("Delete_Account - Step 08: Input valid AccountID to 'Account No'");
		newCusomerPage.sendKeyToTextboxByName(driver, accountID, "accountno");

		log.info("Delete_Account - Step 09: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Delete_Account - Step 10: Accept to Alert and verify 'Account does not exist' is displayed");
		verifyEquals(newCusomerPage.getAlertText(driver), "Account does not exist");
		newCusomerPage.acceptAlert(driver);

		log.info("Delete_Account2 - Step 01: Click to 'Delete Account' label in the left Menu");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Delete Account");

		log.info("Delete_Account2 - Step 02: Verify 'Delete Account Form' heading is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Delete Account Form"));

		log.info("Delete_Account2 - Step 03: Input valid AccountID to 'Account No'");
		newCusomerPage.sendKeyToTextboxByName(driver, accountID2, "accountno");

		log.info("Delete_Account2 - Step 04: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Delete_Account2 - Step 05: Accept and verify Alert");
		newCusomerPage.acceptAlert(driver);
		verifyEquals(newCusomerPage.getAlertText(driver), "Account Deleted Sucessfully");
		newCusomerPage.acceptAlert(driver);
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Delete_Account2 - Step 06: Click to 'Edit Account' label in the left Menu");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Edit Account");

		log.info("Delete_Account2 - Step 07: Verify 'Edit Account Form' heading is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Edit Account Form"));

		log.info("Delete_Account2 - Step 08: Input valid AccountID to 'Account No'");
		newCusomerPage.sendKeyToTextboxByName(driver, accountID2, "accountno");

		log.info("Delete_Account2 - Step 09: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Delete_Account2 - Step 10: Accept to Alert and verify 'Account does not exist' is displayed");
		verifyEquals(newCusomerPage.getAlertText(driver), "Account does not exist");
		newCusomerPage.acceptAlert(driver);

	}

	@Test
	public void Tc_10_Delete_Customer_And_Check_Delete_Succesfully() {
		log.info("Delete_Customer - Step 01: Click to 'Delete Customer' label in the left Menu");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Delete Customer");

		log.info("Delete_Customer - Step 02: Verify 'Delete Customer Form' heading is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Delete Customer Form"));

		log.info("Delete_Customer - Step 03: Input valid CustomerID to 'Customer ID'");
		newCusomerPage.sendKeyToTextboxByName(driver, customerID, "cusid");

		log.info("Delete_Customer - Step 04: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");
		log.info("Delete_Customer - Step 05: Accept and verify Alert");
		newCusomerPage.acceptAlert(driver);

		verifyEquals(newCusomerPage.getAlertText(driver), "Customer deleted Successfully");
		newCusomerPage.acceptAlert(driver);
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Delete_Customer - Step 06: Click to 'Delete Customer' label in the left Menu");
		newCusomerPage.openPageAtLeftSubMenuByText(driver, "Delete Customer");

		log.info("Delete_Customer - Step 07: Verify 'Delete Customer Form' heading is displayed");
		verifyTrue(newCusomerPage.isHeadinglDisplayedByTextName(driver, "Delete Customer Form"));

		log.info("Delete_Customer - Step 08: Input valid CustomerID to 'CustomerID'");
		newCusomerPage.sendKeyToTextboxByName(driver, customerID, "cusid");

		log.info("Delete_Customer - Step 09: Click to 'Submit' button");
		newCusomerPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Delete_Customer - Step 10: Accept to Alert and verify 'Account does not exist' is displayed");
		newCusomerPage.acceptAlert(driver);
		verifyEquals(newCusomerPage.getAlertText(driver), "Customer does not exist!!");
		newCusomerPage.acceptAlert(driver);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	String url, emailID, userID, passwordLogin;
	String email, customerName, addr, state, city, pin, mobileNumber, password, dayofBirth, customerID;
	String emailEdit, cityEdit, addrEdit, stateEdit, pinEdit, mobileNumberEdit;
	String accountID, accountID2, inideposit;
	WebDriver driver;
	FakerConfig fakeData;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	CustomerPageObject newCusomerPage;
}
