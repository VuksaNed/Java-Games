package tenkici;

import java.awt.Color;

public class Trava extends Polje {

	public Trava(Mreza mreza) {
		super(mreza);
		boja=Color.GREEN;
	}

	
	@Override
	public boolean dozvoljeno() {
		return true;
	}

}
