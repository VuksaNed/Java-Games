package tenkici;

import java.awt.Color;

public class Zid extends Polje {

	public Zid(Mreza mreza) {
		super(mreza);
		boja=Color.LIGHT_GRAY;
	}
	

	@Override
	public boolean dozvoljeno() {
		return false;
	}

}
