package tenkici;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Mreza extends Panel implements Runnable {

	private Thread nit=new Thread(this);
	private Igra igra;
	private int velicina=17;
	private Polje matricaPolja[][];
	private ArrayList<Novcic> listaNovicica=new ArrayList<Novcic>();
	private ArrayList<Tenk> listaTenkova=new ArrayList<Tenk>();
	private ArrayList<Igrac> igrac=new ArrayList<Igrac>();
	private boolean rezimizmene=true;
	private boolean trci=false;
	private boolean radi=false;
	private int poeni=0;
	
	public Mreza(Igra igra) {
		super();
		this.igra = igra;
		generisimatricu();
		nit.start();
	}

	public Mreza(Igra igra, int velicina) {
		super();
		this.igra = igra;
		this.velicina = velicina;
		generisimatricu();
	}
	
	public int[] poljeUMrezi(Polje p) {
		for (int i=0;i<velicina;i++)
			for (int j=0;j<velicina;j++) if(matricaPolja[i][j]==p) return new int[] {i,j};
		return null;
	}
	
	private void generisimatricu() {
		setLayout(new GridLayout(0,velicina));
		int dvadesetposto=(int) (velicina*velicina*0.2);
		matricaPolja=new Polje[velicina][velicina];
		while (dvadesetposto!=0) {
			int x= (int) (Math.random()*velicina);
			int y= (int) (Math.random()*velicina);
			while(matricaPolja[x][y]!=null) {
				x=(int) (Math.random()*velicina);
				y= (int) (Math.random()*velicina);
			}
			matricaPolja[x][y]=new Zid(this);
			dvadesetposto--;
		}
		for (int i=0;i<velicina;i++)
			for (int j=0;j<velicina;j++) {
				if(matricaPolja[i][j]==null) matricaPolja[i][j]=new Trava(this);
				add(matricaPolja[i][j]);
				
			}
		
		generisilistenere();
	}
	
	private void azurirajlisteneree(int x, int y) {
		if ((!rezimizmene)||(trci)) return;
		if (igra.dohcek()) {
			if(matricaPolja[y][x] instanceof Trava) return;
			remove(y*velicina+x);
			matricaPolja[y][x]=new Trava(Mreza.this);
			add(matricaPolja[y][x],y*velicina+x);
			revalidate();
			repaint();
		}
		else {
			if(matricaPolja[y][x] instanceof Zid) return;
			remove(y*velicina+x);
			matricaPolja[y][x]=new Zid(Mreza.this);
			add(matricaPolja[y][x],y*velicina+x);
			revalidate();
			matricaPolja[y][x].repaint();
			repaint();
		}
		generisilistenere();
	}
	
	
	private void generisilistenere() {
		for (int i=0;i<velicina;i++)
			for (int j=0;j<velicina;j++) {
				int xx=i;
				int yy=j;
				matricaPolja[i][j].addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						int x=xx;
						int y=yy;
						azurirajlisteneree(y, x);
					}

					@Override
					public void mousePressed(MouseEvent e) {
						int x=xx;
						int y=yy;
						azurirajlisteneree(y, x);
					}	
					
				});
			}
		
	}
	
	private boolean nalazitenk(Polje p) {
		for (int i=0;i<listaTenkova.size();i++) {
			if (p.equals(listaTenkova.get(i).polje)) return true;
		}
		return false;
	}
	
	private boolean nalazinovcic(Polje p) {
		for (int i=0;i<listaNovicica.size();i++) {
			if (p.equals(listaNovicica.get(i).polje)) return true;
		}
		return false;
	}
	
	public synchronized void inicijalizuj(int brnovica) {
		if (/*(radi==false)&&*/(rezimizmene==false)) {
			
			while(listaNovicica.size()!=0) {listaNovicica.get(0).polje.repaint(); listaNovicica.remove(0);}
			while(igrac.size()!=0) {igrac.get(0).polje.repaint(); igrac.remove(0);}
			while(listaTenkova.size()!=0) {listaTenkova.get(0).prekini(); listaTenkova.get(0).polje.repaint();   listaTenkova.remove(0);}
			
		for (int i=0;i<brnovica;i++) {
			int x= (int) (Math.random()*velicina);
			int y= (int) (Math.random()*velicina);
			while((!matricaPolja[x][y].dozvoljeno())||(nalazinovcic(matricaPolja[x][y]))) {
				x=(int) (Math.random()*velicina);
				y= (int) (Math.random()*velicina);
			}
			listaNovicica.add(new Novcic(matricaPolja[x][y]));	
			}
		
		for (int i=0;i<brnovica/3;i++) {
			int x= (int) (Math.random()*velicina);
			int y= (int) (Math.random()*velicina);
			while(!matricaPolja[x][y].dozvoljeno()) {
				x=(int) (Math.random()*velicina);
				y= (int) (Math.random()*velicina);
			}
			listaTenkova.add(new Tenk(matricaPolja[x][y]));
		}
		
		int x= (int) (Math.random()*velicina);
		int y= (int) (Math.random()*velicina);
		while((!matricaPolja[x][y].dozvoljeno())||(nalazitenk(matricaPolja[x][y]))) {
			x=(int) (Math.random()*velicina);
			y= (int) (Math.random()*velicina);
		}
		igrac.add(new Igrac(matricaPolja[x][y]));
		
		poeni=0;
		radi=true;
		trci=true;
		notify();
		}
	}
		
	public void postavipromenu() {
		rezimizmene=true;
	}
		
	public void iskljucipromenu() {
		rezimizmene=false;
	}
	
		
	public int dohvVelicina() {
		return velicina;
	}

	@Override
	public void paint(Graphics g) {
		if (!radi) return;
		for (int i=0;i<igrac.size();i++) igrac.get(i).iscrtaj(igrac.get(i).polje.getGraphics());
		for (int i=0;i<listaNovicica.size();i++) listaNovicica.get(i).iscrtaj(listaNovicica.get(i).polje.getGraphics());
		for (int i=0;i<listaTenkova.size();i++) listaTenkova.get(i).iscrtaj(listaTenkova.get(i).polje.getGraphics());
	}

	public Polje[][] dohvMatricaPolja() {
		return matricaPolja;
	}

	public ArrayList<Novcic> dohvListaNovicica() {
		return listaNovicica;
	}

	public ArrayList<Tenk> dohvListaTenkova() {
		return listaTenkova;
	}

	public ArrayList<Igrac> dohvIgrac() {
		return igrac;
	}

	public void pomeriigraca(int x, int y) {
		if (!radi) return;
		Polje p=igrac.get(0).polje.udaljenoPolje(x, y);
		if ((p!=null)&&(p.dozvoljeno())) {
			igrac.get(0).polje.repaint();
			igrac.get(0).polje=p;
			azuriraj();
			igrac.get(0).polje.repaint();
		}
		
	}
	
	public synchronized void stani() {
		radi=false;
	}
	
	public void ugasi() {
		for (int i=0;i<listaTenkova.size();i++)listaTenkova.get(i).prekini();
		nit.interrupt();
	}
	
	public synchronized void azuriraj() {
		if (nalazinovcic(igrac.get(0).polje)) {
			for (int i=0;i<listaNovicica.size();i++) {
				if (igrac.get(0).polje.equals(listaNovicica.get(i).polje)) {
					poeni++;
					igra.azurirajpoene(poeni);
					listaNovicica.remove(i);
					if (listaNovicica.size()==0) {
						stani();
					}
				}
			}
		}
		if (nalazitenk(igrac.get(0).polje)) {
			for (int i=0;i<listaNovicica.size();i++) listaNovicica.get(i).polje.repaint();
			for (int i=0;i<listaTenkova.size();i++) listaTenkova.get(i).polje.repaint();
			stani();
		}
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					while(!radi) wait(); 
				}
				azuriraj();
				Thread.sleep(40);
				repaint();
			}
		}
		catch (InterruptedException e) {}
		
	}
	
	
	
}
