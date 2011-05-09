package model;


import org.newdawn.slick.opengl.Texture;

public class NonSolidEntity implements Entity{
	
	private Texture tex;
	private Model mod;

	public NonSolidEntity(Model mod, Texture tex){
		this.mod = mod;
		this.tex = tex;
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

}
