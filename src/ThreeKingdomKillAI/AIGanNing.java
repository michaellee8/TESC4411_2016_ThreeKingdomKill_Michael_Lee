package ThreeKingdomKillAI;

import comp.*;

public class AIGanNing extends AICommon {
    private final int DifferenceOfCardsToUseSpec = 3;

    @Override
    public final void join(int index) {
        GameMaster master = GameMaster.getInstance();
        GameMaster.Player player = master.players[index];
        player.attList.add(new Attributes("甘寧", 400, 1, 2, 0, 0, Stage.Action));
        player.heroes.add(this);
    }

    public final void spec(Card card) {
        GameMaster.getInstance().specGanNing(card);
    }

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
