package gui;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import model.Level;

public class Renderer {
	
	public void init(){
		createWindow();
	}
	public void renderLevel(Level lev){
		
	}
	
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
