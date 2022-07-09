package baloncici;

import java.awt.Color;
import java.awt.Graphics;

import baloncici.Scena;

public class Krug {
	
	protected Vektor centar;
	protected Color boja;
	protected double precnik;
	
	public Krug(Vektor centar, Color boja, double precnik) {
		super();
		this.centar = centar;
		this.boja = boja;
		this.precnik = precnik;
	}
	
	public static boolean preklapajuse(Krug k1, Krug k2) {
		double x=Math.sqrt(Math.pow(k1.centar.dohvX()-k2.centar.dohvX(), 2)+Math.pow(k1.centar.dohvY()-k2.centar.dohvY(), 2));
		return (x<=(k1.precnik/2+k2.precnik/2));
	}
	
	public void iscrtaj(Graphics g) {
		g.setColor(boja);
		int x= (int) (centar.dohvX()-precnik/2);
		int y= (int) (centar.dohvY()-precnik/2);
		int pr=(int) precnik;
		g.fillOval(x, y, pr, pr);
	}
	
	
}
