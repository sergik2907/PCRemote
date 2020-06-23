package com.pccontroll.ui.form;

import com.pccontroll.R;
import com.pccontroll.databinding.EnterPcAddressBinding;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.viewmodel.EnterPCAddressFormVM;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/23
 */
public class EnterPCAddressForm extends AbstractForm<EnterPcAddressBinding, EnterPCAddressFormVM> {

	public EnterPCAddressForm() {
		super(R.layout.enter_pc_address);
	}
}
