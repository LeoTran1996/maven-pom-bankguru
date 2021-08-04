package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.CustomerPageUI;

public class NewCustomerPageObject extends BasePage {

	WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSuccessMessageDisplayed() {
		waitforElementVisible(driver, CustomerPageUI.CREATED_NEW_CUS_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, CustomerPageUI.CREATED_NEW_CUS_SUCCESS_MESSAGE);
	}

	public String getCustomerID() {
		waitforElementVisible(driver, CustomerPageUI.CUSTOMER_ID);
		return getElementTex(driver, CustomerPageUI.CUSTOMER_ID);
	}

}
