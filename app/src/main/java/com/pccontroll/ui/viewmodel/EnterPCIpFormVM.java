package com.pccontroll.ui.viewmodel;

import com.pccontroll.R;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.AbstractViewModel;
import com.pccontroll.model.Field;
import com.pccontroll.util.SharedPreferencesUtils;
import com.pccontroll.util.ValidationRegex;
import com.pccontroll.util.ValidationUtil;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class EnterPCIpFormVM extends AbstractViewModel {

	private String field;
	private String value;

	public EnterPCIpFormVM(AbstractForm form) {
		super(form);
		this.field = Field.UUID.name();
	}

	public String getField() {
		return field;
	}

	public void onOkClick() {
		if (ValidationUtil.isValid(Field.valueOf(field), value, ValidationRegex.IP)) {
			SharedPreferencesUtils.setPCIp(getApplication(), value);
			navigate(R.id.selectActionForm);
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
