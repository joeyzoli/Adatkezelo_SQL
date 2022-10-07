import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Foablak extends JFrame 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Gyartasi_adatok adatok;
	private Uj_alapadat ujadat;
	private EASQAS_adatok easqas;
	private Adat_torles adattorlo;
	private Torlo torles;
	private Sajat_SQL sajat_sql;
	private Hatter_beallitas hatterbeall;
	private String kep = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\osz.jpg";
	private String jelszo;
	private static final String jelszavam = "polip13";
	static Color hatter_szine = UIManager.getColor ( "Panel.background" );
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Foablak frame = new Foablak();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * Főablak felépítése
	 * 
	 */
	public Foablak() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200, 800));
        setTitle("Adat kezelő");
        
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Adatbevitel");
		menuBar.add(menu);
		
		JMenuItem gyartasi = new JMenuItem("Gyártási adtok");
		gyartasi.addActionListener(new PanelCsere_adatok());
		menu.add(gyartasi);
        
		JMenuItem torles = new JMenuItem("Törlés");
		torles.addActionListener(new PanelCsere_torles());
		
		JMenuItem adat_torles = new JMenuItem("Adat törlés");
		adat_torles.addActionListener(new PanelCsere_adat_torles());
		
		JMenuItem ujadat = new JMenuItem("Új alapadat");
		ujadat.addActionListener(new PanelCsere_uj_adatok());
		menu.add(ujadat);
		menu.add(adat_torles);
		menu.add(torles);
		
		JMenu lekerdezes = new JMenu("Lekérdezés");
		menuBar.add(lekerdezes);
		
		JMenuItem easqas = new JMenuItem("EASQAS adatok");
		lekerdezes.add(easqas);
		
		JMenuItem sajat_sql = new JMenuItem("Saját SQL");
		sajat_sql.addActionListener(new PanelCsere_sajat_sql());
		lekerdezes.add(sajat_sql);
		
		JMenu beallitasok = new JMenu("Beállítások");
		menuBar.add(beallitasok);
		
		JMenuItem hatterszin = new JMenuItem("Háttér");
		hatterszin.addActionListener(new PanelCsere_hatter());
		
		beallitasok.add(hatterszin);
		easqas.addActionListener(new PanelCsere_easqas());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane gorgeto = new JScrollPane(contentPane);
		getContentPane().add(gorgeto);
		
		JLabel hatter;
		ImageIcon img = new ImageIcon(kep);
		hatter = new JLabel("", img ,JLabel.CENTER);
		contentPane.add(hatter);
		
		contentPane.setBackground(hatter_szine);
		
		//setContentPane(gorgeto);
		
		pack();
        setLocationRelativeTo(null);

	}
	
/*****************************************************************  Menü elemek közötti lépegetés *********************************************************************************************************/
	
	class PanelCsere_adatok implements ActionListener																						//menüelem kiválasztásakor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			adatok = new Gyartasi_adatok();																									//példányosítás
			JScrollPane ablak = new JScrollPane(adatok);
			setContentPane(ablak);																											//új contentpane megadása
			pack();
		 }
	}
	
	class PanelCsere_uj_adatok implements ActionListener																					//menüelem kiválasztásakor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			ujadat = new Uj_alapadat();																										//példányosítás
			JScrollPane ablak = new JScrollPane(ujadat);
			setContentPane(ablak);																											//új contentpane megadása
			pack();
		 }
	}
	
	class PanelCsere_adat_torles implements ActionListener																					//menüelem kiválasztásakor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			adattorlo = new Adat_torles();
			JScrollPane ablak = new JScrollPane(adattorlo);
			setContentPane(ablak);
			pack();
		 }
	}
	
	class PanelCsere_torles implements ActionListener																						//menüelem kiválasztásakor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			Parbeszed();
		 }
	}
	
	class PanelCsere_easqas implements ActionListener																						//menüelem kiválasztásakor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			easqas = new EASQAS_adatok();																									//példányosítás
			JScrollPane ablak = new JScrollPane(easqas);
			setContentPane(ablak);																											//új contentpane megadása
			pack();
		 }
	}
	
	class PanelCsere_sajat_sql implements ActionListener																					//mentés gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			sajat_sql = new Sajat_SQL();
			JScrollPane ablak = new JScrollPane(sajat_sql);
			setContentPane(ablak);
			pack();
		 }
	}
	
	class PanelCsere_hatter implements ActionListener																					//mentés gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			hatterbeall = new Hatter_beallitas();
			JScrollPane ablak = new JScrollPane(hatterbeall);
			setContentPane(ablak);
			pack();
		 }
	}
	
	void Parbeszed()																														//jelszavas védelem a tábla törlő menüponthoz
	{
		JTextField mezo = new JTextField(5);
		JButton gomb = new JButton("Ok");
		gomb.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jelszo = mezo.getText();
				if(jelszavam.equals(jelszo))																								//ha a jelszó stimmel, betölti a kért menüpontot
				{
					torles = new Torlo();
					setContentPane(torles);
					pack();
				}
				else																														//ha nem stimmel a jelszó, hibaüzenetet ír ki
				{
					JOptionPane.showMessageDialog(null, "Helytelen jelszó", "Hiba üzenet", 2);
				}
			}
		});
		getContentPane().add(new JLabel("Jelszó: "));
		getContentPane().add(mezo);
		getContentPane().add(gomb);
		getContentPane().setLayout(new FlowLayout());
		pack();
		setVisible(true);
	}
}
