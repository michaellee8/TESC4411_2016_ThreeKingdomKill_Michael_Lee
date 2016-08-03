import comp.*;
import ThreeKingdomKillAI.*;

public class KingdomTest {

    public static void main(String[] args) {
        GameMaster master = GameMaster.getInstance();

        master.setLightning(30);

        int side = 0;
        master.setPlayer(side, "左方");

        new AIZhangFei().join(side);
        new AIXiaHouDun().join(side);
        new AIZhangLiao().join(side);

        side = 1;
        master.setPlayer(side, "右方");

        new AIXuChu().join(side);
        new AIZhaoYun().join(side);
        new AIMaChao().join(side);

        master.setVisible(0, true);
        master.setVisible(1, true);
        master.start();
    }
}