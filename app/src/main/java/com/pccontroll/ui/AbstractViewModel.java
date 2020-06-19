package com.pccontroll.ui;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.textfield.TextInputLayout;
import com.pccontroll.model.Field;
import com.pccontroll.ui.components.InputEditText;
import com.pccontroll.ui.error.ValidationMessage;
import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/05/31
 */
public abstract class AbstractViewModel extends AndroidViewModel {

	private AbstractForm form;

	public static AbstractViewModel getInstance(AbstractForm form, Class clazz) {
		try {
			Constructor constructor = clazz.getConstructor(AbstractForm.class);
			return (AbstractViewModel) constructor.newInstance(form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public AbstractViewModel(AbstractForm form) {
		super(form.getActivity().getApplication());
		this.form = form;
	}

	protected AbstractForm getForm() {
		return form;
	}

	@BindingAdapter("error")
	public static void setError(TextInputLayout layout, Map<Field, ValidationMessage> validationMap) {
		Field field = Field.valueOf(((InputEditText) layout.getEditText()).getField());
		String errorMessage = validationMap == null || validationMap.isEmpty() ? null : validationMap.get(field).getMessage();
		layout.setError(errorMessage);
	}

	public MutableLiveData<Map<Field, ValidationMessage>> getError() {
		return LiveDataController.getValidationMap();
	}

	public void navigate(Integer destinationId) {
		getForm().getNavController().navigate(destinationId);
	}

	public void onDestroy() {
	}

	public void onStart() {
	}

	public void onBackClick() {
		if (!getForm().getNavController().popBackStack()) {
			getForm().getActivity().finish();
		}
	}
}
