package com.pccontroll.util;

import com.pccontroll.model.Field;
import com.pccontroll.ui.LiveDataController;
import com.pccontroll.ui.error.ValidationMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class ValidationUtil {

	public static boolean isValid(Field field, String value, ValidationRegex regex) {
		Map<Field, ValidationMessage> validationMap = new HashMap<>();
		if (value == null) {
			validationMap.put(field, ValidationMessage.IsMissing);
		} else if (!value.matches(regex.getRegex())) {
			validationMap.put(field, ValidationMessage.IsInvalid);
		}
		LiveDataController.setValidationMap(validationMap);
		return validationMap.isEmpty();
	}
}
