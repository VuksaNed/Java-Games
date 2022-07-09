package igra;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import igra.Pozicija.Smer;

public class Zmija extends Figura {
	
	private List<Pozicija> clanci = new ArrayList<>();

	public Zmija(Pozicija glava) {
		super(glava, Color.GREEN);
		clanci.add(glava);
	}
	
	public void uvecaj() {
		clanci.add(clanci.get(clanci.size() - 1));
	}
	
	public int duzina() {
		return clanci.size();
	}

	@Override
	public boolean prostireSe(Pozicija poz) {
		return clanci.contains(poz);
	}

	@Override
	public void iscrtaj(Tabla tabla) {
		Graphics g = tabla.getGraphics();
		g.setColor(boja);
		int sirinaPolja = tabla.getWidth() / tabla.getBrojKolona();
		int visinaPolja = tabla.getHeight() / tabla.getBrojVrsta();
		for(Pozicija clanak : clanci) {
			g.fillOval(sirinaPolja * clanak.getKolona(), visinaPolja * clanak.getVrsta(), sirinaPolja, visinaPolja);
		}
		g.setColor(Color.WHITE);
		g.fillOval(sirinaPolja * glava.getKolona() + sirinaPolja / 4, visinaPolja * glava.getVrsta() + visinaPolja / 4, sirinaPolja / 2, visinaPolja / 2);
	}

	@Override
	public void pomeri(Smer smer, Tabla tabla) throws GNeMoze {
		Pozicija poz = glava.napraviPoziciju(smer);
		if(tabla.zauzeta(poz) && !poz.equals(clanci.get(clanci.size() - 1))) throw new GNeMoze();
		clanci.remove(clanci.size() - 1);
		clanci.add(0, glava = poz);
	}

}
