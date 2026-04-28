package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.zombieDescription;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie {
	public static int endurance = 8;
    public static int damage = 1;
    public static int frecuencia = 4;
    public static int speed = 4;
    
    public BucketHead() {}
    
    public BucketHead(GameWorld game, int col, int row){
    	super(game, col, row);
		this.life = endurance;
		this.ciclo = speed;
		this.ciclo_ini = speed;
    }
    
    @Override
	protected String getSymbol() {
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return zombieDescription(Messages.BUCKET_HEAD_ZOMBIE_NAME, speed, damage, endurance);
	}
	
	@Override
	public Zombie create(GameWorld game, int col, int row) {
		return new BucketHead(game,col,row);
	}
	
}
