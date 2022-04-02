package com.juaracoding.ujian6.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("framework.properties")
public class ConfigurationProperties {

	@Value("${browser}")
	private String browser;

	@Value("${email}")
	private String email;

	@Value("${username}")
	private String userName;

	@Value("${password}")
	private String password;

	@Value("${textRegister}")
	private String textRegister;

	@Value("${textDashboard}")
	private String textDashboard;

	@Value("${textInvoice}")
	private String textInvoice;

	public String getTextRegister() {
		return textRegister;
	}

	public String getTextInvoice() {
		return textInvoice;
	}

	public String getBrowser() {
		return browser;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getTextDashboard() {
		return textDashboard;
	}

}
