package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToEmailTextbox(String email) {
		waitforElementVisible(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_ID_TEXTBOX, email);
		
	}

	public void clickToSubmitButtonAtRegisterPage() {
		waitforElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
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
