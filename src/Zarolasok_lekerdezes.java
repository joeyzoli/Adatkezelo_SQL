import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.Chart;
import com.spire.xls.ExcelVersion;
import com.spire.xls.LegendPositionType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import com.spire.xls.charts.ChartSerie;
import com.spire.xls.charts.ChartSeries;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Zarolasok_lekerdezes extends JPanel {    
    private JXDatePicker datum_tol;
    private JXDatePicker datum_ig;

    /**
     * Create the panel.
     */
    public Zarolasok_lekerdezes() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Zárolások kimutatása");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(470, 51, 156, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(448, 105, 82, 14);
        add(lblNewLabel_1);               
        
        datum_tol = new JXDatePicker();
        datum_tol.setBounds(540, 102, 110, 20);
        add(datum_tol);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(448, 145, 82, 14);
        add(lblNewLabel_2);
        
        datum_ig = new JXDatePicker();
        datum_ig.setBounds(540, 142, 110, 20);
        add(datum_ig);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Start());
        start_gomb.setBounds(494, 192, 89, 23);
        add(start_gomb);
        
        setBackground(Foablak.hatter_szine);

    }      
    
    class Start implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre   
                String driverName = "com.mysql.cj.jdbc.Driver";                     //driver stringje
                String url = "jdbc:mysql://172.20.22.29";                           //adatbázis IP címe
                String userName = "veasquality";                                    //fehasználónév
                String password = "kg6T$kd14TWbs9&gd";                              //jelszó
                Statement stmt = null;
                DataTable datatable = new DataTable();
                DataTable datatable2 = new DataTable();
                ResultSet resultset = null;
                
                Class.forName(driverName);
                Connection connection = DriverManager.getConnection(url, userName, password);                           //csatlakozás a szerverhez
                stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                
                String sql = "select projekt, sum(zarolt_db) from qualitydb.Zarolasok \r\n"
                        + "where 3 = 3 \r\n"
                        + "and Zarolas_datuma >= '"+ dateFormat.format(datum_tol.getDate()) +"' and Zarolas_datuma <= '"+ dateFormat.format(datum_ig.getDate()) +"' \r\n"
                        + "group by projekt order by sum(zarolt_db) desc \r\n";
               
                stmt.execute(sql);
                ResultSet resultSet = stmt.getResultSet();
                
                Workbook workbook = new Workbook();
                Worksheet sheet = workbook.getWorksheets().get(0);         
                sheet.setName("Diagrammok");
                Worksheet sheet2 = workbook.getWorksheets().get(1);         
                sheet2.setName("Alapadatok");
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                
                /***************************************************első diagramm*************************************/
                
                jdbcAdapter.fillDataTable(datatable, resultSet);
                sheet.getRange().get("A" + 1).setText("Projekt");
                sheet.getRange().get("B" + 1).setText("Sum Zárolt");                   
                
                int cella = 2;
                for (int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++) 
                {          
                    sheet.getRange().get("A" + cella).setText(datatable.getRows().get(szamlalo).getString(0));
                    sheet.getCellRange("B" + cella).setNumberValue(Integer.parseInt(datatable.getRows().get(szamlalo).getString(1)));                                              
                    cella++;
                }
                
                Chart chart = sheet.getCharts().add();
                chart.setDataRange(sheet.getCellRange("A1:B" +cella));
                chart.setSeriesDataFromRange(false);
                
                chart.setLeftColumn(1);
                chart.setTopRow(10);
                chart.setRightColumn(16);
                chart.setBottomRow(30);
                
                //chart.setChartType(ExcelChartType.Column3DClustered);
                //chart.setChartType(ExcelChartType.Column3D);
                
                chart.setChartTitle("Zárolási db számok");
                chart.getChartTitleArea().isBold(true);
                chart.getChartTitleArea().setSize(14);
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
                
                /********************************************Második diagramm*************************************************/
                
                String sql2 = "select zarolas_oka, sum(zarolt_db) from qualitydb.Zarolasok \r\n"
                        + "where 3 = 3 \r\n"
                        + "and Zarolas_datuma >= '"+ dateFormat.format(datum_tol.getDate()) +"' and Zarolas_datuma <= '"+ dateFormat.format(datum_ig.getDate()) +"' \r\n"
                        + "group by zarolas_oka order by sum(zarolt_db) desc limit 10 \r\n";
                stmt.execute(sql2);
                resultset = stmt.getResultSet();
                sheet.getRange().get("G" + 1).setText("Zárolás oka");
                sheet.getRange().get("H" + 1).setText("Sum zárolt");
                
                int cella2 = 2;
                while(resultset.next())
                {   
                    sheet.getRange().get("G" + cella2).setText(resultset.getString(1));
                    sheet.getCellRange("H" + cella2).setNumberValue(Integer.parseInt(resultset.getString(2)));                        
                    cella2++;
                }
                
                Chart chart5 = sheet.getCharts().add();
                chart5.setChartTitle("Top 10 zárolási ok");

                chart5.setDataRange(sheet.getCellRange("G1:I" + cella2));
                chart5.setSeriesDataFromRange(false);
                chart5.getPrimaryValueAxis().setTitle("Zárolt db szám");
                //chart5.getSecondaryValueAxis().setTitle("Üzemben töltött átlag");
         
                //Set position of the chart
                chart5.setLeftColumn(1);
                chart5.setTopRow(31);
                chart5.setRightColumn(30);
                chart5.setBottomRow(51);
                chart5.getPrimaryCategoryAxis().getTitleArea().setTextRotationAngle(90);
                chart5.getPrimaryValueAxis().hasMajorGridLines(false);
                //Apply different chart types to different data series
                //ChartSerie cs5 = (ChartSerie)chart5.getSeries().get(0);
                //cs5.setSerieType(ExcelChartType.ColumnStacked);
                //ChartSerie cs6 = (ChartSerie)chart5.getSeries().get(1);
                //cs6.setSerieType(ExcelChartType.LineMarkers);
         
                //Add a secondary Y axis to the chart
                //cs6.setUsePrimaryAxis(false);
                ChartSeries series5 = chart5.getSeries();
                for (int i = 0;i < series5.size();i++)
                {
                    ChartSerie cs = series5.get(i);
                    cs.getFormat().getOptions().isVaryColor(true);
                    cs.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
                }
                
                /***************************************************  mennyi idő   **********************************************************/
                
                sql2 = "select sum(ellenorzes_ido)/60, sum(ellenorzes_ido)/60*7 from qualitydb.Zarolasok\r\n"
                        + "where 3 = 3\r\n"
                        + "and Zarolas_datuma >= '"+ dateFormat.format(datum_tol.getDate()) +"' and Zarolas_datuma <= '"+ dateFormat.format(datum_ig.getDate()) +"' \r\n";
                stmt.execute(sql2);
                resultset = stmt.getResultSet();
                sheet.getRange().get("K" + 1).setText("");
                sheet.getRange().get("L" + 1).setText("Költség");
                if(resultset.next())
                {
                    sheet.getRange().get("K" + 2).setText("Válogatásra fordított idő");
                    sheet.getRange().get("L" + 2).setNumberValue(resultset.getInt(1));
                    sheet.getRange().get("M" + 2).setText("óra");
                    sheet.getRange().get("K" + 3).setText("Válogatás költsége");
                    sheet.getRange().get("L" + 3).setNumberValue(resultset.getInt(2));
                    sheet.getRange().get("M" + 3).setText("euró");
                }
                ///////////////////////////////////////////////// minden adat
                sql = "select * from qualitydb.Zarolasok\r\n"
                        + "where 3 = 3 \r\n"
                        + "and Zarolas_datuma >= '"+ dateFormat.format(datum_tol.getDate()) +"' and Zarolas_datuma <= '"+ dateFormat.format(datum_ig.getDate()) +"' \r\n"
                        + "order by zarolas_datuma asc \r\n";
                
                /********************************************Harmadik diagramm*************************************************/
                
                sql2 = "select sum(ellenorzes_ido), zarolas_oka \r\n"
                        + "from qualitydb.Zarolasok \r\n"
                        + "where 3 = 3\r\n"
                        + "and Zarolas_datuma >= '"+ dateFormat.format(datum_tol.getDate()) +"' and Zarolas_datuma <= '"+ dateFormat.format(datum_ig.getDate()) +"'\r\n"
                        + "group by zarolas_oka order by sum(ellenorzes_ido) desc limit 10";
                stmt.execute(sql2);
                resultset = stmt.getResultSet();
                sheet.getRange().get("Q" + 1).setText("Zárolás oka");
                sheet.getRange().get("R" + 1).setText("Válogatás idő/perc");
                
                cella2 = 2;
                while(resultset.next())
                {   
                    sheet.getRange().get("Q" + cella2).setText(resultset.getString(2));
                    sheet.getCellRange("R" + cella2).setNumberValue(resultset.getInt(1));                        
                    cella2++;
                }
                
                Chart chart2 = sheet.getCharts().add();
                chart2.setChartTitle("Top 10 zárolásra fordított idő");

                chart2.setDataRange(sheet.getCellRange("Q1:R" + cella2));
                chart2.setSeriesDataFromRange(false);
                chart2.getPrimaryValueAxis().setTitle("Válogaátsra fordított idő");
                //chart5.getSecondaryValueAxis().setTitle("Üzemben töltött átlag");
         
                //Set position of the chart
                chart2.setLeftColumn(1);
                chart2.setTopRow(52);
                chart2.setRightColumn(18);
                chart2.setBottomRow(81);
                chart2.getPrimaryCategoryAxis().getTitleArea().setTextRotationAngle(90);
                chart2.getPrimaryValueAxis().hasMajorGridLines(false);
                
                ChartSeries series2 = chart2.getSeries();
                for (int i = 0;i < series2.size();i++)
                {
                    ChartSerie cs = series2.get(i);
                    cs.getFormat().getOptions().isVaryColor(true);
                    cs.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
                }
                
                
                /////////////////////////////////////////////////////////////
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                jdbcAdapter.fillDataTable(datatable2, resultSet);
                sheet2.insertDataTable(datatable2, true, 1, 1);
                sheet2.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                //sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                
                //String hova = System.getProperty("user.home") + "\\Desktop\\Zárolási kimutatás.xlsx";
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
                        for(int i = workbook2.getNumberOfSheets()-1; i > 1 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }
                        workbook2.setActiveSheet(0);
                        FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath());
                        workbook2.write(output);
                        output.close();
                    }
                    JOptionPane.showMessageDialog(null, "Mentve az alábbi helyre: "+ fajl.getAbsolutePath() +"  \n"+ fajl.getName() +" néven!", "Info", 1);
                }
                else
                {
                    workbook.saveToFile(fajl.getAbsolutePath()+".xlsx", ExcelVersion.Version2016);
                    FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath()+".xlsx");
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i > 1 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }
                        workbook2.setActiveSheet(0);
                        FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath()+".xlsx");
                        workbook2.write(output);
                        output.close();
                    }
                    JOptionPane.showMessageDialog(null, "Mentve az alábbi helyre: "+ fajl.getAbsolutePath() +".xlsx  \n"+ fajl.getName() +".xslx néven!", "Info", 1);
                }                
                    
                resultSet.close();
                stmt.close();
                connection.close();
                Foablak.frame.setCursor(null);                                                                                         //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet                 
            }
         }
    }
}
