package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomePageUI;

public class HomePageObject extends BasePage {

	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getSuccessMessageText() {
		waitforElementVisible(driver, HomePageUI.SUCCESS_MESSAGE);
		return getElementTex(driver, HomePageUI.SUCCESS_MESSAGE);
	}
}
