package krtice;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

public class Basta extends Panel implements Runnable {

	private int vrste;
	private int kolone;
	private Rupa[][] matricaRupa;
	private int kolicinaPovrca;
	private double intervalcekanja=1000;
	private int brojkoraka=10;
	private boolean[][]slobodne;
	private Thread nit=new Thread(this);
	private boolean radi=false;
	private Igra igra;
	
	public Basta(int kolone, int vrste, Igra igra) {
		super();
		this.igra=igra;
		setLayout(new GridLayout(0,4,20,20));
		setBackground(Color.GREEN);
		this.vrste = vrste;
		this.kolone = kolone;
		matricaRupa=new Rupa[vrste][kolone];
		slobodne=new boolean[vrste][kolone];
		for (int i=0;i<vrste;i++)
			for (int j=0;j<kolone;j++) {
				matricaRupa[i][j]=new Rupa(this);
				add(matricaRupa[i][j]);
				slobodne[i][j]=true;
			}
		
		kolicinaPovrca=10;
		nit.start();
	}

	public int dohvBrojkoraka() {
		return brojkoraka;
	}

	public void postBrojkoraka(int brojkoraka) {
		this.brojkoraka = brojkoraka;
		for (int i=0;i<vrste;i++)
			for (int j=0;j<kolone;j++)
				matricaRupa[i][j].postBrojKoraka(brojkoraka);
	}
	
	public void smanjipovrce() {
		kolicinaPovrca--;
	}

	public synchronized void pokreni() {
		radi=true;
		notify();
	}

	public void zaustavi() {
		radi=false;
		for (int i=0;i<vrste;i++)
			for (int j=0;j<kolone;j++) matricaRupa[i][j].prekiniNit();
	}
	
	public void prekini() {
		for (int i=0;i<vrste;i++)
			for (int j=0;j<kolone;j++) matricaRupa[i][j].prekiniNit();
		nit.interrupt();
	}

	public void postIntervalcekanja(int intervalcekanja) {
		this.intervalcekanja = intervalcekanja;
	}
	
	public void slobodnaRupa(Rupa r) {
		for (int i=0;i<vrste;i++)
			for (int j=0;j<kolone;j++) 
				if (matricaRupa.equals(r)) slobodne[i][j]=true;
		
	}
	
	public void pokreniopet(int brk, int vr) {
		kolicinaPovrca=10;
		postBrojkoraka(brk);
		postIntervalcekanja(vr);
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				
				synchronized (this) {
					while(!radi)
						wait();	
				}
				igra.azurirajlabelu(kolicinaPovrca);
				int x=(int) (Math.random()*kolone);
				int y=(int) (Math.random()*vrste);
				while (slobodne[y][x]==false) {
					 x=(int) (Math.random()*kolone);
					 y=(int) (Math.random()*vrste);
				}
				matricaRupa[y][x].postZivotinja(new Krtica(matricaRupa[y][x]));
				slobodne[y][x]=true;
				matricaRupa[y][x].stvoriNit();
				matricaRupa[y][x].pokreniNit();
				repaint();
				Thread.sleep((int)intervalcekanja);
				intervalcekanja=intervalcekanja*0.99;
				if (kolicinaPovrca==0) {
					zaustavi();
					igra.azurirajlabelu(kolicinaPovrca);
				}
				
				repaint();
			}
		}
		catch(InterruptedException e) {}

	}

}
