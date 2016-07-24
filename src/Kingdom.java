import comp.*;

public class Kingdom {
	
	public static void main(String[] args) {
		GameMaster master = GameMaster.getInstance();
		
		master.setLightning(30);
		
		int side = 0;
		master.setPlayer(side, "左方");

		new Manual("HeroHuangGai").join(side);

		
		side = 1;
		master.setPlayer(side, "右方");

		new DummyXuChu().join(side);
		new DummyZhaoYun().join(side);
		new DummyMaChao().join(side);
//		new DummyHuangGai().join(side);
//		new DummyXiaHouDun().join(side);
//		new DummyZhangLiao().join(side);
//		new DummyZhouYu().join(side);
//		new DummyGanNing().join(side);
//		new DummyZhangFei().join(side);

		master.setVisible(1, false);
		master.start();
	}

}
