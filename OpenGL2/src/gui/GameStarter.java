package gui;

import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * This class will link the Graphical user interface to
 * the model of this game
 * @author Lennart
 *
 */
public class GameStarter {
	private Game game;
	private int clickcount = 0;
	private Renderer render;
	
	/**
	 * Makes a new GameStarter object
	 */
	public GameStarter(){
		initGame();		
	}
	
	/**
	 * Initialises the game
	 */
	public void initGame(){
		render = new Renderer();
		render.init();
		gameLoop();
	}
	
	/**
	 * The main loop in the game. Updates the display in each loop.
	 */
	public void gameLoop(){
		while(!Display.isCloseRequested()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("ik leef nog steeds");
			getKeys();
			Display.update();
		}
		Display.destroy();
	}
	
	private void getKeys(){
		if(Keyboard.next()){
			clickcount++;
			System.out.println("klik " + clickcount);
		}
	}
	
	public static void main(String[] args){
		new GameStarter();
	}
}
