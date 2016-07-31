package ThreeKingdomKillAI;

import comp.*;

public class AIZhangFei extends AICommon {
    public final void join(int index) {
        GameMaster master = GameMaster.getInstance();
        GameMaster.Player player = master.players[index];
        player.attList.add(new Attributes("張飛", 400, 2, 2, 0, 0, null));
        player.heroes.add(this);
    }
}
