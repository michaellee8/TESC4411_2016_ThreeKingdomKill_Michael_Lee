import comp.*;

public class DummyXiaHouDun extends HeroXiaHouDun {

	@Override
	public void drawing() {
		// 抽牌。
		draw();
	}

	@Override
	public void myTurn() {
		GameMaster master = GameMaster.getInstance();

		//如果自己沒有裝備戰盾牌而自己手上有戰盾牌，裝備戰盾牌。
		if (!isActive(Card.Shield) && countHandCards(Card.Shield) > 0)
			use(Card.Shield);
		
		//如果對方裝備了戰盾牌而自己手上有搶奪牌，使用搶奪牌奪去對方裝備中的戰盾牌。
		if (master.isActiveOpp(Card.Shield) && countHandCards(Card.Grab) > 0)
			use(Card.Grab, Card.Shield);

		//如果對方裝備了戰盾牌而自己手上有消除牌，使用消除牌除去對方裝備中的戰盾牌。
		if (master.isActiveOpp(Card.Shield) && countHandCards(Card.Repeal) > 0)
			use(Card.Repeal, Card.Shield);
		
		// 如果自己的可攻擊次大於0而手上有殺牌時，使用殺牌攻撃對方。
		while (getAttackTimes() > 0 && countHandCards(Card.Attack) > 0)
			use(Card.Attack);

		// 如果手上有補藥牌而自己的生命值少於最大生命值時，使用補藥牌回復生命值。
		while (countHandCards(Card.Heal) > 0 && getHp() < getMaxHp())
			use(Card.Heal);
	}

	@Override
	public void discarding() {
		// 如果手上擋牌多於2張時，丟棄擋牌。
		while (countHandCards(Card.Defense) > 2)
			discard(Card.Defense);
		
		// 如果手上補藥牌多於1張時，丟棄補藥牌。
		while (countHandCards(Card.Heal) > 1)
			discard(Card.Heal);
		
		// 如果手上殺牌多於1張時，丟棄殺牌。
		while (countHandCards(Card.Attack) > 1)
			discard(Card.Attack);
		
		// 最後如果手牌數量仍然大於可保留手牌數量，由系統處理。

	}

	@Override
	public void beingAttack() {

		//如果生命值小於等於300點、對方可攻撃次數多於1時而手上有擋牌，使用擋牌作出抵抗。
		if (getHp() <= 300 && countHandCards(Card.Defense)>0)
			use(Card.Defense);
			
	}

}
