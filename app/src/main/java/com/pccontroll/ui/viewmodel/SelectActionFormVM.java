package com.pccontroll.ui.viewmodel;

import com.pccontroll.R;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.AbstractViewModel;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class SelectActionFormVM extends AbstractViewModel {

	public SelectActionFormVM(AbstractForm form) {
		super(form);
	}

	public void onKeyboardClick() {
		navigate(R.id.keyboardForm);
	}

	public void onGamepadClick() {
		navigate(R.id.gamePadForm);
	}
}
