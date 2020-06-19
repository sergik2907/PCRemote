package com.pccontroll.ui;

import android.view.KeyEvent;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/01
 */
public interface ViewModelCallback {

	void onKeyDown(int keyCode, KeyEvent event);

	void onKeyUp(int keyCode, KeyEvent event);
}
