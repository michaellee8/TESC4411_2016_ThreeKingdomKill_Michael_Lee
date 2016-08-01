package ThreeKingdomKillAI;

public class AIZhangLiao extends AICoreZhangLiao {
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
