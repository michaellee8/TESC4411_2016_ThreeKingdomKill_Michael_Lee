import comp.*;


public class DummyZhaoYun_edited extends HeroZhaoYun {

	@Override
	public void drawing() {
		// 抽牌。
		draw();		
	}

	@Override
	public void myTurn() {
		
		// 如果自己的可攻擊次大於0而手上有殺牌或擋牌時，作出攻撃。
		while (getAttackTimes() > 0 && (countHandCards(Card.Attack) > 0 || countHandCards(Card.Defense) > 0))
			use(Card.Attack);	// use(Card.Defense); 效果相同
		
		
		// 如果手上有生命之鎧牌而自己的生命值少於最大生命值時，
		while (countHandCards(Card.Vitality) > 0 && getHp() < getMaxHp()) {

			//如果已裝備兩張增益牌，
			if (countBuffs() == 2) {
				//如果裝備了戰斧牌，除下戰斧牌。
				if (isActive(Card.Axe)) {
					deactivate(Card.Axe);
					
				//如果裝備了戰盾牌，除下戰盾牌。
				} else if (isActive(Card.Shield))
					deactivate(Card.Shield);
			}
			
			// 使用生命之鎧牌。
			use(Card.Vitality);
		}
	}

	@Override
	public void discarding() {
		// 由系統處理。
	}

	@Override
	public void beingAttack() {

		// 如果手上有擋牌或殺牌時，使用作出抵擋。
		if (countHandCards(Card.Defense) > 0 ||  countHandCards(Card.Attack) > 0)
			use(Card.Defense);	// use(Card.Attack); 效果相同

	}
}
