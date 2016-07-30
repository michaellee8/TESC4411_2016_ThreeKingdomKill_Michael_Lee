import comp.*;
import java.util.Arrays;
import java.util.List;

public abstract class AICommon extends Hero {
    public abstract void join(int index);

    protected void beforeMyTurn() { // Hero specific

    }

    protected void afterMyTurn() { // Hero specific

    }
    
    protected Boolean canDefense(){ // should be overrided for AIZhaoYun
        return Arrays.asList(this.getBuffList()).contains(Card.Defense);
    }
    
    protected Boolean canAttack(){ // should be overrided for AIZhaoYun
        return Arrays.asList(this.getBuffList()).contains(Card.Attack);
    }
    
    protected static Card[] CardDiscardSequence = {Card.Repeal, Card.Grab, Card.Agility, Card.Axe, Card.Shield, Card.Vitality, Card.Attack,
            Card.Defense, Card.Heal             };

    public void drawing() {
        draw();
    }

    public void myTurn() {
        beforeMyTurn();
        while (countHandCards(Card.Grab) > 0) { //use Card.Grab
            if (GameMaster.getInstance().countOppBuffs() > 0 && this.countBuffs() < 2) {
                List<Card> oppBuffCardList = Arrays.asList(GameMaster.getInstance().getOppBuffList());
                List<Card> myBuffCardList = Arrays.asList(this.getBuffList());
                if (oppBuffCardList.contains(Card.Vitality) && !myBuffCardList.contains(Card.Vitality)) {
                    use(Card.Grab, Card.Vitality);
                } else if (oppBuffCardList.contains(Card.Shield) && !myBuffCardList.contains(Card.Shield)) {
                    use(Card.Grab, Card.Shield);
                } else if (oppBuffCardList.contains(Card.Agility) && !myBuffCardList.contains(Card.Agility)) {
                    use(Card.Grab, Card.Agility);
                } else if (oppBuffCardList.contains(Card.Axe) && !myBuffCardList.contains(Card.Axe)){
                    use(Card.Grab, Card.Axe);
                } else {
                    use(Card.Grab);
                }
            } else {
                use(Card.Grab);
            }
        }
        while (countHandCards(Card.Repeal) > 0) { //use Card.Repeal
            if (GameMaster.getInstance().countOppBuffs() > 0) {
                List<Card> oppBuffCardList = Arrays.asList(GameMaster.getInstance().getOppBuffList());
                if (oppBuffCardList.contains(Card.Vitality)) {
                    use(Card.Repeal, Card.Vitality);
                } else if (oppBuffCardList.contains(Card.Shield)) {
                    use(Card.Repeal, Card.Shield);
                } else if (oppBuffCardList.contains(Card.Agility)) {
                    use(Card.Repeal, Card.Agility);
                } else if (oppBuffCardList.contains(Card.Axe)){
                    use(Card.Repeal, Card.Axe);
                } else {
                    use(Card.Repeal);
                }
            } else {
                use(Card.Repeal);
            }
        }
        while (countHandCards(Card.RepealX) > 0) { //use Card.RepealX for HeroGanNing
            if (GameMaster.getInstance().countOppBuffs() > 0) {
                List<Card> oppBuffCardList = Arrays.asList(GameMaster.getInstance().getOppBuffList());
                if (oppBuffCardList.contains(Card.Vitality)) {
                    use(Card.RepealX, Card.Vitality);
                } else if (oppBuffCardList.contains(Card.Shield)) {
                    use(Card.RepealX, Card.Shield);
                } else if (oppBuffCardList.contains(Card.Agility)) {
                    use(Card.RepealX, Card.Agility);
                } else if (oppBuffCardList.contains(Card.Axe)){
                    use(Card.RepealX, Card.Axe);
                } else {
                    use(Card.RepealX);
                }
            } else {
                use(Card.RepealX);
            }
        }
        
        afterMyTurn();
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
