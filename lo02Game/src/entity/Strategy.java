package entity;

/**
 * The interface Strategy is part of the strategy pattern.
 * 
 * The strategy can be attack or treat or random.
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */

public interface Strategy {

	/**
	 * 
	 * @param p    the student who execute the strategy.
	 * @param zone in which the student execute the strategy.
	 */
	public void executeStrategy(Protagonist p, int zone);

}
