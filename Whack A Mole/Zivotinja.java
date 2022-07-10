package krtice;

import java.awt.Graphics;

public abstract class Zivotinja {

	protected Rupa rupa;
	protected int x=0;
	protected int y=0;
	
	public Zivotinja(Rupa rupa) {
		super();
		this.rupa = rupa;
	}

	public  void postXY(int xx, int yy) {
		x=xx;
		y=yy;
	}
	
	public abstract void iscrtajZivotnju(Graphics g);
	
	public abstract void ispoljiefekatudarene();

	public abstract void ispoljiefekatpobelgle();
	
}
