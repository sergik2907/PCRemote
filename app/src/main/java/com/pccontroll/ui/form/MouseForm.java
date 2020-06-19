package com.pccontroll.ui.form;

import com.pccontroll.R;
import com.pccontroll.databinding.MouseFormBinding;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.viewmodel.MouseFormVM;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class MouseForm extends AbstractForm<MouseFormBinding, MouseFormVM> {
	public MouseForm() {
		super(R.layout.mouse_form);
	}
}
