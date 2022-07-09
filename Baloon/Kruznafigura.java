package baloncici;

import java.awt.Color;

public class Kruznafigura extends Krug {

	protected Vektor brzina;
	protected Scena scena;
	
	public Kruznafigura(Vektor centar, Color boja, double precnik, Vektor brzina, Scena scena) {
		super(centar, boja, precnik);
		this.brzina = brzina;
		this.scena = scena;
	}
	
	public void pomeriSe(double vreme) {
		Vektor v=new Vektor(centar.dohvX()+vreme*brzina.dohvX(), centar.dohvY()+vreme*brzina.dohvY());
		centar=v;
		if ((centar.dohvX()+precnik/2>scena.getWidth())||(centar.dohvX()-precnik/2<0)||
		(centar.dohvY()+precnik/2>scena.getHeight())||(centar.dohvY()-precnik/2<0)) scena.izbaciFiguru(this);
	}
	
	public void obavestiSudar(Kruznafigura k) {
		
	}
	
}
