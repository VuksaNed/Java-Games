package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import igra.Pozicija.Smer;

public class Tabla extends Canvas implements Runnable {
	
	private int brojVrsta;
	private int brojKolona;
	
	private Zmija zmija;
	private Muva muva;
	
	private Smer trenutniSmer, sledeciSmer;
	private Thread nit;
	private int dt = 200;

	public void postaviSmer(Smer smer) {
		if(Math.abs(smer.ordinal() - trenutniSmer.ordinal()) == 2) return;
		sledeciSmer = smer;
	}
	
	public void setDt(int dt) {
		this.dt = dt;
	}

	public Tabla(int brojVrsta, int brojKolona) {
		super();
		this.brojVrsta = brojVrsta;
		this.brojKolona = brojKolona;
		konfigurisi();
	}

	private void konfigurisi() {
		// TODO Auto-generated method stub
		zmija = new Zmija(new Pozicija(brojVrsta / 2, brojKolona / 2));
		generisiMuvi();
		trenutniSmer = sledeciSmer = Smer.DESNO;
		nit = new Thread(this);
		nit.start();
	}
	
	public boolean zauzeta(Pozicija poz) throws GNeMoze {
		if(poz.getVrsta() < 0 || poz.getKolona() < 0 || poz.getVrsta() >= brojVrsta ||
				poz.getKolona() >= brojKolona) throw new GNeMoze();
		return zmija.prostireSe(poz);
	}

	private void generisiMuvi() {
		// TODO Auto-generated method stub
		while(true) {
			int vrsta = (int)(Math.random() * brojVrsta);
			int kolona = (int)(Math.random() * brojKolona);
			Pozicija poz = new Pozicija(vrsta, kolona);
			try {
				if(zauzeta(poz)) continue;
			} catch (GNeMoze e) {}
			muva = new Muva(poz);
			break;
		} 
	}

	public int getBrojVrsta() {
		return brojVrsta;
	}

	public void setBrojVrsta(int brojVrsta) {
		this.brojVrsta = brojVrsta;
	}

	public int getBrojKolona() {
		return brojKolona;
	}

	public void setBrojKolona(int brojKolona) {
		this.brojKolona = brojKolona;
	}
	
	@Override
	public void paint(Graphics g) {
		// setBackground(Color.LIGHT_GRAY);
		int sirinaPolja = getWidth() / brojKolona;
		int visinaPolja = getHeight() / brojVrsta;
		int sirinaTable = sirinaPolja * brojKolona;
		int visinaTable = visinaPolja * brojVrsta;
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, sirinaTable, visinaTable);
		
		g.setColor(Color.DARK_GRAY);
		for(int i = 0; i <= brojKolona; i++) {
			g.drawLine(sirinaPolja * i, 0, sirinaPolja * i, visinaTable);
		}

		for(int i = 0; i <= brojVrsta; i++) {
			g.drawLine(0, visinaPolja*i, sirinaTable, visinaPolja*i);
		}
		
		if(zmija != null) zmija.iscrtaj(this);
		if(muva != null) muva.iscrtaj(this);
	}
	
	private boolean radi = true;

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			try {
				synchronized(this) {
					while(!radi) wait();
				}
				Thread.sleep(dt);
				azuriraj();
				repaint();
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	public synchronized void kreni() {
		konfigurisi();
		repaint();
		radi = true;
		notify();
	}
	
	public synchronized void zaustavi() {
		radi = false;
	}
	
	public void prekini() {
		nit.interrupt();
	}

	private void azuriraj() {
		try {
			zmija.pomeri(trenutniSmer = sledeciSmer, this);
			if(zmija.getGlava().equals(muva.getGlava())) {
				zmija.uvecaj();
				generisiMuvi();
			}
		} catch (GNeMoze e) {
			zmija.setBoja(Color.RED);
			nit.interrupt();
		}
	}

}
