package krtice;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.text.StyledEditorKit.BoldAction;

@SuppressWarnings("serial")
public class Rupa extends Canvas implements Runnable {
	
	private Thread nit= null;
	private Color boja= new Color(102, 51, 0);
	/*private*/ Basta basta;
	private Zivotinja zivotinja=null;
	private boolean radi=false;
	private int brojKoraka=0;
	

	public Rupa(Basta basta) {
		super();
		this.basta = basta;
		
		
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				zgaziRupu();
				zivotinja=null;
				basta.slobodnaRupa(Rupa.this);
				Rupa.this.repaint();
				prekiniNit();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				zgaziRupu();
				zivotinja=null;
				basta.slobodnaRupa(Rupa.this);
				Rupa.this.repaint();
				prekiniNit();
			}
			
			
			
		});
		
	}

	public Zivotinja dohvZivotinja() {
		return zivotinja;
	}

	public void postZivotinja(Zivotinja zivotinja) {
		this.zivotinja = zivotinja;
	}

	public void zgaziRupu() {
		if (zivotinja!=null) {
			zivotinja.ispoljiefekatudarene();
		}
	}

	public void stvoriNit() {
		nit=new Thread(this);
		nit.start();
	}
	
	public synchronized void pokreniNit() {
		radi=true;
		notify();
	}
	
	public void zaustaviNit() {
		zivotinja=null;
		repaint();
		radi=false;
	}
	
	public void prekiniNit() {
		zivotinja=null;
		repaint();
		radi=false;
		if(nit != null) nit.interrupt();
	}
	
	
	public boolean nitRadi() {
		return radi;
	}
	
	public int dohvBrojKoraka() {
		return brojKoraka;
	}

	public void postBrojKoraka(int brojKoraka) {
		this.brojKoraka = brojKoraka;
	}


	@Override
	public void paint(Graphics g) {
		g.setColor(boja);
		g.fillRect(0,0 ,getWidth() , getHeight());
		if (zivotinja!=null)zivotinja.iscrtajZivotnju(g);	
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				
				synchronized (this) {
					while(!radi) wait();
					
				}
				
				for (brojKoraka=1;brojKoraka<=basta.dohvBrojkoraka();brojKoraka++) {
					int x=getHeight()/2;
					int y=getWidth()/2;
					double odnos=1-(brojKoraka+0.0)/basta.dohvBrojkoraka();
					//System.out.println(""+odnos+"  "+ brojKoraka+"  "+basta.dohvBrojkoraka());
					if (zivotinja!=null) zivotinja.postXY((int)(x*odnos),(int) (y*odnos));
					repaint();
					Thread.sleep(100);	
				}
				repaint();
				Thread.sleep(2000);
				if (zivotinja!=null) zivotinja.ispoljiefekatpobelgle();
				zivotinja=null;
				basta.slobodnaRupa(this);
				repaint();
				prekiniNit();
			}
		}
		catch(InterruptedException e) {}
		
	}
	

}
