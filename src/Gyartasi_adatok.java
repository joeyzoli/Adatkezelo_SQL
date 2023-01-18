import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

public class Gyartasi_adatok extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField datum;
	private JTextField muszak;
	private JTextField sor;
	private int szamlalo = 1;
	private ComboBox combobox_tomb;
	private JComboBox<String> vt_azon;
	private JComboBox<String> ellenor_neve;
	private JComboBox<String> hiba_combobox;
	private JComboBox<String> hibakod_combobox;
	private Utolso_sor utolso = new Utolso_sor();
	private JTextField id;
	private JTextField felajanlott;
	private JTextField mintanagysag;
	private JTextField pcb_sorszam;
	private JTextField hibak_szama;
	private JTextField pozicio;
	private SimpleDateFormat idopont;
	private Timestamp timestamp;
	
	/**
	 * Gyártási adatok osztály. Ezen a felületen lehet felvinni az adatokat az SQL táblába
	 */
	public Gyartasi_adatok() 										//konstruktor létrehozza a panelt
	{
		this.setPreferredSize(new Dimension(1100, 650));
		
		JLabel lblNewLabel = new JLabel("Adatbevitel");
		lblNewLabel.setBounds(499, 25, 315, 22);
		lblNewLabel.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_1 = new JLabel("VT azonosító");
		lblNewLabel_1.setBounds(390, 62, 99, 14);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_4 = new JLabel("Dátum:");
		lblNewLabel_4.setBounds(431, 101, 58, 14);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_5 = new JLabel("Műszak:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(441, 127, 48, 14);
		
		JLabel lblNewLabel_6 = new JLabel("Sor:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(457, 468, 32, 14);
		
		combobox_tomb = new ComboBox();
		
		vt_azon = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vt_azon));         //combobox_tomb.getCombobox(ComboBox.vt_azon)
		vt_azon.setBounds(499, 58, 451, 22);
		
		datum = new JTextField();
		datum.setBounds(499, 98, 86, 20);
		datum.setColumns(10);
		
		muszak = new JTextField();
		muszak.setBounds(499, 124, 86, 20);
		muszak.setColumns(10);
		
		sor = new JTextField();
		sor.setBounds(499, 465, 86, 20);
		sor.setColumns(10);
		sor.setText("-");
		
		JButton mentes_gomb = new JButton("Mentés");
		mentes_gomb.setBounds(499, 541, 80, 23);
		mentes_gomb.addActionListener(new Iro());
		mentes_gomb.addKeyListener(new Enter());
		
		szamlalo_szama();											//a számlálót beállító függvény meghívása
		
		id = new JTextField();
		id.setBounds(499, 503, 86, 20);
		id.setColumns(10);
		id.setText(String.valueOf(szamlalo));
		id.setEditable(false);
		
		JLabel lblNewLabel_7 = new JLabel("ID");
		lblNewLabel_7.setBounds(469, 506, 20, 14);
		
		JLabel lblNewLabel_2 = new JLabel("Hibagyűjtés helye");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(378, 206, 111, 14);
		
		hiba_combobox = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.hiba_helye));           //combobox_tomb.getCombobox2(ComboBox.hiba_helye)
		hiba_combobox.setBounds(499, 202, 156, 22);
		
		JLabel lblNewLabel_3 = new JLabel("Felajánlott mennyiség");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(352, 238, 137, 14);
		
		felajanlott = new JTextField();
		felajanlott.setBounds(499, 235, 86, 20);
		felajanlott.setColumns(10);
		felajanlott.setText("0");
		
		JLabel lblNewLabel_8 = new JLabel("Minta nagyság");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(396, 276, 93, 14);
		
		mintanagysag = new JTextField();
		mintanagysag.setBounds(499, 273, 86, 20);
		mintanagysag.setText("0");
		mintanagysag.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("PCB sorszám");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_9.setBounds(413, 314, 76, 14);
		
		pcb_sorszam = new JTextField();
		pcb_sorszam.setBounds(499, 311, 86, 20);
		pcb_sorszam.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Hibakód");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_10.setBounds(422, 353, 67, 14);
		
		hibakod_combobox = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));           //combobox_tomb.getCombobox(ComboBox.hibakodok)
		hibakod_combobox.setBounds(499, 349, 156, 22);
		
		JLabel lblNewLabel_11 = new JLabel("Hibák száma");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_11.setBounds(413, 430, 76, 14);
		
		hibak_szama = new JTextField();
		hibak_szama.setBounds(499, 427, 86, 20);
		hibak_szama.setText("0");
		hibak_szama.setColumns(10);
		
		ellenor_neve = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.ellenorok));       //combobox_tomb.getCombobox2(ComboBox.ellenorok)
		ellenor_neve.setBounds(499, 162, 156, 22);
		
		JLabel lblNewLabel_12 = new JLabel("Ellenőr neve");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_12.setBounds(413, 166, 76, 14);
		
		JLabel lblNewLabel_13 = new JLabel("Pozíció");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_13.setBounds(444, 392, 45, 14);
		
		pozicio = new JTextField();
		pozicio.setBounds(499, 389, 86, 20);
		pozicio.setColumns(10);
		setBackground(Foablak.hatter_szine);
		setLayout(null);
		add(lblNewLabel_13);
		add(lblNewLabel_11);
		add(lblNewLabel_8);
		add(lblNewLabel_10);
		add(lblNewLabel_9);
		add(lblNewLabel_3);
		add(lblNewLabel_2);
		add(lblNewLabel_1);
		add(lblNewLabel_4);
		add(lblNewLabel_12);
		add(lblNewLabel_5);
		add(lblNewLabel_7);
		add(lblNewLabel_6);
		add(muszak);
		add(datum);
		add(pozicio);
		add(hibak_szama);
		add(sor);
		add(id);
		add(mentes_gomb);
		add(vt_azon);
		add(hiba_combobox);
		add(pcb_sorszam);
		add(hibakod_combobox);
		add(lblNewLabel);
		add(mintanagysag);
		add(felajanlott);
		add(ellenor_neve);
	}
	
	class Iro implements ActionListener																						//mentés gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			 {
			    szamlalo_szama();
			    idopont = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    timestamp = new Timestamp(System.currentTimeMillis());
				Db_iro beleir = new Db_iro();																				//példányosítás
				beleir.iro_gyartas(String.valueOf(vt_azon.getSelectedItem()), datum.getText(), muszak.getText(), String.valueOf(ellenor_neve.getSelectedItem()), 
						String.valueOf(hiba_combobox.getSelectedItem()), Integer.parseInt(felajanlott.getText()), Integer.parseInt(mintanagysag.getText()), pcb_sorszam.getText(), 
						String.valueOf(hibakod_combobox.getSelectedItem()), pozicio.getText(), Integer.parseInt(hibak_szama.getText()), sor.getText(), idopont.format(timestamp));				//Író osztály függvényének meghívása paraméterrel
				szamlalo++;																																			//szamlalo növelése a DB-ben levő sorszámhoz
				id.setText(String.valueOf(szamlalo)); 																												//számláló újraírása
		
				Urlap_torlo torles = new Urlap_torlo();																												//űrlap törlő példányosítása
				torles.urlaptorles(felajanlott, mintanagysag, pcb_sorszam, pozicio, hibak_szama, sor);												//űrlap kitöltött mezőinek alaphelyzetbe állítása
			 }
			catch(Exception ex2)
			 {
				ex2.printStackTrace(); 
				String hibauzenet2 = ex2.toString();
				JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
			 }
		 }
	}
	
	public void szamlalo_szama()																										//a számláló pontosságáért felelő függvény
	{	   
	    szamlalo = Integer.parseInt(utolso.utolso("qualitydb.Gyartasi_adatok")) + 1;     		
	}
	
/****************************************** Figyelő, hogy az enter megnyomásával is működjön a gomb, ne kelljen mindig klikkelni  *******************************************************/
	
	class Enter implements KeyListener																									//billentyűzet figyelő eseménykezelő
	{
		public void keyPressed (KeyEvent e) 
		{    
			int key = e.getKeyCode();

		    if (key == KeyEvent.VK_ENTER) 																								//ha az entert nyomják le akkor hívódik meg
		    {
		    	try																														//lényegében lefuttaja ugynazt mintha klikeltél volna
				 {
		    	    szamlalo_szama();
					Db_iro beleir = new Db_iro();
					idopont = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                timestamp = new Timestamp(System.currentTimeMillis());
					beleir.iro_gyartas(String.valueOf(vt_azon.getSelectedItem()), datum.getText(), muszak.getText(), String.valueOf(ellenor_neve.getSelectedItem()), 
							String.valueOf(hiba_combobox.getSelectedItem()), Integer.parseInt(felajanlott.getText()), Integer.parseInt(mintanagysag.getText()), pcb_sorszam.getText(), 
							String.valueOf(hibakod_combobox.getSelectedItem()), pozicio.getText(), Integer.parseInt(hibak_szama.getText()), sor.getText(), idopont.format(timestamp));
					szamlalo++;
					id.setText(String.valueOf(szamlalo)); 
			
					Urlap_torlo torles = new Urlap_torlo();
					torles.urlaptorles(felajanlott, mintanagysag, pcb_sorszam, pozicio, hibak_szama, sor);
				 }
				catch(Exception ex2)
				 { 
					ex2.printStackTrace(); 
					String hibauzenet2 = ex2.toString();
					JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
				 }
		    }
		 
	    }

		@Override
		public void keyTyped(KeyEvent e) 												//kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) 											//kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
		{
			// TODO Auto-generated method stub
			
		}    
	}	
}
