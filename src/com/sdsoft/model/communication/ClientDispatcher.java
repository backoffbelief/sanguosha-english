package com.sdsoft.model.communication;

import java.io.BufferedReader;
import java.io.PrintWriter;

import com.sdsoft.model.communication.GameClient;
import com.sdsoft.model.communication.JSONUtil;
import com.sdsoft.model.communication.SGSPacket;

public class ClientDispatcher extends Thread {
	private final GameClient client;
	private final BufferedReader in;
	private final PrintWriter out;
	private boolean stopped = false;

	public ClientDispatcher(final GameClient client, final BufferedReader in, final PrintWriter out) {
		this.client = client;
		this.in = in;
		this.out = out;
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
			e.printStackTrace();
		}
	}

	public void dispatch(final SGSPacket packet) {
		final Action action = packet.getAction();
		switch (action) {
		case UpdatePlayers:
			this.client.updatePlayers(packet);
			break;
		case GameStart:
			this.client.gameStart(packet);
			break;
		case PickRulerHero:
			this.client.pickRulerHero(packet);
		}

		// if (packet.is(OP_INIT_CLIENT)) {
		// this.client.showGameFrame(packet);
		//
		// } else if (packet.is(OP_UPDATE_PLAYERS)) {
		// this.client.updatePlayers(packet);
		//
		// } else if (packet.is(OP_SEND_MESSAGE)) {
		// this.client.showMessage(packet.getMessage());
		//
		// } else if (packet.is(OP_SEND_CHAT_MESSAGE)) {
		// this.client.showChatMessage(packet.getMessage());
		//
		// } else if (packet.is(OP_DISTRIBUTE_ROLE)) {
		// this.client.setRole(packet.getRole(), packet.getLordId());
		//
		// } else if (packet.is(OP_DISTRIBUTE_LORD_CHARACTER)) {
		// this.client.distributeLordCharacter(packet);
		//
		// } else if (packet.is(OP_DISTRIBUTE_CHARACTER)) {
		// this.client.distributeCharacter(packet);
		//
		// } else if (packet.is(OP_DISTRIBUTE_CARD)) {
		// this.client.distributeCards(packet);
		//
		// } else if (packet.is(OP_FINISH_CHOOSING_CHARACTER)) {
		// this.client.setCharacter(packet);
		//
		// } else if (packet.is(OP_UPDATE_PLAYERS_INFO)) {
		// this.client.updatePlayersInfo(packet);
		//
		// } else if (packet.is(OP_PHASE_START)) {®µ
		// client.startPhase(packet.getPlayerId());
		//
		// } else if (packet.is(OP_PHASE_JUDGE_BEGIN)) {®µ
		// client.judgePhase(packet.getPlayerId());
		//
		// } else if (packet.is(OP_PHASE_DRAW_BEGIN)) {
		// client.drawPhase(packet);
		//
		// } else if (packet.is(OP_PHASE_PLAY_BEGIN)) {®µ
		// client.playPhase(packet.getPlayerId());
		//
		// } else if (packet.is(OP_OFFER_CARD_TO)) {
		// client.beOfferredCardTo(packet);
		//
		// } else if (packet.is(OP_FEEDBACK)) {
		// client.feedback(packet);
		//
		// } else if (packet.is(OP_DECREASE_LIFE)) {¡€
		// client.decreaseLife(packet);
		//
		// } else if (packet.is(OP_PHASE_DISCARD_BEGIN)) {
		// client.discard(packet);
		//
		// } else if (packet.is(OP_DISCARD)) {
		// client.discardEnd(packet);
		//
		// } else if (packet.is(OP_EAT_PEACH)) {
		// client.eatPeach(packet);
		//
		// } else if (packet.is(OP_ADD_EQUIPMENT)) {
		// client.addEquipmentCard(packet);
		// }

	}

	public void dispatch(final String jsonString) {
		final SGSPacket packet = (SGSPacket) JSONUtil.convertToVO(jsonString, SGSPacket.class);
		dispatch(packet);
	}

	public void stopThread() {
		this.stopped = true;
	}

	public void handShake() {

	}
}
