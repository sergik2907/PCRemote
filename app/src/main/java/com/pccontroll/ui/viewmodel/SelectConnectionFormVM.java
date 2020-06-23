package com.pccontroll.ui.viewmodel;

import com.pccontroll.R;
import com.pccontroll.integration.ConnectionManager;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.AbstractViewModel;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/04
 */
public class SelectConnectionFormVM extends AbstractViewModel {

	public SelectConnectionFormVM(AbstractForm form) {
		super(form);
	}

	public void onWiFiClick() {
		ConnectionManager.setIsWiFi(true);
		navigate(R.id.enterPcIpForm);
	}

	public void onBluetoothClick() {
		ConnectionManager.setIsWiFi(false);
		navigate(R.id.enterAddress);
	}
}
