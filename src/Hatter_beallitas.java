import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class Hatter_beallitas extends JPanel 
{
	

	/**
	 * Ez az osztály a háttér szinének beállítására szolgál
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox alap;
	private JCheckBox kek;
	private JCheckBox zold;
	private JCheckBox piros;
	private JCheckBox cian;
	private JCheckBox narancs;
	private JCheckBox sarga;
	private JCheckBox rozsaszin;
	private JCheckBox szurke;
	private JCheckBox feher;
	private JCheckBox sotet_szurke;
	private JCheckBox vilagos_szurke;
	private JCheckBox lila;
	private JCheckBox sajat;
	private JTextField sajat_szin_kod;

	/**
	 * Create the panel.
	 */
	public Hatter_beallitas() 
	{
		
		JLabel lblNewLabel = new JLabel("Háttér szinének a beállítása");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		alap = new JCheckBox("Alapértemezett");
		alap.setSelected(true);
		
		kek = new JCheckBox("Kék");
		
		zold = new JCheckBox("Zöld");
		
		piros = new JCheckBox("Piros");
		
		cian = new JCheckBox("Cián");
		
		narancs = new JCheckBox("Narancssárga");
		
		sarga = new JCheckBox("Sárga");
		
		rozsaszin = new JCheckBox("Rózsaszin");
		
		szurke = new JCheckBox("Szürke");
		
		feher = new JCheckBox("Fehér");
		
		sotet_szurke = new JCheckBox("sötét szürke");
		
		vilagos_szurke = new JCheckBox("Világos szürke");
		
		lila = new JCheckBox("Lila");
		
		sajat = new JCheckBox("Saját szin");
		
		ButtonGroup gombcsoport = new ButtonGroup();
		gombcsoport.add(alap);
		gombcsoport.add(vilagos_szurke);
		gombcsoport.add(sotet_szurke);
		gombcsoport.add(feher);
		gombcsoport.add(szurke);
		gombcsoport.add(rozsaszin);
		gombcsoport.add(sarga);
		gombcsoport.add(narancs);
		gombcsoport.add(piros);
		gombcsoport.add(zold);
		gombcsoport.add(kek);
		gombcsoport.add(lila);
		gombcsoport.add(sajat);
		
		JButton mentes = new JButton("Mentés");
		mentes.addActionListener(new Hatter_beallit());
		
		sajat_szin_kod = new JTextField();
		sajat_szin_kod.setText("142,255,15");
		sajat_szin_kod.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(409, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(521))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(sajat)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(alap)
							.addGap(18)
							.addComponent(kek)
							.addGap(18)
							.addComponent(zold)
							.addGap(18)
							.addComponent(piros)
							.addGap(18)
							.addComponent(cian)
							.addGap(18)
							.addComponent(narancs)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(mentes)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(sarga)
							.addGap(18)
							.addComponent(rozsaszin)
							.addGap(18)
							.addComponent(szurke)
							.addGap(18)
							.addComponent(feher)
							.addGap(18)
							.addComponent(sotet_szurke)
							.addGap(18)
							.addComponent(vilagos_szurke)
							.addGap(18)
							.addComponent(lila))
						.addComponent(sajat_szin_kod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(lblNewLabel)
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(alap)
						.addComponent(kek)
						.addComponent(zold)
						.addComponent(piros)
						.addComponent(cian)
						.addComponent(narancs)
						.addComponent(sarga)
						.addComponent(rozsaszin)
						.addComponent(szurke)
						.addComponent(feher)
						.addComponent(sotet_szurke)
						.addComponent(vilagos_szurke)
						.addComponent(lila))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(sajat)
						.addComponent(sajat_szin_kod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(65)
					.addComponent(mentes)
					.addContainerGap(318, Short.MAX_VALUE))
		);
		setBackground(Foablak.hatter_szine);
		setLayout(groupLayout);

	}
	
	class Hatter_beallit implements ActionListener																						//törlés gomb megnyomáskor hívódik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			if(alap.isSelected())
			{
				Foablak.hatter_szine = UIManager.getColor ( "Panel.background" );
			}
			else if(kek.isSelected())
			{
				Foablak.hatter_szine = Color.BLUE;
			}
			else if(zold.isSelected())
			{
				Foablak.hatter_szine = Color.GREEN;
			}
			else if(piros.isSelected())
			{
				Foablak.hatter_szine = Color.RED;
			}
			else if(cian.isSelected())
			{
				Foablak.hatter_szine = Color.CYAN;
			}
			else if(narancs.isSelected())
			{
				Foablak.hatter_szine = Color.ORANGE;
			}
			else if(sarga.isSelected())
			{
				Foablak.hatter_szine = Color.YELLOW;
			}
			else if(lila.isSelected())
			{
				Foablak.hatter_szine = Color.MAGENTA;
			}
			else if(rozsaszin.isSelected())
			{
				Foablak.hatter_szine = Color.PINK;
			}
			else if(szurke.isSelected())
			{
				Foablak.hatter_szine = Color.GRAY;
			}
			else if(sotet_szurke.isSelected())
			{
				Foablak.hatter_szine = Color.DARK_GRAY;
			}
			else if(vilagos_szurke.isSelected())
			{
				Foablak.hatter_szine = Color.LIGHT_GRAY;
			}
			else if(feher.isSelected())
			{
				Foablak.hatter_szine = Color.WHITE;
			}
			else if(sajat.isSelected())
			{
				String[] sajat_szinek = sajat_szin_kod.getText().split(",");
				Foablak.hatter_szine = new Color(Integer.parseInt(sajat_szinek[0]), Integer.parseInt(sajat_szinek[1]), Integer.parseInt(sajat_szinek[2]));
			}
			JOptionPane.showMessageDialog(null, "Mentés sikeres!!  \n Menüpont váltás után aktiválódik a beállítás!", "Info", 1);
		 }
	}
}
