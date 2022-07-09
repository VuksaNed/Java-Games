package baloncici;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends Kruznafigura {

	private Krug k;
	
	public Igrac(Vektor centar, double precnik, Scena scena) {
		super(centar, Color.GREEN, precnik, new Vektor(0, 0), scena);
		k=new Krug(centar, Color.BLUE, precnik/2);
	}
	
	public void pomerIgraca(double pom) {
		Vektor c=new Vektor(centar.dohvX()+pom, centar.dohvY());
		if ((c.dohvX()+precnik/2>scena.getWidth())||(c.dohvX()-precnik/2<0)||
				(c.dohvY()+precnik/2>scena.getHeight())||(c.dohvY()-precnik/2<0)) return;
		centar=c;
		k.centar=c;
	}

	@Override
	public void iscrtaj(Graphics g) {
		super.iscrtaj(g);
		k.iscrtaj(g);
	}

	@Override
	public void obavestiSudar(Kruznafigura k) {
		scena.prekini();
	}
	
	

}
