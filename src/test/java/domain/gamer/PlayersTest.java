package domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @DisplayName("플레이어 수가 2명 미만, 8명 초과이면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 9})
    void invalidPlayerCountTest(int count) {
        // given
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = "이름" + i;
            players.add(new Player(new Name(name)));
        }

        // then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 2명에서 8명까지만 참가 가능합니다.");
    }

    @DisplayName("플레이어의 이름이 중복되면 예외를 던진다.")
    @Test
    void duplicatedPlayerNameTest() {
        // given
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String name = "이름";
            players.add(new Player(new Name(name)));
        }

        // then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }
}
