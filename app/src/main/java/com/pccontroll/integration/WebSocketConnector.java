package com.pccontroll.integration;

import android.content.Context;
import com.pccontroll.model.Field;
import com.pccontroll.ui.components.SettingsMenu;
import com.pccontroll.util.NetworkUtils;
import com.pccontroll.util.SharedPreferencesUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/05/31
 */
public class WebSocketConnector implements Connector, AutoCloseable {

	private String pcIp;

	public WebSocketConnector(Context context) {
		this.pcIp = SharedPreferencesUtils.getPCIp(context);
	}

	@Override
	public void connect() throws IOException {
	}

	@Override
	public void send(String message) {
		new Thread(() -> {
			try {
				Socket socket = new Socket(pcIp, 9000);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	public void waitForResponse(SettingsMenu.Binder binder) {
		new Thread(() -> {
			try {
				Socket socket = new Socket(pcIp, 9000);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(Field.Ip.getApiKey() + "=" + NetworkUtils.getIPAddress(true));
				ServerSocket serverSocket = new ServerSocket(9100);
				Socket socket1 = serverSocket.accept();
				DataInputStream inputStream = new DataInputStream(socket1.getInputStream());
				String msg = inputStream.readUTF();
				inputStream.close();
				socket1.close();
				serverSocket.close();
				binder.onReceive(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	@Override
	public void disconnect() throws Exception {
		close();
	}

	@Override
	public void close() throws Exception {
	}
}
