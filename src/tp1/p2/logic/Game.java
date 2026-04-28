package tp1.p2.logic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.control.exceptions.NotEnoughCoinsException;
import tp1.p2.control.Level;
import tp1.p2.logic.GameItem.Option;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;

public class Game implements GameStatus, GameWorld {

	private long seed;

	private Level level;
	
	private int cycle;
	
	private int score;

	private GameObjectContainer container;

	private Deque<GameAction> actions;

	// TODO add your attributes here
	public static int Sun = 10;
	private boolean zombieWins = false;
    private boolean playerQuits = false;
	private ZombiesManager zombiesManager;
	private SunsManager sunsManager;
	public static final int INITIAL_SUNCOINS = 50;
	private int SunCoins = INITIAL_SUNCOINS;
	private Record record;

	public Game(long seed, Level level) throws GameException {
		this.seed = seed;
		this.level = level;
		this.container = new GameObjectContainer();
		reset();
	}

	/**
	 * Resets the game.
	 */
	@Override
	public void reset() throws GameException {
		reset(this.level, this.seed);
	}

	/**
	 * Resets the game with the provided level and seed.
	 * 
	 * @param level {@link Level} Used to initialize the game.
	 * @param seed Random seed Used to initialize the game.
	 */
	@Override
	public void reset(Level level, long seed) throws GameException {
		// TODO add your code here
		this.cycle = 0;
		this.score = 0;
		this.actions = new ArrayDeque<>();
		this.SunCoins = INITIAL_SUNCOINS;
		
        if(level != null) this.level = level;
        if(seed != 0) this.seed = seed;
        
        Random rand = new Random(this.seed);
        this.record = new Record(level);
        this.container = new GameObjectContainer();
        this.zombiesManager = new ZombiesManager(this, this.level, rand);
        this.sunsManager = new SunsManager(this, rand);
	}


	/**
	 * Executes the game actions and update the game objects in the board.
	 * 
	 */
	@Override
	public void update() throws GameException{

		// 1. Execute pending actions
		executePendingActions();

		// 2. Execute game Actions
		// TODO add your code here
		zombiesManager.update();
		sunsManager.update();
		// 3. Game object updates
		// TODO add your code here
		container.update();
		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {
			// 4. Remove dead
			deadRemoved = this.container.removeDead();

			// 5. execute pending actions
			executePendingActions();
		}

		this.cycle++;

		// 6. Notify commands that a new cycle started
		Command.newCycle();
		

		// 7. Update record
		// TODO your code here

	}

	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}

	// TODO add your code here
	@Override
	public void pushAction(GameAction gameAction) {
	    this.actions.addLast(gameAction);
	}

	@Override
	public void addSun() {
		sunsManager.addSun();
	}

	@Override
	public void tryToCatchObject(int col, int row) {
		while(container.tryToCatchObject(col, row)) {
			sunsManager.addCatchedSuns();
			addSunCoin(10);
		}
	}

	@Override
	public boolean addItem(GameObject item) {
		return container.addItem(item);
	}

	@Override
	public void zombieWins() {
		zombieWins = true;
	}

	@Override
	public void playerQuits() {
		playerQuits = true;
	}

	@Override
	public void reduceZombie(Option dieOption) {
		score += dieOption == Option.NoExplosion ? 10 : 20;
		zombiesManager.reduceZombie();
	}

	@Override
	public boolean addSunCoin(int num) {
		int tmp = SunCoins + num;
		if(tmp >= 0) SunCoins = tmp;
		else return false;
		return true;
	}

	@Override
	public boolean execute(Command command) throws GameException {
		return command.execute(this);
	}

	@Override
	public GameItem getGameItemInPosition(int col, int row) {
		return container.get(col, row);
	}

	@Override
	public int getCycle() {
		return cycle;
	}

	@Override
	public int getSuncoins() {
		return SunCoins;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public long getSeed() {
		return seed;
	}

	@Override
	public int getRecordScore() {
		return record.getRecordScore();
	}

	@Override
	public String getLevelname() {
		return this.level.name();
	}

	@Override
	public int getRemainingZombies() {
		return zombiesManager.getRemainingZombies();
	}

	@Override
	public String positionToString(int col, int row) {
		return container.positionToString(col, row);
	}

	@Override
	public int getGeneratedSuns() {
		return sunsManager.getGeneratedSuns();
	}

	@Override
	public int getCaughtSuns() {
		return sunsManager.getCatchedSuns();
	}

	@Override
	public boolean isFinished() {
		return playerQuits || zombieWins || zombiesManager.checkPlayerWin();
	}

	@Override
	public boolean isPlayerQuits() {
		return playerQuits;
	}

	@Override
	public boolean isZombieWins() {
		return zombieWins;
	}

	@Override
	public void saveRecord() throws GameException {
		record.save(score);
	}

	@Override
	public void tryToBuy(int cost) throws GameException {
		if(!addSunCoin(-cost)) throw new NotEnoughCoinsException();
	}

	@Override
	public void checkValidPlantPosition(int col, int row) throws GameException {
		if(getGameItemInPosition(col, row) != null) throw new InvalidPositionException(col, row);
	}

	@Override
	public void checkValidZombiePosition(int col, int row) throws GameException {
		if(getGameItemInPosition(col, row) != null) throw new InvalidPositionException(col, row);
	}
	
}
