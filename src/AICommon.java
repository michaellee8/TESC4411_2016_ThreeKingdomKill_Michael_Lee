import comp.*;

public abstract class AICommon extends Hero {
    public abstract void join(int index);

    public abstract void beforeMyTurn();

    protected static Card[] CardDiscardSequence = {Card.Repeal, Card.Grab, Card.Agility, Card.Axe, Card.Shield, Card.Vitality, Card.Attack,
            Card.Defense, Card.Heal             };

    public void drawing() {
        draw();
    }

    public void myTurn() {
        while (countHandCards(Card.Grab) > 0) {
            if (GameMaster.getInstance().countOppBuffs() > 0) {

            }
        }
    }

    public void discarding() {
        for (int i = 0; i < CardDiscardSequence.length; i++) {
            while (countHandCards(CardDiscardSequence[i]) > 0) {
                discard(CardDiscardSequence[i]);
            }
        }
    }

    public void beingAttack() {
        if (countHandCards(Card.Defense) > 0) {
            use(Card.Defense);
        }
    }
}
