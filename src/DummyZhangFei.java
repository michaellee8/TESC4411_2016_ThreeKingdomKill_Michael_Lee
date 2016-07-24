import comp.*;

public class DummyZhangFei extends HeroZhangFei {

	public DummyZhangFei() {
	}
	
	@Override
	public void drawing() {
		// 抽牌。
		draw();
	}

	@Override
	public void myTurn() {
		GameMaster master = GameMaster.getInstance();

		// 如果手上有搶奪牌而對方有裝備增益牌時，使用搶奪牌奪去對方的第一張裝備中的增益牌。
		while (countHandCards(Card.Grab) > 0 && master.countOppBuffs() > 0) {
			Card targetCcard = master.getOppBuffList()[0];
			use(Card.Grab, targetCcard);
		}
		
		// 如果手上有搶奪牌而對方有手牌時，使用搶奪牌奪去對方的一張手牌。
		while (countHandCards(Card.Grab) > 0 && master.countOppHandCards() > 0) {
			use(Card.Grab);
		}

		// 如果自己的可攻擊次大於0而手上有殺牌時，使用殺牌攻撃對方。
		while(countHandCards(Card.Attack) > 0 && getAttackTimes() > 0){
			use(Card.Attack);

			// 如果自己手上有殺牌時但可攻擊次等於0和手上有敏捷牌，裝備敏捷。
			if (countHandCards(Card.Attack) > 0 && getAttackTimes() == 0 && countHandCards(Card.Agility)> 0)
				use(Card.Agility);
		}
		
		// 如果手上有補藥牌而自己的生命值少於最大生命值時，使用補藥牌回復生命值。
		while(countHandCards(Card.Heal) > 0 && getHp() < getMaxHp())
			use(Card.Heal);
		
	}

	@Override
	public void discarding() {
		
		// 計算可保留的手牌數量 (0.01 * 生命值, 再取最大整數值)
		int numOfCardsCanKeep = (int) (Math.ceil(0.01 * getHp()));
		
		while (countHandCards()> numOfCardsCanKeep){

			// 丟棄戰斧牌
			if (countHandCards(Card.Axe) > 0)
				discard(Card.Axe);
			
			// 丟棄敏捷牌
			else if (countHandCards(Card.Agility) > 0)
				discard(Card.Agility);

			// 丟棄戰盾牌
			else if (countHandCards(Card.Shield) > 0)
				discard(Card.Shield);
			
			// 丟棄生命之鎧牌
			else if (countHandCards(Card.Vitality) > 0)
				discard(Card.Vitality);

			// 丟棄補藥牌
			else if (countHandCards(Card.Heal) > 0)
				discard(Card.Heal);
			else
				break;
		}
		
		// 最後如果手牌數量仍然大於可保留手牌數量，由系統處理。

	}

	@Override
	public void beingAttack() {
		// 如果手上有擋牌時，使用檔牌作出抵抗。
		if (countHandCards(Card.Defense) > 0)
			use(Card.Defense);
	}

}
