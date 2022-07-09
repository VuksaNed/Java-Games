package igra;

public class Pozicija {
	
	private int vrsta;
	private int kolona;
	public enum Smer { LEVO, GORE, DESNO, DOLE };
	
	public Pozicija(int vrsta, int kolona) {
		super();
		this.vrsta = vrsta;
		this.kolona = kolona;
	}
	
	public int getVrsta() {
		return vrsta;
	}
	
	public int getKolona() {
		return kolona;
	}
	
	public void pomeri(Smer smer) {
		switch(smer) {
		case LEVO: kolona -= 1; break;
		case GORE: vrsta -= 1; break;
		case DESNO: kolona += 1; break;
		case DOLE: vrsta += 1; break;
		}
	}
	
	public Pozicija napraviPoziciju(Smer smer) {
		Pozicija p = new Pozicija(vrsta, kolona);
		p.pomeri(smer);
		return p;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pozicija other = (Pozicija) obj;
		if (kolona != other.kolona)
			return false;
		if (vrsta != other.vrsta)
			return false;
		return true;
	}
	
	

}
