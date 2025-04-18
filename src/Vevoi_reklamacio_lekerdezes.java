import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.Chart;
import com.spire.xls.ExcelChartType;
import com.spire.xls.ExcelVersion;
import com.spire.xls.LegendPositionType;
import com.spire.xls.ShapeFillType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import com.spire.xls.charts.ChartSerie;
import com.spire.xls.charts.ChartSeries;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

public class Vevoi_reklamacio_lekerdezes extends JPanel 
{
    //private JTextField datumtol;
    //private JTextField datumig;
    static JTable table;
    private ComboBox combobox;
    private JComboBox<String> projekt_box;
    private JRadioButton lezart_gomb;
    private JRadioButton nyitott_gomb;
    private SQL lekerdezes = new SQL();
    private JTextField id_mezo;
    static int fajlszam = 1;
    private JXDatePicker datumtol;
    private JXDatePicker datumig;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    
    /**
     * Create the panel.
     */
    public Vevoi_reklamacio_lekerdezes() 
    {
        this.setPreferredSize(new Dimension(1160, 700));
        setLayout(null);
        
        combobox = new ComboBox();
        JLabel lblNewLabel = new JLabel("Vevői reklamációk lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(494, 11, 264, 22);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Projekt");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(464, 57, 46, 14);
        add(lblNewLabel_1);
        
        projekt_box = new JComboBox<String>(combobox.getCombobox(ComboBox.vevoi_projekt));                   //combobox.getCombobox(ComboBox.vevoi_projekt)
        projekt_box.setBounds(520, 53, 172, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -tól");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(420, 93, 90, 14);
        add(lblNewLabel_2);
        
        datumtol = new JXDatePicker();
        datumtol.setBounds(520, 90, 120, 20);
        add(datumtol);
        
        JLabel lblNewLabel_3 = new JLabel("Dátim -ig");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(430, 124, 80, 14);
        add(lblNewLabel_3);
        
        datumig = new JXDatePicker();
        datumig.setBounds(520, 121, 120, 20);
        add(datumig);
        
        lezart_gomb = new JRadioButton("Nyitott");
        lezart_gomb.setBounds(481, 192, 66, 14);
        add(lezart_gomb);
        
        nyitott_gomb = new JRadioButton("Lezárt");
        nyitott_gomb.setBounds(591, 188, 80, 23);
        add(nyitott_gomb);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.setBounds(517, 262, 89, 23);
        keres_gomb.addActionListener(new Kereses());
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(49, 314, 1070, 200);
        add(pane);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(285, 525, 89, 23);
        //add(excel_gomb);
        
        id_mezo = new JTextField();
        id_mezo.setBounds(520, 152, 86, 20);
        id_mezo.addKeyListener(new Enter());
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("ID");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(464, 155, 46, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Gafikonok");
        lblNewLabel_5.setBounds(430, 529, 80, 14);
        add(lblNewLabel_5);        
        /*
        JLabel lblNewLabel_7 = new JLabel("Csatolt Képek");
        lblNewLabel_7.setBounds(420, 619, 90, 14);
        add(lblNewLabel_7);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Kepmentes());
        mentes_gomb.setBounds(520, 615, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_8 = new JLabel("Csatolt Excel");
        lblNewLabel_8.setBounds(420, 655, 80, 14);
        add(lblNewLabel_8);
        
        JButton excelmentes = new JButton("Mentés");
        excelmentes.setBounds(520, 651, 89, 23);
        excelmentes.addActionListener(new Excelmentes());
        add(excelmentes);*/
        //ido();
        
        setBackground(Foablak.hatter_szine);
        
        JButton v2_grafikon_gomb = new JButton("V2.0 grafikon");
        v2_grafikon_gomb.addActionListener(new Vevoi_2());
        v2_grafikon_gomb.setBounds(494, 525, 157, 23);
        add(v2_grafikon_gomb);
        
        JButton txt_gomb = new JButton("txt");
        txt_gomb.addActionListener(new TXT());
        txt_gomb.setBounds(517, 228, 89, 23);
        add(txt_gomb);
    }
    
    class TXT implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Connection conn = null;
                Statement stmt = null;
                Statement stmt2 = null;
                String[] szoveg = {"Reklamáció dátuma","Projekt","Termék típusa","Vevőnél talált hibás db szám","Hiba leírása:","Gyökérok","Intézkedés"};
                PrintWriter out = null;
                Class.forName("com.mysql.cj.jdbc.Driver");   
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = (Statement) conn.createStatement();
                stmt2 = (Statement) conn.createStatement();
                String sql = "select ID, ertesites_datuma, vevo, Tipus, hanydb, miaproblema, anyag, gep, ember, mod_, Lezaras_datuma from qualitydb.Vevoireklamacio_alap "
                        + "where ertesites_datuma >= '"+ dateFormat.format(datumtol.getDate()) +"' and ertesites_datuma <= '"+ dateFormat.format(datumig.getDate()) +"'";
                stmt.execute(sql);
                ResultSet rs = stmt.getResultSet();
                
                File f = new File(System.getProperty("user.home") + "\\Desktop\\TXT\\");
                f.mkdir();
                //f.getParentFile().mkdirs(); 
                //f.createNewFile();
                int szam = 2;
                String id = "";
                String lezaras = "";
                while(rs.next())
                {
                    id = rs.getString(1);
                    szam = 2;
                    out = new PrintWriter(new FileWriter(System.getProperty("user.home") + "\\Desktop\\TXT\\ID_"+ id +".txt", StandardCharsets.UTF_8), true);
                    for (String s : szoveg) {
                        StringBuilder underlined = new StringBuilder();
                        for (char c : s.toCharArray()) {
                            underlined.append(c).append('\u0332');
                        }
                        if(szam < 7)
                        {
                            if(szam == 5)
                            {
                                out.println(underlined+ ": "+ rs.getString(szam) +" db");
                                szam++;
                                out.println();
                            }
                            else
                            {
                                out.println(underlined+ ": "+ rs.getString(szam));
                                szam++;
                            }
                        }
                        else
                        {
                            if(szam == 7)
                            {
                                out.println();
                                String gyokerok = "";
                                for(int szamlalo = 7; szamlalo < 11; szamlalo++)
                                {
                                    String[] tomb = rs.getString(szamlalo).split(";");
                                    for(int szamlalo2 = 0; szamlalo2 < tomb.length; szamlalo2++)
                                    {
                                        gyokerok += tomb[szamlalo2]+"\n";
                                    }
                                }
                                out.println(underlined+ ": "+ gyokerok);
                                szam++;
                            }
                            else
                            {
                                System.out.println("Fut az intézkedés");
                                //out.println();
                                String intezkedes = "";
                                sql = "select feladat, felelos, hatarido from qualitydb.Vevoireklamacio_elo where rek_id = '"+ id +"'";
                                stmt2.execute(sql);
                                ResultSet rs2 = stmt2.getResultSet();
                                while(rs2.next())
                                {
                                    intezkedes += rs2.getString(1)+ " -> "+ rs2.getString(2)+ ",Hi.: "+ rs2.getString(3) +"\n";
                                }
                                
                                sql = "select feladat, felelos, hatarido from qualitydb.Vevoireklamacio_det where rek_id = '"+ id +"'";
                                stmt2.execute(sql);
                                rs2 = stmt2.getResultSet();
                                while(rs2.next())
                                {
                                    intezkedes += rs2.getString(1)+ " -> "+ rs2.getString(2)+ ",Hi.: "+ rs2.getString(3) +"\n";
                                }
                                out.println(underlined+ ": "+ intezkedes);
                            }
                        }
                    }
                    out.println();
                    lezaras = rs.getString(11);
                    if(lezaras.equals(""))
                    {
                        out.println("Lezárás dátuma: Analizálás alatt.");
                    }
                    else
                    {
                        out.println("Lezárás dátuma: "+ lezaras);
                    }
                    out.flush();
                    out.close();
                }
                JOptionPane.showMessageDialog(null, "Mentve az asztalra TXT mappába", "Info", 1);       
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                String nyitott = "";
                String lezart = "";
                if(lezart_gomb.isSelected())
                {
                    lezart = "igen";
                }
                else
                {
                    lezart = "nem";
                }
                
                if(nyitott_gomb.isSelected())
                {
                    nyitott = "igen";
                }
                else
                {
                    nyitott = "nem";
                }
                                
                lekerdezes.vevoi_lekerdezes(String.valueOf(projekt_box.getSelectedItem()), dateFormat.format(datumtol.getDate()), dateFormat.format(datumig.getDate()), nyitott, lezart, id_mezo.getText());
                TableColumnModel columnModel = table.getColumnModel();
                for (int column = 0; column < table.getColumnCount(); column++) {
                    int width = 15; // Min width
                    for (int row = 0; row < table.getRowCount(); row++) {
                        TableCellRenderer renderer = table.getCellRenderer(row, column);
                        Component comp = table.prepareRenderer(renderer, row, column);
                        width = Math.max(comp.getPreferredSize().width +1 , width);
                    }
                    if(width > 300)
                        width=300;
                    columnModel.getColumn(column).setPreferredWidth(width);
                }
                //table.setModel(modell);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                String nyitott = "";
                String lezart = "";
                if(lezart_gomb.isSelected())
                {
                    lezart = "igen";
                }
                else
                {
                    lezart = "nem";
                }
                
                if(nyitott_gomb.isSelected())
                {
                    nyitott = "igen";
                }
                else
                {
                    nyitott = "nem";
                }
                                
                lekerdezes.vevoi_lekerdezes(String.valueOf(projekt_box.getSelectedItem()), dateFormat.format(datumtol.getDate()), dateFormat.format(datumig.getDate()), nyitott, lezart, id_mezo.getText());
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
    
    class Excel implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String nyitott = "";
                String lezart = "";
                if(lezart_gomb.isSelected())
                {
                    lezart = "igen";
                }
                else
                {
                    lezart = "nem";
                }
                
                if(nyitott_gomb.isSelected())
                {
                    nyitott = "igen";
                }
                else
                {
                    nyitott = "nem";
                }
                lekerdezes.vevoi_lekerdezes_excel(String.valueOf(projekt_box.getSelectedItem()), dateFormat.format(datumtol.getDate()), dateFormat.format(datumig.getDate()), nyitott, lezart);
                Foablak.frame.setCursor(null);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Kepmentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                lekerdezes.vevoi_kepmentes(table.getValueAt(0, 1).toString(), table.getValueAt(0, 3).toString());                
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Excelmentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                lekerdezes.vevoi_excelmentes(table.getValueAt(0, 1).toString(), table.getValueAt(0, 3).toString());                
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    /*public void ido()                                                                   //a pontos dátu meghatározására szolgáló függvény
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        //datumig.setText(formatter.format(date));                                        //az aktuális dátumot hozzáadja az időpont mezőhöz
    }*/
    
    class Vevoi_2 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                
                Connection conn = null;
                Statement stmt = null;
                Workbook workbook = new Workbook();
                workbook.setVersion(ExcelVersion.Version2016);
                Worksheet sheet = workbook.getWorksheets().get(0);   
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String sql = "SELECT DATE_FORMAT(Ertesites_Datuma,'%Y%m'), sum(if(mi = 'Visszajelzés',0,1)) as reklamacio, sum(if(mi = 'Visszajelzés',1,0)) as visszajelzes FROM  qualitydb.Vevoireklamacio_alap where   Ertesites_Datuma >= '"+ dateFormat.format(datumtol.getDate()) 
                        +"' and Ertesites_Datuma <= '"+ dateFormat.format(datumig.getDate()) +"' group by DATE_FORMAT(Ertesites_Datuma,'%Y%m')\n"
                                + "order by DATE_FORMAT(Ertesites_Datuma,'%Y%m') asc";
                stmt.execute(sql);
                ResultSet rs = stmt.getResultSet();
                
                
                /********************************Első diagramm***************************************/

                sheet.getRange().get("A" + 1).setText("Hónap");
                sheet.getRange().get("B" + 1).setText("Reklamáció");
                sheet.getRange().get("C" + 1).setText("Visszajelzés");
                               
                sheet.getRange().get("A" + 2).setText("Január");
                sheet.getRange().get("A" + 3).setText("Február");
                sheet.getRange().get("A" + 4).setText("Március");
                sheet.getRange().get("A" + 5).setText("Április");
                sheet.getRange().get("A" + 6).setText("Május");
                sheet.getRange().get("A" + 7).setText("Június");
                sheet.getRange().get("A" + 8).setText("Július");
                sheet.getRange().get("A" + 9).setText("Augusztus");
                sheet.getRange().get("A" + 10).setText("Szeptember");
                sheet.getRange().get("A" + 11).setText("Október");
                sheet.getRange().get("A" + 12).setText("November");
                sheet.getRange().get("A" + 13).setText("December");
                sheet.getRange().get("A" + 14).setText("Átlag Rek.");
                sheet.getRange().get("A" + 15).setText("Átlag Vissz.");
                
                                        
                int oszto = 0;
                int sor = 2;
                int sumrek = 0;
                int sumvissza = 0;
                while(rs.next()) 
                {
                    sheet.getCellRange("B" + sor).setNumberValue(rs.getInt(2));
                    sheet.getCellRange("C" + sor).setNumberValue(rs.getInt(3));
                    sumrek += rs.getInt(2);
                    sumvissza += rs.getInt(3);
                    sor++;
                    oszto++;
                }
                
                 
                //Date date = new Date();
                //LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                // int month = localDate.getMonthValue();
                String[] datum_tol = dateFormat.format(datumtol.getDate()).split("\\.");
                String[] datum_ig = dateFormat.format(datumig.getDate()).split("\\.");
                LocalDate start_date = LocalDate.of(Integer.parseInt(datum_tol[0]),Integer.parseInt(datum_tol[1]),Integer.parseInt(datum_tol[2]));
                LocalDate end_date = LocalDate.of(Integer.parseInt(datum_ig[0]),Integer.parseInt(datum_ig[1]),Integer.parseInt(datum_ig[2]));
                Period diff = Period.between(start_date,end_date);

                int atlagrek = 0;
                int atlagvissza = 0;
                if(diff.getMonths() == 0)
                {
                    atlagrek =  sumrek;
                    atlagvissza =  sumvissza;
                }
                else
                {
                    atlagrek = (sumrek / oszto);           //month
                    atlagvissza = (sumvissza / oszto);       //month
                }
                sheet.getCellRange("B" + 14).setNumberValue(atlagrek);
                sheet.getCellRange("C" + 15).setNumberValue(atlagvissza);
                       
                Chart chart = sheet.getCharts().add();
                chart.setDataRange(sheet.getCellRange("A1:C15"));
                chart.setSeriesDataFromRange(false);
                
                chart.setLeftColumn(1);
                chart.setTopRow(16);
                chart.setRightColumn(11);
                chart.setBottomRow(36);
                /*
                if (false)
                {
                    chart.setChartType(ExcelChartType.Column3DStacked);
                }
                else
                {
                    chart.setChartType(ExcelChartType.ColumnStacked);
                }
                */
                chart.setChartType(ExcelChartType.ColumnStacked);
                
                chart.setChartTitle("Reklamáció és visszajelzés száma");
                chart.getChartTitleArea().isBold(true);
                chart.getChartTitleArea().setSize(15);
                chart.getPrimaryCategoryAxis().setTitle("Projektek");
                chart.getPrimaryCategoryAxis().getFont().isBold(true);
                chart.getPrimaryCategoryAxis().getTitleArea().isBold(true);
                chart.getPrimaryValueAxis().setTitle("Összesen");
                chart.getPrimaryValueAxis().hasMajorGridLines(false);
                chart.getPrimaryValueAxis().setMinValue(0);
                chart.getPrimaryValueAxis().getTitleArea().isBold(true);
                chart.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);
                
                ChartSeries series = chart.getSeries();
                for (int i = 0;i < series.size();i++)
                {
                    ChartSerie cs = series.get(i);
                    cs.getFormat().getOptions().isVaryColor(true);
                    cs.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
                }
                
                chart.getLegend().setPosition(LegendPositionType.Top);
                
                /********************************Második diagramm*******************************************/
                
                
                sql = "select Vevo, sum(Hanydb)\n"
                        + "from qualitydb.Vevoireklamacio_alap\n"
                        + "where 3=3  and Ertesites_Datuma >= '"+ dateFormat.format(datumtol.getDate()) +"' and Ertesites_Datuma <= '"+ dateFormat.format(datumig.getDate()) +"' \n"                  
                        + "group by Vevo order by sum(Hanydb) desc";
                stmt.execute(sql);
                rs = stmt.getResultSet();
                sheet.getRange().get("L" + 1).setText("Projekt");
                sheet.getRange().get("M" + 1).setText("Reklamált db");
                int cella = 2;
                while(rs.next()) 
                {
                    sheet.getRange().get("L" + cella).setText(rs.getString(1));
                    sheet.getRange().get("M" + cella).setNumberValue(rs.getInt(2));
                    cella++;
                }
                
                Chart chart2 = sheet.getCharts().add();
                chart2.setDataRange(sheet.getCellRange("L1:M" +cella));
                chart2.setSeriesDataFromRange(false);
                
                chart2.setLeftColumn(12);
                chart2.setTopRow(16);
                chart2.setRightColumn(22);
                chart2.setBottomRow(36);
                
                chart2.setChartType(ExcelChartType.ColumnStacked);
                
                chart2.setChartTitle("Reklamált db projektenként");
                chart2.getChartTitleArea().isBold(true);
                chart2.getChartTitleArea().setSize(14);
                chart2.getPrimaryCategoryAxis().setTitle("Projektek");
                chart2.getPrimaryCategoryAxis().getFont().isBold(true);
                chart2.getPrimaryCategoryAxis().getTitleArea().isBold(true);
                chart2.getPrimaryValueAxis().setTitle("Összesen");
                chart2.getPrimaryValueAxis().hasMajorGridLines(false);
                chart2.getPrimaryValueAxis().setMinValue(0);
                chart2.getPrimaryValueAxis().getTitleArea().isBold(true);
                chart2.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);
                
                ChartSeries series2 = chart2.getSeries();
                for (int i = 0;i < series2.size();i++)
                {
                    ChartSerie cs2 = series2.get(i);
                    cs2.getFormat().getOptions().isVaryColor(true);
                    cs2.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
                }        
                chart2.getLegend().setPosition(LegendPositionType.Top);
                
                
                /********************************Harmadik diagramm**********************************************/
                
                sql = "select DATE_FORMAT(Ertesites_Datuma,'%Y%m') as 'Hónap',\n"
                        + "Vevo, \n"
                        + "sum(if(mi = 'Visszajelzés', 0, 1)) as 'Reklamáció', \n"
                        + "sum(if(mi = 'Visszajelzés', 1, 0)) as 'Visszajelzés'\n"
                        + "from qualitydb.Vevoireklamacio_alap\n"
                        + " where 3=3 and Ertesites_Datuma >= '"+ dateFormat.format(datumtol.getDate()) +"' and Ertesites_Datuma <= '"+ dateFormat.format(datumig.getDate()) +"'\n"
                        + "group by Vevo, Hónap order by Hónap asc";
                stmt.execute(sql);
                rs = stmt.getResultSet();
                
                sheet.getRange().get("E" + 1).setText("Hónap");
                sheet.getRange().get("F" + 1).setText("Vevő");
                //sheet.getRange().get("G" + 1).setText("Projekt");
                sheet.getRange().get("G" + 1).setText("Reklamáció");
                sheet.getRange().get("H" + 1).setText("Visszajelzés");
                int cella2 = 2;
                while(rs.next()) 
                {
                    if(rs.getString(1).endsWith("01"))
                    {
                        sheet.getRange().get("E" + cella2).setText("Január");
                    }
                    else if(rs.getString(1).endsWith("02"))
                    {
                        sheet.getRange().get("E" + cella2).setText("Február");
                    }
                    else if(rs.getString(1).endsWith("03"))
                    {
                        sheet.getRange().get("E" + cella2).setText("Március");
                    }
                    else if(rs.getString(1).endsWith("04"))
                    {
                        sheet.getRange().get("E" + cella2).setText("Április");
                    }
                    else if(rs.getString(1).endsWith("05"))
                    {
                        sheet.getRange().get("E" + cella2).setText("Május");
                    }
                    else if(rs.getString(1).endsWith("06"))
                    {
                        sheet.getRange().get("E" + cella2).setText("Június");
                    }
                    else if(rs.getString(1).endsWith("07"))
                    {
                        sheet.getRange().get("E" + cella2).setText("Július");
                    }
                    else if(rs.getString(1).endsWith("08"))
                    {
                        sheet.getRange().get("E" + cella2).setText("Augusztus");
                    }
                    else if(rs.getString(1).endsWith("09"))
                    {
                        sheet.getRange().get("E" + cella2).setText("Szeptember");
                    }
                    else if(rs.getString(1).endsWith("10"))
                    {
                        sheet.getRange().get("E" + cella2).setText("Október");
                    }
                    else if(rs.getString(1).endsWith("11"))
                    {
                        sheet.getRange().get("E" + cella2).setText("November");
                    }
                    else
                    {
                        sheet.getRange().get("E" + cella2).setText("December");
                    }
                    sheet.getRange().get("F" + cella2).setText(rs.getString(2));
                    //sheet.getRange().get("G" + cella2).setText(datatable3.getRows().get(szamlalo).getString(1));                    
                    sheet.getCellRange("G" + cella2).setNumberValue(rs.getInt(3));
                    sheet.getCellRange("H" + cella2).setNumberValue(rs.getInt(4));
                    cella2++;                    
                }
                System.out.println("cella: "+cella2);
                int meddig = 2;
                int mettol = 2;
                for(int szamlalo = 2; szamlalo < cella2; szamlalo++)
                {
                    if(sheet.getRange().get("E" + szamlalo).getText().equals(sheet.getRange().get("E" + (szamlalo+1)).getText()))
                    {
                        meddig++;
                    }
                    else
                    {
                        System.out.println("mettől: "+mettol);
                        System.out.println("Meddig: "+meddig);
                        if(mettol == meddig) 
                        {
                            sheet.getCellRange("E"+(mettol+1)).setText("");
                            meddig++;
                            mettol = meddig;
                        }
                        else
                        {
                            sheet.getCellRange("E"+(mettol+1)+":E"+meddig).setText("");
                            meddig++;
                            mettol = meddig;
                        }
                    }
                }
                /*
                sheet.getCellRange("E2:E5").merge();
                sheet.getCellRange("E6:E14").merge();
                sheet.getCellRange("E15:E16").merge();
                sheet.getCellRange("E17:E19").merge();
                sheet.getCellRange("E20:E22").merge();
                
                Chart chart3 = sheet.getCharts().add(ExcelChartType.BarClustered);
                chart3.setChartTitle( "Value");                
                chart3.getPlotArea().getFill().setFillType( ShapeFillType.NoFill);  
                chart3.getLegend().delete();   
                chart3.setLeftColumn(1);   
                chart3.setTopRow(37);    
                chart3.setRightColumn(16);    
    
                //set the data source of series data
    
                chart3.setDataRange(sheet.getCellRange("G2:G22"));
    
                chart3.setSeriesDataFromRange(false);

                //set the data source of category labels
    
                ChartSerie serie = chart3.getSeries().get(0);
    
                serie.setCategoryLabels( sheet.getCellRange("E2:F22"));

                //show multi-level category labels
    
                chart3.getPrimaryCategoryAxis().setMultiLevelLable( true);
                */
                
                
                Chart chart3 = sheet.getCharts().add();
                chart3.setDataRange(sheet.getCellRange("E1:H" + (cella2-1)));
                chart3.setSeriesDataFromRange(false);
                //serie.setCategoryLabels( sheet.getCellRange("E2:F22"));

                //show multi-level category labels
    
                chart3.getPrimaryCategoryAxis().setMultiLevelLable( true);
                chart3.setLeftColumn(1);
                chart3.setTopRow(37);
                chart3.setRightColumn(16);
                chart3.setBottomRow(73);
                
                chart3.setChartType(ExcelChartType.ColumnStacked);
                
                chart3.setChartTitle("Reklamáció és visszajelzés projektenként");
                chart3.getChartTitleArea().isBold(true);
                chart3.getChartTitleArea().setSize(14);
                chart3.getPrimaryCategoryAxis().setTitle("Projektek");
                chart3.getPrimaryCategoryAxis().getFont().isBold(true);
                chart3.getPrimaryCategoryAxis().getTitleArea().isBold(true);
                chart3.getPrimaryValueAxis().setTitle("Összesen");
                chart3.getPrimaryValueAxis().hasMajorGridLines(false);
                chart3.getPrimaryValueAxis().setMinValue(0);
                chart3.getPrimaryValueAxis().getTitleArea().isBold(true);
                chart3.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);
                
                ChartSeries series3 = chart3.getSeries();
                for (int i = 0;i < series3.size();i++)
                {
                    ChartSerie cs3 = series3.get(i);
                    cs3.getFormat().getOptions().isVaryColor(true);
                    cs3.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
                }
                
                chart3.getLegend().setPosition(LegendPositionType.Top);
                
                /**********************************************Negyedik diagramm******************************************/
                
                sql = "select DATE_FORMAT(Ertesites_Datuma,'%Y%m') as 'Hónap',            \n"
                        + "        sum(if(mi = 'Reklamáció - Vevői' && Lezaras_datuma = '', 1,if(mi = 'Reklamáció - Field kiesés' && Lezaras_datuma = '', 1,0))) as 'Nyitott reklamáció',\n"
                        + "        sum(if(mi = 'Reklamáció - Vevői' && not Lezaras_datuma = '', 1,if(mi = 'Reklamáció - Field kiesés' && not Lezaras_datuma = '', 1,0))) as 'Lezárt reklamáció',\n"
                        + "        sum(if(mi = 'Visszajelzés' && Lezaras_datuma = '', 1,0)) as 'Nyitott visszajelzés',\n"
                        + "        sum(if(mi = 'Visszajelzés' && not Lezaras_datuma = '', 1,0)) as 'Lezárt visszajelzés'\n"
                        + "                from qualitydb.Vevoireklamacio_alap\n"
                        + "                    where 3=3 and Ertesites_Datuma >= '"+ dateFormat.format(datumtol.getDate()) +"' and Ertesites_Datuma <= '"+ dateFormat.format(datumig.getDate()) +"' \n"
                        + "                    group by Hónap  order by Hónap asc";
                stmt.execute(sql);
                rs = stmt.getResultSet();
                
                sheet.getRange().get("R" + 1).setText("Hónap");
                sheet.getRange().get("S" + 1).setText("Nyitott rek");
                sheet.getRange().get("T" + 1).setText("Lezárt rek");
                sheet.getRange().get("U" + 1).setText("Nyitott vissza");
                sheet.getRange().get("V" + 1).setText("Lezárt vissza");
                sheet.getRange().get("W" + 1).setText("Átlag nyitva");
                
                int cella3 = 2;
                while(rs.next()) 
                {          
                    sheet.getCellRange("S" + cella3).setNumberValue(Integer.parseInt(rs.getString(2)));
                    sheet.getCellRange("T" + cella3).setNumberValue(Integer.parseInt(rs.getString(3)));
                    sheet.getCellRange("U" + cella3).setNumberValue(Integer.parseInt(rs.getString(4)));
                    sheet.getCellRange("V" + cella3).setNumberValue(Integer.parseInt(rs.getString(5)));          
                    cella3++;
                }
                
                int cella4 = 2;
                int sum = 0;
                String[] evszam = dateFormat.format(datumig.getDate()).split("\\.");
                String[] evszam2 = dateFormat.format(datumtol.getDate()).split("\\.");
                int valtoev = 0;
                if(Integer.valueOf(evszam[0]) > Integer.valueOf(evszam2[0]))
                { 
                    valtoev = 1;
                }
                int ig = Integer.valueOf(evszam[0]);
                int tol = ig-1;
                System.out.println(ig);
                System.out.println(tol);
                System.out.println(evszam[1]);
                
                sql = "select DATE_FORMAT(Ertesites_Datuma,'%Y%m') as 'Hónap',\n"        // --
                        + "   cast(AVG(if(Lezaras_datuma = '', DATEDIFF(now(), Ertesites_Datuma), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "           from qualitydb.Vevoireklamacio_alap\n"
                        + "       where 3=3 and Ertesites_Datuma >= '"+ dateFormat.format(datumtol.getDate()) +"' and Ertesites_Datuma <= '"+ dateFormat.format(datumig.getDate()) +"' \n"
                        + "             group by Hónap order by Hónap asc";
                stmt.execute(sql);
                rs = stmt.getResultSet();
                ResultSet resultSet;
                int ennyivel_kell = 0;
                /*int size =0;
                if (rs != null) 
                {
                  rs.last();    // moves cursor to the last row
                  size = rs.getRow(); // get row id 
                }*/
                //System.out.println("REsult mérete: "+ size);
                DataTable datatable = new DataTable();
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable, rs);
                for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                {                    
                    String evhet = datatable.getRows().get(szamlalo).getString(0);
                    if(evhet.endsWith("01"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 1)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("Január");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".01.31', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".01.31',DATEDIFF('"+ ig +".01.31', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".02.01' and Ertesites_Datuma <= '"+ ig +".01.31' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("02"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 2)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("Február");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".02.28', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".02.28',DATEDIFF('"+ ig +".02.28', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".03.01' and Ertesites_Datuma <= '"+ ig +".02.28' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("03"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 3)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("Március");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".03.31', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".03.31',DATEDIFF('"+ ig +".03.31', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".04.01' and Ertesites_Datuma <= '"+ ig +".03.31' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("04"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 4)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("Április");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".04.30', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".04.30',DATEDIFF('"+ ig +".04.30', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".05.01' and Ertesites_Datuma <= '"+ ig +".04.30' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("05"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 5)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("Május");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".05.31', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".05.31',DATEDIFF('"+ ig +".05.31', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".06.01' and Ertesites_Datuma <= '"+ ig +".05.31' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).endsWith("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("06"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 6)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("Június");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".06.30', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".06.30',DATEDIFF('"+ ig +".06.30', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".07.01' and Ertesites_Datuma <= '"+ ig +".06.30' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("07"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 7)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("Július");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".07.31', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".07.31',DATEDIFF('"+ ig +".07.31', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".08.01' and Ertesites_Datuma <= '"+ ig +".07.31' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("08"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 8)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("Augusztus");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".08.31', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".08.31',DATEDIFF('"+ ig +".08.31', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".09.01' and Ertesites_Datuma <= '"+ ig +".08.31' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("09"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 9)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("Szeptember");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".09.30', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".09.30',DATEDIFF('"+ ig +".09.30', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".10.01' and Ertesites_Datuma <= '"+ ig +".09.30' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("10"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 10)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("Október");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".10.31', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".10.31',DATEDIFF('"+ ig +".10.31', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".11.01' and Ertesites_Datuma <= '"+ ig +".10.31' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("11"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 11)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("November");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".11.30', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".11.30',DATEDIFF('"+ ig +".11.30', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ tol +".12.01' and Ertesites_Datuma <= '"+ ig +".11.30' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    if(evhet.endsWith("12"))
                    {
                        if(valtoev == 1)
                        {
                            if(Integer.valueOf(evszam[1]) >= 12)
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                            }
                            else
                            {
                                ig = Integer.valueOf(evszam[0]);
                                tol = ig-1;
                                ig--;
                                tol--;
                            }
                        }
                        sheet.getCellRange("R" + cella4).setText("December");
                        sql = "select  cast(AVG(if(Lezaras_datuma = '', DATEDIFF('"+ ig +".12.31', Ertesites_Datuma), \n"
                                + "if(Lezaras_datuma  > '"+ ig +".12.31',DATEDIFF('"+ ig +".12.31', Ertesites_Datuma ), DATEDIFF(Lezaras_datuma , Ertesites_Datuma) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                                + "from qualitydb.Vevoireklamacio_alap\n"
                                + "where 3=3 and Ertesites_Datuma >= '"+ ig +".01.01' and Ertesites_Datuma <= '"+ ig +".12.31' and Figyelem = 'igen'";
                        stmt.execute(sql);
                        resultSet = stmt.getResultSet();
                        if(resultSet.next())
                        sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                        if(resultSet.getString(1).contains("2022"))
                        {
                            sheet.getCellRange("W" + cella4).setNumberValue(69);
                        }
                    }
                    //sheet.getCellRange("R" + cella4).setNumberValue(Integer.parseInt(datatable6.getRows().get(szamlalo).getString(0)));
                    
                    sum += sheet.getCellRange("W" + cella4).getNumberValue();          //Integer.parseInt
                    cella4++;
                    ennyivel_kell++;
                    //rs.next();
                }                
                
                int atlag = sum / ennyivel_kell;
                sheet.getCellRange("W14").setNumberValue(atlag);
                
                Chart chart5 = sheet.getCharts().add();
                chart5.setChartTitle("Nyitott és lezárt visszajelzés és reklamáció");
                chart5.setDataRange(sheet.getCellRange("R1:W13"));
                chart5.setSeriesDataFromRange(false);
                chart5.getPrimaryValueAxis().setTitle("Reklamáció db szám");
                chart5.getSecondaryValueAxis().setTitle("Átlag nyitva nap");
         
                //Set position of the chart
                chart5.setLeftColumn(1);
                chart5.setTopRow(73);
                chart5.setRightColumn(14);
                chart5.setBottomRow(93);
         
                //Apply different chart types to different data series
                ChartSerie cs5 = (ChartSerie)chart5.getSeries().get(0);
                cs5.setSerieType(ExcelChartType.ColumnStacked);
                ChartSerie cs6 = (ChartSerie)chart5.getSeries().get(4);
                cs6.setSerieType(ExcelChartType.LineMarkers);
         
                //Add a secondary Y axis to the chart
                cs6.setUsePrimaryAxis(false);
                
                /**********************************************Ötödik diagramm******************************************/
                
                sheet.getRange().get("A" + 100).setText("Projekt");
                sheet.getRange().get("B" + 100).setText("Belső költség");
                sheet.getRange().get("C" + 100).setText("Fuvar költség");
                sheet.getRange().get("D" + 100).setText("Selejt költség");
                sheet.getRange().get("E" + 100).setText("Egyéb költség");
                int cellaszam6 = 101;
                int resultvege = 0;
                sql = "select Vevo,\n"
                        + " cast(sum(belso_koltseg) as decimal(15,0)),\n"
                        + " cast(sum(fuvar_koltseg) as decimal(15,0)),\n"
                        + " cast(sum(selejt_koltseg) as decimal(15,0)),\n"
                        + " cast(sum(egyeb_koltseg) as decimal(15,0)),\n"
                        + " sum(belso_koltseg)+sum(fuvar_koltseg)+sum(selejt_koltseg)+sum(egyeb_koltseg) as osszes "
                        + "from qualitydb.Vevoireklamacio_alap\n"
                        + "where 3=3 and Lezaras_datuma >= '"+dateFormat.format(datumtol.getDate()) +"' and Lezaras_datuma <= '"+ dateFormat.format(datumig.getDate()) +"'\n"
                        + "group by Vevo order by osszes desc";
                stmt.execute(sql);
                rs = stmt.getResultSet();
                DataTable datatable2 = new DataTable();
                jdbcAdapter.fillDataTable(datatable2, rs);
                for(int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++)
                {
                    sheet.getRange().get("A" + cellaszam6).setText(datatable2.getRows().get(szamlalo).getString(0));
                    sheet.getRange().get("B" + cellaszam6).setNumberValue(Integer.parseInt(datatable2.getRows().get(szamlalo).getString(1)));
                    sheet.getRange().get("C" + cellaszam6).setNumberValue(Integer.parseInt(datatable2.getRows().get(szamlalo).getString(2)));
                    sheet.getRange().get("D" + cellaszam6).setNumberValue(Integer.parseInt(datatable2.getRows().get(szamlalo).getString(3)));
                    sheet.getRange().get("E" + cellaszam6).setNumberValue(Integer.parseInt(datatable2.getRows().get(szamlalo).getString(4)));
                    cellaszam6++;
                    resultvege++;
                }
                Chart chart6 = sheet.getCharts().add();
                chart6.setDataRange(sheet.getCellRange("A100:E"+ (100+resultvege)));            //
                chart6.setSeriesDataFromRange(false);

                chart6.setLeftColumn(1);
                chart6.setTopRow(105);
                chart6.setRightColumn(11);
                chart6.setBottomRow(135);
                
                chart6.setChartType(ExcelChartType.ColumnStacked);
                
                chart6.setChartTitle("Reklamáció költsége projektenként");
                chart6.getChartTitleArea().isBold(true);
                chart6.getChartTitleArea().setSize(14);
                chart6.getPrimaryCategoryAxis().setTitle("Projektek");
                chart6.getPrimaryCategoryAxis().getFont().isBold(true);
                chart6.getPrimaryCategoryAxis().getTitleArea().isBold(true);
                chart6.getPrimaryValueAxis().setTitle("Összesen");
                chart6.getPrimaryValueAxis().hasMajorGridLines(false);
                chart6.getPrimaryValueAxis().setMinValue(0);
                chart6.getPrimaryValueAxis().setMaxValue(500000);
                chart6.getPrimaryValueAxis().getTitleArea().isBold(true);
                chart6.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);
                
                ChartSeries series6 = chart6.getSeries();
                for (int i = 0;i < series6.size();i++)
                {
                    ChartSerie cs = series6.get(i);
                    cs.getFormat().getOptions().isVaryColor(true);
                    cs.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
                }
                
                chart6.getLegend().setPosition(LegendPositionType.Top);
                
                //////////////////////////////////////////////////////////////////////////////////////
                Worksheet sheet2 = workbook.getWorksheets().get(1);
                sql = "select *"
                        + "from qualitydb.Vevoireklamacio_alap\n"
                        + "where 3=3 and Ertesites_datuma >= '"+dateFormat.format(datumtol.getDate()) +"' and Ertesites_datuma <= '"+ dateFormat.format(datumig.getDate()) +"'\n"
                        + "order by Ertesites_datuma asc";
                stmt.execute(sql);
                rs = stmt.getResultSet();
                DataTable datatable3 = new DataTable();
                jdbcAdapter.fillDataTable(datatable3, rs);
                sheet2.insertDataTable(datatable3, true, 1, 1);
                
                sheet.setName("Diagrammok");
                sheet2.setName("Alapadatok");
                
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();                
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                
                sheet2.getAutoFilters().setRange(sheet.getCellRange("A1:BL1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();                
                sheet2.getCellRange("A1:BK1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                
                UIManager.put("FileChooser.openButtonText","Mentés");
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                //System.out.println(fajl.getAbsolutePath());
                if(fajl != null)
                if(fajl.getName().contains(".xlsx"))
                {
                    workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);  
                    FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath());
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i>1 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath());
                        workbook2.write(output);
                        output.close();
                    }
                    JOptionPane.showMessageDialog(null, "Menetve a megadott helyre", "Info", 1);
                }
                else
                {
                    workbook.saveToFile(fajl.getAbsolutePath()+".xlsx", ExcelVersion.Version2016);  
                    FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath()+".xlsx");
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i>1 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath()+".xlsx");
                        workbook2.write(output);
                        output.close();
                    }
                    JOptionPane.showMessageDialog(null, "Menetve a megadott helyre", "Info", 1);
                }
                           
                rs.close();
                stmt.close();
                conn.close();
                Foablak.frame.setCursor(null);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
   public void teszt()
   { 
   
       Workbook workbook = new Workbook();
       Worksheet sheet = workbook.getWorksheets().get(0);
    
       //write data to cells
       sheet.getCellRange("A1").setText( "Main Category");
       sheet.getCellRange("A2").setText("Fruit");
       sheet.getCellRange("A6").setText("Vegies");
       sheet.getCellRange("B1").setText("Sub Category");
       sheet.getCellRange("B2").setText( "Bananas");
       sheet.getCellRange("B3").setText( "Oranges");
       sheet.getCellRange("B4").setText( "Pears");
       sheet.getCellRange("B5").setText("Grapes");
       sheet.getCellRange("B6").setText( "Carrots");
       sheet.getCellRange("B7").setText( "Potatoes");
       sheet.getCellRange("B8").setText( "Celery");
       sheet.getCellRange("B9").setText( "Onions");
       sheet.getCellRange("C1").setText("Value");
       sheet.getCellRange("C2").setValue("52");
       sheet.getCellRange("C3").setValue( "65");
       sheet.getCellRange("C4").setValue( "50");
       sheet.getCellRange("C5").setValue( "45");
       sheet.getCellRange("C6").setValue( "64");
       sheet.getCellRange("C7").setValue( "62");
       sheet.getCellRange("C8").setValue( "89");
       sheet.getCellRange("C9").setValue( "57");
    
       //vertically merge cells from A2 to A5, A6 to A9
       sheet.getCellRange("A2:A5").merge();
       sheet.getCellRange("A6:A9").merge();
       sheet.autoFitColumn(1);
       sheet.autoFitColumn(2);
    
       //add a clustered bar chart to worksheet
       Chart chart = sheet.getCharts().add(ExcelChartType.BarClustered);
       chart.setChartTitle( "Value");
       chart.getPlotArea().getFill().setFillType( ShapeFillType.NoFill);
       chart.getLegend().delete();
       chart.setLeftColumn(5);
       chart.setTopRow(1);
       chart.setRightColumn(14);
    
       //set the data source of series data
       chart.setDataRange(sheet.getCellRange("C2:C9"));
       chart.setSeriesDataFromRange(false);
    
       //set the data source of category labels
       ChartSerie serie = chart.getSeries().get(0);
       serie.setCategoryLabels( sheet.getCellRange("A2:B9"));
    
       //show multi-level category labels
       chart.getPrimaryCategoryAxis().setMultiLevelLable( true);
    
       //save the document
       workbook.saveToFile(System.getProperty("user.home") + "\\Desktop\\teszt.xlsx", ExcelVersion.Version2016);
   }
}
