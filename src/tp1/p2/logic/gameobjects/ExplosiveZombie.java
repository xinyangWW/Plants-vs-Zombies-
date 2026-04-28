package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.zombieDescription;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie {
	public static int endurance = 5;
    public static int explosive_damage = 3;
    public static int frecuencia = 2;
    public static int speed = 2;
    
    public ExplosiveZombie() {}
    
    public ExplosiveZombie(GameWorld game, int col, int row){
    	super(game, col, row);
		this.life = endurance;
		this.ciclo = speed;
		this.ciclo_ini = speed;
    }
    
    @Override
	protected String getSymbol() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return zombieDescription(Messages.EXPLOSIVE_ZOMBIE_NAME, speed, damage, endurance);
	}
	
	@Override
	public void onExit() {
		game.pushAction(new ExplosionAction(col, row, explosive_damage, false));
		game.reduceZombie(dieCause);
	}
	
	@Override
	public Zombie create(GameWorld game, int col, int row) {
		return new ExplosiveZombie(game,col,row);
	}
	
}
