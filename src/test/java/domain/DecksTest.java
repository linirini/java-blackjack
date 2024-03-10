package domain;

import domain.strategy.ShuffledDecksGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DecksTest {

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void createDecksTest() {
        // given
        int expectedSize = 312;

        // when
        ShuffledDecksGenerator decksGenerator = new ShuffledDecksGenerator();
        Decks decks = Decks.createByStrategy(decksGenerator);

        // then
        assertThat(decks.getCards()).hasSize(expectedSize);
    }
}
