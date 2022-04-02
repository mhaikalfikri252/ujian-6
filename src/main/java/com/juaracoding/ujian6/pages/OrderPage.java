package com.juaracoding.ujian6.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.juaracoding.ujian6.drivers.DriverSingleton;

public class OrderPage {
	WebDriver driver;

	public OrderPage() {
		this.driver = DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#noo-site > header > div.navbar-wrapper > div > div > div > a")
	WebElement btnSearch;

	@FindBy(css = "#noo-site > header > div.search-header.search-header-eff > div.noo-container > form > input.form-control")
	WebElement searchItem;

	@FindBy(xpath = "//*[@id=\"noo-site\"]/div[2]/div[2]/div/div/div[1]/div/div[1]/div[2]/div[1]/div/div[1]/a")
	WebElement dress1;

	@FindBy(xpath = "//*[@id=\"noo-site\"]/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/a")
	WebElement dress2;

	@FindBy(id = "pa_color")
	WebElement selectColor;

	@FindBy(id = "pa_size")
	WebElement selectSize;

	@FindBy(className = "qty-increase")
	WebElement btnPlus;

	@FindBy(css = "#product-1467 > div.single-product-content > div.summary.entry-summary > a")
	WebElement btnCompare;

	@FindBy(css = "#DataTables_Table_1 > tbody > tr.add-to-cart.odd > td > a")
	WebElement selectOptions;

	@FindBy(css = "#product-1473 > div.single-product-content > div.summary.entry-summary > form > div > div.woocommerce-variation-add-to-cart.variations_button.woocommerce-variation-add-to-cart-enabled > button")
	WebElement btnAdd1;

	@FindBy(css = "#product-1467 > div.single-product-content > div.summary.entry-summary > form > div > div.woocommerce-variation-add-to-cart.variations_button.woocommerce-variation-add-to-cart-enabled > button")
	WebElement btnAdd2;

	@FindBy(css = "#nav-menu-item-cart > a")
	WebElement btnCart;

	@FindBy(id = "cboxClose")
	WebElement btnCloseCompare;

	@FindBy(id = "#product-1473 > div.single-product-content > div.summary.entry-summary > div.yith-wcwl-add-to-wishlist.add-to-wishlist-1474.wishlist-fragment.on-first-load > div > a")
	WebElement btnWishlist;

	@FindBy(css = "#post-6 > div > div > div.cart-collaterals > div.cart_totals > div > a")
	WebElement btnCheckout;

	public void selectColor(int pilih) {
		selectColor.click();
		List<Keys> listState = new ArrayList<Keys>();
		for (int i = 0; i < pilih; i++) {
			listState.add(Keys.DOWN);
		}
		listState.add(Keys.ENTER);
		CharSequence[] cs = listState.toArray(new CharSequence[listState.size()]);
		Actions keyDown = new Actions(driver);
		keyDown.sendKeys(Keys.chord(cs)).perform();
	}

	public void selectSize(int pilih) {
		selectSize.click();
		List<Keys> listState = new ArrayList<Keys>();
		for (int i = 0; i < pilih; i++) {
			listState.add(Keys.DOWN);
		}
		listState.add(Keys.ENTER);
		CharSequence[] cs = listState.toArray(new CharSequence[listState.size()]);
		Actions keyDown = new Actions(driver);
		keyDown.sendKeys(Keys.chord(cs)).perform();
	}

	public void selectProduct() {
		btnSearch.click();
		searchItem.sendKeys("DRESS");
		searchItem.sendKeys(Keys.ENTER);
		tunggu(1);
		dress1.click();
		selectColor(1);
		selectSize(3);
		btnPlus.click();
		btnAdd1.click();
	}

	public void compareProduct() {
		btnSearch.click();
		searchItem.sendKeys("DRESS");
		searchItem.sendKeys(Keys.ENTER);
		tunggu(1);
		dress2.click();
		selectColor(1);
		selectSize(2);
//		JavascriptExecutor je = (JavascriptExecutor) driver;
//		je.executeScript("arguments[0].scrollIntoView();", btnWishlist);
		tunggu(1);
//		btnPlus.click();
		btnCompare.click();
		tunggu(5);
		btnCloseCompare.click();
		tunggu(1);
		btnAdd2.click();
		tunggu(1);
		btnCart.click();
		btnCheckout.click();
	}

	public static void tunggu(int detik) {
		try {
			Thread.sleep(detik * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
