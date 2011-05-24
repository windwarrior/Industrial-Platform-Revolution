package gui;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import model.Level;

public class Renderer {
	
	/**
	 * initialises the renderer. Creates a new window.
	 */
	public void init(){
		createWindow();
	}
	
	/**
	 * ?
	 * @param lev
	 */
	public void renderLevel(Level lev){
		
	}
	
	/**
	 * Tells LWJGL to create a display 
	 */
	public void createWindow(){
		try {
			
			Display.setDisplayMode(new DisplayMode(800,450));

			Display.setVSyncEnabled(true);
			Display.create();
		} catch (LWJGLException e) {
			System.err.println("Could not create window");
			System.exit(-1);
		}
	}
}
