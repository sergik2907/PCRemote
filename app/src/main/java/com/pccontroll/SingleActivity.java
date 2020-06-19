package com.pccontroll;

import android.os.Bundle;
import android.view.KeyEvent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import com.pccontroll.ui.ViewModelCallback;
import com.pccontroll.util.UIUtil;

public class SingleActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.single_activity);
			Navigation.findNavController(this, R.id.nav_host_form);
			UIUtil.overrideFont(this);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			UIUtil.hideAndroidSystemButtons(getWindow().getDecorView());
		}
	}

	@Override
	public void onBackPressed() {
		//@comment: do nothing
	}
}
