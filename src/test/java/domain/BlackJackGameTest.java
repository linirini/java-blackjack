package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.deck.Deck;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.SettedShuffleStrategy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlackJackGameTest {
    Deck deck;

    @BeforeEach
    void init() {
        Card card1 = new Card(Symbol.DIAMOND, Rank.EIGHT);
        Card card2 = new Card(Symbol.CLOVER, Rank.BIG_ACE);
        Card card3 = new Card(Symbol.SPADE, Rank.KING);
        Card card4 = new Card(Symbol.CLOVER, Rank.SEVEN);
        Card card5 = new Card(Symbol.SPADE, Rank.EIGHT);
        Card card6 = new Card(Symbol.HEART, Rank.TWO);
        Card card7 = new Card(Symbol.CLOVER, Rank.NINE);
        Card card8 = new Card(Symbol.DIAMOND, Rank.THREE);

        List<Card> cards = List.of(card1, card2, card3, card4, card5, card6, card7, card8);
        SettedShuffleStrategy shuffleStrategy = new SettedShuffleStrategy(cards);
        deck = new Deck(shuffleStrategy);
    }

    @DisplayName("딜러와 플레이어에게 카드를 2장씩 준다.")
    @Test
    void prepareCardTest() {
        // given
        Name name1 = new Name("lini");
        Name name2 = new Name("kaki");
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(player1, player2));
        BlackJackGame blackJackGame = new BlackJackGame(deck);

        // when
        blackJackGame.prepareCards(dealer, players);

        // then
        assertAll(
                () -> assertThat(dealer.getHand()).hasSize(2),
                () -> assertThat(player1.getHand()).hasSize(2),
                () -> assertThat(player2.getHand()).hasSize(2)
        );
    }

    @DisplayName("딜러의 점수가 17 미만이므로 카드 주기를 성공한다.")
    @Test
    void giveDealerCardSuccessTest() {
        // given
        Dealer dealer = new Dealer();

        BlackJackGame blackJackGame = new BlackJackGame(deck);

        blackJackGame.takeTurn(dealer); // 3 다이아몬드
        blackJackGame.takeTurn(dealer); // 9 클로버
        blackJackGame.takeTurn(dealer); // 2 하트

        // when
        boolean dealerResult = blackJackGame.takeTurn(dealer);

        int expectedDealerSize = 4;

        // then
        assertAll(
                () -> assertThat(dealerResult).isTrue(),
                () -> assertThat(dealer.getHand()).hasSize(expectedDealerSize)
        );
    }

    @DisplayName("플레이어의 버스트가 아니므로, 카드 추가에 성공한다.")
    @Test
    void givePlayerCardSuccessTest() {
        // given
        Name name = new Name("lini");
        Player player = new Player(name);

        BlackJackGame blackJackGame = new BlackJackGame(deck);

        blackJackGame.takeTurn(player); // 3 다이아몬드
        blackJackGame.takeTurn(player); // 9 클로버
        blackJackGame.takeTurn(player); // 2 하트

        // when
        boolean playerResult = blackJackGame.takeTurn(player);

        int expectedPlayerSize = 4;

        // then
        assertAll(
                () -> assertThat(playerResult).isTrue(),
                () -> assertThat(player.getHand()).hasSize(expectedPlayerSize)
        );
    }


    @DisplayName("딜러의 점수가 17 이상이므로 카드 주기를 실패한다.")
    @Test
    void giveDealerCardFailureTest() {
        // given
        Dealer dealer = new Dealer();

        BlackJackGame blackJackGame = new BlackJackGame(deck);

        blackJackGame.takeTurn(dealer); // 3 다이아몬드
        blackJackGame.takeTurn(dealer); // 9 클로버
        blackJackGame.takeTurn(dealer); // 2 하트
        blackJackGame.takeTurn(dealer); // 8 스페이드

        // when
        boolean dealerResult = blackJackGame.takeTurn(dealer);

        int expectedDealerSize = 4;

        // then
        assertAll(
                () -> assertThat(dealerResult).isFalse(),
                () -> assertThat(dealer.getHand()).hasSize(expectedDealerSize)
        );
    }

    @DisplayName("플레이어의 버스트이면 카드 주기를 실패한다.")
    @Test
    void givePlayerCardFailureTest() {
        // given
        Name name = new Name("lini");
        Player player = new Player(name);

        BlackJackGame blackJackGame = new BlackJackGame(deck);

        blackJackGame.takeTurn(player); // 3 다이아몬드
        blackJackGame.takeTurn(player); // 9 클로버
        blackJackGame.takeTurn(player); // 2 하트
        blackJackGame.takeTurn(player); // 8 스페이드

        // when
        boolean playerResult = blackJackGame.takeTurn(player);

        int expectedPlayerSize = 4;

        // then
        assertAll(
                () -> assertThat(playerResult).isFalse(),
                () -> assertThat(player.getHand()).hasSize(expectedPlayerSize)
        );
    }
}
