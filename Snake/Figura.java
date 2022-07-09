package igra;

import java.awt.Color;

import igra.Pozicija.Smer;

public abstract class Figura {
	
	protected Pozicija glava;
	protected Color boja;
	
	public Figura(Pozicija glava, Color boja) {
		super();
		this.glava = glava;
		this.boja = boja;
	}
	
	public Pozicija getGlava() {
		return glava;
	}
	
	public void setBoja(Color boja) {
		this.boja = boja;
	}
	
	public abstract void iscrtaj(Tabla tabla);
	
	public abstract boolean prostireSe(Pozicija poz);
	
	public abstract void pomeri(Smer smer, Tabla tabla) throws GNeMoze;

}
