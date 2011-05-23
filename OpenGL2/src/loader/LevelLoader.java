package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import model.Entity;
import model.Level;
import model.Model;
import model.NonSolidEntity;

public class LevelLoader {
	private BufferedReader input;
	private int levelheight;
	private String levelDescription;
	private int levelwidth;
	private String levelName;
	private InputStreamReader ins;
	private URL url;
	private Entity[][] background;
	private Entity[][] foreground;
	private Entity[][] frontProps;
	private Entity[][] backProps;
	
	public LevelLoader(){
		
	}

	public Level loadLevel(String path){
		//Het string path is de locatie van de map met de ldf submappen

		Map<String,Entity> modelMap;
		
		handleGeneralFile(path);

		System.out.println("->      Completed loading General.ldf.    Task 1/6");

		modelMap = handleModelFile(path);
		
		System.out.println("-->     Completed loading Models.ldf.     Task 2/6");
		
		background = handleLevelPositionFile(path, "Background.ldf", modelMap);
		
		System.out.println("--->    Completed loading Background.ldf. Task 3/6");
		
		foreground = handleLevelPositionFile(path, "Foreground.ldf", modelMap);
		
		System.out.println("---->   Completed loading Foreground.ldf. Task 4/6");
		
		backProps = handleLevelPositionFile(path, "BackProps.ldf", modelMap);
		
		System.out.println("----->  Completed loading BackProps.ldf.  Task 5/6");
		
		frontProps = handleLevelPositionFile(path, "FrontProps.ldf", modelMap);
		
		System.out.println("------> Completed loading FrontProps.ldf. Task 6/6");
		
		System.out.println("--> Level loading complete <--");
		
		Level result = new Level(levelName, levelDescription, background, foreground, backProps, frontProps);
		
		return result;
	}

	private synchronized Entity[][] handleLevelPositionFile(String path, String fileName, Map<String, Entity> modelMap) {
		Entity[][] result  = new Entity[levelwidth][levelheight];
		Scanner gs = null;
		try {
			url = LevelLoader.class.getResource(path + fileName);
			ins = new InputStreamReader(url.openStream());
			input = new BufferedReader(ins);
			gs = new Scanner(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int j = 0;
		while(gs.hasNextLine()){
			char[] line = gs.nextLine().toCharArray();
			
			for(int i = 1; i<line.length; i++){
				if(line[i] != '_' && modelMap.containsKey(line[i])){
					result[i][j] = modelMap.get(line[i]);
				}
			}
			j++;
		}
		try {
			input.close();
			gs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	
	private synchronized Map<String,Entity> handleModelFile(String path) {
		Scanner gs = null;
		Map<String,Entity> result = new HashMap<String,Entity>();
		try {
			url = LevelLoader.class.getResource(path + "Models.ldf");
			ins = new InputStreamReader(url.openStream());
			input = new BufferedReader(ins);
			gs = new Scanner(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(gs.hasNextLine()){
			String line = gs.nextLine();
			Scanner ls = new Scanner(line);
			ObjLoader objl = new ObjLoader();
			if(ls.hasNext()){
				String name = ls.next();
				if(!(name.equals("#") || name.equals(""))){
					String objectPath = ls.next();
					String filetype = ls.next().toUpperCase();
					String texturePath = ls.next();
					boolean isSolid = Boolean.parseBoolean(ls.next());
					boolean isParted = Boolean.parseBoolean(ls.next());
					String shortHand = ls.next();
					int width = 1;
					int height = 1;
					if(isParted){
						width = Integer.parseInt(ls.next());
						height = Integer.parseInt(ls.next());
					}
					
					if(!isSolid){
						try {
							Model mod = objl.loadOBJModel(objectPath);
							Texture tex = TextureLoader.getTexture(filetype, this.getClass().getResourceAsStream((texturePath)));
							Entity entity = new NonSolidEntity(mod,tex,width,height);
							result.put(shortHand, entity);
						} catch (IOException e) {
							System.err.println(e.getMessage());
						}
						
					}
					
					
				}
			}
		}
		
		
		//En de bende opruimen
		try {			
			input.close();
			gs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	private synchronized void handleGeneralFile(String path) {
		Scanner gs = null;
		try {
			url = LevelLoader.class.getResource(path + "General.ldf");
			ins = new InputStreamReader(url.openStream());
			input = new BufferedReader(ins);
			gs = new Scanner(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		while(gs.hasNextLine()){
			String line = gs.nextLine();
			Scanner ls = new Scanner(line);
			String typeString = ls.next();
			if(!typeString.equals("#")){
				try{
					String value = line.replaceFirst(typeString + " ", "");
//					System.out.println("type: " + typeString + " Value: " + value);
					if(typeString.equals("Height:")){
						levelheight = Integer.parseInt(value);
					}
					else if(typeString.equals("Width:")){
						levelwidth = Integer.parseInt(value);
					}
					else if(typeString.equals("Description:")){
						levelDescription = value;
					}
					else if(typeString.equals("Name:")){
						levelName = value;
					}
				}
				catch(NumberFormatException e){
					e.printStackTrace();
				}

			}
			ls.close();

			
		}
		//En de bende opruimen
		try {			
			input.close();
			gs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		new LevelLoader();
	}
}
