package com.pccontroll.integration;

import android.content.Context;
import com.pccontroll.ui.components.SettingsMenu;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/23
 */
public class ConnectionManager {

	private static boolean isWiFi;

	public static void send(Context context, String raw) {
		if (isWiFi) {
			new WebSocketConnector(context).send(raw);
		} else {
			BluetoothConnector.getInstance(context).send(raw);
		}
	}

	public static void waitForResponse(Context context, SettingsMenu.Binder binder) {
		if (isWiFi) {
			new WebSocketConnector(context).waitForResponse(binder);
		} else {
			BluetoothConnector.getInstance(context).waitForResponse(binder);
		}
	}

	public static void setIsWiFi(boolean isWiFi) {
		ConnectionManager.isWiFi = isWiFi;
	}
}
