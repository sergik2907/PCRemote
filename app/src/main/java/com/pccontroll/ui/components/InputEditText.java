package com.pccontroll.ui.components;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import androidx.databinding.BindingAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.pccontroll.ui.LiveDataController;
import com.pccontroll.util.UIUtil;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2019/09/26
 */
public class InputEditText extends TextInputEditText {

	protected static final String SUPER_PARCELABLE_STATE_KEY = "superParcelableState";

	private static final String FIELD = "field";

	private String field;

	public InputEditText(Context context) {
		super(context);
	}

	public InputEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable(SUPER_PARCELABLE_STATE_KEY, super.onSaveInstanceState());
		bundle.putString(FIELD, field);
		InputMethodManager mgr = (InputMethodManager)
				getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.hideSoftInputFromWindow(this.getWindowToken(), 0);
		UIUtil.hideAndroidSystemButtons(getRootView());
		return bundle;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		Bundle bundle = (Bundle) state;
		field = bundle.getString(FIELD);
		super.onRestoreInstanceState(bundle.getParcelable(SUPER_PARCELABLE_STATE_KEY));
	}

	@Override
	public boolean onKeyPreIme(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			InputMethodManager mgr = (InputMethodManager)
					getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			mgr.hideSoftInputFromWindow(this.getWindowToken(), 0);
			UIUtil.hideAndroidSystemButtons(getRootView());
		}
		return false;
	}

	@Override
	protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
		LiveDataController.setValidationMap(null);
	}

	@BindingAdapter("field")
	public static void setField(InputEditText inputEditText, String field) {
		inputEditText.setField(field);
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}
}
