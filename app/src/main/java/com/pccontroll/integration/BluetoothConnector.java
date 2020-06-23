package com.pccontroll.integration;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import com.pccontroll.model.Field;
import com.pccontroll.ui.components.SettingsMenu;
import com.pccontroll.util.NetworkUtils;
import com.pccontroll.util.SharedPreferencesUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/05/31
 */
public class BluetoothConnector implements Connector, AutoCloseable {

	private static BluetoothConnector instance;
	private static BluetoothDevice device;

	private static final UUID MY_UUID =
			UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	private static String address = "00:1A:7D:DA:71:0D";

	private BluetoothSocket btSocket = null;

	public static BluetoothConnector getInstance(Context context) {
		if (instance == null) {
			instance = new BluetoothConnector(context);
		}
		return instance;
	}

	private BluetoothConnector(Context context) {
		address = SharedPreferencesUtils.getAddress(context);
		BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
		device = btAdapter.getRemoteDevice(address);
		btAdapter.cancelDiscovery();
	}

	@Override
	public void connect() {
	}

	@Override
	public void send(String message) {
		try {
			btSocket = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
			btSocket.connect();
			OutputStream outStream = btSocket.getOutputStream();
			byte[] msgBuffer = message.getBytes();
			outStream.write(msgBuffer);
			outStream.flush();
			btSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void waitForResponse(SettingsMenu.Binder binder) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					btSocket = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
					btSocket.connect();
					OutputStream outStream = btSocket.getOutputStream();
					byte[] msgBuffer = (Field.Ip.getApiKey() + "=" + NetworkUtils.getIPAddress(true)).getBytes();
					outStream.write(msgBuffer);
					outStream.flush();
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								InputStream inputStream;
								do {
									inputStream = btSocket.getInputStream();
								} while (inputStream.available() <= 0);
								byte[] buffer = new byte[256];
								int bytes;
								bytes = inputStream.read(buffer);
								String readMessage = new String(buffer, 0, bytes);
								binder.onReceive(readMessage);
								btSocket.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void disconnect() throws Exception {
	}

	@Override
	public void close() throws Exception {
	}
}
