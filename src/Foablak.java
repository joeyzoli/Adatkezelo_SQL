import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	static Foablak frame;
	private JPanel contentPane;
	private Gyartasi_adatok adatok;
	private Uj_alapadat ujadat;
	private EASQAS_adatok easqas;
	private Adat_torles adattorlo;
	private Torlo torles;
	private Sajat_SQL sajat_sql;
	private PO_szam po;
	private Hitlista hitlista;
	//private Uj_adatok muszaki_adatok;
	//private Muszaki_leker muszaki_leker;
	private ProGlove proglove;
	private Hatter_beallitas hatterbeall;
	private String kep = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\osz.jpg";
	private String jelszo;
	private JTextField mezo;
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
					frame = new Foablak();
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
        setTitle("Minőségügyi Adatbázis kezelő");
        
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Adatbevitel");
		menuBar.add(menu);
		
		JMenuItem gyartasi = new JMenuItem("Gyártási adtok");
		gyartasi.addActionListener(new PanelCsere_adatok());
		menu.add(gyartasi);
        
		JMenuItem torles = new JMenuItem("Dev");
		torles.addActionListener(new PanelCsere_torles());
		
		JMenuItem adat_torles = new JMenuItem("Adat törlés");
		adat_torles.addActionListener(new PanelCsere_adat_torles());
		
		JMenuItem ujadat = new JMenuItem("Új alapadat");
		ujadat.addActionListener(new PanelCsere_uj_adatok());
		
		JMenuItem proglove = new JMenuItem("ProGlove");
		proglove.addActionListener(new PanelCsere_proglove());
		menu.add(proglove);
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
		
		JMenuItem po_szam = new JMenuItem("PO szám lekérdezés");
		po_szam.addActionListener(new PanelCsere_po_szam());
		lekerdezes.add(po_szam);
		
		JMenuItem hitlista = new JMenuItem("Hitlista lekérdezés");
		hitlista.addActionListener(new PanelCsere_hitlista());
		lekerdezes.add(hitlista);
		/*
		JMenu mnNewMenu = new JMenu("Műszaki");
		menuBar.add(mnNewMenu);
		
		JMenuItem ujadat_menu = new JMenuItem("Új adat felvitele");
		ujadat_menu.addActionListener(new PanelCsere_muszaki_ujadat());
		mnNewMenu.add(ujadat_menu);
		
		JMenuItem adatleker_menu = new JMenuItem("Adatok lekérdezése");
		adatleker_menu.addActionListener(new PanelCsere_muszaki_leker());
		mnNewMenu.add(adatleker_menu);
		*/
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
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
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
	
	class PanelCsere_po_szam implements ActionListener                                                                                   //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            po = new PO_szam();
            JScrollPane ablak = new JScrollPane(po);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_hitlista implements ActionListener                                                                                   //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            hitlista = new Hitlista();
            JScrollPane ablak = new JScrollPane(hitlista);
            setContentPane(ablak);
            pack();
         }
    }
	/*
	class PanelCsere_muszaki_ujadat implements ActionListener                                                                                   //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            muszaki_adatok = new Uj_adatok();
            JScrollPane ablak = new JScrollPane(muszaki_adatok);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_muszaki_leker implements ActionListener                                                                                   //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            muszaki_leker = new Muszaki_leker();
            JScrollPane ablak = new JScrollPane(muszaki_leker);
            setContentPane(ablak);
            pack();
         }
    }
	*/
	class PanelCsere_proglove implements ActionListener                                                                                   //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            proglove = new ProGlove();
            JScrollPane ablak = new JScrollPane(proglove);
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
	    JPanel ablakos = new JPanel();
		mezo = new JTextField(5);
		mezo.addKeyListener(new Enter());
		JButton gomb = new JButton("Ok");
		gomb.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jelszo = mezo.getText();
				if(jelszavam.equals(jelszo))																								//ha a jelszó stimmel, betölti a kért menüpontot
				{
					torles = new Torlo();
					JScrollPane ablak = new JScrollPane(torles);
					setContentPane(ablak);
					pack();
				}
				else																														//ha nem stimmel a jelszó, hibaüzenetet ír ki
				{
					JOptionPane.showMessageDialog(null, "Helytelen jelszó", "Hiba üzenet", 2);
				}
			}
		});
		ablakos.add(new JLabel("Jelszó: "));
		ablakos.add(mezo);
		ablakos.add(gomb);
		ablakos.setLayout(new FlowLayout());
		setContentPane(ablakos);
		pack();
		setVisible(true);
	}
	
	class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                jelszo = mezo.getText();
                if(jelszavam.equals(jelszo))                                                                                                //ha a jelszó stimmel, betölti a kért menüpontot
                {
                    torles = new Torlo();
                    setContentPane(torles);
                    pack();
                }
                else                                                                                                                        //ha nem stimmel a jelszó, hibaüzenetet ír ki
                {
                    JOptionPane.showMessageDialog(null, "Helytelen jelszó", "Hiba üzenet", 2);
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
