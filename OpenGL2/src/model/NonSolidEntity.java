package model;


import org.newdawn.slick.opengl.Texture;

public class NonSolidEntity implements Entity{
	
	private Texture tex;
	private Model mod;
	private int width;
	private int height;

	public NonSolidEntity(Model mod, Texture tex, int width, int height){
		this.mod = mod;
		this.tex = tex;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public Model getModel() {
		return mod;
	}

	@Override
	public Texture getTexture() {
		return tex;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

}
