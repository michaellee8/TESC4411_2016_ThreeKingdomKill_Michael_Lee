import comp.*;

public class DummyXuChu extends HeroXuChu {

	boolean specOn = false;
	@Override
	public void drawing() {
		
		//如果手上的擋牌數量少於2點時，使用強化技能。 否則，進行抽牌。
		if (countHandCards(Card.Defense) < 2) {
			spec();
			specOn = true;
		} else {
			draw();
			specOn = false;
		}
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
		
		//當沒有使用強化技能或生命值少於等於250點時，而手上有擋牌，使用擋牌作出抵抗。
		if ((!specOn || getHp() < 250) && countHandCards(Card.Defense) > 0)
			use(Card.Defense);
		
	}

}
