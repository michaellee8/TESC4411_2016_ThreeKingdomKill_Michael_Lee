package ThreeKingdomKillAI;

import java.lang.Math;

public class AIXuChu extends AICoreXuChu{
    private final int DifferenceOfCardsToUseSpec = 2;

    @Override
    public void drawing() {
        if (this.countHandCards() + DifferenceOfCardsToUseSpec >= Math.ceil(this.getHp() / 0.01)) {
            spec();
        } else {
            draw();
        }
    }
}
