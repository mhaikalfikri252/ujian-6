package com.juaracoding.ujian6.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.juaracoding.ujian6.drivers.DriverSingleton;

public class CheckoutPage {
	WebDriver driver;

	public CheckoutPage() {
		this.driver = DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "billing_first_name")
	WebElement firstName;

	@FindBy(id = "billing_last_name")
	WebElement lastName;

	@FindBy(id = "billing_company")
	WebElement company;

	@FindBy(id = "select2-billing_country-container")
	WebElement country;

	@FindBy(id = "billing_address_1")
	WebElement address1;

	@FindBy(id = "billing_address_2")
	WebElement address2;

	@FindBy(id = "billing_city")
	WebElement town;

	@FindBy(id = "billing_state")
	WebElement state;

	@FindBy(id = "select2-billing_state-container")
	WebElement province;

	@FindBy(id = "billing_postcode")
	WebElement postCode;

	@FindBy(id = "billing_phone")
	WebElement phone;

	@FindBy(id = "billing_email")
	WebElement email;

	@FindBy(id = "order_comments")
	WebElement orderComment;

	@FindBy(css = "#payment > ul > li > label")
	WebElement cashOnLabel;

	@FindBy(id = "terms")
	WebElement btnAgree;

	@FindBy(id = "place_order")
	WebElement btnPlaceOrder;

	@FindBy(css = "#post-7 > div > div > div > p.woocommerce-thankyou-order-received")
	WebElement textInvoice;

	public void selectCountry(int pilih) {
		country.click();
		List<Keys> listState = new ArrayList<Keys>();
		for (int i = 0; i < pilih; i++) {
			listState.add(Keys.DOWN);
		}
		listState.add(Keys.ENTER);
		CharSequence[] cs = listState.toArray(new CharSequence[listState.size()]);
		Actions keyDown = new Actions(driver);
		keyDown.sendKeys(Keys.chord(cs)).perform();
	}

	public void selectProvince(int pilih) {
		province.click();
		List<Keys> listState = new ArrayList<Keys>();
		for (int i = 0; i < pilih; i++) {
			listState.add(Keys.DOWN);
		}
		listState.add(Keys.ENTER);
		CharSequence[] cs = listState.toArray(new CharSequence[listState.size()]);
		Actions keyDown = new Actions(driver);
		keyDown.sendKeys(Keys.chord(cs)).perform();
	}

	public void checkoutProduct() {
		firstName.clear();
		firstName.sendKeys("test");
		lastName.clear();
		lastName.sendKeys("order");
		company.clear();
		company.sendKeys("company");
		selectCountry(1);
		address1.clear();
		address1.sendKeys("jl.amd");
		address2.clear();
		address2.sendKeys("no.10");
		town.clear();
		town.sendKeys("citytown");
//		selectProvince(1);
		postCode.clear();
		postCode.sendKeys("23118");
		phone.clear();
		phone.sendKeys("081269696868");
		email.clear();
		email.sendKeys("haikalfikri106@gmail.com");
		orderComment.sendKeys("mantap");
		tunggu(1);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView();", cashOnLabel);
		btnAgree.click();
		btnPlaceOrder.click();
	}

	public String getTextInvoice() {
		return textInvoice.getText();
	}

	public static void tunggu(int detik) {
		try {
			Thread.sleep(detik * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
