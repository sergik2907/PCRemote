package com.pccontroll.model;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public enum Field {

	Ip("ip"),
	Address("address"),
	Keyboard("keyboard"),
	Mouse("mouse"),
	Button("button");

	private String apiKey;

	Field(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public static Field fromApiKey(String apiKey) {
		for (Field field : Field.values()) {
			if (field.getApiKey()!=null&&field.getApiKey().equals(apiKey)){
				return field;
			}
		}
		return null;
	}
}
