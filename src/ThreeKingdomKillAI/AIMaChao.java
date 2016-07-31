package ThreeKingdomKillAI;
import comp.*;

public class AIMaChao extends AICommon {
    @Override
    public final void join(int index) {
        GameMaster master = GameMaster.getInstance();
        GameMaster.Player player = master.players[index];
        player.attList.add(new Attributes("馬超", 400, 1, 2, 20, 0, null));
        player.heroes.add(this);
    }
}
