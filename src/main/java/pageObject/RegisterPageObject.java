package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getUserIDValue() {
		waitforElementVisible(driver, RegisterPageUI.USER_ID_TEXT_VALUE);
		return getElementTex(driver, RegisterPageUI.USER_ID_TEXT_VALUE);
	}

	public String getPasswordValue() {
		waitforElementVisible(driver, RegisterPageUI.PASSWORD_TEXT_VALUE);
		return getElementTex(driver, RegisterPageUI.PASSWORD_TEXT_VALUE);
	}
	
	
}
