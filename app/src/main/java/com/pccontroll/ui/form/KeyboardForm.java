package com.pccontroll.ui.form;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pccontroll.R;
import com.pccontroll.databinding.KeyboardFormBinding;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.viewmodel.KeyboardFormVM;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/01
 */
public class KeyboardForm extends AbstractForm<KeyboardFormBinding, KeyboardFormVM> {


	public KeyboardForm() {
		super(R.layout.keyboard_form);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
}
