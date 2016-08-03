package ThreeKingdomKillAI;

import java.util.Arrays;
import java.util.List;

import comp.Card;
import comp.GameMaster;
import comp.HeroZhangLiao;

class AICoreZhangLiao extends HeroZhangLiao {
	protected double AgressiveCoefficient = 2.0; // Hero Specific, Controls in which proportion of HP will hero switch between attack-sided and defense-sided mode, 2.0 is average, higher means more aggressive

	private int i = 0;

	protected void beforeMyTurn() { // TO-DOs before the attack stage

	}

	protected void afterMyTurn() { // TO-DOs after the defense stage

	}

	protected void inMyTurn() { // TO-DOs between attack stage and defense stage

	}

	protected Boolean canDefense() { // should be overrode for AIZhaoYun
		return this.countHandCards(Card.Defense) > 0;
	}

	protected Boolean canAttack() { // should be overrode for AIZhaoYun
		return this.countHandCards(Card.Attack) > 0;
	}

	protected static Card[] CardDiscardSequence = { Card.Repeal, Card.Grab, Card.Shield, Card.Axe, Card.Agility,
			Card.Attack, Card.Defense, Card.Heal, Card.Vitality };

	public void drawing() {
		draw();
	}

	public void myTurn() {
		beforeMyTurn();
		i = 0;
		while (i <= 10 && countHandCards(Card.Grab) > 0
				&& (GameMaster.getInstance().countOppBuffs() + GameMaster.getInstance().countOppHandCards()) > 0) { // use Card.Grab
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
			i++;
		}
		i = 0;
		while (i <= 10 && countHandCards(Card.Repeal) > 0
				&& (GameMaster.getInstance().countOppHandCards() > 0 || GameMaster.getInstance().countOppBuffs() > 0)) { // use Card.Repeal
			if (GameMaster.getInstance().countOppBuffs() > 0) {
				List<Card> oppBuffCardList = Arrays.asList(GameMaster.getInstance().getOppBuffList());
				if (oppBuffCardList.contains(Card.Vitality)
						&& GameMaster.getInstance().getOppHp() + 75 >= GameMaster.getInstance().getOppMaxHp()) {
					use(Card.Repeal, Card.Vitality);
				} else if (GameMaster.getInstance().countOppHandCards() <= 2 && this.countHandCards(Card.Attack) > 0) {
					use(Card.Repeal);
				} else if (oppBuffCardList.contains(Card.Shield)) {
					use(Card.Repeal, Card.Shield);
				} else if (oppBuffCardList.contains(Card.Agility)) {
					use(Card.Repeal, Card.Agility);
				} else if (oppBuffCardList.contains(Card.Axe)) {
					use(Card.Repeal, Card.Axe);
				} else if (oppBuffCardList.contains(Card.Vitality)) {
					use(Card.Vitality, Card.Shield);
				} else {
					use(Card.Repeal);
				}
			} else {
				use(Card.Repeal);
			}
			i++;
		}
		i = 0;
		AttackBuffWhile: while (i <= 10 && this.countHandCards(Card.Axe) > 0 || this.countHandCards(Card.Agility) > 0) { // use Attack Buff
			if ((Arrays.asList(this.getBuffList()).contains(Card.Vitality)
					&& Arrays.asList(this.getBuffList()).contains(Card.Shield))
					&& this.getHp() <= this.getMaxHp() / AgressiveCoefficient
					|| (Arrays.asList(this.getBuffList()).contains(Card.Agility)
							&& Arrays.asList(this.getBuffList()).contains(Card.Axe))) {
				break;
			} else {
				switch (this.countBuffs()) {
				case 0:
					if (!Arrays.asList(this.getBuffList()).contains(Card.Agility)
							&& this.countHandCards(Card.Agility) > 0) {
						this.use(Card.Agility);
					} else if (!Arrays.asList(this.getBuffList()).contains(Card.Axe)
							&& this.countHandCards(Card.Axe) > 0) {
						this.use(Card.Axe);
					}
					break;
				case 1:
					if (Arrays.asList(this.getBuffList()).contains(Card.Shield)
							|| Arrays.asList(this.getBuffList()).contains(Card.Vitality)) {
						if (this.countHandCards(Card.Attack) >= 2 && canAttack()
								&& !Arrays.asList(this.getBuffList()).contains(Card.Agility)
								&& this.countHandCards(Card.Agility) > 0) {
							this.use(Card.Agility);
						} else if (!Arrays.asList(this.getBuffList()).contains(Card.Agility)
								&& this.countHandCards(Card.Agility) > 0) {
							this.use(Card.Agility);
						} else if (!Arrays.asList(this.getBuffList()).contains(Card.Axe)
								&& this.countHandCards(Card.Axe) > 0) {
							this.use(Card.Axe);
						}
					} else if (!Arrays.asList(this.getBuffList()).contains(Card.Agility)
							&& this.countHandCards(Card.Agility) > 0) {
						this.use(Card.Agility);
					} else if (!Arrays.asList(this.getBuffList()).contains(Card.Axe)
							&& this.countHandCards(Card.Axe) > 0) {
						this.use(Card.Axe);
					} else {
						break AttackBuffWhile;
					}
					break;
				case 2:
					if (Arrays.asList(this.getBuffList()).contains(Card.Shield)) {
						this.deactivate(Card.Shield);
					} else if (Arrays.asList(this.getBuffList()).contains(Card.Vitality)) {
						this.deactivate(Card.Vitality);
					} else {
						break AttackBuffWhile;
					}
					if (this.countHandCards(Card.Agility) > 0 && this.countHandCards(Card.Attack) >= 2 && canAttack()
							&& !Arrays.asList(this.getBuffList()).contains(Card.Agility)) {
						this.use(Card.Agility);
					} else if (!Arrays.asList(this.getBuffList()).contains(Card.Agility)
							&& this.countHandCards(Card.Agility) > 0) {
						this.use(Card.Agility);
					} else if (!Arrays.asList(this.getBuffList()).contains(Card.Axe)
							&& this.countHandCards(Card.Axe) > 0) {
						this.use(Card.Axe);
					} else {
						break AttackBuffWhile;
					}

					break;
				default:
					break;
				}
			}
			i++;
		}
		i = 0;
		while (i <= 10 && this.getAttackTimes() > 0 && canAttack()) { // use Card.Attack
			this.use(Card.Attack);
			i++;
		}
		i = 0;
		while (i <= 10 && canAttack() && this.countHandCards(Card.Agility) > 0) { // use Card.Atack again if still have Card.Agility
			this.deactivate(Card.Agility);
			this.use(Card.Agility);
			this.use(Card.Attack);
			i++;
		}
		inMyTurn();
		i = 0;
		while (i <= 10 && this.countHandCards(Card.Vitality) > 0 && this.getHp() + 75 <= this.getMaxHp()) { // use Card.Vitality
			if (Arrays.asList(this.getBuffList()).contains(Card.Vitality)) {
				this.deactivate(Card.Vitality);
				this.use(Card.Vitality);
			} else if (this.countBuffs() == 2) {
				if (Arrays.asList(this.getBuffList()).contains(Card.Axe)) {
					this.deactivate(Card.Axe);
				} else if (Arrays.asList(this.getBuffList()).contains(Card.Agility)) {
					this.deactivate(Card.Agility);
				} else if (Arrays.asList(this.getBuffList()).contains(Card.Shield)) {
					this.deactivate(Card.Shield);
				} else if (Arrays.asList(this.getBuffList()).contains(Card.Vitality)) {
					this.deactivate(Card.Vitality);
				} else {
					break;
				}
				if (this.countBuffs() < 2) {
					this.use(Card.Vitality);
				} else {
					break;
				}
			}
			i++;
		}
		i = 0;
		while (i <= 10 && this.countHandCards(Card.Heal) > 0 && this.getHp() + 75 <= this.getMaxHp()) { // use Card.Heal
			this.use(Card.Heal);
			i++;
		}
		i = 0;
		while (i <= 10 && this.countHandCards(Card.Shield) > 0) { // use Card.Shield
			if (this.countBuffs() < 2 && !Arrays.asList(this.getBuffList()).contains(Card.Shield)) {
				this.use(Card.Shield);
			} else if (this.getHp() <= this.getMaxHp() / AgressiveCoefficient
					&& !Arrays.asList(this.getBuffList()).contains(Card.Shield)) {
				if (Arrays.asList(this.getBuffList()).contains(Card.Axe)) {
					this.deactivate(Card.Axe);
				} else if (Arrays.asList(this.getBuffList()).contains(Card.Agility)) {
					this.deactivate(Card.Agility);
				} else if (Arrays.asList(this.getBuffList()).contains(Card.Vitality)) {
					this.deactivate(Card.Vitality);
				} else {
					break;
				}
			} else {
				break;
			}
			i++;
		}
		afterMyTurn();
	}

	public void discarding() {
		for (int i = 0; i < CardDiscardSequence.length; i++) {
			while (countHandCards(CardDiscardSequence[i]) > 0
					&& this.countHandCards() > Math.ceil(this.getHp() / 0.01)) {
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
