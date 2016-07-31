package ThreeKingdomKillAI;

import comp.*;

public class AIHuangGai extends AICommon {
    public final void join(int index) {
        GameMaster master = GameMaster.getInstance();
        GameMaster.Player player = master.players[index];
        player.attList.add(new Attributes("黄蓋", 400, 1, 2, 0, 0, Stage.Action));
        player.heroes.add(this);
    }

    public final void spec() {
        GameMaster.getInstance().specHuangeGai();
    }

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
        while ((this.countHandCards(Card.Heal) > 0 || this.countHandCards(Card.Vitality) > 0)&& this.getHp()-75 >= this.getMaxHp() / 2 && this.countHandCards() < 8) {
            this.spec();
            if (this.countHandCards(Card.Heal) > 0){
                use(Card.Heal);
            }
        }
    }

    @Override
    protected void inMyTurn() {
        while ((this.countHandCards(Card.Heal) > 0 || this.countHandCards(Card.Vitality) > 0)&& this.getHp()-75 >= this.getMaxHp() / 2 && this.countHandCards() < 8) {
            this.spec();
            if (this.countHandCards(Card.Heal) > 0){
                use(Card.Heal);
            }
        }
    }
}
