import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
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
	/**
	 * Gyártási adatok osztály. Ezen a felületen lehet felvinni az adatokat az SQL táblába
	 */
	public Gyartasi_adatok() 										//konstruktor létrehozza a panelt
	{
		this.setPreferredSize(new Dimension(1100, 650));
		
		JLabel lblNewLabel = new JLabel("Adatbevitel");
		lblNewLabel.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_1 = new JLabel("VT azonosító");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_4 = new JLabel("Dátum:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_5 = new JLabel("Műszak:");
		
		JLabel lblNewLabel_6 = new JLabel("Sor:");
		
		combobox_tomb = new ComboBox();
		
		vt_azon = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vt_azon));
		
		datum = new JTextField();
		datum.setColumns(10);
		
		muszak = new JTextField();
		muszak.setColumns(10);
		
		sor = new JTextField();
		sor.setColumns(10);
		sor.setText("-");
		
		JButton mentes_gomb = new JButton("Mentés");
		mentes_gomb.addActionListener(new Iro());
		mentes_gomb.addKeyListener(new Enter());
		
		szamlalo_szama();											//a számlálót beállító függvény meghívása
		
		id = new JTextField();
		id.setColumns(10);
		id.setText(String.valueOf(szamlalo));
		id.setEditable(false);
		
		JLabel lblNewLabel_7 = new JLabel("ID");
		
		JLabel lblNewLabel_2 = new JLabel("Hibagyűjtés helye");
		
		hiba_combobox = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.hiba_helye));
		
		JLabel lblNewLabel_3 = new JLabel("Felajánlott mennyiség");
		
		felajanlott = new JTextField();
		felajanlott.setColumns(10);
		felajanlott.setText("0");
		
		JLabel lblNewLabel_8 = new JLabel("Minta nagyság");
		
		mintanagysag = new JTextField();
		mintanagysag.setText("0");
		mintanagysag.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("PCB sorszám");
		
		pcb_sorszam = new JTextField();
		pcb_sorszam.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Hibakód");
		
		hibakod_combobox = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));
		
		JLabel lblNewLabel_11 = new JLabel("Hibák száma");
		
		hibak_szama = new JTextField();
		hibak_szama.setText("0");
		hibak_szama.setColumns(10);
		
		ellenor_neve = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.ellenorok));
		
		JLabel lblNewLabel_12 = new JLabel("Ellenőr neve");
		
		JLabel lblNewLabel_13 = new JLabel("Pozíció");
		
		pozicio = new JTextField();
		pozicio.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_13))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblNewLabel_11))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(384)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_8)
										.addComponent(lblNewLabel_10)
										.addComponent(lblNewLabel_9)
										.addComponent(lblNewLabel_3)
										.addComponent(lblNewLabel_2)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_12)
										.addComponent(lblNewLabel_5)))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_6, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(muszak, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(datum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(pozicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(hibak_szama, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(sor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(mentes_gomb)
											.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(vt_azon, Alignment.LEADING, 0, 107, Short.MAX_VALUE)
												.addComponent(hiba_combobox, Alignment.LEADING, 0, 107, Short.MAX_VALUE)
												.addComponent(pcb_sorszam, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(hibakod_combobox, Alignment.LEADING, 0, 107, Short.MAX_VALUE)
												.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
												.addComponent(mintanagysag, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(felajanlott, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(ellenor_neve, Alignment.LEADING, 0, 107, Short.MAX_VALUE))
											.addGap(426))))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(vt_azon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(datum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(muszak, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ellenor_neve, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_12))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(hiba_combobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(felajanlott, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8)
						.addComponent(mintanagysag, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pcb_sorszam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_9))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_10)
						.addComponent(hibakod_combobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_13)
						.addComponent(pozicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(hibak_szama, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_11))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(sor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7))
					.addGap(18)
					.addComponent(mentes_gomb)
					.addContainerGap())
		);
		setBackground(Foablak.hatter_szine);
				
		setLayout(groupLayout);
	}
	
	class Iro implements ActionListener																						//mentés gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			 {
			    szamlalo_szama();
				Db_iro beleir = new Db_iro();																				//példányosítás
				beleir.iro_gyartas(szamlalo, String.valueOf(vt_azon.getSelectedItem()), datum.getText(), muszak.getText(), String.valueOf(ellenor_neve.getSelectedItem()), 
						String.valueOf(hiba_combobox.getSelectedItem()), Integer.parseInt(felajanlott.getText()), Integer.parseInt(mintanagysag.getText()), pcb_sorszam.getText(), 
						String.valueOf(hibakod_combobox.getSelectedItem()), pozicio.getText(), Integer.parseInt(hibak_szama.getText()), sor.getText());				//Író osztály függvényének meghívása paraméterrel
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
					beleir.iro_gyartas(szamlalo, String.valueOf(vt_azon.getSelectedItem()), datum.getText(), muszak.getText(), String.valueOf(ellenor_neve.getSelectedItem()), 
							String.valueOf(hiba_combobox.getSelectedItem()), Integer.parseInt(felajanlott.getText()), Integer.parseInt(mintanagysag.getText()), pcb_sorszam.getText(), 
							String.valueOf(hibakod_combobox.getSelectedItem()), pozicio.getText(), Integer.parseInt(hibak_szama.getText()), sor.getText());
					szamlalo++;
					id.setText(String.valueOf(szamlalo)); 
			
					Urlap_torlo torles = new Urlap_torlo();
					torles.urlaptorles(felajanlott, mintanagysag, pcb_sorszam, pozicio, hibak_szama, sor);
				 }
				catch(Exception ex2)
				 {
				    szamlalo_szama();
                    Db_iro beleir = new Db_iro();
                    beleir.iro_gyartas(szamlalo, String.valueOf(vt_azon.getSelectedItem()), datum.getText(), muszak.getText(), String.valueOf(ellenor_neve.getSelectedItem()), 
                            String.valueOf(hiba_combobox.getSelectedItem()), Integer.parseInt(felajanlott.getText()), Integer.parseInt(mintanagysag.getText()), pcb_sorszam.getText(), 
                            String.valueOf(hibakod_combobox.getSelectedItem()), pozicio.getText(), Integer.parseInt(hibak_szama.getText()), sor.getText());
                    szamlalo++;
                    id.setText(String.valueOf(szamlalo)); 
            
                    Urlap_torlo torles = new Urlap_torlo();
                    torles.urlaptorles(felajanlott, mintanagysag, pcb_sorszam, pozicio, hibak_szama, sor);
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
