package com.pccontroll.util;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public enum ValidationRegex {

	IP("\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");

	private String regex;

	ValidationRegex(String regex) {
		this.regex = regex;
	}

	public String getRegex() {
		return regex;
	}
}
