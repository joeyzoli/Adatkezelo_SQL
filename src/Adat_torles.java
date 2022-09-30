import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Adat_torles extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField pcb_sorszam;
	private Db_torlo pcb_torlo;

	/**
	 * Create the panel.
	 */
	public Adat_torles() 
	{
		
		JLabel lblNewLabel = new JLabel("Hibásan felvitt adatok törlése");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("PCB sorszám");
		
		pcb_sorszam = new JTextField();
		pcb_sorszam.setColumns(10);
		
		JButton torlo_gomb = new JButton("Törlés");
		torlo_gomb.addActionListener(new PCB_torlo());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(368)
							.addComponent(lblNewLabel_1)
							.addGap(66)
							.addComponent(pcb_sorszam, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(432)
							.addComponent(torlo_gomb))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(409)
							.addComponent(lblNewLabel)))
					.addContainerGap(346, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addComponent(lblNewLabel)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pcb_sorszam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(33)
					.addComponent(torlo_gomb)
					.addContainerGap(297, Short.MAX_VALUE))
		);
		setBackground(Foablak.hatter_szine);
		setLayout(groupLayout);

	}
	
	class PCB_torlo implements ActionListener																						//törlés gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {


			int res = JOptionPane.showConfirmDialog(null, 																			//megerősítést kérő üzenet jelenik meg
			"Figyelem! Biztosan ezt akarod?", 
			"Figyelmeztetés", 
			JOptionPane.YES_NO_OPTION);
			 
			if (res == JOptionPane.YES_OPTION)																						//igen gomb megnyomásakot törli a megadott sort
			{
				pcb_torlo = new Db_torlo();
				pcb_torlo.pcb_torlo(pcb_sorszam.getText());
			}
			else																													//nem válasz estén hibaüzenet és a pcb sorszám törlése
			{
				JOptionPane.showMessageDialog(null, "Törlés megszakítva!!", "Hiba üzenet", 2);
				pcb_sorszam.setText("");
			}
		 }
	}
}
