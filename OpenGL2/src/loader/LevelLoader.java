package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class LevelLoader {
	private static BufferedReader input;
	
	public LevelLoader(){
		
	}
	
	public static void loadLevel(String path){
		//Het string path is de locatie van de map met de ldf submappen
		
		try {
			URL url = LevelLoader.class.getResource(path + "General.ldf");
			InputStreamReader ins = new InputStreamReader(url.openStream());
			input = new BufferedReader(ins);
			
			handleGeneralFile();
			
			url = LevelLoader.class.getResource(path + "Models.ldf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void handleGeneralFile() {
		Scanner gs = new Scanner(input);
		
		while(gs.hasNextLine()){
			String line = gs.nextLine();
			Scanner ls = new Scanner(line);
			String typeString = ls.next();
			if(!typeString.equals("#")){
				String value = line.
				System.out.println(typeString + " " + value);
			}
		}
	}

	public static void main(String[] args){
		loadLevel("");
	}
}
