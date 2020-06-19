package com.pccontroll.ui.form;

import com.pccontroll.R;
import com.pccontroll.databinding.SelectActionFormBinding;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.viewmodel.SelectActionFormVM;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class SelectActionForm extends AbstractForm<SelectActionFormBinding, SelectActionFormVM> {

	public SelectActionForm() {
		super(R.layout.select_action_form);
	}
}
