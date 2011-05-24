package gui;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import loader.LevelLoader;
import model.Entity;
import model.Level;
import model.Model;
import model.NormalPoint;
import model.TexturePoint;
import model.TexturedNormalTriangle;
import model.TexturedTriangle;
import model.Triangle;
import model.Vertex;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

/**
 * This class will link the Graphical user interface to
 * the model of this game
 * @author Lennart and Steven
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
	
}