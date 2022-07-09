package tenkici;

import java.awt.Color;
import java.awt.Graphics;

public class Tenk extends Figura implements Runnable {

	private Thread nit=new Thread(this);
	private boolean radi=true;
	
	public Tenk(Polje polje) {
		super(polje);
		nit.start();
	}

	@Override
	public void iscrtaj(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, polje.getWidth(), polje.getHeight());
		g.drawLine(0, polje.getHeight(), polje.getWidth(), 0);
		
	}

	public synchronized void kreni() {
		radi=true;
		notify();
	}
	
	public synchronized void stani() {
		radi=false;
	}
	
	public void prekini() {
		nit.interrupt();
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					while(!radi) wait();
				}
				double x=Math.random();
				if (x<0.25) {
					Polje p=polje.udaljenoPolje(0, -1);
					if ((p!=null)&&(p.dozvoljeno())) {
						polje.repaint();
						polje=p;
					}
				}
				else if(x<0.5) {
					Polje p=polje.udaljenoPolje(0, 1);
					if ((p!=null)&&(p.dozvoljeno())) {
						polje.repaint();
						polje=p;
					}
				}
				else if(x<0.75) {
					Polje p=polje.udaljenoPolje(-1, 0);
					if ((p!=null)&&(p.dozvoljeno())) {
						polje.repaint();
						polje=p;
					}
				}
				else {
					Polje p=polje.udaljenoPolje(1, 0);
					if ((p!=null)&&(p.dozvoljeno())) {
						polje.repaint();
						polje=p;
					}
				}
				
				Thread.sleep(500);
			}
		}
		catch(InterruptedException e) {}
		
	}

}
