package com.pccontroll.ui.form;

import com.pccontroll.R;
import com.pccontroll.databinding.EnterPcIpFormBinding;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.viewmodel.EnterPCIpFormVM;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class EnterPCIpForm extends AbstractForm<EnterPcIpFormBinding, EnterPCIpFormVM> {

	public EnterPCIpForm() {
		super(R.layout.enter_pc_ip_form);
	}
}
