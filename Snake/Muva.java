package igra;

import java.awt.Color;
import java.awt.Graphics;

import igra.Pozicija.Smer;

public class Muva extends Figura {

	public Muva(Pozicija glava) {
		super(glava, Color.BLACK);
	}

	@Override
	public void iscrtaj(Tabla tabla) {
		Graphics g = tabla.getGraphics();
		g.setColor(boja);
		int sirinaPolja = tabla.getWidth() / tabla.getBrojKolona();
		int visinaPolja = tabla.getHeight() / tabla.getBrojVrsta();
		int kolonaGlave = glava.getKolona();
		int vrstaGlava = glava.getVrsta();
		g.drawLine(sirinaPolja * kolonaGlave, visinaPolja * vrstaGlava, sirinaPolja * kolonaGlave + sirinaPolja, visinaPolja * vrstaGlava + visinaPolja);
		g.drawLine(sirinaPolja * kolonaGlave + sirinaPolja, visinaPolja * vrstaGlava, sirinaPolja * kolonaGlave, visinaPolja * vrstaGlava + visinaPolja);
	}

	@Override
	public boolean prostireSe(Pozicija poz) {
		return glava.equals(poz);
	}

	@Override
	public void pomeri(Smer smer, Tabla tabla) {}

}
