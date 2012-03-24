package com.sdsoft.model.gameplay.card;

public class AbstractCard implements Card {
	public enum Suit {
		SPACE,
		HEART,
		DIAMOND,
		CLUB;
	}

	//##Michael 20120324 --> A bit of renaming to match typical English translations.
	//						 Also corrected American spelling
	public enum CardType {
		BASIC(null),
		TOOL(null),
		EQUIPMENT(null),
		DELAYED_TOOL(TOOL),
		INSTANT_TOOL(TOOL),
		WEAPON(EQUIPMENT),
		ARMOUR(EQUIPMENT),
		PLUS_HORSE(EQUIPMENT),
		MINUS_HORSE(EQUIPMENT);
		CardType parent;

		private CardType(final CardType parent) {
			this.parent = parent;
		}
	}

	protected int number;
	protected Suit suit;
	protected CardType type;
	protected AbstractCard assignmentCard;

	protected AbstractCard(final int number, final Suit suit, final CardType type) {
		this.number = number;
		this.suit = suit;
		this.type = type;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public void setNumber(final int number) {
		this.number = number;
	}

	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public void setSuit(final Suit suit) {
		this.suit = suit;
	}

	@Override
	public CardType getType() {
		return type;
	}

	@Override
	public void setType(final CardType type) {
		this.type = type;
	}

	@Override
	public AbstractCard getAssignmentCard() {
		return assignmentCard;
	}

	@Override
	public void setAssignmentCard(final AbstractCard assignmentCard) {
		this.assignmentCard = assignmentCard;
	}
}
