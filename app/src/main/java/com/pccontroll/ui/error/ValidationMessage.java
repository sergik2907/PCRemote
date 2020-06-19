package com.pccontroll.ui.error;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public enum  ValidationMessage {

	IsMissing("Value is missing"),
	IsInvalid("Value is invalid");

	private String message;

	ValidationMessage(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
