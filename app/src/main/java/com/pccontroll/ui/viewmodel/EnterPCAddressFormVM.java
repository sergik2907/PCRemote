package com.pccontroll.ui.viewmodel;

import com.pccontroll.R;
import com.pccontroll.model.Field;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.AbstractViewModel;
import com.pccontroll.util.SharedPreferencesUtils;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/23
 */
public class EnterPCAddressFormVM extends AbstractViewModel {

	private String field;
	private String value;

	public EnterPCAddressFormVM(AbstractForm form) {
		super(form);
		this.field = Field.Address.name();
	}

	public String getField() {
		return field;
	}

	public void onOkClick() {
		SharedPreferencesUtils.setAddress(getApplication(), value);
		navigate(R.id.selectActionForm);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
