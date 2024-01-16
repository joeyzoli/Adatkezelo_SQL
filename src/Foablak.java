import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
	static JPanel contentPane;
	private Gyartasi_adatok adatok;
	private Uj_alapadat ujadat;
	private EASQAS_adatok easqas;
	private Ell_szeriaszam szeriaszam;
	private Adat_torles adattorlo;
	private Torlo torles;
	private Sajat_SQL sajat_sql;
	private PO_szam po;
	private Hitlista hitlista;
	private ProGlove proglove;
	//private Loxone loxon;
	//private Socomec socomec;
	//private Hager hager;
	private Hatter_beallitas hatterbeall;
	private Vevoi_reklmacio_bevitel vevoi_rek;
	private Vevoi_reklamacio_lezaras vevoi_lezar;
	private Vevoi_reklamacio_lekerdezes vevoi_leker;
	private Vevoi_reklamacio_ujadat vevoi_ujadat;
	private Retour retour;
	private Retour_lekerdez retour_lekerdez;
	private Retour_Mile retour_mile;
	private Beszallitoi_PPM ppm;
	private Telecom_utolso utolsofolyamat;
	private IFS_archive archive;
	private Tracy_utolso utolsotracy;
	private Gepes_ellenorok ellenorok;
	private AVM_csomagoloanyag avm;
	private IQC_ellenorzes iqc;
	private Ellenorok ellenori_nevek;
	private Proglove_kamera kamera;
	private Teszt_kezdes teszt_kezd;
	private Teszt_lezaras teszt_lezar;
	private Monitoring monitor;
	private OQC_adatok oqcadatok;
	private SQA_osszesito osszesit;
	private Smelter_masolo smelter;
	private Zarolasok_osszesito zarolas;
	private Zarolasok_lekerdezes zarolas_kimutatas;
	private Kiszallitasi_minoseg kiszallitasi;
	private Retour_szeriaszamok returtortenet;
	private Cmrt_adatok cmrt;
	private Fajl_atiras fajlok;
	private Ellenori_lapok lapok;
	private CSV csv;
	private Techem_OQC techem;
	private AIN_fordito fordito;
	private OQC_valaszto oqcvalaszto;
	private String kep = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\osz.jpg";
	private String kep2 = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\osz_alt.jpg";
	private String kep3 = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\osz_alt_11.jpg";
	private String jelszo;
	private JPasswordField mezo;
	private static final String jelszavam = "polip13";
	static Color hatter_szine = UIManager.getColor ( "Panel.background" );
	static Dimension meretek = new Dimension(1200, 850);
	

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
					File mentes = new File(System.getProperty("user.home") + "\\hatterszin.txt");
					if(mentes.exists())
					{
    					FileReader fr = new FileReader(System.getProperty("user.home") + "\\hatterszin.txt");
    			        try (BufferedReader br = new BufferedReader(fr)) 
    			        {
                            Color szin = new Color(Integer.parseInt(br.readLine()));
                            contentPane.setBackground(szin);
                            hatter_szine = szin;
                        }
    			        frame.setVisible(true);
					}
					else
					{
					    frame.setVisible(true);
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		Emailszal beolvasas = new Emailszal();
        Thread szal1 = new Thread(beolvasas);
        szal1.start();
	}

	/**
	 * 
	 * Főablak felépítése
	 * 
	 */
	public Foablak() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                       //JFrame.EXIT_ON_CLOSE
        this.setPreferredSize(meretek);
        setTitle("Minőségügyi Adatbázis kezelő");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Adatbevitel");
		menu.setForeground(new Color(0, 0, 255));
		menuBar.add(menu);
		
		JMenuItem gyartasi = new JMenuItem("Gyártási adatok");
		gyartasi.addActionListener(new PanelCsere_adatok());
		menu.add(gyartasi);
        
		JMenuItem torles = new JMenuItem("Dev");
		torles.addActionListener(new PanelCsere_torles());
		
		JMenuItem adat_torles = new JMenuItem("Adat módosítás/törlés");
		adat_torles.addActionListener(new PanelCsere_adat_torles());
		
		JMenuItem ujadat = new JMenuItem("Új alapadat");
		ujadat.addActionListener(new PanelCsere_uj_adatok());
		
		JMenuItem proglove = new JMenuItem("ProGlove");
		proglove.addActionListener(new PanelCsere_proglove());
		
		JMenuItem ellenori_lapok = new JMenuItem("Ellenőri lapok");
		ellenori_lapok.addActionListener(new Panelcsere_Ellenorilapok());
		menu.add(ellenori_lapok);
		menu.add(proglove);
		
		JMenuItem vevoi_reklamaciok = new JMenuItem("Vevői reklamációk");
		vevoi_reklamaciok.addActionListener(new PanelCsere_vevoi_bevitel());
		menu.add(vevoi_reklamaciok);
		
		JMenuItem vevoi_lezaras = new JMenuItem("Vevői lezárás");
		vevoi_lezaras.addActionListener(new PanelCsere_vevoi_lezaras());
		menu.add(vevoi_lezaras);
		
		JMenuItem vevoi_ujadat = new JMenuItem("Vevői új adat");
		vevoi_ujadat.addActionListener(new PanelCsere_vevoi_ujadat());
		menu.add(vevoi_ujadat);
		
		JMenuItem retour = new JMenuItem("Retour");
		retour.addActionListener(new PanelCsere_retour());
		menu.add(retour);
		
		JMenuItem ellenori_nevsor = new JMenuItem("Ellenőrök");
		ellenori_nevsor.addActionListener(new PanelCsere_ellenorok_nevek());
		
		JMenuItem zarolasok = new JMenuItem("Zárolási nyilvántartás");
		zarolasok.addActionListener(new Panelcsere_Zarolas());
		
		JMenuItem retour_szeriaszamok = new JMenuItem("Retour szériaszámok");
		retour_szeriaszamok.addActionListener(new Panelcsere_Retour_szeriaszam());
		menu.add(retour_szeriaszamok);
		menu.add(zarolasok);
		menu.add(ellenori_nevsor);
		menu.add(ujadat);
		menu.add(adat_torles);
		menu.add(torles);
		
		JMenu lekerdezes = new JMenu("Lekérdezés");
		lekerdezes.setForeground(new Color(0, 153, 0));
		menuBar.add(lekerdezes);
		
		JMenuItem easqas = new JMenuItem("EASQAS adatok");
		lekerdezes.add(easqas);
		
		JMenuItem sajat_sql = new JMenuItem("Saját SQL");
		sajat_sql.addActionListener(new PanelCsere_sajat_sql());
		
		JMenuItem vevoi_lekerdezes = new JMenuItem("Vevői reklamációk");
		vevoi_lekerdezes.addActionListener(new PanelCsere_vevoi_lekerdezes());
		lekerdezes.add(vevoi_lekerdezes);
		
		JMenuItem szeriaszam = new JMenuItem("Átvételi adatok");
		szeriaszam.addActionListener(new PanelCsere_szeriaszamok());
		lekerdezes.add(szeriaszam);
		
		JMenuItem zarolasok_kimutatasa = new JMenuItem("Zárolások");
		zarolasok_kimutatasa.addActionListener(new Panelcsere_Zarolas_kimutatas());
		lekerdezes.add(zarolasok_kimutatasa);
		
		JMenuItem techem = new JMenuItem("Techem");
		techem.addActionListener(new Panelcsere_Techem());
		lekerdezes.add(techem);
		lekerdezes.add(sajat_sql);
		
		JMenuItem po_szam = new JMenuItem("PO szám lekérdezés");
		po_szam.addActionListener(new PanelCsere_po_szam());
		lekerdezes.add(po_szam);
		
		JMenuItem hitlista = new JMenuItem("Hitlista lekérdezés");
		hitlista.addActionListener(new PanelCsere_hitlista());
		lekerdezes.add(hitlista);
		
		JMenuItem retour_lekerdezes = new JMenuItem("Retour");
		retour_lekerdezes.addActionListener(new PanelCsere_retour_lekerdez());
		
		JMenuItem retour_mile = new JMenuItem("Hager Retour");
		retour_mile.addActionListener(new PanelCsere_retour_mile());
		lekerdezes.add(retour_mile);
		lekerdezes.add(retour_lekerdezes);
		
		JMenuItem folyamatellenorok = new JMenuItem("Gépes folyamatellenőrök");
		folyamatellenorok.addActionListener(new PanelCsere_ellenorok());
		
		JMenuItem telecom_utolso = new JMenuItem("Telecom folyamatok");
		telecom_utolso.addActionListener(new PanelCsere_telecom_utolso());
		
		JMenuItem tracy_utolso = new JMenuItem("Tracy utosló folyamat");
		tracy_utolso.addActionListener(new PanelCsere_tracy_utolso());
		lekerdezes.add(tracy_utolso);
		lekerdezes.add(telecom_utolso);
		
		JMenuItem ifs_archive = new JMenuItem("IFS archive");
		ifs_archive.addActionListener(new PanelCsere_ifs_archive());
		lekerdezes.add(ifs_archive);
		lekerdezes.add(folyamatellenorok);
		
		JMenu vizsga = new JMenu("Vizsga");
		vizsga.setForeground(new Color(255, 140, 0));
		menuBar.add(vizsga);
		
		JMenuItem vizsga_teszt = new JMenuItem("Teszt");
		vizsga_teszt.addActionListener(new PanelCsere_teszt_kezd());
		vizsga.add(vizsga_teszt);
		
		JMenuItem vizsga_pontok = new JMenuItem("Pontok");
		vizsga.add(vizsga_pontok);
		vizsga_pontok.addActionListener(new PanelCsere_teszt_pont());
		
		JMenu monitoring = new JMenu("Monitoring");
		monitoring.setForeground(new Color(0, 153, 0));
		menuBar.add(monitoring);
		
		JMenuItem oqc = new JMenuItem("OQC átvételek");
		monitoring.add(oqc);
		oqc.addActionListener(new PanelCsere_monitoring());
		
		JMenu oqc_menu = new JMenu("OQC");
		menuBar.add(oqc_menu);
		
		JMenuItem oqc_adatbevitel = new JMenuItem("OQC adatbevitel");
		oqc_adatbevitel.addActionListener(new Panelcsere_OQC_valaszto());
		oqc_menu.add(oqc_adatbevitel);
		
		JMenuItem oqc_adatok = new JMenuItem("OQC adatok");
		oqc_adatok.addActionListener(new PanelCsere_oqcadatok());
		oqc_menu.add(oqc_adatok);
		
		JMenu sqa = new JMenu("SQA");
		sqa.setForeground(new Color(0, 0, 255));
		menuBar.add(sqa);
		
		JMenuItem avm_csomagolas = new JMenuItem("AVM csomagolás PPM");
		sqa.add(avm_csomagolas);
		
		JMenuItem beszallitoi_ppm = new JMenuItem("Beszállítói PPM");
		sqa.add(beszallitoi_ppm);
		
		JMenuItem iqc_1 = new JMenuItem("IQC ellenőrzés");
		sqa.add(iqc_1);
		
		JMenuItem proglove_kamera = new JMenuItem("Proglove kamera reklamáció");
		sqa.add(proglove_kamera);
		
		JMenuItem beszallitoi_reklamacio = new JMenuItem("Beszállítói reklamációk");
		beszallitoi_reklamacio.addActionListener(new PanelCsere_beszallitoi_reklamacio());
		sqa.add(beszallitoi_reklamacio);
		proglove_kamera.addActionListener(new PanelCsere_kamera());
		iqc_1.addActionListener(new PanelCsere_iqc());
		beszallitoi_ppm.addActionListener(new PanelCsere_beszallitoi_ppm());
		avm_csomagolas.addActionListener(new PanelCsere_avm());
		
		JMenu egyeb = new JMenu("Egyéb");
		menuBar.add(egyeb);
		
		JMenuItem smelter_masolo = new JMenuItem("Smelter másoló");
		smelter_masolo.addActionListener(new PanelCsere_Smelter());
		egyeb.add(smelter_masolo);
		
		JMenuItem kiszallitasi_minoseg = new JMenuItem("Kiszállítási minőség");
		kiszallitasi_minoseg.addActionListener(new Panelcsere_Kiszallitasi_minoseg());
		egyeb.add(kiszallitasi_minoseg);
		
		JMenuItem Reach_adatok = new JMenuItem("CMRT adatok");
		Reach_adatok.addActionListener(new Panelcsere_CMRT());
		egyeb.add(Reach_adatok);
		
		JMenuItem fajl_atiras = new JMenuItem("Fájl átírás");
		fajl_atiras.addActionListener(new Panelcsere_Fajlatiras());
		egyeb.add(fajl_atiras);
		
		JMenuItem csv_gyart = new JMenuItem("CSV készítő");
		csv_gyart.addActionListener(new Panelcsere_CSV());
		egyeb.add(csv_gyart);
		
		JMenuItem ain = new JMenuItem("AIN fordító");
		egyeb.add(ain);
		ain.addActionListener(new Panelcsere_AIN());
		JMenu beallitasok = new JMenu("Beállítások");
		menuBar.add(beallitasok);
		
		JMenuItem hatterszin = new JMenuItem("Háttér");
		hatterszin.addActionListener(new PanelCsere_hatter());
		
		beallitasok.add(hatterszin);
		
		JMenu leiras = new JMenu("Leírás");
		leiras.setForeground(new Color(255, 140, 0));
		menuBar.add(leiras);
		
		JMenuItem prog_leiras = new JMenuItem("Program leírása");
		prog_leiras.addActionListener(new Leiras_megnyitas());
		leiras.add(prog_leiras);
		easqas.addActionListener(new PanelCsere_easqas());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane gorgeto = new JScrollPane(contentPane);
		getContentPane().add(gorgeto);
		
		SimpleDateFormat rovid = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat hosszu = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date mai = new Date();
		String maiido = rovid.format(mai);
		Date reggelvege = null;
		Date d3 = new Date();
        try {
            reggelvege = hosszu.parse(maiido+" 11:00:00");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		JLabel hatter;
		if(System.getProperty("user.name").equals("Boncz Szilárd"))
		{
		    if(reggelvege.compareTo(d3) > 0) 
	        {
		        ImageIcon img = new ImageIcon(kep2);
	            hatter = new JLabel("", img ,JLabel.CENTER);
	            contentPane.add(hatter);
	        }
		    else
		    {
		        ImageIcon img = new ImageIcon(kep3);
                hatter = new JLabel("", img ,JLabel.CENTER);
                contentPane.add(hatter);
		    }		    
		}
		else if(System.getProperty("user.name").equals("boncz.szilard"))
        {
            if(reggelvege.compareTo(d3) > 0) 
            {
                ImageIcon img = new ImageIcon(kep2);
                hatter = new JLabel("", img ,JLabel.CENTER);
                contentPane.add(hatter);
            }
            else
            {
                ImageIcon img = new ImageIcon(kep3);
                hatter = new JLabel("", img ,JLabel.CENTER);
                contentPane.add(hatter);
            }           
        }
		else if(System.getProperty("user.name").equals("farkas.zoltan"))
        {
		    if(reggelvege.compareTo(d3) > 0) 
            {
                ImageIcon img = new ImageIcon(kep2);
                hatter = new JLabel("", img ,JLabel.CENTER);
                contentPane.add(hatter);
            }
            else
            {
                ImageIcon img = new ImageIcon(kep3);
                hatter = new JLabel("", img ,JLabel.CENTER);
                contentPane.add(hatter);
            }
        }
		else if(System.getProperty("user.name").equals("kovacs.sandor"))
        {
		    if(reggelvege.compareTo(d3) > 0) 
            {
		        ImageIcon img = new ImageIcon(kep2);
	            hatter = new JLabel("", img ,JLabel.CENTER);
	            contentPane.add(hatter);
            }
		    else
		    {
		        ImageIcon img = new ImageIcon(kep3);
	            hatter = new JLabel("", img ,JLabel.CENTER);
	            contentPane.add(hatter);
		    }
            
        }
		else
		{
    		ImageIcon img = new ImageIcon(kep);
    		hatter = new JLabel("", img ,JLabel.CENTER);
    		contentPane.add(hatter);
		}
		contentPane.setBackground(hatter_szine);
		
		//setContentPane(gorgeto);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		pack();
        setLocationRelativeTo(null);
        //sorszamol();

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
	
	class PanelCsere_szeriaszamok implements ActionListener                                                                                      //menüelem kiválasztásakor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            szeriaszam = new Ell_szeriaszam();                                                                                                   //példányosítás
            JScrollPane ablak = new JScrollPane(szeriaszam);
            setContentPane(ablak);                                                                                                          //új contentpane megadása
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
	
	class PanelCsere_po_szam implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            po = new PO_szam();
            JScrollPane ablak = new JScrollPane(po);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_hitlista implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            hitlista = new Hitlista();
            JScrollPane ablak = new JScrollPane(hitlista);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_proglove implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            proglove = new ProGlove();
            JScrollPane ablak = new JScrollPane(proglove);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_vevoi_bevitel implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            vevoi_rek = new Vevoi_reklmacio_bevitel();
            JScrollPane ablak = new JScrollPane(vevoi_rek);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_vevoi_lezaras implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            vevoi_lezar = new Vevoi_reklamacio_lezaras();
            JScrollPane ablak = new JScrollPane(vevoi_lezar);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_vevoi_ujadat implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            vevoi_ujadat = new Vevoi_reklamacio_ujadat();
            JScrollPane ablak = new JScrollPane(vevoi_ujadat);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_vevoi_lekerdezes implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            vevoi_leker = new Vevoi_reklamacio_lekerdezes();
            JScrollPane ablak = new JScrollPane(vevoi_leker);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_retour implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            retour = new Retour();
            JScrollPane ablak = new JScrollPane(retour);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_retour_lekerdez implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            retour_lekerdez = new Retour_lekerdez();
            JScrollPane ablak = new JScrollPane(retour_lekerdez);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_retour_mile implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            retour_mile = new Retour_Mile();
            JScrollPane ablak = new JScrollPane(retour_mile);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_beszallitoi_ppm implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            ppm = new Beszallitoi_PPM();
            JScrollPane ablak = new JScrollPane(ppm);
            ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_telecom_utolso implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            utolsofolyamat = new Telecom_utolso();
            JScrollPane ablak = new JScrollPane(utolsofolyamat);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_tracy_utolso implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            utolsotracy = new Tracy_utolso();
            JScrollPane ablak = new JScrollPane(utolsotracy);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_ifs_archive implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            archive = new IFS_archive();
            JScrollPane ablak = new JScrollPane(archive);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_avm implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            avm = new AVM_csomagoloanyag();
            JScrollPane ablak = new JScrollPane(avm);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_iqc implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            iqc = new IQC_ellenorzes();
            JScrollPane ablak = new JScrollPane(iqc);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_kamera implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            kamera = new Proglove_kamera();
            JScrollPane ablak = new JScrollPane(kamera);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_ellenorok implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            ellenorok = new Gepes_ellenorok();
            JScrollPane ablak = new JScrollPane(ellenorok);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_ellenorok_nevek implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            ellenori_nevek = new Ellenorok();
            JScrollPane ablak = new JScrollPane(ellenori_nevek);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_teszt_kezd implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            teszt_kezd = new Teszt_kezdes();
            JScrollPane ablak = new JScrollPane(teszt_kezd);
            ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_teszt_pont implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            teszt_lezar = new Teszt_lezaras();
            JScrollPane ablak = new JScrollPane(teszt_lezar);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_monitoring implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            monitor = new Monitoring();
            JScrollPane ablak = new JScrollPane(monitor);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_oqcadatok implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            oqcadatok = new OQC_adatok();
            JScrollPane ablak = new JScrollPane(oqcadatok);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_beszallitoi_reklamacio implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            osszesit = new SQA_osszesito();
            JScrollPane ablak = new JScrollPane(osszesit);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_Smelter implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            smelter = new Smelter_masolo();
            JScrollPane ablak = new JScrollPane(smelter);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_Zarolas implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            zarolas = new Zarolasok_osszesito();
            JScrollPane ablak = new JScrollPane(zarolas);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_Zarolas_kimutatas implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            zarolas_kimutatas = new Zarolasok_lekerdezes();
            JScrollPane ablak = new JScrollPane(zarolas_kimutatas);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_Kiszallitasi_minoseg implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            kiszallitasi = new Kiszallitasi_minoseg();
            JScrollPane ablak = new JScrollPane(kiszallitasi);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_Retour_szeriaszam implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            returtortenet = new Retour_szeriaszamok();
            JScrollPane ablak = new JScrollPane(returtortenet);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_CMRT implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            cmrt = new Cmrt_adatok();
            JScrollPane ablak = new JScrollPane(cmrt);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_Fajlatiras implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            fajlok = new Fajl_atiras();
            JScrollPane ablak = new JScrollPane(fajlok);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_Ellenorilapok implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            lapok = new Ellenori_lapok();
            JScrollPane ablak = new JScrollPane(lapok);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_CSV implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            csv = new CSV();
            JScrollPane ablak = new JScrollPane(csv);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_Techem implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            techem = new Techem_OQC();
            JScrollPane ablak = new JScrollPane(techem);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_AIN implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            fordito = new AIN_fordito();
            JScrollPane ablak = new JScrollPane(fordito);
            setContentPane(ablak);
            pack();
         }
    }
	
	class Panelcsere_OQC_valaszto implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            oqcvalaszto = new OQC_valaszto();
            JScrollPane ablak = new JScrollPane(oqcvalaszto);
            setContentPane(ablak);
            pack();
         }
    }
	
	class PanelCsere_hatter implements ActionListener																					//menüelem megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
		    hatterbeall = new Hatter_beallitas();
			JScrollPane ablak = new JScrollPane(hatterbeall);
			setContentPane(ablak);
			pack();
		 }
	}
	
	class Leiras_megnyitas implements ActionListener                                                                                  //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Desktop.getDesktop().open(new File("\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Minőségügy adatbázis kezelő program leírása_v0.docx"));                            //megnyitja az excelben szereplő helyen levő infó fájlt
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
         }
    }
	
	void csere(JPanel panel)
	{	    
        JScrollPane ablak = new JScrollPane(panel);
        setContentPane(ablak);
        pack();                      
	}
	void Parbeszed()																														//jelszavas védelem a tábla törlő menüponthoz
	{
	    JPanel ablakos = new JPanel();
		mezo = new JPasswordField(5);
		mezo.addKeyListener(new Enter());
		JButton gomb = new JButton("Ok");
		gomb.addActionListener(new ActionListener()
		{
			@SuppressWarnings("deprecation")
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
        @SuppressWarnings("deprecation")
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
	
	static class Emailszal implements Runnable
    {
        public void run()
        {
            SQL lekerdez = new SQL();
            lekerdez.vevoi_email();            
            Calendar calendar = Calendar.getInstance();
            int nap = calendar.get(Calendar.DAY_OF_WEEK);
            if(nap == 2)
            {
                SQA_SQL sqa = new SQA_SQL();
                sqa.sqa_email();
                sqa.retour_email();                
                System.out.println("Lefutott az SQA email");
            }
            else
            {
                SQA_SQL sqa = new SQA_SQL();
                //sqa.retour_email();
                String modosit = "update qualitydb.SQA_reklamaciok set  Ertesitve = 'Nem' where Lezaras_ido is null";
                sqa.mindenes(modosit);
                modosit = "update qualitydb.Retour set  Ertesitve = 'Nem' where Modositas_datuma is not null";
                sqa.mindenes(modosit);
                System.out.println("Ma nem fut le az SQA email rész");
                System.out.println("A hét napja:" + nap);
            }
            
            //System.out.println(System.getProperty("user.name"));
            /*
            Runtime runtime = Runtime.getRuntime();
            try {
                Process proc = runtime.exec("shutdown -s -t 0");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.exit(0);
            if(System.getProperty("user.name").equals("tatai.mihaly"))          //tatai.mihaly
            {
                JOptionPane.showMessageDialog(null, "BUZI vagy Köcsög!!!!!!", "Info", 1);                               
            }           
            /*if(System.getProperty("user.name").equals("csader.zsolt"))          //tatai.mihaly
            {
                JOptionPane.showMessageDialog(null, "A számítógép megsemmisíti Önmagát az OK gomb megnyomása után", "Info", 1);                               
            }*/
            //JOptionPane.showMessageDialog(null, "Üdvözöllek "+System.getProperty("user.name")+ "\n Legyen szép napod!", "Üdvözlő üzenet", 1);
            //System.out.println(ProcessHandle.current().info().user().get());            //VEAS\kovacs.zoltan
            /*if(System.getProperty("user.name").equals("reznyak.norbert"))          //reznyak.norbert
            {
                int reply = JOptionPane.showConfirmDialog(null, "Rednszerleállás és önmegsemmisítés?", "Önmegsemmisítés", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "HELLO");
                    Runtime runtime = Runtime.getRuntime();
                    try {
                        Process proc = runtime.exec("shutdown -s -t 0");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Üdv a programban!");
                }                              
            }*/
            
        }
    }
	/*
	public void sorszamol()
	{
	    String hely = "c:\\Users\\kovacs.zoltan\\eclipse-workspace\\Adat kezelo\\src\\";
	    FileReader fr = null;
	    int sorokszama = 0;
	    int karakterek = 0;
	    File mappa = new File(hely);
	    File[] fajlok = mappa.listFiles();
	    
	    //BufferedReader br = new BufferedReader(fr);  
	    for(int szamlalo = 0; szamlalo < fajlok.length; szamlalo++)
	    {
	        try 
            {
                fr = new FileReader(fajlok[szamlalo]);
                BufferedReader br = new BufferedReader(fr);  
                BufferedReader beolvaso = new BufferedReader(new FileReader(fajlok[szamlalo], StandardCharsets.UTF_8));
    
                String buffer;
                
                while((buffer = beolvaso.readLine()) != null)   
                {  
                    sorokszama++;
                }
                beolvaso.close();
                
                int i;    
                while((i=br.read())!=-1)
                {  
                    karakterek++;
                }  
            } 
	        catch (Exception e) 
	        {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	    }
	    System.out.println("Sorosk száma: " + sorokszama);
	    System.out.println("Karakterek száma: " + karakterek);
              
        try 
        {
            fr.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
	    
	}*/
	
}
