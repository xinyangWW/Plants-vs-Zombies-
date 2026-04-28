package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.zombieDescription;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie {
	public static int endurance = 2;
    public static int damage = 1;
    public static int frecuencia = 4;
    public static int speed = 1;
    
    public Sporty() {}
    
    public Sporty(GameWorld game, int col, int row){
    	super(game, col, row);
		this.life = endurance;
		this.ciclo = speed;
		this.ciclo_ini = speed;
    }
    
    @Override
	protected String getSymbol() {
		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return zombieDescription(Messages.SPORTY_ZOMBIE_NAME, speed, damage, endurance);
	}
	
	@Override
	public Zombie create(GameWorld game, int col, int row) {
		return new Sporty(game,col,row);
	}

}
