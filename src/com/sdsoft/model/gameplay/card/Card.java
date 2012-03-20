package com.sdsoft.model.gameplay.card;

import com.sdsoft.model.gameplay.card.AbstractCard.CardType;
import com.sdsoft.model.gameplay.card.AbstractCard.Suit;

public interface Card {
	public int getNumber();

	public void setNumber(int number);

	public Suit getSuit();

	public void setSuit(Suit suit);

	public CardType getType();

	public void setType(CardType type);

	public AbstractCard getAssignmentCard();

	public void setAssignmentCard(AbstractCard assignmentCard);
}
