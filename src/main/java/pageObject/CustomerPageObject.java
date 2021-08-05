package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.CustomerPageUI;

public class CustomerPageObject extends BasePage {

	WebDriver driver;

	public CustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSuccessMessageDisplayed() {
		waitforElementVisible(driver, CustomerPageUI.CREATED_NEW_CUS_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, CustomerPageUI.CREATED_NEW_CUS_SUCCESS_MESSAGE);
	}
	
	public boolean isMiniStateMessageDisplayed() {
		waitforElementVisible(driver, CustomerPageUI.MINI_STATE_MESSAGE);
		return isElementDisplayed(driver, CustomerPageUI.MINI_STATE_MESSAGE);
	}
	
	public boolean isCustomisedMessageDisplayed() {
		waitforElementVisible(driver, CustomerPageUI.CUSTOMISED_STATEMENT_MESSAGE);
		return isElementDisplayed(driver, CustomerPageUI.CUSTOMISED_STATEMENT_MESSAGE);
	}

	public String getCustomerID() {
		waitforElementVisible(driver, CustomerPageUI.CUSTOMER_ID);
		return getElementTex(driver, CustomerPageUI.CUSTOMER_ID);
	}

	public String getAccountID() {
		waitforElementVisible(driver, CustomerPageUI.ACCOUNT_ID);
		return getElementTex(driver, CustomerPageUI.ACCOUNT_ID);
	}

	public String getCustomerIDAttributeValue() {
		waitforElementVisible(driver, CustomerPageUI.CUSTOMER_ID_AT_EDIT_ACCOUNT_FORM);
		return getElementAttributeByName(driver, CustomerPageUI.CUSTOMER_ID_AT_EDIT_ACCOUNT_FORM, "value");
	}

	public String getBalanceIDAttributeValue() {
		waitforElementVisible(driver, CustomerPageUI.BALANCE_AT_EDIT_ACCOUNT_FORM);
		return getElementAttributeByName(driver, CustomerPageUI.BALANCE_AT_EDIT_ACCOUNT_FORM, "value");
	}

}
