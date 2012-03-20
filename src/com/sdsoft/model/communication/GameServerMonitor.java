package com.sdsoft.model.communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sdsoft.util.MultiValueKey;

public class GameServerMonitor extends Thread {

	private static Logger log = Logger.getLogger(GameServerMonitor.class);
	private final int port;
	private final GameServer server;
	private final Map<MultiValueKey, ServerDispatcher> dispatcherMap = new HashMap<MultiValueKey, ServerDispatcher>();
	private final Map<MultiValueKey, BufferedReader> readerMap = new HashMap<MultiValueKey, BufferedReader>();
	private final Map<MultiValueKey, PrintWriter> writerMap = new HashMap<MultiValueKey, PrintWriter>();

	public GameServerMonitor(final GameServer server) {
		this.server = server;
		// this.owner = server.getOwner();
		// this.port = server.getPort();
		this.port = server.getPort();
	}

	@Override
	public void run() {

		try {
			final ServerSocket ds = new ServerSocket(port);
			while (true) {
				final Socket socket = ds.accept();
				final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				final InetAddress inetAddress = socket.getInetAddress();
				final String ip = inetAddress.getHostAddress();
				final int clientport = socket.getPort();
				final MultiValueKey key = new MultiValueKey(ip, clientport);
				readerMap.put(key, in);
				writerMap.put(key, out);
				final ServerDispatcher dispatcher = new ServerDispatcher(server, in, out, ip, clientport);
				dispatcher.start();
				dispatcherMap.put(key, dispatcher);
			}

		} catch (final Exception e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void send(final SGSPacket packet, final String ip, final int port) {
		final PrintWriter writer = writerMap.get(new MultiValueKey(ip, port));
		if (writer != null) {
			String message = JSONUtil.convertToString(packet);
			message += "\n";
			writer.write(message);
			writer.flush();
		}
	}
}
