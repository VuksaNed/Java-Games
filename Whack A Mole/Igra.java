package krtice;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {

	//private static boolean postoji=false;
	//private static Igra igra=null;
	
	private Basta basta;
	private Panel desni=new Panel(new GridLayout(0, 1));
	private Panel desnig=new Panel(new GridLayout(0,1));
	private Panel denid=new Panel();
	private Label tezina=new Label();
	private CheckboxGroup odredjivanjeTezine=new CheckboxGroup();
	private Checkbox lako=new Checkbox();
	private Checkbox srednje=new Checkbox();
	private Checkbox tesko=new Checkbox();
	private Button dugme= new Button();
	private Label povrce= new Label();
	
	public Igra() {
		super();
		setTitle("Igra");
		setBounds(400, 30, 800, 800);
		
		basta=new Basta(4, 4 ,this);
		dodajKomonente();
		
		add(basta,BorderLayout.CENTER);
		add(desni, BorderLayout.EAST);
		dodajListaner();

		setVisible(true);
	}
	
	private void dodajKomonente() {
		tezina.setText("Tezina: ");
		tezina.setFont(new Font(null,Font.BOLD,15));
		desnig.add(tezina);
		
		
		lako=new Checkbox("Lako",false, odredjivanjeTezine);
		srednje=new Checkbox("Srednje",false, odredjivanjeTezine);
		tesko=new Checkbox("Tesko",false, odredjivanjeTezine);
		desnig.add(lako);
		desnig.add(srednje);
		desnig.add(tesko);
		
		dugme.setLabel("Kreni");
		desnig.add(dugme);
		
		povrce.setText("Povrce: ");
		povrce.setFont(new Font(null,Font.BOLD,15));
		denid.add(povrce);
		desni.add(desnig);
		desni.add(denid);
		desni.setPreferredSize(new Dimension(100, 400));
		
	}

	
	public void azurirajlabelu(int x) {
		povrce.setText("Povrce: "+x);
	}
	

	public void dodajListaner() {

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				basta.prekini();
				dispose();
			}
				
		});
		
		dugme.addActionListener((l)->{
			if (dugme.getLabel()=="Kreni") {
				dugme.setLabel("Stani");
				if (lako.getState()) {
					basta.pokreniopet(10, 1000);
				}
				if (srednje.getState()) {
					basta.pokreniopet(8, 750);
				}
				if (tesko.getState()) {
					basta.pokreniopet(6, 500);;
				}
				basta.pokreni();
			}
			else {
				dugme.setLabel("Kreni");
				basta.zaustavi();
			}
		});
		
	
	
	
	}
	/*
	public boolean handleEvent(Event e) {
		if(e.id==Event.WINDOW_DESTROY) {
			dispose();
			//System.exit(0);
			return true;
		}else return false;
	}*/

	public static void main(String[] args) {
		new Igra();
	}

}
