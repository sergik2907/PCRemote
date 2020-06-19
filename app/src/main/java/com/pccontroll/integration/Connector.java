package com.pccontroll.integration;

import java.io.IOException;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/05/31
 */
public interface Connector {

	void connect() throws IOException;

	void send(String message) throws IOException;

	void disconnect() throws Exception;

}
