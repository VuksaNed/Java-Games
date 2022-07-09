package baloncici;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


@SuppressWarnings("serial")
public class Igra extends Frame {

	private Scena scena;
	
	public Igra() {
		super();
		setTitle("Baloni");
		setBounds(400, 40, 700, 700);

		this.scena=new Scena(this);
		
		dodajkomponente();
		dodajListenere();
		
		setVisible(true);
		
	}

	private void dodajListenere() {

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				scena.zaustavi();
				dispose();
			}
				
		});
		
		scena.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					scena.pomeriigraca(-3);
					break;
				case KeyEvent.VK_D:
					scena.pomeriigraca(3);
					break;
				
				}
			}
			
			
			
		});
		
	}

	private void dodajkomponente() {
		add(scena);
		
	}

	public static void main(String[] args) {
		new Igra();

	}
	
}
