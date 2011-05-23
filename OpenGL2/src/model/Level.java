package model;

public class Level {
	
	private Entity[][] background;
	private Entity[][] foreground;
	private String levelName;
	private String levelDescription;
	private Entity[][] frontProps;
	private Entity[][] backProps;
	


	public Level(String levelName, String levelDescription, Entity[][] background, Entity[][] foreground,	Entity[][] backProps, Entity[][] frontProps) {
		this.levelName = levelName;
		this.levelDescription = levelDescription;
		this.background = background;
		this.foreground = foreground;
		this.backProps = backProps;
		this.frontProps = frontProps;
		
	}


	public Entity[][] getBackground() {
		return background;
	}

	public Entity[][] getForeground() {
		return foreground;
	}


	public String getLevelName() {
		return levelName;
	}

	public String getLevelDescription() {
		return levelDescription;
	}

	public Entity[][] getFrontProps() {
		return frontProps;
	}

	public Entity[][] getBackProps() {
		return backProps;
	}
}
