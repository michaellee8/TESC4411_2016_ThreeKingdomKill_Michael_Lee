package ThreeKingdomKillAI;

import comp.*;

public class AIHuangGai extends AICoreHuangGai {
    @Override
    public void beingAttack() {
        if (this.canDefense()
            && !(((this.countHandCards(Card.Heal) > 0) || this.countHandCards(Card.Vitality) > 0) && this.getHp() - 100 > this.getMaxHp() / 2)) {
            use(Card.Defense);
        }
    }

    @Override
    protected void beforeMyTurn() {
        while (this.countHandCards(Card.Heal) > 0 && this.getHp() <= this.getMaxHp() / 2) {
            this.use(Card.Heal);
        }
        if (this.getHp() + 50 >= this.getMaxHp() && this.countHandCards() < 8) {
            this.spec();
        }
        while ((this.countHandCards(Card.Heal) > 0 || this.countHandCards(Card.Vitality) > 0) && this.getHp() - 75 >= this.getMaxHp() / 2
               && this.countHandCards() < 8) {
            this.spec();
            if (this.countHandCards(Card.Heal) > 0) {
                use(Card.Heal);
            }
        }
    }

    @Override
    protected void inMyTurn() {
        while ((this.countHandCards(Card.Heal) > 0 || this.countHandCards(Card.Vitality) > 0) && this.getHp() - 75 >= this.getMaxHp() / 2
               && this.countHandCards() < 8) {
            this.spec();
            if (this.countHandCards(Card.Heal) > 0) {
                use(Card.Heal);
            }
        }
    }
}
