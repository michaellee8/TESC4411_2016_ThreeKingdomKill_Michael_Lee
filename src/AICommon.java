import comp.*;
import java.util.Arrays;
import java.util.List;

public abstract class AICommon extends Hero {
    protected final double AgressiveCoefficient = 2.0; // Hero Specific, Controls in which porpotion of HP will hero switch between attack-sided and defense-sided

    public abstract void join(int index);

    protected void beforeMyTurn() { // Hero specific

    }

    protected void afterMyTurn() { // Hero specific

    }

    protected void inMyTurn() { // Hero specific

    }

    protected Boolean canDefense() { // should be overrode for AIZhaoYun
        return this.countHandCards(Card.Defense) > 0;
    }

    protected Boolean canAttack() { // should be overrode for AIZhaoYun
        return this.countHandCards(Card.Attack) > 0;
    }

    protected static Card[] CardDiscardSequence = {Card.Repeal, Card.Grab, Card.Shield, Card.Axe, Card.Agility, Card.Vitality, Card.Attack,
            Card.Defense, Card.Heal             };

    public void drawing() {
        draw();
    }

    public void myTurn() {
        beforeMyTurn();
        while (countHandCards(Card.Grab) > 0) { // use Card.Grab
            if (GameMaster.getInstance().countOppBuffs() > 0 && this.countBuffs() < 2) {
                List<Card> oppBuffCardList = Arrays.asList(GameMaster.getInstance().getOppBuffList());
                List<Card> myBuffCardList = Arrays.asList(this.getBuffList());
                if (oppBuffCardList.contains(Card.Vitality) && !myBuffCardList.contains(Card.Vitality)) {
                    use(Card.Grab, Card.Vitality);
                } else if (oppBuffCardList.contains(Card.Shield) && !myBuffCardList.contains(Card.Shield)) {
                    use(Card.Grab, Card.Shield);
                } else if (oppBuffCardList.contains(Card.Agility) && !myBuffCardList.contains(Card.Agility)) {
                    use(Card.Grab, Card.Agility);
                } else if (oppBuffCardList.contains(Card.Axe) && !myBuffCardList.contains(Card.Axe)) {
                    use(Card.Grab, Card.Axe);
                } else {
                    use(Card.Grab);
                }
            } else {
                use(Card.Grab);
            }
        }
        while (countHandCards(Card.Repeal) > 0) { // use Card.Repeal
            if (GameMaster.getInstance().countOppBuffs() > 0) {
                List<Card> oppBuffCardList = Arrays.asList(GameMaster.getInstance().getOppBuffList());
                if (oppBuffCardList.contains(Card.Vitality)) {
                    use(Card.Repeal, Card.Vitality);
                } else if (oppBuffCardList.contains(Card.Shield)) {
                    use(Card.Repeal, Card.Shield);
                } else if (oppBuffCardList.contains(Card.Agility)) {
                    use(Card.Repeal, Card.Agility);
                } else if (oppBuffCardList.contains(Card.Axe)) {
                    use(Card.Repeal, Card.Axe);
                } else {
                    use(Card.Repeal);
                }
            } else {
                use(Card.Repeal);
            }
        }
        while (countHandCards(Card.RepealX) > 0) { // use Card.RepealX for
                                                   // HeroGanNing
            if (GameMaster.getInstance().countOppBuffs() > 0) {
                List<Card> oppBuffCardList = Arrays.asList(GameMaster.getInstance().getOppBuffList());
                if (oppBuffCardList.contains(Card.Vitality)) {
                    use(Card.RepealX, Card.Vitality);
                } else if (oppBuffCardList.contains(Card.Shield)) {
                    use(Card.RepealX, Card.Shield);
                } else if (oppBuffCardList.contains(Card.Agility)) {
                    use(Card.RepealX, Card.Agility);
                } else if (oppBuffCardList.contains(Card.Axe)) {
                    use(Card.RepealX, Card.Axe);
                } else {
                    use(Card.RepealX);
                }
            } else {
                use(Card.RepealX);
            }
        }

        while (this.countHandCards(Card.Axe) > 0 || this.countHandCards(Card.Agility) > 0) {
            if ((Arrays.asList(this.getBuffList()).contains(Card.Vitality) && Arrays.asList(this.getBuffList()).contains(Card.Shield))
                && this.getHp() <= this.getMaxHp() / AgressiveCoefficient
                || (Arrays.asList(this.getBuffList()).contains(Card.Agility) && Arrays.asList(this.getBuffList()).contains(Card.Axe))) {
                break;
            } else {
                switch (this.countBuffs()) {
                    case 0:
                        if (this.countHandCards(Card.Axe) > 0) {
                            this.use(Card.Axe);
                        }
                        if (this.countHandCards(Card.Agility) > 0) {
                            this.use(Card.Agility);
                        }
                        break;
                    case 1:
                        if (Arrays.asList(this.getBuffList()).contains(Card.Shield)
                            || Arrays.asList(this.getBuffList()).contains(Card.Vitality)) {
                            if (this.countHandCards(Card.Attack) >= 2 && !Arrays.asList(this.getBuffList()).contains(Card.Agility)
                                && this.countHandCards(Card.Agility) > 0) {
                                this.use(Card.Agility);
                            } else {
                                if (!Arrays.asList(this.getBuffList()).contains(Card.Agility) && this.countHandCards(Card.Agility) > 0) {
                                    this.use(Card.Agility);
                                }
                                if (!Arrays.asList(this.getBuffList()).contains(Card.Axe) && this.countHandCards(Card.Axe) > 0) {
                                    this.use(Card.Axe);
                                }
                            }
                        } else {
                            if (!Arrays.asList(this.getBuffList()).contains(Card.Agility) && this.countHandCards(Card.Agility) > 0) {
                                this.use(Card.Agility);
                            }
                            if (!Arrays.asList(this.getBuffList()).contains(Card.Axe) && this.countHandCards(Card.Axe) > 0) {
                                this.use(Card.Axe);
                            }
                        }
                        break;
                    case 2:
                        
                        break;
                    default:
                        break;
                }
            }
        }

        afterMyTurn();
    }

    public void discarding() {
        for (int i = 0; i < CardDiscardSequence.length; i++) {
            while (countHandCards(CardDiscardSequence[i]) > 0 && this.countHandCards() > Math.ceil(this.getHp() / 0.01)) {
                discard(CardDiscardSequence[i]);
            }
        }
    }

    public void beingAttack() {
        if (this.canDefense()) {
            use(Card.Defense);
        }
    }
}
