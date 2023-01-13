import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

public class EASQAS_adatok extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField datum_tol;
	private JTextField datum_ig;
	private JComboBox<String> hiba_box;
	private JComboBox<String> projekt_box;
	private ComboBox combobox_tomb;
	private JButton projekt_excel;

	/**
	 * Create the panel.
	 */
	public EASQAS_adatok() 
	{
		this.setPreferredSize(new Dimension(1100, 650));
		
		JLabel lblNewLabel = new JLabel("EASQAS adatok lekérezése");
		lblNewLabel.setBounds(497, 11, 184, 48);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Projekt");
		lblNewLabel_1.setBounds(263, 78, 53, 14);
		
		combobox_tomb = new ComboBox();
		
		
		projekt_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.projekt));                //combobox_tomb.getCombobox2(ComboBox.projekt)
		projekt_box.setBounds(497, 74, 191, 22);
		
		JLabel lblNewLabel_2 = new JLabel("Időpont -tól");
		lblNewLabel_2.setBounds(263, 121, 84, 14);
		
		datum_tol = new JTextField();
		datum_tol.setBounds(497, 118, 146, 20);
		datum_tol.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Dátum -ig");
		lblNewLabel_3.setBounds(263, 166, 72, 14);
		
		datum_ig = new JTextField();
		datum_ig.setBounds(497, 163, 146, 20);
		datum_ig.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Hibagyűtés helye");
		lblNewLabel_4.setBounds(263, 211, 104, 14);
		
		hiba_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.hiba_helye));                //combobox_tomb.getCombobox2(ComboBox.hiba_helye)
		hiba_box.setBounds(497, 207, 191, 22);
		
		JLabel lblNewLabel_5 = new JLabel("Projekt");
		lblNewLabel_5.setBounds(275, 286, 53, 14);
		
		projekt_excel = new JButton("Excel");
		projekt_excel.setBounds(338, 282, 72, 23);
		projekt_excel.addActionListener(new Projekt_lekerdezo());
		projekt_excel.addKeyListener(new Enter());
		
		JLabel lblNewLabel_6 = new JLabel("Termék");
		lblNewLabel_6.setBounds(469, 286, 60, 14);
		
		JButton termek_excel = new JButton("Excel");
		termek_excel.setBounds(539, 282, 72, 23);
		termek_excel.addActionListener(new Termek_lekerdezo());
		termek_excel.addKeyListener(new Enter2());
		
		JLabel lblNewLabel_7 = new JLabel("Hibák");
		lblNewLabel_7.setBounds(674, 286, 34, 14);
		
		JButton hibak_excel = new JButton("Excel");
		hibak_excel.setBounds(718, 282, 72, 23);
		hibak_excel.addActionListener(new Hiba_lekerdezo());
		hibak_excel.addKeyListener(new Enter3());
		setBackground(Foablak.hatter_szine);
		setLayout(null);
		add(lblNewLabel_1);
		add(lblNewLabel_2);
		add(lblNewLabel_3);
		add(lblNewLabel_4);
		add(lblNewLabel_5);
		add(projekt_excel);
		add(lblNewLabel_6);
		add(termek_excel);
		add(lblNewLabel_7);
		add(hibak_excel);
		add(projekt_box);
		add(datum_tol);
		add(datum_ig);
		add(hiba_box);
		add(lblNewLabel);

	}
	
	class Projekt_lekerdezo implements ActionListener																						//mentés gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			{
			    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			    if(projekt_box.getSelectedItem().equals("-"))
			    {
    				String querry = "call qualitydb.projekt_lekerdezo(?,?,?)";																	//tárolt eljárás Stringje
    				SQL lekerdezo = new SQL();																									//példányosítás
    				lekerdezo.lekerdez_projekt(querry, datum_tol.getText(), datum_ig.getText(), String.valueOf(hiba_box.getSelectedItem()));	//függvénymeghívása a paraméterekkel
			    }
			    else
			    {
			        String querry = "call qualitydb.projekt_lekerdezo_projekt(?,?,?,?)";                                                                   //tárolt eljárás Stringje
                    SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                    lekerdezo.lekerdez_projekt_projekt(querry, String.valueOf(projekt_box.getSelectedItem()), datum_tol.getText(), datum_ig.getText(), String.valueOf(hiba_box.getSelectedItem()));    //függvénymeghívása a paraméterekkel
			    }
			    Foablak.frame.setCursor(null);
			}
			catch (Exception e1) 
	        {
	            e1.printStackTrace();
	            String hibauzenet2 = e1.toString();
				JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
	        }
		 }
      
	}
	
	class Termek_lekerdezo implements ActionListener																						//termék gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			{
			    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String querry = "call qualitydb.termek_lekerdezo(?,?,?)";																	//tárolt eljárás Stringje
				SQL lekerdezo = new SQL();																									//példányosítás
				lekerdezo.lekerdez_projekt(querry, datum_tol.getText(), datum_ig.getText(), String.valueOf(hiba_box.getSelectedItem()));	//függvénymeghívása a paraméterekkel
				Foablak.frame.setCursor(null);
			}
			catch (Exception e1) 
	        {
	            e1.printStackTrace();
	            String hibauzenet2 = e1.toString();
				JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
	        }
		 }
	}
	
	class Hiba_lekerdezo implements ActionListener																							//hiba gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			{
			    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String querry = "call qualitydb.hibak_lekerdezo(?,?,?)";																	//tárolt eljárás stringje
				SQL lekerdezo = new SQL();																									//példányosítás
				lekerdezo.lekerdez_projekt(querry, datum_tol.getText(), datum_ig.getText(), String.valueOf(hiba_box.getSelectedItem()));	//függvénymeghívása a paraméterekkel
				Foablak.frame.setCursor(null);
			}
			catch (Exception e1) 
	        {
	            e1.printStackTrace();
	            String hibauzenet2 = e1.toString();
				JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
	        }
		 }
	}
	
	class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                try
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    if(projekt_box.getSelectedItem().equals("-"))
                    {
                        String querry = "call qualitydb.projekt_lekerdezo(?,?,?)";                                                                  //tárolt eljárás Stringje
                        SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                        lekerdezo.lekerdez_projekt(querry, datum_tol.getText(), datum_ig.getText(), String.valueOf(hiba_box.getSelectedItem()));    //függvénymeghívása a paraméterekkel
                    }
                    else
                    {
                        String querry = "call qualitydb.projekt_lekerdezo_projekt(?,?,?,?)";                                                                   //tárolt eljárás Stringje
                        SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                        lekerdezo.lekerdez_projekt_projekt(querry, String.valueOf(projekt_box.getSelectedItem()), datum_tol.getText(), datum_ig.getText(), String.valueOf(hiba_box.getSelectedItem()));    //függvénymeghívása a paraméterekkel
                    }
                    Foablak.frame.setCursor(null);
                }
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
            }
         
        }
        @Override
        public void keyTyped(KeyEvent e)                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }    
    }
	
	class Enter2 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                try
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    String querry = "call qualitydb.termek_lekerdezo(?,?,?)";                                                                   //tárolt eljárás Stringje
                    SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                    lekerdezo.lekerdez_projekt(querry, datum_tol.getText(), datum_ig.getText(), String.valueOf(hiba_box.getSelectedItem()));    //függvénymeghívása a paraméterekkel
                    Foablak.frame.setCursor(null);
                }
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
            }
         
        }
        @Override
        public void keyTyped(KeyEvent e)                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }    
    }
	
	class Enter3 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                try
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    String querry = "call qualitydb.hibak_lekerdezo(?,?,?)";                                                                    //tárolt eljárás stringje
                    SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                    lekerdezo.lekerdez_projekt(querry, datum_tol.getText(), datum_ig.getText(), String.valueOf(hiba_box.getSelectedItem()));    //függvénymeghívása a paraméterekkel
                    Foablak.frame.setCursor(null);
                }
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
            }
         
        }
        @Override
        public void keyTyped(KeyEvent e)                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }    
    }   
}
