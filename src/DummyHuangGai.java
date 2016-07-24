import comp.*;

import comp.GameMaster;
import comp.HeroHuangGai;

public class DummyHuangGai extends HeroHuangGai {

	@Override
	public void drawing() {
		// 抽牌。
		draw();
	}

	@Override
	public void myTurn() {
		boolean act = false;

		do {
			act = false;	// 未有行動
			
			//如果生命值大於最大生命值的一半時，使用技能。
			while (getHp() > getMaxHp() / 2 && countHandCards() < 8) {
				spec();
				act = true;		// 已行動
			}
			
			// 如果自己沒有裝備敏捷牌而手上有敏捷牌，
			if(!isActive(Card.Agility) && countHandCards(Card.Agility) > 0) {
				// 如果已裝備了一張以上增益牌，而其中一張是生命之鎧牌，先除下生命之鎧牌。
				if (countBuffs()>1 && isActive(Card.Vitality))
					deactivate(Card.Vitality);
					
				use(Card.Agility);	//裝備敏捷牌。
			}
			
			// 如果自己的可攻擊次大於0而手上有殺牌時，使用殺牌攻撃對方。
			while (getAttackTimes() > 0 && countHandCards(Card.Attack) > 0) {
				use(Card.Attack);
				act = true;
			}

			// 如果手上有補藥牌而自己的生命值少於最大生命值時，使用補藥牌回復生命值。
			while (countHandCards(Card.Heal) > 0 && getHp() < getMaxHp()) {
				use(Card.Heal);
				act = true;
			}
			
			// 如果手上有生命之鎧牌而自己的生命值少於最大生命值時，裝備生命之鎧牌。
			while (countHandCards(Card.Vitality) > 0 && getHp() < getMaxHp()) {
				use(Card.Vitality);
				act = true;
			}

		} while (act);	//如果有行動，繼續下一輪。不然，離開DO..WHILE LOOP。
	}

	@Override
	public void discarding() {
		// 由系統處理。
	}

	@Override
	public void beingAttack() {
		GameMaster master = GameMaster.getInstance();

		// 如果生命值小於等於300點、對方可攻撃次數多於1時而手上有擋牌，使用擋牌作出抵抗。
		if (getHp() <= 300 && master.getOppAttackTimes() > 1 && countHandCards(Card.Defense) > 0)
			use(Card.Defense);

		// 如果生命值小於等於200點而手上有擋牌，使用擋牌作出抵抗。
		else if (getHp() < 200 && countHandCards(Card.Defense) > 0)
			use(Card.Defense);

	}

}
