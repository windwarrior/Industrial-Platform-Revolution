package model;

public class Level {
	private int levelHeight;
	private int levelWidth;
	
	private char[][] background;
	private char[][] foreground;
	private char[][] backScenery;
	private char[][] foreScenery;
	
	public Level(int levelWidth, int levelHeight){
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
		
		background = new char[levelWidth][levelHeight];
		foreground = new char[levelWidth][levelHeight];
		backScenery = new char[levelWidth][levelHeight];
		foreScenery = new char[levelWidth][levelHeight];
	}
}
