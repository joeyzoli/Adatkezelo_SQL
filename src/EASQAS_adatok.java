import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
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
		
		JLabel lblNewLabel = new JLabel("EASQAS adatok");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Projekt");
		
		combobox_tomb = new ComboBox();
		
		
		projekt_box = new JComboBox<String>(combobox_tomb.getCombobox_projekt());
		
		JLabel lblNewLabel_2 = new JLabel("Időpont -tól");
		
		datum_tol = new JTextField();
		datum_tol.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Dátum -ig");
		
		datum_ig = new JTextField();
		datum_ig.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Hibagyűtés helye");
		
		hiba_box = new JComboBox<String>(combobox_tomb.getCombobox_hiba());
		
		JLabel lblNewLabel_5 = new JLabel("Projekt");
		
		projekt_excel = new JButton("Excel");
		projekt_excel.addActionListener(new Projekt_lekerdezo());
		projekt_excel.addKeyListener(new Enter());
		
		JLabel lblNewLabel_6 = new JLabel("Termék");
		
		JButton termek_excel = new JButton("Excel");
		termek_excel.addActionListener(new Termek_lekerdezo());
		termek_excel.addKeyListener(new Enter2());
		
		JLabel lblNewLabel_7 = new JLabel("Hibák");
		
		JButton hibak_excel = new JButton("Excel");
		hibak_excel.addActionListener(new Hiba_lekerdezo());
		hibak_excel.addKeyListener(new Enter3());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(319)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addGap(18)
									.addComponent(projekt_excel)))
							.addGap(105)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_6)
									.addGap(18)
									.addComponent(termek_excel)
									.addGap(103)
									.addComponent(lblNewLabel_7)
									.addGap(18)
									.addComponent(hibak_excel))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(projekt_box, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(datum_tol)
									.addComponent(datum_ig)
									.addComponent(hiba_box, 0, 381, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(574)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(385, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(projekt_box, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(datum_tol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(datum_ig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(hiba_box, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(84)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_6)
						.addComponent(termek_excel)
						.addComponent(projekt_excel)
						.addComponent(lblNewLabel_7)
						.addComponent(hibak_excel))
					.addContainerGap(503, Short.MAX_VALUE))
		);
		setBackground(Foablak.hatter_szine);
		setLayout(groupLayout);

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
