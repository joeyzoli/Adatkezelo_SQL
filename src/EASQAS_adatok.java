import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTable;

public class EASQAS_adatok extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JTextField datum_tol;
	//private JTextField datum_ig;
	private JComboBox<String> hiba_box;
	private JComboBox<String> projekt_box;
	private ComboBox combobox_tomb;
	private JButton projekt_excel;
	static JTable table;
	static JScrollPane gorgeto;
	private String projekt = System.getProperty("user.home") + "\\Desktop\\ProjektPPM.xlsx";
	private String termek = System.getProperty("user.home") + "\\Desktop\\TermékPPM.xlsx";
	private String hiba = System.getProperty("user.home") + "\\Desktop\\Hibák_adatai.xlsx";
	private JDatePickerImpl datum_tol;
	private JDatePickerImpl datum_ig;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public EASQAS_adatok()
	{
		this.setPreferredSize(new Dimension(1224, 679));
		UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Ma");
        p.put("text.month", "Hónap");
        p.put("text.year", "Év");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        
        UtilDateModel model2 = new UtilDateModel();
        /////////dátum beállítása
        /*String dateValue = "2024.03.23";  // must be in (yyyy- mm- dd ) format
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy.MM.dd").parse(dateValue);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model2.setValue(date);*/
        Properties p2 = new Properties();
        p2.put("text.today", "Ma");
        p2.put("text.month", "Hónap");
        p2.put("text.year", "Év");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);           
		
		JLabel lblNewLabel = new JLabel("EASQAS adatok lekérezése");
		lblNewLabel.setBounds(497, 11, 184, 48);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Projekt");
		lblNewLabel_1.setBounds(263, 78, 53, 14);
		
		combobox_tomb = new ComboBox();
		
		
		projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.projekt));                //combobox_tomb.getCombobox(ComboBox.projekt)
		projekt_box.setBounds(497, 74, 191, 22);
		
		JLabel lblNewLabel_2 = new JLabel("Időpont -tól");
		lblNewLabel_2.setBounds(263, 121, 84, 14);
		
		datum_tol = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datum_tol.setBounds(497, 118, 146, 20);

		
		JLabel lblNewLabel_3 = new JLabel("Dátum -ig");
		lblNewLabel_3.setBounds(263, 166, 72, 14);
		
		datum_ig = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datum_ig.setBounds(497, 163, 146, 20);
		
		JLabel lblNewLabel_4 = new JLabel("Hibagyűtés helye");
		lblNewLabel_4.setBounds(263, 211, 104, 14);
		
		hiba_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hiba_helye));                //combobox_tomb.getCombobox(ComboBox.hiba_helye)
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
		
		JButton mutat_projekt = new JButton("Mutat");
		mutat_projekt.setBounds(338, 315, 72, 23);
		mutat_projekt.addActionListener(new Projekt_mutat());
		add(mutat_projekt);
		
		JButton mutat_termek = new JButton("Mutat");
		mutat_termek.setBounds(539, 316, 72, 23);
		mutat_termek.addActionListener(new Termek_mutat());
		add(mutat_termek);
		
		JButton mutat_hiba = new JButton("Mutat");
		mutat_hiba.setBounds(718, 316, 72, 23);
		mutat_hiba.addActionListener(new Hiba_mutat());
		add(mutat_hiba);
		
		table = new JTable();
		gorgeto = new JScrollPane(table);
		gorgeto.setBounds(10, 379, 1164, 224);
		add(gorgeto);
		
		JLabel lblNewLabel_8 = new JLabel("Touch Up riport");
		lblNewLabel_8.setBounds(377, 637, 110, 14);
		add(lblNewLabel_8);
		
		JButton touchup_gomb = new JButton("Start");
		touchup_gomb.addActionListener(new Touc_Up());
		touchup_gomb.setBounds(497, 633, 89, 23);
		add(touchup_gomb);

	}
	
	 public class DateLabelFormatter extends AbstractFormatter {

	        private String datePattern = "yyyy.MM.dd";
	        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	        @Override
	        public Object stringToValue(String text) throws ParseException {
	            return dateFormatter.parseObject(text);
	        }

	        @Override
	        public String valueToString(Object value) throws ParseException {
	            if (value != null) {
	                Calendar cal = (Calendar) value;
	                return dateFormatter.format(cal.getTime());
	            }
	            //System.out.println(datePicker.getJFormattedTextField().getText());
	            return "";
	        }       

	    }
	
	class Projekt_lekerdezo implements ActionListener																						//mentés gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			{
			    if(datum_tol.getJFormattedTextField().getText().equals(""))
			    {
			        JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-tól dátumot!", "Hiba üzenet", 2);
			    }
			    else if(datum_ig.getJFormattedTextField().getText().equals(""))
                {
			        JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-ig dátumot!", "Hiba üzenet", 2);
                }
			    else
                {    
    			    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    			    String querry = "call qualitydb.projekt_lekerdezo(?,?,?,?)";                                                                 //tárolt eljárás Stringje
                    SQL lekerdezo = new SQL();                                                                                                  //példányosítás                
    			    if(projekt_box.getSelectedItem().equals("-"))
    			    {
    			        if(hiba_box.getSelectedItem().equals("-"))
    	                {
    			            lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", "%", projekt);	//függvénymeghívása a paraméterekkel
    	                }
    			        else
    	                {
    			            lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), "%", projekt);   //függvénymeghívása a paraméterekkel
    	                }
    			    }
    			    else
    			    {
    			        if(hiba_box.getSelectedItem().equals("-"))
                        {
    			            lekerdezo.lekerdez_projekt_osszegez(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", String.valueOf(projekt_box.getSelectedItem()), projekt);    //függvénymeghívása a paraméterekkel
                        }
                        else
                        {
                            lekerdezo.lekerdez_projekt_osszegez(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), String.valueOf(projekt_box.getSelectedItem()), projekt);    //függvénymeghívása a paraméterekkel
                        }
                                           
                        
    			    }
    			    Foablak.frame.setCursor(null);
    			    JOptionPane.showMessageDialog(null, "Mentve az asztalra ProjektPPM.xlsx néven", "Info", 1);
                }
			}
			catch (Exception e1) 
	        {
			    e1.printStackTrace();
	            String hibauzenet2 = e1.toString();
	            Email hibakuldes = new Email();
	            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
	            JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
	        }
		 }
      
	}
	
	class Projekt_mutat implements ActionListener                                                                                      //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                if(datum_tol.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-tól dátumot!", "Hiba üzenet", 2);
                }
                else if(datum_ig.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-ig dátumot!", "Hiba üzenet", 2);
                }
                else
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    String querry = "call qualitydb.projekt_lekerdezo(?,?,?,?)";                                                                 //tárolt eljárás Stringje
                    SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                    int sumfelajanlott = 0;
                    int summinta = 0;
                    int sumhiba = 0;
                    float ppm = 0;
                    if(projekt_box.getSelectedItem().equals("-"))
                    {
                        if(hiba_box.getSelectedItem().equals("-"))
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", "%");   //függvénymeghívása a paraméterekkel
                        }
                        else
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), "%");   //függvénymeghívása a paraméterekkel
                        }               
                    }
                    else
                    {
                        if(hiba_box.getSelectedItem().equals("-"))
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", String.valueOf(projekt_box.getSelectedItem()));    //függvénymeghívása a paraméterekkel
                        }
                        else
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), String.valueOf(projekt_box.getSelectedItem()));    //függvénymeghívása a paraméterekkel
                        }
                        for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                        {
                           sumfelajanlott += Integer.parseInt(table.getValueAt(szamlalo, 3).toString());
                        }
                        for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                        {
                           summinta += Integer.parseInt(table.getValueAt(szamlalo, 4).toString());
                        }
                        for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                        {
                           sumhiba += Integer.parseInt(table.getValueAt(szamlalo, 5).toString());
                        }
                        ppm = ((float)sumhiba/(float)sumfelajanlott)* (float)1000000;
                        //String[] egesz = String.valueOf(ppm).split("\\.");
                        Vector<Object> vector = new Vector<Object>();
                        vector.add("");
                        vector.add("");
                        vector.add("Sum:");
                        vector.add(sumfelajanlott);
                        vector.add(summinta);
                        vector.add(sumhiba);
                        vector.add(ppm);
                        //System.out.println(sumfelajanlott + " "+ summinta +" "+ sumhiba + " " + egesz[0]);
                        SQL.alapmodell.addRow(vector);
                        table.setModel(SQL.alapmodell);
                    }
                    Foablak.frame.setCursor(null);
                }
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
      
    }
	
	class Termek_lekerdezo implements ActionListener																						//termék gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			{
			    if(datum_tol.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-tól dátumot!", "Hiba üzenet", 2);
                }
                else if(datum_ig.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-ig dátumot!", "Hiba üzenet", 2);
                }
                else
                {
    			    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));			    
    				String querry = "call qualitydb.termek_lekerdezo(?,?,?,?)";																	//tárolt eljárás Stringje
    				SQL lekerdezo = new SQL();																									//példányosítás
    				 if(projekt_box.getSelectedItem().equals("-"))
    	             {
    				     if(hiba_box.getSelectedItem().equals("-"))
    	                 {
    				        lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", "%", termek);  //függvénymeghívása a paraméterekkel
    	                 }
    	                 else
    	                 {
    	                    lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), "%", termek);  //függvénymeghívása a paraméterekkel
    	                 }				     
    	             }
    				 else
    				 {
    				     if(hiba_box.getSelectedItem().equals("-"))
    				     {
    				         lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", String.valueOf(projekt_box.getSelectedItem()), termek);  //függvénymeghívása a paraméterekkel
    	                 }
    	                 else
    	                 {
    	                     lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), String.valueOf(projekt_box.getSelectedItem()), termek);  //függvénymeghívása a paraméterekkel
    	                 }       				     
    				 }
    				Foablak.frame.setCursor(null);
    				JOptionPane.showMessageDialog(null, "Mentve az asztalra TermékPPM.xlsx néven", "Info", 1);
                }
			}
			catch (Exception e1) 
	        {
			    e1.printStackTrace();
	            String hibauzenet2 = e1.toString();
	            Email hibakuldes = new Email();
	            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
	            JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
	        }
		 }
	}
	
	class Termek_mutat implements ActionListener                                                                                       //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                if(datum_tol.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-tól dátumot!", "Hiba üzenet", 2);
                }
                else if(datum_ig.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-ig dátumot!", "Hiba üzenet", 2);
                }
                else
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    String querry = "call qualitydb.termek_lekerdezo(?,?,?,?)";                                                                 //tárolt eljárás Stringje
                    SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                    if(projekt_box.getSelectedItem().equals("-"))
                    {
                        if(hiba_box.getSelectedItem().equals("-"))
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", "%");  //függvénymeghívása a paraméterekkel
                        }
                        else
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), "%");  //függvénymeghívása a paraméterekkel
                        }                   
                    }
                    else
                    {
                        if(hiba_box.getSelectedItem().equals("-"))
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", String.valueOf(projekt_box.getSelectedItem()));  //függvénymeghívása a paraméterekkel
                        }
                        else
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), String.valueOf(projekt_box.getSelectedItem()));  //függvénymeghívása a paraméterekkel
                        }                           
                    }                 
                    Foablak.frame.setCursor(null);
                }
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
            }
         }
    }
	
	class Hiba_lekerdezo implements ActionListener																							//hiba gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			{
			    if(datum_tol.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-tól dátumot!", "Hiba üzenet", 2);
                }
                else if(datum_ig.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-ig dátumot!", "Hiba üzenet", 2);
                }
                else
                {
    			    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));			    
    			    String querry = "call qualitydb.hibak_lekerdezo(?,?,?,?)";                                                                   //tárolt eljárás stringje
                    SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                    if(projekt_box.getSelectedItem().equals("-"))
                    {
                        if(hiba_box.getSelectedItem().equals("-"))
                        {
                            lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", "%", hiba); //függvénymeghívása a paraméterekkel
                        }
                        else
                        {
                            lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), "%", hiba); //függvénymeghívása a paraméterekkel
                        }                   
                    }
                    else
                    {
                        if(hiba_box.getSelectedItem().equals("-"))
                        {
                            lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", String.valueOf(projekt_box.getSelectedItem()), hiba);   //függvénymeghívása a paraméterekkel
                        }
                        else
                        {
                            lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), String.valueOf(projekt_box.getSelectedItem()), hiba);   //függvénymeghívása a paraméterekkel
                        }                           
                    }                 			    
    				Foablak.frame.setCursor(null);
    				JOptionPane.showMessageDialog(null, "Mentve az asztalra Hibák_adatai.xlsx néven", "Info", 1);
                }
			}
			catch (Exception e1) 
	        {
			    e1.printStackTrace();
	            String hibauzenet2 = e1.toString();
	            Email hibakuldes = new Email();
	            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
	            JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
	        }
		 }
	}
	
	class Hiba_mutat implements ActionListener                                                                                         //hiba gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                if(datum_tol.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-tól dátumot!", "Hiba üzenet", 2);
                }
                else if(datum_ig.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-ig dátumot!", "Hiba üzenet", 2);
                }
                else
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    String querry = "call qualitydb.hibak_lekerdezo(?,?,?,?)";                                                                   //tárolt eljárás stringje
                    SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                    if(projekt_box.getSelectedItem().equals("-"))
                    {
                        if(hiba_box.getSelectedItem().equals("-"))
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", "%");   //függvénymeghívása a paraméterekkel
                        }
                        else
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), "%");   //függvénymeghívása a paraméterekkel
                        }                   
                    }
                    else
                    {
                        if(hiba_box.getSelectedItem().equals("-"))
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", String.valueOf(projekt_box.getSelectedItem()));   //függvénymeghívása a paraméterekkel
                        }
                        else
                        {
                            lekerdezo.lekerdez_mutat(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), String.valueOf(projekt_box.getSelectedItem()));   //függvénymeghívása a paraméterekkel
                        }                           
                    }                   
                    if(projekt_box.getSelectedItem().equals("-"))
                    {               
                        
                    }
                    else
                    {
                        
                    }
                    Foablak.frame.setCursor(null);
                }
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
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
                    if(datum_tol.getJFormattedTextField().getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-tól dátumot!", "Hiba üzenet", 2);
                    }
                    else if(datum_ig.getJFormattedTextField().getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-ig dátumot!", "Hiba üzenet", 2);
                    }
                    else
                    {
                        Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        String querry = "call qualitydb.projekt_lekerdezo(?,?,?,?)";                                                                 //tárolt eljárás Stringje
                        SQL lekerdezo = new SQL();                                                                                                  //példányosítás                    
                        if(projekt_box.getSelectedItem().equals("-"))
                        {
                            if(hiba_box.getSelectedItem().equals("-"))
                            {
                                lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", "%", projekt); //függvénymeghívása a paraméterekkel
                            }
                            else
                            {
                                lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), "%", projekt);   //függvénymeghívása a paraméterekkel
                            }
                        }
                        else
                        {
                            if(hiba_box.getSelectedItem().equals("-"))
                            {
                                lekerdezo.lekerdez_projekt_osszegez(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", String.valueOf(projekt_box.getSelectedItem()), projekt);    //függvénymeghívása a paraméterekkel
                            }
                            else
                            {
                                lekerdezo.lekerdez_projekt_osszegez(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), String.valueOf(projekt_box.getSelectedItem()), projekt);    //függvénymeghívása a paraméterekkel
                            }
                                               
                            
                        }
                        Foablak.frame.setCursor(null);
                        JOptionPane.showMessageDialog(null, "Mentve az asztalra ProjektPPM.xlsx néven", "Info", 1);
                    }
                }
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
                    JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
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
                    if(datum_tol.getJFormattedTextField().getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-tól dátumot!", "Hiba üzenet", 2);
                    }
                    else if(datum_ig.getJFormattedTextField().getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-ig dátumot!", "Hiba üzenet", 2);
                    }
                    else
                    {
                        Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                    
                        String querry = "call qualitydb.termek_lekerdezo(?,?,?,?)";                                                                 //tárolt eljárás Stringje
                        SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                         if(projekt_box.getSelectedItem().equals("-"))
                         {
                             if(hiba_box.getSelectedItem().equals("-"))
                             {
                                lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", "%", termek);  //függvénymeghívása a paraméterekkel
                             }
                             else
                             {
                                lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), "%", termek);  //függvénymeghívása a paraméterekkel
                             }                   
                         }
                         else
                         {
                             if(hiba_box.getSelectedItem().equals("-"))
                             {
                                 lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", String.valueOf(projekt_box.getSelectedItem()), termek);  //függvénymeghívása a paraméterekkel
                             }
                             else
                             {
                                 lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), String.valueOf(projekt_box.getSelectedItem()), termek);  //függvénymeghívása a paraméterekkel
                             }                           
                         }
                        Foablak.frame.setCursor(null);
                        JOptionPane.showMessageDialog(null, "Mentve az asztalra TermékPPM.xlsx néven", "Info", 1);
                    }
                }
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
                    JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
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
                    if(datum_tol.getJFormattedTextField().getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-tól dátumot!", "Hiba üzenet", 2);
                    }
                    else if(datum_ig.getJFormattedTextField().getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-ig dátumot!", "Hiba üzenet", 2);
                    }
                    else
                    {
                        Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                    
                        String querry = "call qualitydb.hibak_lekerdezo(?,?,?,?)";                                                                   //tárolt eljárás stringje
                        SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                        if(projekt_box.getSelectedItem().equals("-"))
                        {
                            if(hiba_box.getSelectedItem().equals("-"))
                            {
                                lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", "%", hiba); //függvénymeghívása a paraméterekkel
                            }
                            else
                            {
                                lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), "%", hiba); //függvénymeghívása a paraméterekkel
                            }                   
                        }
                        else
                        {
                            if(hiba_box.getSelectedItem().equals("-"))
                            {
                                lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), "%", String.valueOf(projekt_box.getSelectedItem()), hiba);   //függvénymeghívása a paraméterekkel
                            }
                            else
                            {
                                lekerdezo.lekerdez_projekt(querry, datum_tol.getJFormattedTextField().getText(), datum_ig.getJFormattedTextField().getText(), String.valueOf(hiba_box.getSelectedItem()), String.valueOf(projekt_box.getSelectedItem()), hiba);   //függvénymeghívása a paraméterekkel
                            }                           
                        }                               
                        Foablak.frame.setCursor(null);
                        JOptionPane.showMessageDialog(null, "Mentve az asztalra Hibák_adatai.xlsx néven", "Info", 1);
                    }
                }
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
                    JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
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
	
	class Touc_Up implements ActionListener                                                                                         //hiba gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {               
                if(datum_tol.getJFormattedTextField().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátom-tól dátumot!", "Hiba üzenet", 2);
                }                
                else
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Connection conn = null;
                    Statement stmt = null;
                    DataTable datatable = new DataTable();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                    stmt = (Statement) conn.createStatement();
                    String sajat = "SELECT Vevo, datum, muszak, Ellenor_neve as 'Dolgozó száma'\n"
                            + "FROM qualitydb.Gyartasi_adatok \n"
                            + "WHERE\n"
                            + "    3 = 3 -- AND Vevo = 'Loxone robot'\n"
                            + "    and Hibagyujtes_helye = 'Kézi TouchUp'\n"
                            + "    and datum = '"+ datum_tol.getJFormattedTextField().getText() +"'\n"
                            + "    group by Vevo, datum, muszak, Ellenor_neve\n"
                            + "    order by muszak asc";
                    stmt.execute(sajat);
                    ResultSet rs = stmt.getResultSet();
                    String kodok = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Touch up számok.xlsx";
                    if(rs != null)
                    {
                        Workbook workbook = new Workbook();
                        workbook.setVersion(ExcelVersion.Version2016);
                        JdbcAdapter jdbcAdapter = new JdbcAdapter();
                        jdbcAdapter.fillDataTable(datatable, rs);
                
                        //Get the first worksheet
                        Worksheet sheet = workbook.getWorksheets().get(0);
                        sheet.insertDataTable(datatable, true, 1, 1);
                        
                        Workbook workbook2 = new Workbook();
                        workbook2.loadFromFile(kodok);
                        Worksheet sheet2 = workbook2.getWorksheets().get(0);
                        sheet.getRange().get("E" + 1).setText("Dolgozó neve");
                        for(int szamlalo = 2; szamlalo < sheet.getLastRow() +1; szamlalo++)
                        {
                            try
                            {
                                Integer.parseInt(sheet.getRange().get("D" + szamlalo).getText());
                                for(int szamlalo2 = 1; szamlalo2 < sheet2.getLastRow() +1; szamlalo2++)
                                {
                                    if(sheet.getRange().get("D" + szamlalo).getText().equals(sheet2.getRange().get("A" + szamlalo2).getNumberText()) || sheet.getRange().get("D" + szamlalo).getText().equals(sheet2.getRange().get("C" + szamlalo2).getNumberText()))
                                    {
                                        sheet.getRange().get("E" + szamlalo).setText(sheet2.getRange().get("B" + szamlalo2).getText());
                                        System.out.println("Találat");
                                    }
                                    //System.out.println(sheet2.getRange().get("A" + szamlalo2).getNumberText());
                                    //System.out.println(sheet2.getRange().get("B" + szamlalo2).getText());
                                }
                            }
                            catch (Exception e2) 
                            {
                                System.out.println("Kivétel keletkezett");
                                sheet.getRange().get("E" + szamlalo).setText(sheet.getRange().get("D" + szamlalo).getText());
                                sheet.getRange().get("D" + szamlalo).setText("");
                            }
                        }
                        
                        sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
                        sheet.getAllocatedRange().autoFitColumns();
                        sheet.getAllocatedRange().autoFitRows();
                        
                        sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                        String menteshelye = System.getProperty("user.home") + "\\Desktop\\Touch Up lapok.xlsx";
                        workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                        rs.close();
                        stmt.close();
                        conn.close();    
                        FileInputStream fileStream = new FileInputStream(menteshelye);
                        try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook3.getNumberOfSheets()-1; i>0 ;i--)
                            {    
                                workbook3.removeSheetAt(i); 
                            }      
                            FileOutputStream output = new FileOutputStream(menteshelye);
                            workbook3.write(output);
                            output.close();
                        }
                        
                        File excel = new File(menteshelye);
                        
                        Email uzenet = new Email();              //"nagy.balint@veas.videoton.hu, molnar.jozsef@veas.videoton.hu,csader.zsolt@veas.videoton.hu"
                        String szoveg = "Sziasztok!\t\n\nA csatolmányban találjátok mely operátorok adtak le Touch Up papírt.\t\n\n"                           
                                         +"Üdvözlettel: EASQAS program";
                        
                        //magyarne.eva@veas.videoton.hu,kozar.eva@veas.videoton.hu,szeghalmi.zsolt@veas.videoton.hu,kutassy.jozsef@veas.videoton.hu,frey.janos@veas.videoton.hu,godri.zita@veas.videoton.hu,szidor.olimer@veas.videoton.hu
                        uzenet.mindenes_email_csatolmannyal_cc("kovacs.zoltan@veas.videoton.hu", 
                                "",     //hidi.szilard@veas.videoton.hu,tinka.melinda@veas.videoton.hu,kovacs.zoltan@veas.videoton.hu
                                "Leadott Touch Up lapok "+ datum_tol.getJFormattedTextField().getText() ,szoveg,excel);
                        excel.delete();
                        Foablak.frame.setCursor(null);
                        JOptionPane.showMessageDialog(null, "Email elküldve", "Info", 1);
                    }
                }
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
            }
         }
    }
}
