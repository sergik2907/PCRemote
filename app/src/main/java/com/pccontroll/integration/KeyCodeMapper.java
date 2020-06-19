package com.pccontroll.integration;

import android.view.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class KeyCodeMapper {

	private static final Map<Integer, String> keyCodes = new HashMap<>();

	static {
		keyCodes.put(KeyEvent.KEYCODE_DEL, "backspace");
	}

	public static String map(Integer value) {
		return keyCodes.get(value);
	}
}
