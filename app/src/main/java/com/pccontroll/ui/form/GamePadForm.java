package com.pccontroll.ui.form;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;
import com.pccontroll.R;
import com.pccontroll.databinding.GamepadFormBinding;
import com.pccontroll.model.Button;
import com.pccontroll.room.AppDatabase;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.components.GamePadButton;
import com.pccontroll.ui.viewmodel.GamePadFormVM;
import java.util.List;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class GamePadForm extends AbstractForm<GamepadFormBinding, GamePadFormVM> {

	private ConstraintLayout layout;
	private AppDatabase database;

	public GamePadForm() {
		super(R.layout.gamepad_form);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		layout = view.findViewById(R.id.constraintLayout);
		database = Room.databaseBuilder(getActivity(),
				AppDatabase.class, AppDatabase.dbName).build();
		new Thread(new Runnable() {
			@Override
			public void run() {
				List<Button> buttons = database.buttonDao().getAll();
				for (Button button : buttons) {
					GamePadButton gamePadButton = new GamePadButton(getContext());
					layout.post(new Runnable() {
						@Override
						public void run() {
							layout.addView(gamePadButton);
							button.to(gamePadButton);
						}
					});
					database.buttonDao().delete(button);
				}
			}
		}).start();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < layout.getChildCount(); i++) {
					View view = layout.getChildAt(i);
					if (view instanceof GamePadButton) {
						Button button = new Button();
						button.from((GamePadButton) view);
						database.buttonDao().insert(button);
					}
				}
			}
		}).start();
	}
}