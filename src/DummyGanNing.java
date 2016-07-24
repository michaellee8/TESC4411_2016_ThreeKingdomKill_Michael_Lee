import comp.*;

public class DummyGanNing extends HeroGanNing {

	@Override
	public void drawing() {
		// 抽牌。
		draw();
	}

	@Override
	public void myTurn() {
		GameMaster master = GameMaster.getInstance();

		// 如果手上有消除牌而對方有裝備增益牌時，使用消除牌除去對方的第一張裝備中的增益牌。
		while (countHandCards(Card.Repeal) > 0 && master.countOppBuffs() > 0) {
			Card targetCcard = master.getOppBuffList()[0];
			use(Card.Repeal, targetCcard);
		}

		// 如果手上有補藥牌而對方有手上有牌時，轉換補藥牌成為消除X牌，再用該消除X牌除去對方的手上的一張牌。
		while (countHandCards(Card.Heal) > 0 && master.countOppHandCards() > 0) {
			spec(Card.Heal);
			use(Card.RepealX);
		}

		// 如果自己的可攻擊次大於0而手上有殺牌時，使用殺牌攻撃對方。
		while (getAttackTimes() > 0 && countHandCards(Card.Attack) > 0)
			use(Card.Attack);

		// 如果手上有補藥牌而自己的生命值少於最大生命值時，使用補藥牌回復生命值。
		while (countHandCards(Card.Heal) > 0 && getHp() < getMaxHp())
			use(Card.Heal);
	}

	@Override
	public void discarding() {
		
		// 計算可保留的手牌數量 (0.01 * 生命值, 再取最大整數值)
		int numOfCardsCanKeep = (int) (Math.ceil(0.01 * getHp()));
		
		while (numOfCardsCanKeep > 0) {
			// 丟棄殺牌
			if (countHandCards(Card.Attack) > 0) {
				discard(Card.Attack);
				numOfCardsCanKeep--;
			} else
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
