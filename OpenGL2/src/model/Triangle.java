package model;

public class Triangle {
	protected Vertex[] vertexArray;
	
	public Triangle(Vertex one, Vertex two, Vertex three){
		vertexArray = new Vertex[3];
		vertexArray[0] = one;
		vertexArray[1] = two;
		vertexArray[2] = three;
	}
	
	public Triangle(Vertex[] vertexArray){
		this.vertexArray = vertexArray;
	}
	
	public Vertex[] getVertexArray() {
		return vertexArray;
	}

	public void setVertexArray(Vertex[] vertexArray) {
		this.vertexArray = vertexArray;
	}
	
	public void setVertex(Vertex vertex, int position){
		vertexArray[position] = vertex;
	}

	
	
	
}
