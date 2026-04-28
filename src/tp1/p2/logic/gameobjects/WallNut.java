package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.plantDescription;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant{
	public static int coste = 50;
    public static int endurance = 10;
    public static int damage = 0;
    public static int frecuencia = 0;

    public WallNut() {}
    
    public WallNut(GameWorld game, int col, int row) {
    	super(game,col,row);
    	this.life = endurance;
    	this.ciclo = frecuencia;
    }

	@Override
	public int getCost() {
		return WallNut.coste;
	}

	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME;
	}

	@Override
	protected Plant create(GameWorld game, int col, int row) {
		return new WallNut(game,col,row);
	}

	@Override
	protected String getSymbol() {
		return Messages.WALL_NUT_SYMBOL;
	}

	@Override
	public String getDescription() {
		return plantDescription(Messages.WALL_NUT_NAME_SHORTCUT, coste, damage, endurance);
	}

	@Override
	public void update() {}

	@Override
	public void onEnter() {}

	@Override
	public void onExit() {}
}
