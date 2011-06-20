package gui;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.Texture;

import model.Entity;
import model.Level;
import model.Model;
import model.NormalPoint;
import model.TexturePoint;
import model.TexturedNormalTriangle;
import model.TexturedTriangle;
import model.Triangle;
import model.Vertex;

public class Renderer {

	/**
	 * initialises the renderer. Creates a new window.
	 */
	public void init(){
		createWindow();
		initOpenGL();
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


	public void initOpenGL(){
		glMatrixMode(GL_PROJECTION); 
		glLoadIdentity();
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);

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

		glDepthFunc(GL_LEQUAL);
		GLU.gluPerspective(30.0f,((float)Display.getDisplayMode().getWidth()/(float)Display.getDisplayMode().getHeight()),0.1f,100.0f);
		glMatrixMode(GL_MODELVIEW); // Select The Modelview Matrix


	}
	public void renderLevel(Level lev, int xleft, int xright, int ybottom, int ytop){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
		glMatrixMode(GL_MODELVIEW); 
		glLoadIdentity();

		glTranslatef(((float) -1*((xright-xleft)/2.0f)),((float)(ytop-ybottom))/2.0f,-20.0f);


//		glRotatef(90.0f,0.0f,1.0f,0.0f);

		Entity[][] background = lev.getBackground();
		renderEntityArray(background, xleft, xright, ybottom, ytop);
		
		glTranslatef(0.0f,0.0f,2.5f);
		Entity[][] foreground = lev.getForeground();
		renderEntityArray(foreground, xleft, xright, ybottom, ytop);
		
		glTranslatef(0.0f,0.0f,-2.0f);
		Entity[][] backprops = lev.getBackProps();
		renderEntityArray(backprops, xleft, xright, ybottom, ytop);
		
		glTranslatef(0.0f,0.0f,4.0f);
		Entity[][] frontprops = lev.getFrontProps();
		renderEntityArray(frontprops, xleft, xright, ybottom, ytop);
		
	}
	
	public void renderEntityArray(Entity[][] array, int xleft, int xright, int ybottom, int ytop){

		glPushMatrix();
		for(int j = ybottom; j<ytop; j++){
			glPushMatrix();
			for(int i = xleft; i<xright; i++){
				Entity ent = array[i][j];

				if(ent != null){
					float x = 1 * ((float) ent.getHeight())/2.0f;
					float y = -1 * ((float) ent.getWidth())/2.0f;
					renderModel(ent, x, y);
					glTranslatef((float) -1*ent.getWidth()/2.0f + 1.0f,(float) ent.getHeight()/2.0f,0.0f);
				}else{
					glTranslatef(1.0f,0.0f,0.0f);
				}

				
			}
			
			glPopMatrix();

			glTranslatef(0.0f,-1.0f,0.0f);
		}
		
		glPopMatrix();
	}

	public void renderModel(Entity ent, float x, float y){
		Texture tex = ent.getTexture();
		Model mod = ent.getModel();
		glTranslatef(x,y,0.0f);
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

}
