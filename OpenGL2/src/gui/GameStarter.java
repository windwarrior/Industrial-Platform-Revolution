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
	public static void main(String[] args){
		new GameStarter();
	}
	
	public GameStarter(){

		initGame();
	}
	private Renderer render;
	
	
	public void initGame(){
		render = new Renderer();
		render.init();
		gameLoop();
	}
	
	public void gameLoop(){
		while(!Display.isCloseRequested()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
}
