package gui;

import loader.LevelLoader;
import model.Level;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * This class will link the Graphical user interface to
 * the model of this game
 * @author Lennart and Steven
 *
 */
public class GameStarter {
	private Game game;
	private int clickcount = 0;
	private Renderer render;
	private Level lev;
	private int i;
	private long oldTime = System.currentTimeMillis();
	private long newTime;
	private long timeDiff;
	
	private int horMove = 0;
	private int vertMove = 0;
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
		LevelLoader l = new LevelLoader();
		lev = l.loadLevel("");
		game = new Game(lev);
		gameLoop();
	}
	
	/**
	 * The main loop in the game. Updates the display in each loop.
	 */
	public void gameLoop(){
		while(!Display.isCloseRequested()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timeDiff = oldTime-newTime > 0 ? oldTime-newTime : 1;
			oldTime = newTime;
			getKeys();
			game.updateGame(horMove, vertMove, timeDiff);
			render.renderLevel(lev, i > 0 ? i: 0,i+15 < lev.getBackground().length ? i + 15 : lev.getBackground().length  - 1 , 0, 7, 0.0f,0.0f);
			Display.update();
		}
		Display.destroy();
	}
	
	private void getKeys(){
		while(Keyboard.next()){
			clickcount++;
			System.out.println("klik " + clickcount);
			if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT){
				System.out.println("rechts");
				horMove = 1;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_LEFT){
				horMove = -1;
			}
		}
	}
	

	public static void main(String[] args){
		new GameStarter();
	}
}
