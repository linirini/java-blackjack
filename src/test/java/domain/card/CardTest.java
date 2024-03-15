package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @DisplayName("카드를 생성한다.")
    @Test
    void createCardTest() {
        // given
        Symbol expectedSymbol = Symbol.HEART;
        Rank expectedRank = Rank.QUEEN;

        // when
        Card card = Card.of(expectedSymbol, expectedRank);
        Symbol symbol = card.getSymbol();
        Rank rank = card.getRank();

        // then
        assertThat(symbol).isEqualTo(expectedSymbol);
        assertThat(rank).isEqualTo(expectedRank);
    }
}
