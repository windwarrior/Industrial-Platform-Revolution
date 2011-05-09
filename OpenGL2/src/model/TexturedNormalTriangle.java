package model;

public class TexturedNormalTriangle extends TexturedTriangle{

	private NormalPoint n1;
	private NormalPoint n2;
	private NormalPoint n3;
	private NormalPoint[] normals = new NormalPoint[3];
	public TexturedNormalTriangle(Vertex one, Vertex two, Vertex three,
			TexturePoint t1, TexturePoint t2, TexturePoint t3, 
			NormalPoint n1, NormalPoint n2, NormalPoint n3) {
		super(one, two, three, t1, t2, t3);
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
		normals[0] = n1;
		normals[1] = n2;
		normals[2] = n3;
	}
	
	public NormalPoint[] getNormalArray(){
		return normals;
	}

}
