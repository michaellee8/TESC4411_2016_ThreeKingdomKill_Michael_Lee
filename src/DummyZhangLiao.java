import comp.*;

import comp.GameMaster;
import comp.HeroZhangLiao;

public class DummyZhangLiao extends HeroZhangLiao {
	@Override
	public void drawing() {
		GameMaster master = GameMaster.getInstance();
		
		//如果D對方手牌數量大於0，使用技能。 不然，進行抽牌。
		if (master.countOppHandCards() > 0)
			spec();
		else
			draw();
	}

	@Override
	public void myTurn() {

		// 如果自己的可攻擊次大於0而手上有殺牌時，使用殺牌攻撃對方。
		while (getAttackTimes() > 0 && countHandCards(Card.Attack) > 0)
			use(Card.Attack);

		// 如果手上有補藥牌而自己的生命值少於最大生命值時，使用補藥牌回復生命值。
		while (countHandCards(Card.Heal) > 0 && getHp() < getMaxHp())
			use(Card.Heal);
	}

	@Override
	public void discarding() {
		//由系統處理。
	}

	@Override
	public void beingAttack() {
		
		//如果手上有擋牌，使用擋牌作出抵抗。
		if (countHandCards(Card.Defense) > 0)
			use(Card.Defense);
		
	}

}
