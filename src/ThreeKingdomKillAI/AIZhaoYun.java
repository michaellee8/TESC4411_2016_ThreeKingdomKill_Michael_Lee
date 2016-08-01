package ThreeKingdomKillAI;

import comp.*;

public class AIZhaoYun extends AICoreZhaoYun {
    @Override
    protected Boolean canDefense() {
        return (this.countHandCards(Card.Defense) + this.countHandCards(Card.Attack)) > 0;
    }

    @Override
    protected Boolean canAttack() {
        if (this.getHp() / this.AgressiveCoefficient / 1.5 <= this.getMaxHp()
            && (this.countHandCards(Card.Defense) + this.countHandCards(Card.Attack)) < 2) {
            return false;
        } else if ((this.countHandCards(Card.Defense) + this.countHandCards(Card.Attack)) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
