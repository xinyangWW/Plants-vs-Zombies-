package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.zombieDescription;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Zombie extends GameObject{
	public static int endurance = 5;
    public static int damage = 1;
    public static int frecuencia = 2;
    public static int speed = 2;
    protected int ciclo_ini = speed;

    public Zombie() {}
    
    public Zombie(GameWorld game, int col, int row) {
    	super(game,col,row);
		this.life = endurance;
		this.ciclo = speed;
		this.ciclo_ini = speed;
	}

	@Override
	public boolean receiveZombieAttack(int damage, Option option) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage, Option option) {
		if(isAlive()) {
			this.dieCause = option;
			life -= damage;
			return true;
		}
		return false;
	}

	@Override
	public boolean isAlive() {
		return life > 0;
	}

	@Override
	protected String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return zombieDescription(Messages.ZOMBIE_NAME, speed, damage, endurance);
	}
	
	@Override
	public void update() {
		GameItem item = game.getGameItemInPosition(col - 1, row);
		if(item != null && !item.catchObject()) if(this.ciclo == 0) this.ciclo = ciclo_ini;
		if(item == null || !item.receiveZombieAttack(damage, Option.NoExplosion)) {
			if(this.ciclo == 0) {
				this.col--;
				this.ciclo = this.ciclo_ini;
			}
			GameItem it = game.getGameItemInPosition(col - 1, row);
			if(it != null) it.receiveZombieAttack(damage, Option.NoExplosion);
		}
		if(col < 0) game.zombieWins();
		this.ciclo--;
	}

	@Override
	public void onEnter() {}

	@Override
	public void onExit() {game.reduceZombie(dieCause);}
	
	public Zombie create(GameWorld game, int col, int row) {
		return new Zombie(game,col,row);
	}
}
