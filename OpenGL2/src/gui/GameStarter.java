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
	
	
	public GameStarter(){
		initGame();
		
	}
	private Renderer render;
	private Level lev;
	
	
	public void initGame(){
		render = new Renderer();
		render.init();
		LevelLoader l = new LevelLoader();
		lev = l.loadLevel("");
		gameLoop();
	}
	
	public void gameLoop(){
		while(!Display.isCloseRequested()){
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println("ik leef nog steeds");
			render.renderLevel(lev, 0, 10, 0, 5);
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