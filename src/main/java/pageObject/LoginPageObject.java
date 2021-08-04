package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.LoginPageUI;

public class LoginPageObject extends BasePage {
	
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToHereLink() {
		waitforElementClickable(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);	
	}

}
