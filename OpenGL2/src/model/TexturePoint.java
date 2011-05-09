package model;

public class TexturePoint {
	private float x;
	private float y;
	
	/**
	 * Maakt een nieuw TexturePoint
	 */
	public TexturePoint(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Zet de x co�rdinaat van deze TexturePoint
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Vraagt de x co�rdinaat van deze TexturePoint op
	 * @return
	 */
	public float getX() {
		return x;
	}

	/**
	 * Zet de x co�rdinaat van deze TexturePoint
	 * @param x
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Vraagt de y co�rdinaat van deze TexturePoint op
	 * @return
	 */
	public float getY() {
		return y;
	}

}
