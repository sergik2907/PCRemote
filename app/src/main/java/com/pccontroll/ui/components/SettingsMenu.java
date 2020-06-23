package com.pccontroll.ui.components;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.pccontroll.R;
import com.pccontroll.integration.WebSocketConnector;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/07
 */
public class SettingsMenu extends ViewGroup {

	private EditText nameEditText;
	private EditText widthEditText;
	private EditText heightEditText;
	private EditText xEditText;
	private EditText yEditText;
	private TextView bindCharTextView;
	private String name;
	private String width;
	private String height;
	private String x;
	private String y;
	private String bindChar;
	private GamePadButton.OnButtonAttributeChangeCallback callback;

	public SettingsMenu(Context context, GamePadButton.OnButtonAttributeChangeCallback callback, String name, Integer width, Integer height, Integer x, Integer y, Integer color, Integer bindChar) {
		super(context);
		this.name = name;
		this.width = String.valueOf(width);
		this.height = String.valueOf(height);
		this.x = String.valueOf(x);
		this.y = String.valueOf(y);
		if (bindChar != null) {
			this.bindChar = String.valueOf((char) (int) bindChar);
		}
		this.callback = callback;
	}

	public SettingsMenu(Context context) {
		super(context);
	}

	public View init() {
		View view = View.inflate(getContext(), R.layout.settings_menu, null);
		nameEditText = view.findViewById(R.id.name);
		nameEditText.setText(name);
		widthEditText = view.findViewById(R.id.width);
		widthEditText.setText(width);
		heightEditText = view.findViewById(R.id.height);
		heightEditText.setText(height);
		xEditText = view.findViewById(R.id.x);
		xEditText.setText(x);
		yEditText = view.findViewById(R.id.y);
		yEditText.setText(y);
		bindCharTextView = view.findViewById(R.id.bind_char);
		bindCharTextView.setText(bindChar);
		nameEditText.addTextChangedListener(new TextNotifier(ButtonAttribute.Name));
		widthEditText.addTextChangedListener(new TextNotifier(ButtonAttribute.Width));
		heightEditText.addTextChangedListener(new TextNotifier(ButtonAttribute.Height));
		xEditText.addTextChangedListener(new TextNotifier(ButtonAttribute.X));
		yEditText.addTextChangedListener(new TextNotifier(ButtonAttribute.Y));
		view.findViewById(R.id.bind).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new WebSocketConnector(getContext()).waitForResponse(new Binder() {
					@Override
					public void onReceive(String key) {
						bindCharTextView.post(new Runnable() {
							@Override
							public void run() {
								bindCharTextView.setText(String.valueOf((char) Integer.parseInt(key)));
								callback.onBind(key);
							}
						});
					}
				});
			}
		});
		view.findViewById(R.id.close).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((ConstraintLayout) view.getParent()).removeView(view);
			}
		});
		view.findViewById(R.id.remove).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((ConstraintLayout) view.getParent()).removeView(view);
				callback.remove();
			}
		});
		return view;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
	}

	public interface Binder {
		void onReceive(String key);
	}

	private class TextNotifier implements TextWatcher {

		private ButtonAttribute buttonAttribute;

		private TextNotifier(ButtonAttribute buttonAttribute) {
			this.buttonAttribute = buttonAttribute;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			String value = String.valueOf(s);
			switch (buttonAttribute) {
				case Width:
					if (value.isEmpty()) {
						return;
					}
					callback.onWidthChange(value);
					break;
				case Height:
					if (value.isEmpty()) {
						return;
					}
					callback.onHeightChange(value);
					break;
				case X:
					if (value.isEmpty()) {
						return;
					}
					callback.onXChange(value);
					break;
				case Y:
					if (value.isEmpty()) {
						return;
					}
					callback.onYChange(value);
					break;
				case Name:
					callback.onNameChange(value);
					break;
//				case Color:
//					callback.onColorChange(value);
			}
		}
	}
}
