package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sun extends GameObject {
    public static int endurance = 10;

    public Sun() {}
    
    public Sun(GameWorld game, int col, int row) {
    	super(game,col,row);
    	this.life = endurance + 1;
    }
    
	@Override
	public boolean receiveZombieAttack(int damage, Option option) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage, Option option) {
		return false;
	}

	@Override
	public boolean catchObject() {
		return true;
	}

	@Override
	public boolean isAlive() {
		return life > 0;
	}

	@Override
	protected String getSymbol() {
		return Messages.SUN_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.SUN_DESCRIPTION;
	}

	@Override
	public void update() {
		this.life--;
	}

	@Override
	public void onEnter() {}

	@Override
	public void onExit() {}

}
