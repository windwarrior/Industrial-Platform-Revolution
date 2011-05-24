package model;


import org.newdawn.slick.opengl.Texture;


public interface Entity {
	
	/**
	 * Levert het model van deze Entity.
	 * @return
	 */
	public Model getModel();
	
	/**
	 * Levert de texture van deze Entity.
	 * @return
	 */
	public Texture getTexture();
	
	/**
	 * Geeft true als dit object solid is.
	 * @return
	 */
	public boolean isSolid();
	
	public int getWidth();
	
	public int getHeight();
	
}
