package domain;

import domain.card.Card;

import java.util.List;

public interface DeckGenerator {

    List<Card> generate();
}
