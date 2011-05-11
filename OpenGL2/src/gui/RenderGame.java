package gui;

import static org.lwjgl.opengl.GL11.*;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import loader.ObjLoader;
import model.Model;
import model.NormalPoint;
import model.TexturePoint;
import model.TexturedNormalTriangle;
import model.TexturedTriangle;
import model.Triangle;
import model.Vertex;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class RenderGame {
	private boolean DEBUG =  true;
	private Model m;
	private long old_time = 0;
	private long timediff = 0;
	private Texture tex1;
	private Model m2;
	private Texture tex2;
	private Texture oilDrumTex;
	private Model oildrum;
	private int movX;
	private int movY;
	private int movZ;
	private int rotX;
	private int rotY;
	
	private float movTotalX = 0.0f;
	private float movTotalY = 0.0f;
	private float movTotalZ = 0.0f;
	
	private float rotTotalX = 0.0f;
	private float rotTotalY = 0.0f;
	private float rotTotalZ = 0.0f;
	private Model personfiller;
	
	public RenderGame(){
		ObjLoader obj = new ObjLoader();
		try {
			m = obj.loadOBJModel("BG_TILE.obj");
			m2 = obj.loadOBJModel("FOREGROUND_TILE3.obj");
			oildrum = obj.loadOBJModel("SCENERY_OIL_DRUM_NORMALS.obj");
			personfiller = obj.loadOBJModel("Test.obj");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		createWindow();
		initOpenGL();
		old_time = System.currentTimeMillis();
		gameLoop();
		
	}
	
	private void gameLoop() {
		while(!Display.isCloseRequested()){

			if(DEBUG){
				getDebugKeys();
			}

			drawOpenGL();
			Display.update();
		}
		Display.destroy();
	}

	private void drawOpenGL(){
		long new_time = System.currentTimeMillis();
		timediff = new_time - old_time;
		old_time = new_time;
		if(timediff>0){
			Display.setTitle((1000/timediff) + " frames per second");
			
		}
		
		movTotalX += movX*((float)timediff/(float)1000)*6.0f;
		movTotalY += movY*((float)timediff/(float)1000)*6.0f;
		movTotalZ += movZ*((float)timediff/(float)1000)*6.0f;
		rotTotalX += rotX*((float)timediff/(float)1000)*15.0f;
		rotTotalY += rotY*((float)timediff/(float)1000)*15.0f;
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
		glMatrixMode(GL_MODELVIEW); 
		glLoadIdentity();	
		
		
		glTranslatef(0.0f,-1.25f,-13.0f);
		glRotatef(rotTotalX,0.0f,1.0f,0.0f);
		glRotatef(rotTotalY,1.0f,0.0f,0.0f);
		renderModel(personfiller,null);
		glTranslatef(movTotalX,1.25f+movTotalY,-3.0f+movTotalZ);
		
		
		
//		
		
//		glRotatef(10.0f,0.0f,1.0f,0.0f);
		
		glTranslatef(-10.5f,-2.5f,2.5f);
//		glRotatef(15.0f,1.0f,1.0f,0.0f);

		for(int i = 0; i<10; i++){
			glTranslatef(1.5f,5.5f,-2.5f);
			renderModel(m,tex1);
			glTranslatef(0.0f,-2.0f,0.0f);
			renderModel(m,tex1);
			glTranslatef(0.0f,-2.0f,0.0f);
			renderModel(m,tex1);
			
			glTranslatef(-0.5f,-1.5f,2.5f);
			renderModel(m2,tex2);
			glTranslatef(1.0f,0.0f,0.0f);
			renderModel(m2,tex2);
			
			glTranslatef(-1.0f,1.0f,-1.0f);
			renderModel(oildrum, oilDrumTex);
			glTranslatef(1.0f,0.0f,0.0f);
			renderModel(oildrum, oilDrumTex);
			glTranslatef(0.0f,-1.0f,1.0f);
		}
		

		
	}
	
	private void getDebugKeys(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			movY = -1;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			movY = 1;
		} else{
			movY = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			movX = -1;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			movX = 1;
		} else{
			movX = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			movZ = -1;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			movZ = 1;
		} else{
			movZ = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			rotX = -1;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			rotX = 1;
		} else{
			rotX = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			rotY = -1;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			rotY = 1;
		} else{
			rotY = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_0)){
			rotTotalX = 0.0f;
			rotTotalY= 0.0f;
			rotTotalZ = 0.0f;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_9)){
			movTotalX = 0.0f;
			movTotalY = 0.0f;
			movTotalZ = 0.0f;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_1)){
			movTotalX = 0.0f;
			movTotalY = 0.0f;
			movTotalZ = 0.0f;
			
			rotTotalX = 10.0f;
			rotTotalY= 0.0f;
			rotTotalZ = 0.0f;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_2)){
			movTotalX = 0.0f;
			movTotalY = 0.0f;
			movTotalZ = 0.0f;
			
			rotTotalX = 20.0f;
			rotTotalY= 0.0f;
			rotTotalZ = 0.0f;
		}
	}

	private void initOpenGL() {
//		glClearColor(0.25f,0.5f,1.0f,1.0f);
		try {
			tex1 = TextureLoader.getTexture("PNG", this.getClass().getClassLoader().getResourceAsStream(("BG_TILE_TEX.png")));
			tex2 = TextureLoader.getTexture("PNG", this.getClass().getClassLoader().getResourceAsStream(("FOREGROUND_TILE_TEX2.png")));
			oilDrumTex = TextureLoader.getTexture("PNG", this.getClass().getClassLoader().getResourceAsStream(("SCENERY_OIL_DRUM_TEX.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		glMatrixMode(GL_PROJECTION); 
		glLoadIdentity();
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		
		glDepthFunc(GL_LEQUAL);
		GLU.gluPerspective(30.0f,((float)Display.getDisplayMode().getWidth()/(float)Display.getDisplayMode().getHeight()),0.1f,100.0f);
		glMatrixMode(GL_MODELVIEW); // Select The Modelview Matrix
		
		FloatBuffer lightAmbient = BufferUtils.createFloatBuffer(8).put( new float[] {0.7f, 0.7f, 0.7f, 1.0f});
		FloatBuffer lightDiffuse = BufferUtils.createFloatBuffer(8).put(new float[] {1.0f, 1.0f, 1.0f, 1.0f});
		FloatBuffer LightPosition  = BufferUtils.createFloatBuffer(8).put(new float[] {0.0f, 0.0f, 1.0f, 0.0f});
		FloatBuffer lightCutoff = BufferUtils.createFloatBuffer(8).put(new float[] {180.0f,0.0f,0.0f,0.0f});
		
		glLight(GL_LIGHT0, GL_SPOT_CUTOFF, (FloatBuffer) lightCutoff.flip());
		glLight(GL_LIGHT0, GL_AMBIENT, (FloatBuffer) lightAmbient.flip());
		glLight(GL_LIGHT0, GL_DIFFUSE, (FloatBuffer) lightDiffuse.flip());
		glLight(GL_LIGHT0, GL_POSITION, (FloatBuffer) LightPosition.flip());
		
		
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		
		
		
	}

	public void createWindow(){
		try {
			
			Display.setDisplayMode(new DisplayMode(800,450));

			Display.setVSyncEnabled(true);
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void renderModel(Model mod, Texture tex){
		if(tex != null){
			tex.bind();
		}
		ArrayList<Triangle> tris = mod.getModelTris();
		glBegin(GL_TRIANGLES); // begin tekenen openGL triangles
			for(int i = 0; i<tris.size(); i++){
				if (tris.get(i) instanceof TexturedTriangle){
					Vertex[] v = tris.get(i).getVertexArray();
					TexturePoint[] t = ((TexturedTriangle) tris.get(i)).getTextureArray();
					
					glTexCoord2f(t[0].getX(), 0.0f-t[0].getY());
					glVertex3f(v[0].getX(),v[0].getY(),v[0].getZ());

					glTexCoord2f(t[1].getX(), 0.0f-t[1].getY());
					glVertex3f(v[1].getX(),v[1].getY(),v[1].getZ());
					
					glTexCoord2f(t[2].getX(), 0.0f-t[2].getY());
					glVertex3f(v[2].getX(),v[2].getY(),v[2].getZ());
				}
				else if(tris.get(i) instanceof TexturedNormalTriangle){
					Vertex[] v = tris.get(i).getVertexArray();
					TexturePoint[] t = ((TexturedTriangle) tris.get(i)).getTextureArray();
					NormalPoint[] n = ((TexturedNormalTriangle) tris.get(i)).getNormalArray();
					
					glNormal3f(n[0].getX(), n[0].getY(), n[0].getZ());
					glTexCoord2f(t[0].getX(), 0.0f-t[0].getY());
					glVertex3f(v[0].getX(),v[0].getY(),v[0].getZ());
					
					glNormal3f(n[1].getX(), n[1].getY(), n[1].getZ());
					glTexCoord2f(t[1].getX(), 0.0f-t[1].getY());
					glVertex3f(v[1].getX(),v[1].getY(),v[1].getZ());
					
					glNormal3f(n[2].getX(), n[2].getY(), n[2].getZ());
					glTexCoord2f(t[2].getX(), 0.0f-t[2].getY());
					glVertex3f(v[2].getX(),v[2].getY(),v[2].getZ());
				}
				else{
					Vertex[] v = tris.get(i).getVertexArray();
					glVertex3f(v[0].getX(),v[0].getY(),v[0].getZ());
					glVertex3f(v[1].getX(),v[1].getY(),v[1].getZ());
					glVertex3f(v[2].getX(),v[2].getY(),v[2].getZ());
				}
			}
		glEnd(); // einde tekenen openGL triagles
	}
	
	
	public static void main(String[] args){
		new RenderGame();
	}
}
