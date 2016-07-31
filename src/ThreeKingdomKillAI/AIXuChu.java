package ThreeKingdomKillAI;

import comp.*;
import java.lang.Math;

public class AIXuChu extends AICommon{
    private final int DifferenceOfCardsToUseSpec = 2;
    public final void join(int index) {
        GameMaster master = GameMaster.getInstance();
        GameMaster.Player player = master.players[index];
        player.attList.add(new Attributes("許褚", 400, 1, 2, 0, 0, Stage.Drawing));
        player.heroes.add(this);
    }

    public final void spec() {
        GameMaster.getInstance().specXuChu();
    }

    @Override
    public void drawing() {
        if (this.countHandCards() + DifferenceOfCardsToUseSpec >= Math.ceil(this.getHp() / 0.01)) {
            spec();
        } else {
            draw();
        }
    }
}
