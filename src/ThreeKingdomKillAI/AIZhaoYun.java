package ThreeKingdomKillAI;

import comp.*;

public class AIZhaoYun extends AICommon {
    public final void join(int index) {
        GameMaster master = GameMaster.getInstance();
        GameMaster.Player player = master.players[index];
        player.attList.add(new Attributes("趙雲", 400, 1, 2, 0, 0, null));
        player.heroes.add(this);
    }

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
