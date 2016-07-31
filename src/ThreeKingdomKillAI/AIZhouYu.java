package ThreeKingdomKillAI;

import comp.*;

public class AIZhouYu extends AICommon {
    public final void join(int index) {
        GameMaster master = GameMaster.getInstance();
        GameMaster.Player player = master.players[index];
        player.attList.add(new Attributes("�L�J��", 400, 1, 2, 0, 20, null));
        player.heroes.add(this);
    }
}
