package tenkici;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Igra extends Frame {

	
	private Panel desni=new Panel(new GridLayout(0,2));
	private Panel desnidesni= new Panel(new GridLayout(0,1));
	private Panel desnilevi=new Panel(new GridLayout(0, 1));
	private Panel donji=new Panel();
	private Mreza mreza;
	private Button pocni;
	private Label labelanovcic;
	private TextField textnovcic;
	private Label poeni;
	private Label labelapodloga;
	private CheckboxGroup izborpodloga;
	private Checkbox izbortrava;
	private Checkbox izborzid;
	private MenuBar trakamenija;
	private Menu rezim;
	
	public Igra() throws HeadlessException {
		super();
		this.mreza = new Mreza(this);
		setTitle("Igra");
		setBounds(400, 40, 700, 625);
		
		dodajkomponente();
		dodajListenere();
		
		setVisible(true);
	}
	
	boolean dohcek() {
		if (izbortrava.getState()) return true;
		else return false;
	}

	private void dodajkomponente() {
		add(mreza);
		
		
		labelanovcic=new Label("Novcica:");
		donji.add(labelanovcic);
		
		textnovcic=new TextField();
		donji.add(textnovcic);
		
		poeni=new Label("Poeni: 0");
		donji.add(poeni);
		
		pocni=new Button("Pocni");
		donji.add(pocni);
		
		labelapodloga=new Label("Podloga:");
		desnilevi.add(labelapodloga, BorderLayout.CENTER);
		
		izborpodloga=new CheckboxGroup();
		izbortrava=new Checkbox("Trava",true, izborpodloga);
		izbortrava.setBackground(Color.GREEN);
		izborzid=new Checkbox("Zid", false, izborpodloga);
		izborzid.setBackground(Color.LIGHT_GRAY);
		desnidesni.add(izbortrava);
		desnidesni.add(izborzid);
		
		desni.add(desnilevi);
		desni.add(desnidesni);
		
		add(donji,BorderLayout.SOUTH);
		add(desni,BorderLayout.EAST);
		
		trakamenija=new MenuBar();
		rezim=new Menu("Rezim");
		rezim.add("Rezim izmene");
		rezim.add("Rezim igranja");
		trakamenija.add(rezim);
		setMenuBar(trakamenija);
		
	}
	
	public void azurirajpoene(int x) {
		poeni.setText("Poena: "+x);
	}

	private void dodajListenere() {
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				mreza.ugasi();
				dispose();
			}
				
		});
		
		rezim.addActionListener((a)->{
			if (a.getActionCommand()=="Rezim izmene") mreza.postavipromenu();
			if (a.getActionCommand()=="Rezim igranja") mreza.iskljucipromenu();
		});
		
		pocni.addActionListener((a)->{
			mreza.inicijalizuj(Integer.parseInt(textnovcic.getText()));
			mreza.requestFocus();
		});
		
		
		mreza.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					mreza.pomeriigraca(-1, 0);
					break;
				case KeyEvent.VK_A:
					mreza.pomeriigraca(0, -1);
					break;
				case KeyEvent.VK_S:
					mreza.pomeriigraca(1, 0);
					break;
				case KeyEvent.VK_D:
					mreza.pomeriigraca(0, 1);
					break;
				
				}
			}
			
			
			
		});
		
	}


	public static void main(String[] args) {
		new Igra();

	}

}
