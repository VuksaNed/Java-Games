package igra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import igra.Pozicija.Smer;

@SuppressWarnings("serial")
public class Igra extends Frame {

	private Tabla tabla;

	private boolean uToku = true;

	public Igra() {
		super("Zmija");
		setSize(1000, 1000);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				tabla.prekini();
				dispose();
			}
		});

		tabla = new Tabla(20, 20);
		add(tabla, BorderLayout.CENTER);

		tabla.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					tabla.postaviSmer(Smer.GORE);
					break;
				case KeyEvent.VK_S:
					tabla.postaviSmer(Smer.DOLE);
					break;
				case KeyEvent.VK_A:
					tabla.postaviSmer(Smer.LEVO);
					break;
				case KeyEvent.VK_D:
					tabla.postaviSmer(Smer.DESNO);
					break;
				}
			}
		});

		Button stani = new Button("Stani");
		add(stani, BorderLayout.SOUTH);

		stani.addActionListener(e -> {
			if (uToku) {
				tabla.zaustavi();
				stani.setLabel("Kreni");
			} else {
				tabla.kreni();
				stani.setLabel("Stani");
			}
			uToku = !uToku;
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new Igra();
	}

}
