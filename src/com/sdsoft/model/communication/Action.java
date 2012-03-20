package com.sdsoft.model.communication;

public enum Action {
	// host to client action
	UpdatePlayers("Update Players"),
	SendMessage("Send Game Message"),
	GameStart("Game start"),
	DistributeRole("Distribute Role"),
	DistributeHero("Distribute Hero"),
	DistributeCard("Distribute Card"),
	TurnStart("Turn start"),
	JudgementStart("Judgement Start"),
	JudgementEnd("Judgement End"),
	DrawStart("Draw Start"),
	DrawEnd("Draw End"),
	PlayStart("Play start"),
	PlayEnd("Play End"),
	DiscardStart("Discard Start"),
	DiscardEnd("Discard End"),
	TurnEnd("Turn End"),
	AddEquipment("Add Equipment"),
	IncreaseHealth("Increase Health"),
	DeductHealth("Deduct Health"),
	GameEnd("Game End"),
	PickHero("Pick Hero"),
	PickRulerHero("Pick Ruler Hero"),

	// client to host action
	InitClient("Init Client"),
	ConnectToHost("Connect to Host"),
	Feedback("Feedback"),
	PickRulerHeroFinished("Pick Ruler Hero Finished"),
	// both way action
	SendClientMessage("Send Cliend Message"),

	// String OP_TEST_SERVER = "OP_TEST_SERVER";
	// String OP_CONNECT = "OP_CONNECT";
	// String OP_UPDATE_PLAYERS = "OP_UPDATE_PLAYERS";
	// String OP_INIT_CLIENT = "OP_INIT_CLIENT";
	// String OP_SEND_MESSAGE = "OP_SEND_MESSAGE";
	// String OP_SEND_CHAT_MESSAGE = "OP_SEND_CHAT_MESSAGE";
	// String OP_DISTRIBUTE_ROLE = "OP_DISTRIBUTE_ROLE";
	// String OP_DISTRIBUTE_LORD_CHARACTER = "OP_DISTRIBUTE_LORD_CHARACTER";
	// String OP_DISTRIBUTE_CHARACTER = "OP_DISTRIBUTE_CHARACTER";
	// String OP_FINISH_CHOOSING_LORD_CHARACTER =
	// "OP_FINISH_CHOOSING_LORD_CHARACTER";
	// String OP_FINISH_CHOOSING_CHARACTER = "OP_FINISH_CHOOSING_CHARACTER";
	// String OP_DISTRIBUTE_CARD = "OP_DISTRIBUTE_CARD";
	// String OP_UPDATE_PLAYERS_INFO = "OP_UPDATE_PLAYERS_INFO";
	// String OP_PHASE_START = "OP_PHASE_START";§‹
	// String OP_PHASE_JUDGE_BEGIN = "OP_PHASE_JUDGE_BEGIN";
	// String OP_PHASE_JUDGE_END = "OP_PHASE_JUDGE_END";
	// String OP_PHASE_DRAW_BEGIN = "OP_PHASE_DRAW_BEGIN"; §‹
	// String OP_PHASE_DRAW_END = "OP_PHASE_DRAW_END";
	// String OP_PHASE_PLAY_BEGIN = "OP_PHASE_PLAY_BEGIN";
	// String OP_PHASE_PLAY_END = "OP_PHASE_PLAY_END";
	// String OP_PHASE_DISCARD_BEGIN = "OP_PHASE_DISCARD_BEGIN";
	// String OP_PHASE_DISCARD_END = "OP_PHASE_DISCARD_END";
	// String OP_ADD_EQUIPMENT = "OP_ADD_EQUIPMENT";
	// String OP_DISCARD = "OP_DISCARD";//å¼ƒç‰Œ
	// String OP_OFFER_CARD_TO = "OP_OFFER_CARD_TO";
	// String OP_FEEDBACK = "OP_FEEDBACK";
	// String OP_DECREASE_LIFE = "OP_DECREASE_LIFE";
	// String OP_OFFLINE = "OP_OFFLINE";
	// String OP_SERVER_STOP = "OP_SERVER_STOP";
	// String OP_EAT_PEACH = "OP_EAT_PEACH";¡ƒ

	;
	String name;

	private Action(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
