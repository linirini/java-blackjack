package domain;

import domain.gamer.Player;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Betting {
    private static final BigDecimal MONEY_UNIT = new BigDecimal(1000);
    private static final BigDecimal MINIMUM_MONEY = new BigDecimal(5000);
    private static final BigDecimal MAXIMUM_MONEY = new BigDecimal(500000);
    private static final String INVALID_MONEY_UNIT = String.format("베팅 금액은 %s원 단위로 베팅 가능합니다.", MONEY_UNIT);
    private static final String INVALID_MONEY_RANGE = String.format("베팅 금액은 %s원 이상, %s원 이하만 가능합니다.", MINIMUM_MONEY, MAXIMUM_MONEY);
    private final Map<Player, Money> betting;

    public Betting(final Map<Player, String> betting) {
        this.betting = new LinkedHashMap<>();
        for (final Map.Entry<Player, String> entry : betting.entrySet()) {
            Money money = new Money(entry.getValue());
            validateUnit(money);
            validateRange(money);
            this.betting.put(entry.getKey(), money);
        }
    }

    private void validateUnit(final Money money) {
        if (!money.getValue().remainder(MONEY_UNIT).equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException(INVALID_MONEY_UNIT);
        }
    }

    private void validateRange(final Money money) {
        if (lessThanMinimum(money.getValue()) || moreThanMaximum(money.getValue())) {
            throw new IllegalArgumentException(INVALID_MONEY_RANGE);
        }
    }

    private boolean lessThanMinimum(final BigDecimal money) {
        return money.compareTo(MINIMUM_MONEY) < 0;
    }

    private boolean moreThanMaximum(final BigDecimal money) {
        return money.compareTo(MAXIMUM_MONEY) > 0;
    }

    public Money calculateProfit(final Player player, final PlayerResult playerResult) {
        Money betMoney = betting.get(player);
        return playerResult.earn(betMoney);
    }
}
