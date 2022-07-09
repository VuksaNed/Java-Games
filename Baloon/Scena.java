package baloncici;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Scena extends Canvas implements Runnable {

	private Igra igra;
	private Thread nit=new Thread(this);
	private boolean radi=true;
	private Igrac igrac;
	private ArrayList<Kruznafigura> listafig=new ArrayList<Kruznafigura>();
	
	
	public Scena(Igra igra) {
		super();
		this.igra = igra;
		igrac=new Igrac(new Vektor(igra.getWidth()/2, igra.getHeight()*0.9), 30, this);
		nit.start();
	}

	public synchronized void pokreni() {
		radi=true;
		notify();
	}
	
	public void zaustavi() {
		nit.interrupt();
	}
	
	public void prekini(){
		radi=false;
		repaint();
	}
	
	public void dodajFiguru(Kruznafigura e) {
		listafig.add(e);
	}
	
	public void izbaciFiguru(Kruznafigura e) {
		for(int i=0;i<listafig.size();i++) if (listafig.get(i)==e) listafig.remove(i);
	}

	private Kruznafigura genkrug() {
		if (Math.random()>0.5) return new Kruznafigura(new Vektor( Math.random()*getWidth(), 25), Color.RED, 20, new Vektor(Math.random(), Math.random()), this);
		else return new Kruznafigura(new Vektor( Math.random()*getWidth(), 25), Color.RED, 20, new Vektor(Math.random()*(-1), Math.random()), this);
	
	}
	
	private void azruriraj() {
		double ver= Math.random();
		if (ver<=0.1) dodajFiguru(genkrug());
		for(int i=0;i<listafig.size();i++) listafig.get(i).pomeriSe(6);
		for(int i=0;i<listafig.size();i++)
			for(int j=i+1;j<listafig.size();j++)
				if (Krug.preklapajuse(listafig.get(i), listafig.get(j))) {
					listafig.get(i).obavestiSudar(listafig.get(j));
					listafig.get(j).obavestiSudar(listafig.get(i));
				}
		
		for(int i=0;i<listafig.size();i++) if (Krug.preklapajuse(listafig.get(i), igrac)) {
			igrac.obavestiSudar(listafig.get(i));
			while (listafig.size()!=0) listafig.remove(0);
		}
		
	}
	
	public void pomeriigraca(int i) {
		igrac.pomerIgraca(i);
	}

	@Override
	public void paint(Graphics g) {
		if (radi) igrac.iscrtaj(g);
		for(int i=0;i<listafig.size();i++) listafig.get(i).iscrtaj(g);
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					while(!radi) wait(); 
				}
				azruriraj();
				repaint();
				Thread.sleep(60);
			}
		}
		catch (InterruptedException e) {}
		
	}

}
