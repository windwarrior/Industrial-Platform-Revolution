package model;

public class Vertex {
	private float x;
	private float y;
	private float z;

	/**
	 * Maakt een vertex met een x, een y, en een z co�rdinaat
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vertex(float x, float y, float z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);

	}

	/**
	 * Zet de x co�rdinaat van deze vertex
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Vraagt de x co�rdinaat van deze vertex op
	 * @return
	 */
	public float getX() {
		return x;
	}

	/**
	 * Zet de x co�rdinaat van deze vertex
	 * @param x
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Vraagt de y co�rdinaat van deze vertex op
	 * @return
	 */
	public float getY() {
		return y;
	}

	/**
	 * Zet de x co�rdinaat van deze vertex
	 * @param x
	 */
	public void setZ(float z) {
		this.z = z;
	}

	/**
	 * Vraagt de z co�rdinaat van deze vertex op
	 * @return
	 */
	public float getZ() {
		return z;
	}

	public String toString(){
		return super.toString() + " " + x + " " + y + " " + z;
	}
}
