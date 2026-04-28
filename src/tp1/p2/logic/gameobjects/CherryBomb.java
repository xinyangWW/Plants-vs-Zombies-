package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.plantDescription;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant {
	public static int coste = 50;
    public static int endurance = 2;
    public static int damage = 10;
    public static int frecuencia = 2;

    public CherryBomb() {}
    
    public CherryBomb(GameWorld game, int col, int row) {
    	super(game,col,row);
    	this.life = endurance;
    	this.ciclo = frecuencia + 1;
    }

	@Override
	public int getCost() {
		return CherryBomb.coste;
	}

	@Override
	public String getName() {
		return Messages.CHERRY_BOMB_NAME;
	}

	@Override
	protected Plant create(GameWorld game, int col, int row) {
		return new CherryBomb(game, col, row);
	}

	@Override
	protected String getSymbol() {
		return ciclo != 1 ? Messages.CHERRY_BOMB_SYMBOL : Messages.CHERRY_BOMB_SYMBOL.toUpperCase();
	}

	@Override
	public String getDescription() {
		return plantDescription(Messages.CHERRY_BOMB_NAME_SHORTCUT, coste, damage, endurance);
	}

	@Override
	public void update() {
		this.ciclo--;
		if(ciclo == 0) {
			this.life = 0;
			game.pushAction(new ExplosionAction(col, row, damage, true));
		}
	}

	@Override
	public void onEnter() {}

	@Override
	public void onExit() {}
}
