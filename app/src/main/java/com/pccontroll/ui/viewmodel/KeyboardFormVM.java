package com.pccontroll.ui.viewmodel;

import com.pccontroll.R;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.AbstractViewModel;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/01
 */
public class KeyboardFormVM extends AbstractViewModel {

	public KeyboardFormVM(AbstractForm form) {
		super(form);
	}

	public void onNextClick() {
		navigate(R.id.mouseForm);
	}
}
