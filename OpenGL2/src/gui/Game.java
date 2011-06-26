package gui;

import model.Level;

/**
 * Game is a class which holds the current state of the game
 * It is responible for moving the AI characters and keeping track
 * where the player is currently located.
 * @author Lennart
 *
 */
public class Game {
	private float playerX;
	private float playerY;
	private float playerSpeed = 1.4f; // A human will walk at 5km per hour, but this game calculates in meters per second
	private Level lev;
	
	public Game(Level lev) {
		this.lev = lev;
	}

	/**
	 * This method is used to update the game by calling methods that calculate the
	 * further movements of all the characters.
	 * It will also calculate collisions.
	 * @require {requestX,requestY} == 1 || -1;
	 * @param requestX The requested X position
	 * @param requestY The requested Y position
	 */
	public void updateGame(int requestX, int requestY, long timeDiff){
		//Dummy implementation of the movement, player will always move.
		//no collision detection yet.
		playerX += (1/timeDiff)*requestX*playerSpeed;
		playerY += (1/timeDiff)*requestY*playerSpeed;
	}
	
}
