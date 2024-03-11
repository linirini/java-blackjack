package controller;

import domain.*;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.strategy.ShuffledDeckGenerator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {

    public void play() {
        Players players = readPlayers();
        Dealer dealer = new Dealer();
        BlackJackGame blackJackGame = new BlackJackGame(Deck.createByStrategy(new ShuffledDeckGenerator()));
        blackJackGame.prepareCards(dealer,players);
        OutputView.printInitialCardsMessage(dealer, players);
        handOutCard(blackJackGame, dealer, players);
        OutputView.printCardsAndResult(dealer, players);
        PlayerResults playerResults = blackJackGame.findPlayerResult(dealer, players);
        OutputView.printFinalGameResult(playerResults);
    }

    private Players readPlayers() {
        List<String> names = InputView.readPlayerNames();
        return createPlayers(names);
    }

    private Players createPlayers(final List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        return new Players(players);
    }

    private void handOutCard(final BlackJackGame blackJackGame, final Dealer dealer, final Players players) {
        askPlayersHit(blackJackGame, players);
        askDealerHit(blackJackGame, dealer);
    }

    private void askPlayersHit(final BlackJackGame blackJackGame, final Players players) {
        for (Player player : players.getPlayers()) {
            askSelection(blackJackGame, player);
        }
    }

    private void askSelection(final BlackJackGame blackJackGame, final Player player) {
        while (!player.isBust() && isRetry(blackJackGame, player)) {
            OutputView.printAllCards(player);
        }
    }

    private boolean isRetry(final BlackJackGame blackJackGame, final Player player) {
        boolean retry = InputView.readSelectionOf(player);
        if (!retry) {
            return false;
        }
        return blackJackGame.takeTurn(player);
    }

    private void askDealerHit(final BlackJackGame blackJackGame, final Dealer dealer) {
        while (blackJackGame.takeTurn(dealer)) {
            OutputView.printDealerHit(dealer);
        }
    }
}
