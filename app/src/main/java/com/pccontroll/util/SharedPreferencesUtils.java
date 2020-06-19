package com.pccontroll.util;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/05/31
 */
public class SharedPreferencesUtils {

	private static final String CONNECTIVITY = "connectivity";
	private static final String PC_IP = "ip";
	private static final String PC_UUID = "uuid";

	public static void setPCIp(Context context, String address) {
		SharedPreferences.Editor editor = context.getSharedPreferences(CONNECTIVITY, MODE_PRIVATE).edit();
		editor.putString(PC_IP, address);
		editor.apply();
	}

	public static String getPCIp(Context context) {
		return context.getSharedPreferences(CONNECTIVITY, MODE_PRIVATE).getString(PC_IP, null);
	}

	public static void setUUID(Context context, String address) {
		SharedPreferences.Editor editor = context.getSharedPreferences(CONNECTIVITY, MODE_PRIVATE).edit();
		editor.putString(PC_UUID, address);
		editor.apply();
	}

	public static String getUUID(Context context) {
		return context.getSharedPreferences(CONNECTIVITY, MODE_PRIVATE).getString(PC_UUID, null);
	}
}
