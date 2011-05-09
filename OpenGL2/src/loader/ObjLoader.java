package loader;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.Model;
import model.NormalPoint;
import model.TexturePoint;
import model.TexturedNormalTriangle;
import model.TexturedTriangle;
import model.Triangle;
import model.Vertex;


public class ObjLoader {
	static BufferedReader inputStream = null;
	
	public ObjLoader(){

	}


	public Model loadOBJModel(String path){
		Model model = null;
		try {
			URL url = this.getClass().getResource(path);
			InputStreamReader ins = new InputStreamReader(url.openStream());
			inputStream = new BufferedReader(ins);
			model = makeObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}


	private Model makeObject() throws IOException{
		String line;
		Scanner scan;
		boolean textured = false;
		boolean normals = false;
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		ArrayList<TexturePoint> texturepoints = new ArrayList<TexturePoint>();
		ArrayList<NormalPoint> normalPoints = new ArrayList<NormalPoint>();
		while((line = inputStream.readLine()) != null){
			
			scan = new Scanner(line);
			
			if(scan.hasNext()){
				String typeString = scan.next();
				if(typeString.equals("#")){
					// Commentaar regel, print het commentaar naar de standaard uitvoer
					System.out.println(line);
					
				}
				else if(typeString.equals("v")){
					//Lees de vertices in, stop ze in een lijstje om er later een model van te maken
					float x = Float.parseFloat(scan.next());
					float y = Float.parseFloat(scan.next());
					float z = Float.parseFloat(scan.next());
					vertices.add(new Vertex(x,y,z));
				}
				else if(typeString.equals("vn")){
					float x = Float.parseFloat(scan.next());
					float y = Float.parseFloat(scan.next());
					float z = Float.parseFloat(scan.next());
					normalPoints.add(new NormalPoint(x,y,z));
					normals = true;
				}
				else if(typeString.equals("vt")){
					//Blijkbaar zijn er ook nog texture coördinaten
					float x = Float.parseFloat(scan.next());
					float y = Float.parseFloat(scan.next());
					texturepoints.add(new TexturePoint(x,y));
					textured = true;
				}
				else if(typeString.equals("f")){
					//Helaas is in het OBJ formaat 1 de eerste vertex, dus moet er 1 vanaf gehaald worden, om zo 0 als laagste waarde te krijgen
					//Omdat eerst de punten gedefinieerd worden in een OBJ file, zullen er geen 'verkeerde' vertices 
					if(textured && normals){
						scan.useDelimiter(Pattern.compile("[ //]"));
						int vertexOne   = Integer.parseInt(scan.next()) - 1;
						int texOne = Integer.parseInt(scan.next()) - 1;
						int normalOne = Integer.parseInt(scan.next()) - 1;
						
						int vertexTwo   = Integer.parseInt(scan.next()) - 1;
						int texTwo = Integer.parseInt(scan.next()) - 1;
						int normalTwo = Integer.parseInt(scan.next()) - 1;
						
						int vertexThree = Integer.parseInt(scan.next()) - 1;
						int texThree = Integer.parseInt(scan.next()) - 1;
						int normalThree = Integer.parseInt(scan.next()) - 1;
						
						Triangle tri = new TexturedNormalTriangle(vertices.get(vertexOne),vertices.get(vertexTwo),vertices.get(vertexThree), texturepoints.get(texOne), texturepoints.get(texTwo), texturepoints.get(texThree), normalPoints.get(normalOne), normalPoints.get(normalTwo), normalPoints.get(normalThree));
						triangles.add(tri);
					}
					else if (textured && !normals){
						scan.useDelimiter(Pattern.compile("[ //]"));
						int vertexOne   = Integer.parseInt(scan.next()) - 1;
						
						int texOne = Integer.parseInt(scan.next()) - 1;
						int vertexTwo   = Integer.parseInt(scan.next()) - 1;
						int texTwo = Integer.parseInt(scan.next()) - 1;
						int vertexThree = Integer.parseInt(scan.next()) - 1;
						int texThree = Integer.parseInt(scan.next()) - 1;
						Triangle tri = new TexturedTriangle(vertices.get(vertexOne),vertices.get(vertexTwo),vertices.get(vertexThree), texturepoints.get(texOne), texturepoints.get(texTwo), texturepoints.get(texThree));
						triangles.add(tri);
//						System.out.printf("%d/%d %d/%d %d/%d\n", vertexOne, texOne, vertexTwo, texTwo, vertexThree, texThree);
					}
					else{
						int vertexOne   = Integer.parseInt(scan.next()) - 1;
						int vertexTwo   = Integer.parseInt(scan.next()) - 1;
						int vertexThree = Integer.parseInt(scan.next()) - 1;
						Triangle tri = new Triangle(vertices.get(vertexOne),vertices.get(vertexTwo),vertices.get(vertexThree));
						triangles.add(tri);
					}
					
					
//					
					
				}
			}	
			
		}
		Model mod = new Model(triangles);
		System.out.println("-> Er zijn " + vertices.size() + "  vertices gemaakt!");
		System.out.println("-> Er zijn " + triangles.size() + " triangles gemaakt!");
		System.out.println("-> Er zijn " + texturepoints.size() + " texturepoints gemaakt!");
		System.out.println("-> Er zijn " + normalPoints.size() + " normalpoints gemaakt!");
		return mod;
	}


}
