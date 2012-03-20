package com.sdsoft.model.communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.log4j.Logger;


public class GameClientMonitor extends Thread {
	private static Logger log = Logger.getLogger(GameClientMonitor.class);
	public int clientPort = 5222;
	private final GameClient client;
	private final boolean isReady = false;
	private ClientDispatcher dispatcher;
	private PrintWriter out;

	public GameClientMonitor(final GameClient client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			final Socket socket = new Socket(client.getServerAddress(), client.getServerPort());
			final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			final ClientDispatcher dispatcher = new ClientDispatcher(client, in, out);
			this.dispatcher = dispatcher;
			dispatcher.start();
			send(new SGSPacket(Action.ConnectToHost, client.getName(), 0));

		} catch (final Exception e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void send(final SGSPacket packet) {
		String message = JSONUtil.convertToString(packet);
		message += "\n";
		out.write(message);
		out.flush();
	}
}
