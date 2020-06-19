package com.pccontroll.ui;

import androidx.lifecycle.MutableLiveData;
import com.pccontroll.model.Field;
import com.pccontroll.ui.error.ValidationMessage;
import java.util.Map;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class LiveDataController {
	private static MutableLiveData<Map<Field, ValidationMessage>> validationMap = new MutableLiveData<>();
	private static MutableLiveData<Boolean> isGamePadSettingsOpened = new MutableLiveData<>();
	private static MutableLiveData<Integer> gamePadBind = new MutableLiveData<>();

	public static MutableLiveData<Map<Field, ValidationMessage>> getValidationMap() {
		return validationMap;
	}

	public static void setValidationMap(Map<Field, ValidationMessage> validationMap) {
		getValidationMap().setValue(validationMap);
	}

	public static MutableLiveData<Boolean> getIsGamePadSettingsOpened() {
		return isGamePadSettingsOpened;
	}

	public static void setIsGamePadSettingsOpened(Boolean isGamePadSettingsOpened) {
		LiveDataController.isGamePadSettingsOpened.setValue(isGamePadSettingsOpened);
	}

	public static MutableLiveData<Integer> getGamePadBind() {
		return gamePadBind;
	}

	public static void setGamePadBind(MutableLiveData<Integer> gamePadBind) {
		LiveDataController.gamePadBind = gamePadBind;
	}
}
