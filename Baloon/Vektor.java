package baloncici;

public class Vektor implements Cloneable {
	
	private double x;
	private double y;
	
	public Vektor(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double dohvX() {
		return x;
	}

	public double dohvY() {
		return y;
	}

	@Override
	public Vektor clone() throws CloneNotSupportedException {
		Vektor v= (Vektor) super.clone();
		v.x=this.x;
		v.y=this.y;
		return v;
	}
	
	public void mnozenjeSkalarom(double t) {
		x=x*t;
		y=y*t;
	}
	
	public Vektor saber(Vektor v) {
		return new Vektor(x+v.x, y+v.y);
	}

}
