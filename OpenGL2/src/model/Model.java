package model;

import java.util.ArrayList;

public class Model {
	private ArrayList<Triangle> tris;

	public Model(ArrayList<Triangle> tris){
		this.tris = tris;
	}
	
	public ArrayList<Triangle> getModelTris(){
		return tris;
	}
	

}
