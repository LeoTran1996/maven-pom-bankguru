package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class NewCustomerPageObject extends BasePage {

	WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
