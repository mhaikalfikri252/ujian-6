package com.juaracoding.ujian6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.juaracoding.ujian6.drivers.DriverSingleton;

public class LoginAfterRegisterPage {
	WebDriver driver;

	public LoginAfterRegisterPage() {
		this.driver = DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user_login")
	WebElement userLogin;

	@FindBy(id = "user_pass")
	WebElement userPass;

	@FindBy(id = "wp-submit")
	WebElement btnLogin;

	public void loginUser(String email, String password) {
		userLogin.sendKeys(email);
		userPass.sendKeys(password);
		btnLogin.click();
	}
}
