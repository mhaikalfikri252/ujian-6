package com.juaracoding.ujian6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.juaracoding.ujian6.drivers.DriverSingleton;

public class RegisterPage {
	WebDriver driver;

	public RegisterPage() {
		this.driver = DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "body > p > a")
	WebElement dismiss;

	@FindBy(css = "#noo-site > header > div.noo-topbar > div > ul.pull-right.noo-topbar-right > li:nth-child(2) > a")
	WebElement myAccount;

	@FindBy(id = "reg_username")
	WebElement userName;

	@FindBy(id = "reg_email")
	WebElement userEmail;

	@FindBy(id = "reg_password")
	WebElement userPassword;

	@FindBy(css = "#customer_login > div.u-column2.col-2 > form > p:nth-child(5) > button")
	WebElement btnRegister;

	@FindBy(css = "#post-8 > div > div > div.woocommerce-notices-wrapper > ul > li > strong")
	WebElement textRegister;

	@FindBy(css = "#login > p.login.message")
	WebElement textLogin;

	public void registerSubmit(String username, String email, String password) {
		dismiss.click();
		myAccount.click();
		userName.sendKeys(username);
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		btnRegister.click();
	}

	public String getTextRegister() {
		return textRegister.getText();
	}

	public String getTextLogin() {
		return textLogin.getText();
	}
}
