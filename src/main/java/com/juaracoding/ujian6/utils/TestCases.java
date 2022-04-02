package com.juaracoding.ujian6.utils;

public enum TestCases {

	T1("Testing Register Customer"), T2("Testing Login Customer"), T3("Testing Order Customer");

	private String testName;

	TestCases(String value) {
		this.testName = value;
	}

	public String getTestName() {
		return testName;
	}
}
