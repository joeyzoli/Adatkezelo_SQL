import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Vevoireklamacio_d0 extends JPanel {
    private JTextField beszallito_mezo;
    private JTextField vevorek_mezo;
    private JTextField veasrek_mezo;
    //private JTextField frissites_mezo;
    private JTextField email_mezo;
    private JTextField email2_mezo;
    private JTextField rma_mezo;
    private JTextField telefonszam_mezo;
    private JTextField telefonszam2_mezo;
    private JTextField visszakuldes_mezo;
    private JLabel lblNewLabel_15;
    static JTable table;
    static DefaultTableModel modell;
    private JSeparator separator;
    static JComboBox<String> vevo_box;
    private ComboBox combobox_tomb = new ComboBox();
    static JComboBox<String> tipus_box;
    private JComboBox<String> felelos_box;
    //private String excelhelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Projekt- mérnök.xlsx";
    private String excelhelye = "\\\\172.20.22.7\\kozos\\telefonkönyv\\Telefonlista_VEAS_új.xls";
    private String excelhelye2 = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\CCS2.xlsx";
    private Kivalaszt valaszto = new Kivalaszt();
    private JTextField felelos_mezo;
    private SQA_SQL cikkszamok = new SQA_SQL();
    private String sql;
    private Workbook workbook = new Workbook();
    static JDatePickerImpl ertesitve;
    private UtilDateModel model;
    static JTable table_1;
    private DefaultTableModel modell2;
    
    private JDatePickerImpl frissites;
    private UtilDateModel model2;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d0() {
        setLayout(null);
        
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Ma");
        p.put("text.month", "Hónap");
        p.put("text.year", "Év");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        ertesitve = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        
        model2 = new UtilDateModel();
        Properties p2 = new Properties();
        p2.put("text.today", "Ma");
        p2.put("text.month", "Hónap");
        p2.put("text.year", "Év");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
        frissites = new JDatePickerImpl(datePanel2, new DateLabelFormatter2());
        
        JLabel lblNewLabel = new JLabel("Vevő");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(214, 57, 46, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Beszállító");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(537, 57, 71, 14);
        add(lblNewLabel_1);
        
        beszallito_mezo = new JTextField();
        beszallito_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        beszallito_mezo.setText("Videoton EAS Kft.");
        beszallito_mezo.setBounds(618, 54, 162, 20);
        add(beszallito_mezo);
        beszallito_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Értesítés dátuma");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(900, 57, 102, 14);
        add(lblNewLabel_2);
        

        ertesitve.setBounds(1012, 54, 120, 20);
        add(ertesitve);
        
        JLabel lblNewLabel_3 = new JLabel("Vevői reklamációs szám");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(119, 105, 141, 14);
        add(lblNewLabel_3);
        
        vevorek_mezo = new JTextField();
        vevorek_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        vevorek_mezo.setBounds(274, 102, 86, 20);
        add(vevorek_mezo);
        vevorek_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("VEAS reklamációs szám");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(462, 105, 146, 14);
        add(lblNewLabel_4);
        
        veasrek_mezo = new JTextField();
        veasrek_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        veasrek_mezo.setBounds(618, 102, 86, 20);
        //veasrek_mezo.setText(Vevoireklamacio_fejlec.id_mezo.getText());
        add(veasrek_mezo);
        veasrek_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Utolsó frissítés");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(916, 105, 86, 14);
        add(lblNewLabel_5);
        
        //frissites = new JDatePickerImpl();
        //frissites_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        frissites.setBounds(1012, 102, 120, 20);
        add(frissites);
        //frissites_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Felelős");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setBounds(214, 163, 46, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Felelős");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_7.setBounds(562, 163, 46, 14);
        add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Típus");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_8.setBounds(956, 163, 46, 14);
        add(lblNewLabel_8);
        
        email_mezo = new JTextField();
        email_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        email_mezo.setBounds(274, 208, 254, 20);
        add(email_mezo);
        email_mezo.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("E-mail");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_9.setBounds(189, 211, 71, 14);
        add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("E-mail");
        lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_10.setBounds(562, 211, 46, 14);
        add(lblNewLabel_10);
        
        email2_mezo = new JTextField();
        email2_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        email2_mezo.setBounds(618, 208, 254, 20);
        add(email2_mezo);
        email2_mezo.setColumns(10);
        
        rma_mezo = new JTextField();
        rma_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        rma_mezo.setBounds(1012, 208, 86, 20);
        add(rma_mezo);
        rma_mezo.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("RMA szám");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_11.setBounds(931, 211, 71, 14);
        add(lblNewLabel_11);
        
        JLabel lblNewLabel_12 = new JLabel("Telefonszám");
        lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_12.setBounds(158, 264, 102, 14);
        add(lblNewLabel_12);
        
        telefonszam_mezo = new JTextField();
        telefonszam_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        telefonszam_mezo.setBounds(274, 261, 162, 20);
        add(telefonszam_mezo);
        telefonszam_mezo.setColumns(10);
        
        telefonszam2_mezo = new JTextField();
        telefonszam2_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        telefonszam2_mezo.setBounds(618, 261, 162, 20);
        add(telefonszam2_mezo);
        telefonszam2_mezo.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("Telefonszám");
        lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_13.setBounds(522, 264, 86, 14);
        add(lblNewLabel_13);
        
        visszakuldes_mezo = new JTextField();
        visszakuldes_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        visszakuldes_mezo.setBounds(1012, 261, 86, 20);
        add(visszakuldes_mezo);
        visszakuldes_mezo.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Visszaküldés dátuma");
        lblNewLabel_14.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_14.setBounds(873, 264, 129, 14);
        add(lblNewLabel_14);
        
        setBackground(Foablak.hatter_szine);
        
        lblNewLabel_15 = new JLabel("Feltöltött fájlok");
        lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_15.setBounds(142, 373, 118, 14);
        add(lblNewLabel_15);
        
        table = new JTable(){
            public Class<?> getColumnClass(int column) {
                return (column == 0) ? Icon.class : Object.class;
              }
            };;
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    if (me.getClickCount() == 2) {     // to detect doble click events
                       JTable target = (JTable)me.getSource();
                       int row = target.getSelectedRow(); // select a row 
                       try
                       {
                           String[] fajl = Vevoireklamacio_d2.fajlok.get(row).split(";");
                           Desktop.getDesktop().open(new File(fajl[1]));                            //megnyitja az kiválaszott fájlt
                       }
                       catch (Exception e1) 
                       {              
                           e1.printStackTrace();
                           String hibauzenet = e1.toString();
                           Email hibakuldes = new Email();
                           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                       }
                    }    
                 }
              });  
                 
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Fájl tipusa", "Fájl neve"});
        table.setModel(modell);
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(274, 373, 577, 132);        
        add(gorgeto);
        
        separator = new JSeparator();
        separator.setBounds(53, 314, 1312, 46);
        add(separator);
        
        vevo_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));                                     //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        vevo_box.addActionListener(valaszto);
        vevo_box.setBounds(270, 53, 215, 22);
        add(vevo_box);
        
        sql = "select part_no || '  ' || REVISION_TEXT || '  ' || ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO) as cikkszamok\r\n"
                + "from ifsapp.PART_REVISION\r\n"
                + "where 3 = 3\r\n"
                + "and ifsapp.inventory_part_api.Get_Part_Product_Code(contract,part_no) = '1'\r\n"
                + "-- and ifsapp.inventory_part_api.Get_Second_Commodity(contract, Part_no) = 'VLOXN'\r\n"
                + "group by part_no, REVISION_TEXT, ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO)\r\n"
                + "ORDER by part_no";
        String[] tomb = {"Válassz vevőt"};
        tipus_box = new JComboBox<String>(tomb);                             //cikkszamok.tombvissza(sql)      //combobox_tomb.getCombobox(ComboBox.vevoi_cikk)
        tipus_box.setBounds(1012, 159, 353, 22);
        tipus_box.addActionListener(new Valtozas());
        tipus_box.addActionListener(new Hozzaad());
        add(tipus_box);
        
        String[] nevsor = {"-","Borbély Szilvia","Mile József","Reznyák Norbert","Szatmári Edina", "Tisler Péter"};
        
        felelos_box = new JComboBox<String>(nevsor);                                                                                                         //nevsor
        felelos_box.setBounds(618, 159, 162, 22);
        felelos_box.addActionListener(new Email_keres2());
        add(felelos_box);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(274, 160, 162, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Kiválasztott fájl törlése");
        lblNewLabel_16.setBounds(1012, 373, 155, 14);
        add(lblNewLabel_16);
        
        JButton fajltorles_gomb = new JButton("Törlés");
        fajltorles_gomb.addActionListener(new Fajl_torles());
        fajltorles_gomb.setBounds(1022, 398, 89, 23);
        add(fajltorles_gomb);
        
        modell2 = new DefaultTableModel();
        modell2.setColumnIdentifiers(new Object[]{"Reklamált típus"});
        table_1 = new JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
          };;
        table_1.setModel(modell2);
        table_1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect doble click events
                   JTable target = (JTable)me.getSource();
                   int row = target.getSelectedRow(); // select a row 
                   try
                   {
                       modell2.removeRow(row);
                   }
                   catch (Exception e1) 
                   {              
                       e1.printStackTrace();
                       String hibauzenet = e1.toString();
                       Email hibakuldes = new Email();
                       hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                       JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                   }
                }    
             }
          });
        JScrollPane gorgeto2 = new JScrollPane(table_1);
        gorgeto2.setBounds(1120, 208, 245, 95);
        add(gorgeto2);
        new ArrayList<String>();

    }
    
    public void veasid()
    {
        veasrek_mezo.setText(Vevoireklamacio_fejlec.id_mezo.getText());
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
                Vevoireklamacio_fejlec.ertesites_cimke.setText(dateFormatter.format(cal.getTime()));                   
                String input = dateFormatter.format(cal.getTime());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                LocalDate date = LocalDate.parse(input, formatter);
                // Increment the date by one day
                LocalDate newDate = date.plusDays(1);
                // Format the new date as a string
                String output = newDate.format(formatter);
                Vevoireklamacio_fejlec.qr_cimke.setText(output);
                newDate = date.plusDays(2);
                output = newDate.format(formatter);
                Vevoireklamacio_fejlec.d3_cimke.setText(output);
                newDate = date.plusDays(14);
                output = newDate.format(formatter);
                Vevoireklamacio_fejlec.d5_cimke.setText(output);
                newDate = date.plusDays(30);
                output = newDate.format(formatter);
                Vevoireklamacio_fejlec.lezaras_cimke.setText(output);                    
                
                Vevoireklamacio_fejlec.ertesitve = Color.GREEN;
                Vevoireklamacio_fejlec.qr = Color.YELLOW;
                Vevoireklamacio_fejlec.d3 = Color.gray;
                Vevoireklamacio_fejlec.d5 = Color.gray;
                Vevoireklamacio_fejlec.lezaras = Color.GRAY;
                Vevoireklamacio_V2.d3.hatarido();
                Vevoireklamacio_V2.d5.hatarido();
                Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
                repaint();
                return dateFormatter.format(cal.getTime());
            }
            //System.out.println(datePicker.getJFormattedTextField().getText());
            return "";
        }

    }
    
    public class DateLabelFormatter2 extends AbstractFormatter {

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
                Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
                return dateFormatter.format(cal.getTime());
            }
            //System.out.println(datePicker.getJFormattedTextField().getText());
            return "";
        }

    }
    /*
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    Vevoireklamacio_fejlec.ertesites_cimke.setText(ertesites_mezo.getText());                   
                    String input = ertesites_mezo.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                    LocalDate date = LocalDate.parse(input, formatter);
                    // Increment the date by one day
                    LocalDate newDate = date.plusDays(1);
                    // Format the new date as a string
                    String output = newDate.format(formatter);
                    Vevoireklamacio_fejlec.qr_cimke.setText(output);
                    newDate = date.plusDays(2);
                    output = newDate.format(formatter);
                    Vevoireklamacio_fejlec.d3_cimke.setText(output);
                    newDate = date.plusDays(14);
                    output = newDate.format(formatter);
                    Vevoireklamacio_fejlec.d5_cimke.setText(output);
                    newDate = date.plusDays(30);
                    output = newDate.format(formatter);
                    Vevoireklamacio_fejlec.lezaras_cimke.setText(output);                    
                    
                    Vevoireklamacio_fejlec.ertesitve = Color.GREEN;
                    Vevoireklamacio_fejlec.qr = Color.YELLOW;
                    Vevoireklamacio_fejlec.d3 = Color.gray;
                    Vevoireklamacio_fejlec.d5 = Color.gray;
                    Vevoireklamacio_fejlec.lezaras = Color.GRAY;
                    Vevoireklamacio_V2.d3.hatarido();
                    Vevoireklamacio_V2.d5.hatarido();
                    repaint();
                }
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
    }*/
    
    public void mentes()
    {
        try 
        {
            SQA_SQL ment = new SQA_SQL();
            String sql = "select * from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            String[] letezik = ment.tombvissza_sajat(sql);
            String cikkszamok = "";
            for(int szamlalo = 0; szamlalo < table_1.getRowCount();szamlalo++)
            {
                cikkszamok += table_1.getValueAt(szamlalo, 0).toString() +";";
            }
            if(letezik.length > 0)
            {
                sql = "update qualitydb.Vevoireklamacio_alap set mi = '"+ String.valueOf(Vevoireklamacio_fejlec.fajta_box.getSelectedItem()) +"', Vevo = '"+ String.valueOf(vevo_box.getSelectedItem()) +"', "
                        + "Beszallito = '"+ beszallito_mezo.getText() +"', Ertesites_datuma = '"+ ertesitve.getJFormattedTextField().getText() +"', Vevoi_szam = '"+ vevorek_mezo.getText() +"',"
                        + "Utolso_frissites ='"+ frissites.getJFormattedTextField().getText() +"', Felelos = '"+ felelos_mezo.getText() +";"+ String.valueOf(felelos_box.getSelectedItem()) +"', Tipus = '"+ cikkszamok +"',"
                        + "Email = '"+ email_mezo.getText() +";"+ email2_mezo.getText() +"', RMA_szam = '"+ rma_mezo.getText() +"', Telefon = '"+ telefonszam_mezo.getText() +";"+ telefonszam2_mezo.getText() +"',"
                        + "Visszakuldes_datuma = '"+ visszakuldes_mezo.getText() +"' where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";                
            }
            else
            {
                sql = "insert into qualitydb.Vevoireklamacio_alap (mi,Vevo,Beszallito,Ertesites_datuma,Vevoi_szam,Utolso_frissites,Felelos,Tipus,Email,RMA_szam,Telefon,Visszakuldes_datuma)  "
                        + "Values('"+ String.valueOf(Vevoireklamacio_fejlec.fajta_box.getSelectedItem()) +"','"+ String.valueOf(vevo_box.getSelectedItem()) +"',"
                        + "'"+ beszallito_mezo.getText() +"','"+ ertesitve.getJFormattedTextField().getText() +"','"+ vevorek_mezo.getText() +"',"
                        + "'"+ frissites.getJFormattedTextField().getText() +"','"+ felelos_mezo.getText() +";"+ String.valueOf(felelos_box.getSelectedItem()) +"','"+ cikkszamok +"',"       //String.valueOf(tipus_box.getSelectedItem())
                        + "'"+ email_mezo.getText() +";"+ email2_mezo.getText() +"','"+ rma_mezo.getText() +"','"+ telefonszam_mezo.getText() +";"+ telefonszam2_mezo.getText() +"',"
                        + "'"+ visszakuldes_mezo.getText() +"')";
            }
            ment.mindenes(sql);
            String szin1 = String.valueOf(Vevoireklamacio_fejlec.ertesitve.getRGB());
            String szin2 = String.valueOf(Vevoireklamacio_fejlec.qr.getRGB());
            String szin3 = String.valueOf(Vevoireklamacio_fejlec.d3.getRGB());
            String szin4 = String.valueOf(Vevoireklamacio_fejlec.d5.getRGB());
            String szin5 = String.valueOf(Vevoireklamacio_fejlec.lezaras.getRGB());
            /*int rowCount = modell2.getRowCount();           
            for (int i = rowCount - 1; i > -1; i--) 
            {
              modell2.removeRow(i);
            }*/
            sql = "update qualitydb.Vevoireklamacio_alap set Szinek = '"+ szin1+";"+ szin2+";"+ szin3+";"+ szin4+";"+ szin5+"' where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            ment.mindenes(sql);
            
            int rowCount = modell.getRowCount();
            
            for (int i = rowCount - 1; i > -1; i--) 
            {
              modell.removeRow(i);
            }
            table.setModel(modell);
        } 
        catch (Exception e1) 
        {              
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        }
    }
    
    public void visszatolt()
    {
        Connection conn = null;
        Statement stmt = null;        
        try 
        {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            stmt = (Statement) conn.createStatement();
            String sql = "select * from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            if(rs.next())
            {               
                vevo_box.removeActionListener(valaszto);
                Vevoireklamacio_fejlec.fajta_box.setSelectedItem(rs.getString(2));
                /*sql = "select part_no || '  ' || REVISION_TEXT || '  ' || ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO) as cikkszamok \r\n"
                        + "from ifsapp.PART_REVISION\r\n"
                        + "where 3 = 3\r\n"
                        + "and ifsapp.inventory_part_api.Get_Part_Product_Code(contract,part_no) = '1'\r\n"
                        + "-- and ifsapp.inventory_part_api.Get_Second_Commodity(contract, Part_no) = 'VLOXN'\r\n"
                        + "group by part_no, REVISION_TEXT, ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO)\r\n"
                        + "ORDER by part_no";
                
                DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>(cikkszamok.tombvissza(sql));
                
                tipus_box.setModel(model2);*/
                vevo_box.setSelectedItem(rs.getString(3));
                beszallito_mezo.setText(rs.getString(4));
                //ertesites_mezo.setText(rs.getString(5));
                Date date3 = null;
                String dateValue = rs.getString(5).replace("-", ".");
                try {
                    date3 = new SimpleDateFormat("yyyy.MM.dd").parse(dateValue);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                model.setValue(date3);
                if(rs.getString(5).equals("")){}
                else
                {
                    Vevoireklamacio_fejlec.ertesites_cimke.setText(rs.getString(5));
                    Vevoireklamacio_fejlec.ertesitve = Color.GREEN;
                    String[] szinek = rs.getString(50).split(";");
                    Color szin2 = new Color(Integer.parseInt(szinek[1]));
                    Color szin3 = new Color(Integer.parseInt(szinek[2]));
                    Color szin4 = new Color(Integer.parseInt(szinek[3]));
                    Color szin5 = new Color(Integer.parseInt(szinek[4]));
                    Vevoireklamacio_fejlec.qr = szin2;
                    Vevoireklamacio_fejlec.d3 = szin3;
                    Vevoireklamacio_fejlec.d5 = szin4;
                    Vevoireklamacio_fejlec.lezaras = szin5;
                    
                    String input = rs.getString(5);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                    SimpleDateFormat rovid = new SimpleDateFormat("yyyy.MM.dd");
                    Date ma = new Date();
                    ma = rovid.parse(rovid.format(ma));
                    LocalDate date = LocalDate.parse(input, formatter);
                    // Increment the date by one day
                    LocalDate newDate = date.plusDays(1);
                    // Format the new date as a string
                    String output = newDate.format(formatter);
                    Vevoireklamacio_fejlec.qr_cimke.setText(output);
                    Date hatarido = rovid.parse(output);
                    if(hatarido.compareTo(ma) >= 0){}
                    else
                    {
                        if(Vevoireklamacio_fejlec.qr.getRGB() == Color.GREEN.getRGB()) {}
                        else
                        {
                            Vevoireklamacio_fejlec.qr = Color.RED;
                        }                   
                    }
                    newDate = date.plusDays(2);
                    output = newDate.format(formatter);
                    Vevoireklamacio_fejlec.d3_cimke.setText(output);
                    hatarido = rovid.parse(output);
                    if(hatarido.compareTo(ma) >= 0){}
                    else
                    {
                        if(Vevoireklamacio_fejlec.d3.getRGB() == Color.GREEN.getRGB()) {}
                        else
                        {
                            Vevoireklamacio_fejlec.d3 = Color.RED;
                        }
                    }
                    newDate = date.plusDays(14);
                    output = newDate.format(formatter);
                    Vevoireklamacio_fejlec.d5_cimke.setText(output);
                    hatarido = rovid.parse(output);
                    if(hatarido.compareTo(ma) >= 0){}
                    else
                    {
                        if(Vevoireklamacio_fejlec.d5.getRGB() == Color.GREEN.getRGB()) {}
                        else
                        {
                            Vevoireklamacio_fejlec.d5 = Color.RED;
                        }
                    }
                    newDate = date.plusDays(30);
                    output = newDate.format(formatter);
                    Vevoireklamacio_fejlec.lezaras_cimke.setText(output);
                    hatarido = rovid.parse(output);
                    if(hatarido.compareTo(ma) >= 0){}
                    else
                    {
                        if(Vevoireklamacio_fejlec.lezaras.getRGB() == Color.GREEN.getRGB()) {}
                        else
                        {
                            Vevoireklamacio_fejlec.lezaras = Color.RED;
                        }
                    }
                }                            
                vevorek_mezo.setText(rs.getString(6));
                veasrek_mezo.setText(Vevoireklamacio_fejlec.id_mezo.getText());
                frissites.getJFormattedTextField().getText();
                
                dateValue = rs.getString(7).replace("-", ".");
                try {
                    date3 = new SimpleDateFormat("yyyy.MM.dd").parse(dateValue);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    System.out.println("hibára futott, nincs utolsó frissítés");
                }
                model2.setValue(date3);
                //frissites_mezo.setText(rs.getString(7));
                String[] felelos = rs.getString(8).split(";");
                
                if(felelos.length > 1)
                {
                    felelos_mezo.setText(felelos[0]);
                    felelos_box.setSelectedItem(felelos[1]); 
                }
                else if(felelos.length == 1)
                {
                    felelos_mezo.setText("");
                    felelos_box.setSelectedItem(felelos[0]); 
                }
                else
                {
                    felelos_mezo.setText("");
                    felelos_box.setSelectedItem(""); 
                }
                
                String[] cikkszamok = rs.getString(9).split(";");
                String[] cikkek = {cikkszamok[0]};
                DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>(cikkek);
                tipus_box.setModel(model2);
                //tipus_box.setSelectedItem(cikkszamok[0]);
                
                int rowCount = modell2.getRowCount();           
                for (int i = rowCount - 1; i > -1; i--) 
                {
                  modell2.removeRow(i);
                }
                if(cikkszamok.length == 1)
                {
                    modell2.addRow(new Object[]{cikkszamok[0]});
                }
                else
                {
                    for(int szamlalo = 0; szamlalo < cikkszamok.length;szamlalo++)
                    {
                        modell2.addRow(new Object[]{cikkszamok[szamlalo]});                       
                    }                    
                }            
                table_1.setModel(modell2);
                //
                String[] email = rs.getString(10).split(";");
                if(email.length > 1)
                {
                    email_mezo.setText(email[0]);
                    email2_mezo.setText(email[1]); 
                }
                else if(email.length == 1)
                {
                    email_mezo.setText(email[0]);
                    email2_mezo.setText("");
                }
                else
                {
                    email_mezo.setText("");
                    email2_mezo.setText("");
                }            
                rma_mezo.setText(rs.getString(11));
                String[] telefon = rs.getString(12).split(";");
                if(telefon.length > 1)
                {
                    telefonszam_mezo.setText(telefon[0]);
                    telefonszam2_mezo.setText(telefon[1]); 
                }
                else if(telefon.length == 1)
                {
                    telefonszam_mezo.setText(telefon[0]);
                    telefonszam2_mezo.setText("");
                }
                else
                {
                    telefonszam_mezo.setText("");
                    telefonszam2_mezo.setText("");
                }        
                visszakuldes_mezo.setText(rs.getString(13));
            }
            int rowCount = modell.getRowCount();
            
            for (int i = rowCount - 1; i > -1; i--) 
            {
              modell.removeRow(i);
            }
            table.setModel(modell);
            Vevoireklamacio_V2.d5.hatarido();
            vevo_box.addActionListener(valaszto);
            stmt.close();
            conn.close();
            Foablak.frame.setCursor(null);                                                //egér mutató alaphelyzetbe állítása
        }          
        catch (Exception e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
        } finally 
        {
           try 
           {
              if (stmt != null)
                  stmt.close();
           } 
           catch (SQLException se) {}
           try 
           {
              if (conn != null)
                 conn.close();
           } 
           catch (SQLException se) 
           {
               se.printStackTrace();
               String hibauzenet = se.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
               JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kivétel esetén kiírja a hibaüzenetet
           }  
        }
        //JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
    }
    
    class Kivalaszt implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                DefaultComboBoxModel<String> model;
                String keresett = String.valueOf(vevo_box.getSelectedItem());
                String vevo = "";
                System.out.println(keresett);
                workbook.loadFromFile(excelhelye2);
                workbook.setVersion(ExcelVersion.Version2016);
                Worksheet sheet = workbook.getWorksheets().get(0);
                for(int szamlalo = 1; szamlalo < sheet.getLastDataRow()+1; szamlalo++)
                {
                    if(keresett.toUpperCase().equals(sheet.getRange().get("B"+szamlalo).getText().toUpperCase()))
                    {
                        vevo =  sheet.getRange().get("A"+szamlalo).getText();
                        System.out.println(vevo);
                    }
                    System.out.println(sheet.getRange().get("B"+szamlalo).getText().toUpperCase());
                }
                sql = "select part_no || '  ' || REVISION_TEXT || '  ' || ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO) as cikkszamok\r\n"
                        + "from ifsapp.PART_REVISION\r\n"
                        + "where 3 = 3\r\n"
                        + "and ifsapp.inventory_part_api.Get_Part_Product_Code(contract,part_no) = '1'\r\n"
                        + "and ifsapp.inventory_part_api.Get_Second_Commodity(contract, Part_no) = '"+ vevo +"'\r\n"
                        + "group by part_no, REVISION_TEXT, ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO)\r\n"
                        + "ORDER by part_no";
                
                String[] cikkek = cikkszamok.tombvissza(sql);                
                String[] ujmodell = new String[cikkek.length];
                for(int szamlalo = 0; szamlalo <cikkek.length; szamlalo++)
                {
                    ujmodell[szamlalo] = cikkek[szamlalo];
                }               
                model = new DefaultComboBoxModel<>(ujmodell);
                   
                tipus_box.setModel(model);
                Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true); 
                Foablak.frame.setCursor(null);                                                //egér mutató alaphelyzetbe állítása
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Email_keres2 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                workbook.loadFromFile(excelhelye);
                workbook.setVersion(ExcelVersion.Version2016);
                Worksheet sheet = workbook.getWorksheets().get(0);
                String nev = String.valueOf(felelos_box.getSelectedItem());
                for(int szamlalo = 2; szamlalo < sheet.getLastRow()+1;szamlalo++)
                {
                    if(sheet.getRange().get("C"+szamlalo).getText().toLowerCase().contains(nev.toLowerCase()))
                    {
                        email2_mezo.setText(sheet.getRange().get("I"+szamlalo).getText());
                        telefonszam2_mezo.setText(sheet.getRange().get("G"+szamlalo).getText());
                        break;
                    }
                        
                }
                Vevoireklamacio_d1.vezeto_mezo.setText(String.valueOf(felelos_box.getSelectedItem()));
                Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
                Foablak.frame.setCursor(null);                                                //egér mutató alaphelyzetbe állítása
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Fajl_torles implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                int sor = table.getSelectedRow();
                if(sor< 0)
                {
                    JOptionPane.showMessageDialog(null, "Nincs kiválasztva fájl!!", "Hiba üzenet", 2);                                                     // hibaüzenetet kiratása
                }
                else
                {
                    sql = "delete from qualitydb.Vevoireklamacio_fajlok where Rek_ID ='"+ Vevoireklamacio_fejlec.id_mezo.getText() +"' and Fajl_neve = '"+ table.getValueAt(sor, 1).toString() +"'";
                    cikkszamok.mindenes(sql);
                    modell.removeRow(sor);
                    Vevoireklamacio_d2.fajlok.remove(sor);
                }
                Foablak.frame.setCursor(null);                                                //egér mutató alaphelyzetbe állítása
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Valtozas implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Hozzaad implements ActionListener                                                                                             //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                modell2.addRow(new Object[]{String.valueOf(tipus_box.getSelectedItem())});
                table_1.setModel(modell2);
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
