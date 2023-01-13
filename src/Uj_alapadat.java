import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Uj_alapadat extends JPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField vevo_azon;
	private JTextField vevo_megnev;
	private JTextField vt_megnev;
	private ComboBox combobox_tomb = new ComboBox();
	private JComboBox<String> vevo_box;
	private JTextField hibakod;
	private JTextField hiba_megnevezes;
	private JTextField ellenor_neve;
	private String ellenorok = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ellenőrök.csv";

	/**
	 * Create the panel.
	 */
	public Uj_alapadat() 
	{
		
		JLabel lblNewLabel = new JLabel("Új alapadat felvitele");
		lblNewLabel.setBounds(503, 38, 127, 18);
		lblNewLabel.setForeground(new Color(204, 102, 0));
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Vevő");
		lblNewLabel_1.setBounds(137, 150, 26, 13);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 11));
		
		vevo_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.projekt));				//combobox_tomb.getCombobox(ComboBox.projekt)
		vevo_box.setBounds(259, 145, 255, 22);
		
		JLabel lblNewLabel_2 = new JLabel("Vevői azonosító");
		lblNewLabel_2.setBounds(137, 189, 78, 13);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JLabel lblNewLabel_3 = new JLabel("Vevői megnevezés");
		lblNewLabel_3.setBounds(137, 227, 93, 13);
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JLabel lblNewLabel_4 = new JLabel("VT megnevezés");
		lblNewLabel_4.setBounds(137, 265, 79, 13);
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 11));
		
		vevo_azon = new JTextField();
		vevo_azon.setBounds(259, 185, 255, 20);
		vevo_azon.setColumns(10);
		
		vevo_megnev = new JTextField();
		vevo_megnev.setBounds(259, 223, 255, 20);
		vevo_megnev.setColumns(10);
		
		vt_megnev = new JTextField();
		vt_megnev.setBounds(259, 261, 255, 20);
		vt_megnev.setColumns(10);
		
		JButton felvisz = new JButton("Cikkszám felvesz");
		felvisz.setBounds(259, 299, 111, 23);
		felvisz.addActionListener(new Fajlba_iro());
		
		JLabel lblNewLabel_5 = new JLabel("Cikkszám");
		lblNewLabel_5.setBounds(204, 91, 43, 14);
		
		JLabel lblNewLabel_6 = new JLabel("Hibakód");
		lblNewLabel_6.setBounds(698, 91, 38, 14);
		
		JLabel lblNewLabel_7 = new JLabel("Hibakód");
		lblNewLabel_7.setBounds(621, 149, 57, 14);
		
		hibakod = new JTextField();
		hibakod.addActionListener(new Hibakod());
		hibakod.setBounds(723, 146, 86, 20);
		hibakod.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Hiba megnevezés");
		lblNewLabel_8.setBounds(621, 188, 92, 14);
		
		hiba_megnevezes = new JTextField();
		hiba_megnevezes.setBounds(723, 185, 196, 20);
		hiba_megnevezes.setColumns(10);
		
		JButton uj_hibagomb = new JButton("Hiba felvesz");
		uj_hibagomb.setBounds(723, 299, 105, 23);
		setBackground(Foablak.hatter_szine);
		setLayout(null);
		add(lblNewLabel_5);
		add(lblNewLabel_6);
		add(lblNewLabel_1);
		add(lblNewLabel_2);
		add(lblNewLabel_3);
		add(lblNewLabel_4);
		add(vevo_box);
		add(vevo_azon);
		add(vevo_megnev);
		add(vt_megnev);
		add(lblNewLabel_8);
		add(lblNewLabel_7);
		add(felvisz);
		add(hibakod);
		add(hiba_megnevezes);
		add(uj_hibagomb);
		add(lblNewLabel);
		
		JLabel lblNewLabel_9 = new JLabel("Ellenőr");
		lblNewLabel_9.setBounds(204, 365, 46, 14);
		add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Ellenőr neve");
		lblNewLabel_10.setBounds(137, 406, 78, 14);
		add(lblNewLabel_10);
		
		ellenor_neve = new JTextField();
		ellenor_neve.setBounds(259, 403, 255, 20);
		add(ellenor_neve);
		ellenor_neve.setColumns(10);
		
		JButton ellenor_gomb = new JButton("Ellenőr felvesz");
		ellenor_gomb.addActionListener(new Ellenor());
		ellenor_gomb.setBounds(259, 457, 127, 23);
		add(ellenor_gomb);

	}
	
	class Fajlba_iro implements ActionListener																							//felvisz gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try(FileWriter fw = new FileWriter("\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\vt_lista.csv", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println(vt_megnev.getText() + "," + vevo_azon.getText() + "," + vevo_box.getSelectedItem() + ","+ vevo_megnev.getText() + "," );  
				    //more code
				    JOptionPane.showMessageDialog(null, "Új adat felvitel kész", "Info", 1);
				    System.out.println("Fájlba írás kész");
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
		            String hibauzenet2 = e1.toString();
					JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
				}
		 }
	}
	
	class Ellenor implements ActionListener                                                                                         //felvisz gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try(FileWriter fw = new FileWriter("\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ellenőrök.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    OutputStream os = new FileOutputStream(ellenorok);
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true))
                {
                    out.println(ellenor_neve.getText());  
                    //more code
                    JOptionPane.showMessageDialog(null, "Új adat felvitel kész", "Info", 1);
                } 
                catch (IOException e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
         }
    }
	
	class Hibakod implements ActionListener                                                                                         //felvisz gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try(FileWriter fw = new FileWriter("\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Hibakód.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(hibakod.getText() + "," + hiba_megnevezes.getText());  
                    //more code
                    JOptionPane.showMessageDialog(null, "Új adat felvitel kész", "Info", 1);
                    System.out.println("Fájlba írás kész");
                } 
                catch (IOException e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
         }
    }
}
