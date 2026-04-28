package tp1.p2.logic;

import java.util.Random;

import tp1.p2.logic.gameobjects.Sun;

public class SunsManager {

	private static final int COOLDOWN_RANDOM_SUN = 5;

	private GameWorld game;

	private Random rand;

	private int cooldown;
	
	private int catchedSun = 0;
	
	private int generatedSun = 0;
	
	private boolean sinSol = false;

	public SunsManager(GameWorld game, Random rand) {
		this.game = game;
		this.rand = rand;
		this.cooldown = COOLDOWN_RANDOM_SUN;
		this.catchedSun = 0;
		this.generatedSun = 0;
	}

	public int getCatchedSuns() {
		return catchedSun;
	}

	public int getGeneratedSuns() {
		return generatedSun;
	}

	public void update() {
		if (cooldown == 0) {
			if(!sinSol) addSun();
			cooldown = COOLDOWN_RANDOM_SUN;
		} else {
			cooldown--;
		}
	}

	private int getRandomInt(int bound) {
		return this.rand.nextInt(bound);
	}

	public void addSun() {
		if(sinSol) {
			game.addSunCoin(10);
		}
		else {
			int col = getRandomInt(GameWorld.NUM_COLS);
			int row = getRandomInt(GameWorld.NUM_ROWS);
			game.addItem(new Sun(this.game, col, row));
			generatedSun++;
		}
	}
	
	public void addCatchedSuns() {
		catchedSun++;
	}
}
