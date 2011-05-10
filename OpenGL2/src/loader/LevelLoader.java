package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class LevelLoader {
	private BufferedReader input;
	private int levelheight;
	private String levelDescription;
	private int levelwidth;
	private String levelName;
	private InputStreamReader ins;
	private URL url;

	public LevelLoader(String path){
		loadLevel(path);
	}

	public void loadLevel(String path){
		//Het string path is de locatie van de map met de ldf submappen


		handleGeneralFile(path);

		System.out.println("-> Completed loading General.ldf. Task 1/6");

		handleModelFile(path);
		
		System.out.println("--> Completed loading Models.ldf. Task 2/6");

	}

	private synchronized void handleModelFile(String path) {
		Scanner gs = null;
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
			if(ls.hasNext()){
				String name = ls.next();
				if(!(name.equals("#") || name.equals(""))){
					String objectPath = ls.next();
					String filetype = ls.next();
					String textureLocation = ls.next();
					boolean isSolid = Boolean.parseBoolean(ls.next());
					boolean isParted = Boolean.parseBoolean(ls.next());
					String shortHand = ls.next();
					int width = 1;
					int height = 1;
					if(isParted){
						width = Integer.parseInt(ls.next());
						height = Integer.parseInt(ls.next());
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
		new LevelLoader("");
	}
}
