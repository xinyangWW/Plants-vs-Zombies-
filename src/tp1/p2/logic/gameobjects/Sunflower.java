package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.plantDescription;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant {
	public static int coste = 20;
    public static int endurance = 1;
    public static int damage = 0;
    public static int frecuencia = 3;
    
    public Sunflower() {}
    
    public Sunflower(GameWorld game, int col, int row) {
    	super(game,col,row);
    	this.life = endurance;
    	this.ciclo = frecuencia;
    }

	@Override
	public int getCost() {
		return Sunflower.coste;
	}

	@Override
	public String getName() {
		return Messages.SUNFLOWER_NAME;
	}

	@Override
	protected String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}

	@Override
	public String getDescription() {
		return plantDescription(Messages.SUNFLOWER_NAME_SHORTCUT, coste, damage, endurance);
	}

	@Override
	public void update() {
		if(this.ciclo == 0) {
    		game.addSun();
    		this.ciclo = Sunflower.frecuencia - 1;
    	} else this.ciclo--;
	}

	@Override
	protected Plant create(GameWorld game, int col, int row) {
		return new Sunflower(game, col, row);
	}

	@Override
	public void onEnter() {}

	@Override
	public void onExit() {}
}
