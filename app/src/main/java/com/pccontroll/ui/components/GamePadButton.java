package com.pccontroll.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.google.android.material.button.MaterialButton;
import com.pccontroll.integration.WebSocketConnector;
import com.pccontroll.model.Field;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/07
 */
public class GamePadButton extends MaterialButton {

	private Integer bindChar;
	private Integer xMargin = 0;
	private Integer yMargin = 0;

	public GamePadButton(Context context) {
		super(context);
		init();
	}

	public GamePadButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public GamePadButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				SettingsMenu menu = new SettingsMenu(getContext(), new OnButtonAttributeChangeCallback() {
					@Override
					public void onNameChange(String s) {
						setText(s);
					}

					@Override
					public void onWidthChange(String s) {
						ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) GamePadButton.this.getLayoutParams();
						params.width = Integer.parseInt(s);
						GamePadButton.this.setLayoutParams(params);
					}

					@Override
					public void onHeightChange(String s) {
						ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) GamePadButton.this.getLayoutParams();
						params.height = Integer.parseInt(s);
						GamePadButton.this.setLayoutParams(params);
					}

					@Override
					public void onXChange(String s) {
						xMargin = Integer.valueOf(s);
						ConstraintSet constraintSet = new ConstraintSet();
						ConstraintLayout layout = (ConstraintLayout) v.getParent();
						constraintSet.clone(layout);
						constraintSet.connect(v.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, xMargin);
						constraintSet.connect(v.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, yMargin);
						constraintSet.applyTo(layout);
					}

					@Override
					public void onYChange(String s) {
						yMargin = Integer.valueOf(s);
						ConstraintSet constraintSet = new ConstraintSet();
						ConstraintLayout layout = (ConstraintLayout) v.getParent();
						constraintSet.clone(layout);
						constraintSet.connect(v.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, xMargin);
						constraintSet.connect(v.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, yMargin);
						constraintSet.applyTo(layout);
					}

					@Override
					public void onColorChange(String s) {
						GamePadButton.this.setBackgroundColor(Integer.parseInt(s));
					}

					@Override
					public void onBind(String s) {
						bindChar = Integer.valueOf(s);
					}

					@Override
					public void remove() {
						ConstraintLayout layout = (ConstraintLayout) getParent();
						layout.removeView(GamePadButton.this);
					}
				}, String.valueOf(getText()), getWidth(), getHeight(), getConstraintLayoutParams() == null ? 0 : getConstraintLayoutParams().leftMargin, getConstraintLayoutParams() == null ? 0 : getConstraintLayoutParams().topMargin, getBackgroundTintList().getDefaultColor(), bindChar);
				ConstraintLayout layout = (ConstraintLayout) getParent();
				View view = menu.init();
				view.setId(View.generateViewId());
				view.setLayoutParams(new ConstraintLayout.LayoutParams(600, 900));
				layout.addView(view);
				return false;
			}
		});
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (bindChar != null) {
					new WebSocketConnector(getContext()).send(Field.Button.getApiKey() + "=" + bindChar);
				}
			}
		});
	}

	private LinearLayout.LayoutParams getConstraintLayoutParams() {
		ViewGroup.LayoutParams params = getLayoutParams();
		return params instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) params : null;
	}

	public interface OnButtonAttributeChangeCallback {

		void onNameChange(String s);

		void onWidthChange(String s);

		void onHeightChange(String s);

		void onXChange(String s);

		void onYChange(String s);

		void onColorChange(String s);

		void onBind(String s);

		void remove();
	}

	public Integer getBindChar() {
		return bindChar;
	}

	public Integer getxMargin() {
		return xMargin;
	}

	public Integer getyMargin() {
		return yMargin;
	}

	public void setBindChar(Integer bindChar) {
		this.bindChar = bindChar;
	}

	public void setxMargin(Integer xMargin) {
		this.xMargin = xMargin;
	}

	public void setyMargin(Integer yMargin) {
		this.yMargin = yMargin;
	}
}
