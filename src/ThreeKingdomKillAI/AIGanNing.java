package ThreeKingdomKillAI;

import comp.*;

public class AIGanNing extends AICoreGanNing {
    private final int DifferenceOfCardsToUseSpec = 3;

    @Override
    protected void beforeMyTurn() {
        if (this.countHandCards() + DifferenceOfCardsToUseSpec >= Math.ceil(this.getHp() / 0.01)) {
            if (this.countHandCards(Card.Axe) > 0 || this.countHandCards(Card.Agility) > 0 || this.countHandCards(Card.Shield) > 0) {
                if (GameMaster.getInstance().isActiveOpp(Card.Vitality)
                    && GameMaster.getInstance().getOppHp() + 75 >= GameMaster.getInstance().getOppMaxHp()) {
                    if (this.countHandCards(Card.Shield) > 0) {
                        spec(Card.Shield);
                        use(Card.RepealX, Card.Vitality);
                    } else if (this.countHandCards(Card.Axe) > 0) {
                        spec(Card.Axe);
                        use(Card.RepealX, Card.Vitality);
                    } else if (this.countHandCards(Card.Agility) > 0) {
                        spec(Card.Agility);
                        use(Card.RepealX, Card.Vitality);
                    }
                } else if (GameMaster.getInstance().countOppHandCards() == 2) {
                    if (this.countHandCards(Card.Shield) > 1 || this.isActive(Card.Shield)) {
                        spec(Card.Shield);
                        use(Card.RepealX);
                    } else if (this.countHandCards(Card.Axe) > 1 || this.isActive(Card.Shield)) {
                        spec(Card.Axe);
                        use(Card.RepealX);
                    } else if (this.countHandCards(Card.Agility) > 1 || this.isActive(Card.Shield)) {
                        spec(Card.Agility);
                        use(Card.RepealX);
                    }
                } else if (GameMaster.getInstance().countOppHandCards() == 1) {
                    if (this.countHandCards(Card.Shield) > 0) {
                        spec(Card.Shield);
                        use(Card.RepealX);
                    } else if (this.countHandCards(Card.Axe) > 0) {
                        spec(Card.Axe);
                        use(Card.RepealX);
                    } else if (this.countHandCards(Card.Agility) > 0) {
                        spec(Card.Agility);
                        use(Card.RepealX);
                    }
                } else {
                    return;
                }
            }
        }
    }
}
