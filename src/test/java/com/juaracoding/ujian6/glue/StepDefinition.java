package com.juaracoding.ujian6.glue;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.juaracoding.ujian6.config.AutomationFrameworkConfig;
import com.juaracoding.ujian6.drivers.DriverSingleton;
import com.juaracoding.ujian6.pages.CheckoutPage;
import com.juaracoding.ujian6.pages.LoginAfterRegisterPage;
import com.juaracoding.ujian6.pages.LoginPage;
import com.juaracoding.ujian6.pages.OrderPage;
import com.juaracoding.ujian6.pages.RegisterPage;
import com.juaracoding.ujian6.utils.ConfigurationProperties;
import com.juaracoding.ujian6.utils.Constants;
import com.juaracoding.ujian6.utils.TestCases;
import com.juaracoding.ujian6.utils.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfig.class)
public class StepDefinition {

	private static WebDriver driver;
	private RegisterPage registerPage;
	private LoginAfterRegisterPage loginAfterRegisterPage;
	private LoginPage loginPage;
	private OrderPage orderPage;
	private CheckoutPage checkoutPage;
	ExtentTest extentTest;
	static ExtentReports reports = new ExtentReports("src/main/resources/TestReport.html");

	@Autowired
	ConfigurationProperties configurationProperties;

	@Before
	public void initializeObjects() {
		DriverSingleton.getInstance(configurationProperties.getBrowser());
		registerPage = new RegisterPage();
		loginAfterRegisterPage = new LoginAfterRegisterPage();
		loginPage = new LoginPage();
		orderPage = new OrderPage();
		checkoutPage = new CheckoutPage();
		TestCases[] tests = TestCases.values();
		extentTest = reports.startTest(tests[Utils.testCount].getTestName());
		Utils.testCount++;
	}

	@AfterStep
	public void getResult(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			String screenshotPath = Utils.getScreenshot(driver, scenario.getName().replace(" ", "_"));
			extentTest.log(LogStatus.FAIL, "Screenshot:/n" + extentTest.addScreenCapture(screenshotPath));
		}
	}

	@After
	public void closeObject() {
		reports.endTest(extentTest);
		reports.flush();
	}

	@AfterAll
	public static void closeBrowser() {
//		driver.quit();
	}

	@Given("Customer mengakses url")
	public void customer_mengakses_url() {
		driver = DriverSingleton.getDriver();
		driver.get(Constants.URL);
		extentTest.log(LogStatus.PASS, "Navigating to " + Constants.URL);
	}

	@When("Customer klik register button")
	public void customer_klik_register_button() {
		registerPage.registerSubmit(configurationProperties.getUserName(), configurationProperties.getEmail(),
				configurationProperties.getPassword());
		extentTest.log(LogStatus.PASS, "Customer klik register button");
	}

	@Then("Customer sudah pernah membuat akun dengan email ini")
	public void customer_sudah_pernah_membuat_akun_dengan_email_ini() {
		tunggu();
		assertEquals(configurationProperties.getTextRegister(), registerPage.getTextRegister());
		extentTest.log(LogStatus.PASS, "Customer sudah pernah membuat akun dengan email ini");
	}

	@When("Customer klik login button")
	public void customer_klik_login_button() {
//		loginAfterRegisterPage.loginUser(configurationProperties.getEmail(), configurationProperties.getPassword());
		loginPage.submitLogin(configurationProperties.getEmail(), configurationProperties.getPassword());
		extentTest.log(LogStatus.PASS, "Customer klik login button");
	}

	@Then("Customer berhasil login")
	public void customer_berhasil_login() {
		tunggu();
		assertEquals(configurationProperties.getTextDashboard(), loginPage.getTextDashboard());
		extentTest.log(LogStatus.PASS, "Customer berhasil login");
	}

	@When("Customer memilih product")
	public void customer_memilih_product() {
		orderPage.selectProduct();
		extentTest.log(LogStatus.PASS, "Customer memilih product");
	}

	@And("Customer compare product")
	public void customer_compare_product() {
		orderPage.compareProduct();
		extentTest.log(LogStatus.PASS, "Customer compare product");
	}

	@And("Customer checkout product")
	public void customer_checkout_product() {
		checkoutPage.checkoutProduct();
		extentTest.log(LogStatus.PASS, "Customer checkout product");
	}

	@Then("Customer berhasil order")
	public void customer_berhasil_order() {
		assertEquals(configurationProperties.getTextInvoice(), checkoutPage.getTextInvoice());
		extentTest.log(LogStatus.PASS, "Customer berhasil order");
	}

	public static void tunggu() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scroll() {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,500)");
	}

}
