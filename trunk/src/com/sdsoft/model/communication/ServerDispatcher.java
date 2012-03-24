package com.sdsoft.model.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerDispatcher extends Thread {
	private final GameServer server;
	private boolean stopped = false;
	private final BufferedReader in;
	private final PrintWriter out;
	private final String ip;
	private final int port;

	public ServerDispatcher(final GameServer server, final BufferedReader in, final PrintWriter out, final String ip, final int port) {
		this.in = in;
		this.out = out;
		this.server = server;
		this.ip = ip;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			String message = null;
			while (!stopped && (message = in.readLine()) != null) {
				final SGSPacket dp = (SGSPacket) JSONUtil.convertToVO(message, SGSPacket.class);
				dispatch(dp);
			}
		} catch (final Exception e) {
			try {
				in.close();
				out.close();
			} catch (final IOException e1) {
				e1.printStackTrace();
			}
			server.unregisterPlayer(ip, port);
			// e.printStackTrace();
		}
	}

	public void dispatch(final SGSPacket packet) {
		// if (packet.is(OP_SEND_CHAT_MESSAGE)) {
		// server.playerTalk(packet);
		//
		// } else if (packet.is(OP_FINISH_CHOOSING_LORD_CHARACTER)) {
		// server.distributeCharacters(packet);
		//
		// } else if (packet.is(OP_FINISH_CHOOSING_CHARACTER)) {
		// server.setCharacter(packet.getPlayerId(), new
		// org.dizem.sanguosha.model.card.character.Character(packet.getCharacterVO()));
		//
		// } else if (packet.is(OP_PHASE_JUDGE_END)) {
		// notifyServer();
		//
		// } else if (packet.is(OP_PHASE_DRAW_END)) {
		// notifyServer();
		//
		// } else if (packet.is(OP_OFFER_CARD_TO)) {
		// server.offerCard(packet);
		//
		// } else if (packet.is(OP_FEEDBACK)) {
		// server.feedback(packet);
		//
		// } else if (packet.is(OP_DECREASE_LIFE)) {
		// server.decreaseLife(packet);
		//
		// } else if (packet.is(OP_PHASE_PLAY_END)) {
		// notifyServer();
		//
		// } else if (packet.is(OP_DISCARD)) {
		// server.discard(packet);
		// notifyServer();
		//
		// } else if (packet.is(OP_PHASE_DISCARD_END)) {
		// notifyServer();
		//
		// } else if (packet.is(OP_EAT_PEACH)) {
		// server.eatPeach(packet);
		//
		// } else if (packet.is(OP_ADD_EQUIPMENT)) {
		// server.addEquipmentCard(packet);
		// }

		final Action action = packet.getAction();
		switch (action) {
		case ConnectToHost:
			this.server.playerConnect(packet, ip, port);
			break;
		case SendClientMessage:
			this.server.chat(packet);
			break;
		case PickRulerHeroFinished:
			this.server.pickRulerHeroFinished(packet);
			break;
		}

	}

	public void notifyServer() {
		// synchronized (server.gameThread) {
		// server.gameThread.notify();
		// }
	}

	public void stopRunning() {
		this.stopped = true;
	}

	public void send(final SGSPacket packet) {
		String message = JSONUtil.convertToString(packet);
		message += "\n";
	}
}
