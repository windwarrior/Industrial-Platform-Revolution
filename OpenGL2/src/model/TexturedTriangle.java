package model;

public class TexturedTriangle extends Triangle {
	private TexturePoint t1;
	private TexturePoint t2;
	private TexturePoint t3;
	private TexturePoint[] textureArray = new TexturePoint[3];
	public TexturedTriangle(Vertex one, Vertex two, Vertex three, TexturePoint t1,  TexturePoint t2, TexturePoint t3) {
		super(one, two, three);
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		textureArray[0] = t1;
		textureArray[1] = t2;
		textureArray[2] = t3;
		
	}
	
	public TexturePoint[] getTextureArray() {
		return textureArray;
	}
	
}
