package com.juaracoding.ujian6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.juaracoding.ujian6.drivers.DriverSingleton;

public class LoginPage {
	WebDriver driver;

	public LoginPage() {
		this.driver = DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "body > p > a")
	WebElement dismiss;

	@FindBy(css = "#noo-site > header > div.noo-topbar > div > ul.pull-right.noo-topbar-right > li:nth-child(2) > a")
	WebElement myAccount;

	@FindBy(id = "username")
	WebElement userName;

	@FindBy(id = "password")
	WebElement userPassword;

	@FindBy(css = "#customer_login > div.u-column1.col-1 > form > p:nth-child(3) > button")
	WebElement btnLogin;

	@FindBy(css = "#post-8 > div > div > nav > ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--dashboard > a")
	WebElement textDashboard;

	public void submitLogin(String username, String password) {
//		dismiss.click();
//		myAccount.click();
		userName.clear();
		userName.sendKeys(username);
		userPassword.sendKeys(password);
		btnLogin.click();
	}

	public String getTextDashboard() {
		return textDashboard.getText();
	}
}
