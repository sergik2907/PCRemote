package com.pccontroll.ui.viewmodel;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.BindingAdapter;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.AbstractViewModel;
import com.pccontroll.ui.components.GamePadButton;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class GamePadFormVM extends AbstractViewModel {
	public GamePadFormVM(AbstractForm form) {
		super(form);
	}

	@BindingAdapter("onLongClick")
	public static void setOnLongClick(ConstraintLayout layout, View.OnLongClickListener listener) {
		layout.setOnLongClickListener(listener);
	}

	public View.OnLongClickListener onLongClick() {
		return new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (v instanceof ConstraintLayout) {
					GamePadButton button = new GamePadButton(v.getContext());
					button.setTransformationMethod(null);
					button.setId(View.generateViewId());
					((ConstraintLayout) v).addView(button);
					ConstraintSet constraintSet = new ConstraintSet();
					constraintSet.clone((ConstraintLayout) v);
					constraintSet.connect(button.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
					constraintSet.connect(button.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
					constraintSet.applyTo((ConstraintLayout) v);
				}
				return false;
			}
		}
				;
	}
}
