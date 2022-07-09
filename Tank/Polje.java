package tenkici;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Polje extends Canvas {

	private Mreza mreza;
	protected Color boja=Color.BLUE;
	public Polje(Mreza mreza) {
		super();
		this.mreza = mreza;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(boja);
		g.fillRect(0, 0, getWidth(),getHeight());
	}



	public Mreza dohvMreza() {
		return mreza;
	}
	
	public int[] dohvPoziciju(){
		return mreza.poljeUMrezi(this);
	}
	
	public Polje udaljenoPolje(int pomvrste, int pomkolone) {
		int[] niz=dohvPoziciju();
		if (niz==null) return null;
		if ((niz[0]+pomvrste)>=mreza.dohvVelicina()||((niz[0]+pomvrste)<0)||
				((niz[1]+pomkolone)>=mreza.dohvVelicina())||((niz[1]+pomkolone)<0)) return null;
		if (mreza.dohvMatricaPolja()[niz[0]+pomvrste][niz[1]+pomkolone]==null) return null;
		else return mreza.dohvMatricaPolja()[niz[0]+pomvrste][niz[1]+pomkolone];
	}
	
	public abstract boolean dozvoljeno();
	
}
