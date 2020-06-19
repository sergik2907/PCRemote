package com.pccontroll.ui.components;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import com.google.android.material.textfield.TextInputEditText;
import com.pccontroll.integration.KeyCodeMapper;
import com.pccontroll.integration.WebSocketConnector;
import com.pccontroll.model.Field;
import java.util.Locale;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/02
 */
public class KeyboardInputEditText extends TextInputEditText {

	public static final String SHIFT = "shift";

	public KeyboardInputEditText(Context context) {
		super(context);
		addTextChangedListener(new KeyboardTextWatcher());
	}

	public KeyboardInputEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		addTextChangedListener(new KeyboardTextWatcher());
	}

	public KeyboardInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		addTextChangedListener(new KeyboardTextWatcher());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		String value = KeyCodeMapper.map(keyCode);
		if (value != null) {
			send(value);
		}
		return super.onKeyDown(keyCode, event);
	}

	private class KeyboardTextWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			String value = String.valueOf(s);
			String shift = value.matches("[A-Z]") ? SHIFT : "";
			send(shift + (int) value.charAt(0));
			removeTextChangedListener(this);
			getText().clear();
			addTextChangedListener(this);

		}
	}

	private void send(String value) {
		final String valueToSend = Field.Keyboard.getApiKey() + "=" + value;
		InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
		final String locale = ims.getLanguageTag();
		if (locale.contains(Locale.ENGLISH.getLanguage())) {
			new WebSocketConnector(getContext()).send(valueToSend);
		}
	}
}
