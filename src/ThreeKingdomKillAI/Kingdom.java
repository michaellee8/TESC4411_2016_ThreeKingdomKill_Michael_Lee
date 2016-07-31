import comp.*;

public class Kingdom {
	
	public static void main(String[] args) {
		GameMaster master = GameMaster.getInstance();
		
		master.setLightning(30);
		
		int side = 0;
		master.setPlayer(side, "左方");

		new AIZhaoYun().join(side);
		new AIZhaoYun().join(side);
		new AIZhaoYun().join(side);

		
		side = 1;
		master.setPlayer(side, "右方");

		new AIZhaoYun().join(side);
		new AIZhaoYun().join(side);
		new AIZhaoYun().join(side);

		master.setVisible(1, true);
		master.setVisible(0, true);
		master.start();
	}

}
