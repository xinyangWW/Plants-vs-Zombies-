package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.plantDescription;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant {
	public static int coste = 50;
    public static int endurance = 3;
    public static int damage = 1;
    public static int frecuencia = 1;

    public Peashooter() {}
    
    public Peashooter(GameWorld game, int col, int row) {
    	super(game,col,row);
    	this.life = endurance;
    	this.ciclo = frecuencia;
    }

	@Override
	public int getCost() {
		return coste;
	}
    
    @Override
	protected String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL;
	}

	@Override
	public String getName() {
		return Messages.PEASHOOTER_NAME;
	}

	@Override
	public String getDescription() {
		return plantDescription(Messages.PEASHOOTER_NAME_SHORTCUT, coste, damage, endurance);
	}

	@Override
	public void update() {
		for(int i = this.col; i < GameWorld.NUM_COLS; i++) {
			GameItem item = game.getGameItemInPosition(i, row);
			if(item != null && item.receivePlantAttack(damage, Option.NoExplosion)) break;
		}
	}

	@Override
	protected Plant create(GameWorld game, int col, int row) {
		return new Peashooter(game, col, row);
	}

	@Override
	public void onEnter() {}

	@Override
	public void onExit() {}
}
