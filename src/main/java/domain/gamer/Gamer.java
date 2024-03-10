package domain.gamer;

import domain.card.Card;
import java.util.List;

public abstract class Gamer {
    protected Name name;
    protected Hand hand;

    public Gamer(final Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public abstract boolean isStay();

    public void hit(final Card card) {
        hand.add(card);
    }

    public int calculateTotalScore() {
        return hand.sum();
    }

    public boolean isBust() {
        return hand.isOverBlackJack();
    }

    public boolean isBlackJack() {
        return hand.sum() == 21 && hand.getCards().size() == 2;
    }

    public void receiveInitialCards(final List<Card> cards) {
        for (final Card card : cards) {
            hand.add(card);
        }
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public Name getName() {
        return name;
    }
}
