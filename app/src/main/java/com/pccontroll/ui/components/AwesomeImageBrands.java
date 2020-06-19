package com.pccontroll.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.pccontroll.util.UIUtil;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/05
 */
public class AwesomeImageBrands extends AppCompatTextView {

	public AwesomeImageBrands(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public AwesomeImageBrands(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AwesomeImageBrands(Context context) {
		super(context);
		init();
	}

	private void init() {
		setTypeface(UIUtil.getTypeface(getContext(), UIUtil.FONT_AWESOME_BRANDS));
	}

}