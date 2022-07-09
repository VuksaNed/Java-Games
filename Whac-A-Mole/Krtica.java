package krtice;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {
	Rupa mojaRupa;
	Color boja= Color.DARK_GRAY;
	
	public Krtica(Rupa rupa) {
		super(rupa);
		mojaRupa = rupa;
	}

	@Override
	public void iscrtajZivotnju(Graphics g) {
		g.setColor(boja);

		g.fillOval(x,y, (rupa.getWidth()-(2*x)), (rupa.getHeight()-(2*y)));
		
	}

	@Override
	public void ispoljiefekatudarene() {
		rupa.prekiniNit();
	}

	@Override
	public void ispoljiefekatpobelgle() {
		rupa.basta.smanjipovrce();
		rupa.prekiniNit();
		
	}

	
	
}
