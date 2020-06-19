package com.pccontroll.ui.form;

import com.pccontroll.R;
import com.pccontroll.databinding.SelectConnectionFormBinding;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.viewmodel.SelectConnectionFormVM;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/04
 */
public class SelectConnectionForm extends AbstractForm<SelectConnectionFormBinding, SelectConnectionFormVM> {

	public SelectConnectionForm() {
		super(R.layout.select_connection_form);
	}
}
