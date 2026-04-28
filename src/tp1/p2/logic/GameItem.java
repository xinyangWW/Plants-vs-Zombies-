package tp1.p2.logic;

/**
 * Represents a game item and its allowed game actions.
 *
 */
public interface GameItem {
	public enum Option {NoExplosion, PlantExplosion, ZombieExplosion};
	/**
	 * Receive a zombie attack.
	 * 
	 * @param damage Received damage.
	 * 
	 * @return <code>true</code> if a plant has been attacked, <code>false</code>
	 *         otherwise.
	 */
	boolean receiveZombieAttack(int damage, Option option);

	/**
	 * Receive a plant attack.
	 * 
	 * @param damage Received damage.
	 * 
	 * @return <code>true</code> if a plant has been attacked, <code>false</code>
	 *         otherwise.
	 */
	boolean receivePlantAttack(int damage, Option option);

	
	/**
	 * Try to catch a sun (if no other sun has been catched this cycle).
	 * 
	 * @return <code>true</code> if the sun has been catched, <code>false</code> otherwise.
	 */
	boolean catchObject();

}
