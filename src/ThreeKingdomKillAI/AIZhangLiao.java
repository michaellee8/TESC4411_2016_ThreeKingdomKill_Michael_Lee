package ThreeKingdomKillAI;

import comp.*;

public class AIZhangLiao extends AICommon {
    private final int DifferenceOfCardsToUseSpec = 2;
    @Override
    public final void join(int index) {
        GameMaster master = GameMaster.getInstance();
        GameMaster.Player player = master.players[index];
        player.attList.add(new Attributes("�i��", 400, 1, 2, 0, 0, Stage.Drawing));
        player.heroes.add(this);
    }

    public final void spec() {
        GameMaster.getInstance().specZhangLiao();
    }
    
    @Override
    public void drawing(){
        if (this.countHandCards() + DifferenceOfCardsToUseSpec >= Math.ceil(this.getHp() / 0.01)) {
            spec();
        } else {
            draw();
        }
    }
}
