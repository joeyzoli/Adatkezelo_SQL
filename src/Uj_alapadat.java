import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

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

	/**
	 * Create the panel.
	 */
	public Uj_alapadat() 
	{
		
		JLabel lblNewLabel = new JLabel("Új alapadat felvitele");
		lblNewLabel.setForeground(new Color(204, 102, 0));
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Vevő");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 11));
		
		vevo_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.projekt));				//combobox_tomb.getCombobox_projekt()
		
		JLabel lblNewLabel_2 = new JLabel("Vevői azonosító");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JLabel lblNewLabel_3 = new JLabel("Vevői megnevezés");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JLabel lblNewLabel_4 = new JLabel("VT megnevezés");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 11));
		
		vevo_azon = new JTextField();
		vevo_azon.setColumns(10);
		
		vevo_megnev = new JTextField();
		vevo_megnev.setColumns(10);
		
		vt_megnev = new JTextField();
		vt_megnev.setColumns(10);
		
		JButton felvisz = new JButton("Cikkszám Felvisz");
		felvisz.addActionListener(new Fajlba_iro());
		
		JLabel lblNewLabel_5 = new JLabel("Cikkszám");
		
		JLabel lblNewLabel_6 = new JLabel("Hibakód");
		
		JLabel lblNewLabel_7 = new JLabel("Hibakód");
		
		hibakod = new JTextField();
		hibakod.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Hiba megnevezés");
		
		hiba_megnevezes = new JTextField();
		hiba_megnevezes.setColumns(10);
		
		JButton uj_hibagomb = new JButton("Hiba felvisz");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(204)
					.addComponent(lblNewLabel_5)
					.addPreferredGap(ComponentPlacement.RELATED, 451, Short.MAX_VALUE)
					.addComponent(lblNewLabel_6)
					.addGap(370))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(137)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(vevo_box, Alignment.LEADING, 0, 152, Short.MAX_VALUE)
								.addComponent(vevo_azon, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
								.addComponent(vevo_megnev, Alignment.LEADING, 98, 152, Short.MAX_VALUE)
								.addComponent(vt_megnev, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_8)
								.addComponent(lblNewLabel_7))
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(felvisz)
							.addGap(353)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(hibakod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(hiba_megnevezes, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
						.addComponent(uj_hibagomb))
					.addGap(187))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(518, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(476))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(lblNewLabel)
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_6))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(vevo_azon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(vevo_box, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_7)
								.addComponent(hibakod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_8)
								.addComponent(hiba_megnevezes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(vevo_megnev, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(vt_megnev, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(felvisz)
						.addComponent(uj_hibagomb))
					.addContainerGap(241, Short.MAX_VALUE))
		);
		setBackground(Foablak.hatter_szine);
		setLayout(groupLayout);

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
}
