package com.pccontroll.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import com.pccontroll.util.UIUtil;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2019/11/26
 */
	public class AwesomeImageDefault extends AppCompatTextView {

	public AwesomeImageDefault(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public AwesomeImageDefault(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AwesomeImageDefault(Context context) {
		super(context);
		init();
	}

	private void init() {
		setTypeface(UIUtil.getTypeface(getContext(), UIUtil.FONT_AWESOME_DEFAULT));
	}
}