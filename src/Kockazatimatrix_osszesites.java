import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Kockazatimatrix_osszesites extends JPanel {
    private JTable table;
    private JRadioButton felett;
    private JRadioButton alatt;
    private JRadioButton minden;
    private DefaultTableModel modell;

    /**
     * Create the panel.
     */
    public Kockazatimatrix_osszesites() {
        setLayout(null);
        
        this.setPreferredSize(new Dimension(1200, 810));
        Foablak.meretek.setSize(1250, 900);
        
        JLabel lblNewLabel = new JLabel("Kockázatok összesítése értékelé alapján");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(504, 43, 390, 14);
        add(lblNewLabel);
        
        felett = new JRadioButton("12.5 felett");
        felett.setBounds(576, 99, 109, 23);
        add(felett);
        
        alatt = new JRadioButton("12.5 alatt");
        alatt.setBounds(576, 135, 109, 23);
        add(alatt);
        
        minden = new JRadioButton("Minden kockázat");
        minden.setBounds(576, 168, 130, 23);
        add(minden);
        
        ButtonGroup csoport = new ButtonGroup();
        csoport.add(alatt);
        csoport.add(felett);
        csoport.add(minden);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Kereses());
        keres_gomb.setBounds(560, 211, 89, 23);
        add(keres_gomb);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Kockázat","Jelen intézkedés","Valószínűség","Következmény","Kockázati szint"});
        table = new JTable();        
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(32, 264, 1184, 220);
        add(gorgeto);
        
        setBackground(Foablak.hatter_szine);

    }
    
    private class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
        WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            return this;
        }
    }
    
    class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                int valasztas = 0;
                if(minden.isSelected()) valasztas = 1;
                if(alatt.isSelected()) valasztas = 1;
                if(felett.isSelected()) valasztas = 1;
                if(valasztas < 1)
                {
                    JOptionPane.showMessageDialog(null, "Nincs keresési feltétel kiválasztva!!", "Hiba üzenet", 2);
                }
                else
                {
                    int rowCount = modell.getRowCount();
                    for (int i = rowCount - 1; i > -1; i--) 
                    {
                      modell.removeRow(i);
                    }                
                    //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                    Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                        
                    Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
                    Statement stmt = con.createStatement();
                    String sql = "Select * from qualitydb.Kockazatimatrix_alap where 3 = 3";
                    ResultSet rs = stmt.executeQuery(sql);
                    ArrayList<String> adatok = new ArrayList<String>();
                    float osztando = 0;
                    float oszto = 0;
                    float osztando_k = 0;
                    float oszto_k = 0;
                    while(rs.next())
                    {
                        osztando = 0;
                        oszto = 0;
                        osztando_k = 0;
                        oszto_k = 0;
                        if(rs.getString(7).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(7));
                            oszto++;
                        }
                        if(rs.getString(10).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(10));
                            oszto++;
                        }
                        if(rs.getString(13).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(13));
                            oszto++;
                        }
                        if(rs.getString(16).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(16));
                            oszto++;
                        }
                        if(rs.getString(19).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(19));
                            oszto++;
                        }
                        if(rs.getString(22).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(22));
                            oszto++;
                        }
                        if(rs.getString(25).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(25));
                            oszto++;
                        }
                        if(rs.getString(28).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(28));
                            oszto++;
                        }
                        if(rs.getString(31).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(31));
                            oszto++;
                        }
                        if(rs.getString(34).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(34));
                            oszto++;
                        }
                        if(rs.getString(37).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(37));
                            oszto++;
                        }
                        if(rs.getString(40).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(40));
                            oszto++;
                        }
                        if(rs.getString(43).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(43));
                            oszto++;
                        }
                        if(rs.getString(46).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(46));
                            oszto++;
                        }
                        if(rs.getString(49).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(49));
                            oszto++;
                        }
                        if(rs.getString(52).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(52));
                            oszto++;
                        }
                        if(rs.getString(55).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(55));
                            oszto++;
                        }
                        if(rs.getString(58).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(58));
                            oszto++;
                        }
                        if(rs.getString(61).equals("")){}
                        else
                        {
                            osztando += Integer.parseInt(rs.getString(61));
                            oszto++;
                        }
                        /////////////////////////// most pedig a következmény//////////////////////////////////
                        if(rs.getString(8).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(8));
                            oszto_k++;
                        }
                        if(rs.getString(11).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(11));
                            oszto_k++;
                        }
                        if(rs.getString(14).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(14));
                            oszto_k++;
                        }
                        if(rs.getString(17).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(17));
                            oszto_k++;
                        }
                        if(rs.getString(20).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(20));
                            oszto_k++;
                        }
                        if(rs.getString(23).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(23));
                            oszto_k++;
                        }
                        if(rs.getString(26).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(26));
                            oszto_k++;
                        }
                        if(rs.getString(29).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(29));
                            oszto_k++;
                        }
                        if(rs.getString(32).equals("") || rs.getString(32).equals(" ")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(32));
                            oszto_k++;
                        }
                        if(rs.getString(35).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(35));
                            oszto_k++;
                        }
                        if(rs.getString(38).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(38));
                            oszto_k++;
                        }
                        if(rs.getString(41).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(41));
                            oszto_k++;
                        }
                        if(rs.getString(44).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(44));
                            oszto_k++;
                        }
                        if(rs.getString(47).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(47));
                            oszto_k++;
                        }
                        if(rs.getString(50).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(50));
                            oszto_k++;
                        }
                        if(rs.getString(53).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(53));
                            oszto_k++;
                        }
                        if(rs.getString(56).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(56));
                            oszto_k++;
                        }
                        if(rs.getString(59).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(59));
                            oszto_k++;
                        }
                        if(rs.getString(62).equals("")){}
                        else
                        {
                            osztando_k += Integer.parseInt(rs.getString(62));
                            oszto_k++;
                        }
                        
                        float atlag = osztando/oszto;
                        float atlag_k = osztando_k/oszto_k;
                        float kockazat = atlag*atlag_k;
                        adatok.add(rs.getString(4)+";"+ rs.getString(5)+";"+ String.format("%.2f", atlag)+";"+ String.format("%.2f", atlag_k)+";"+ String.format("%.2f", kockazat));
                        //modell.addRow(new Object[]{rs.getString(4), rs.getString(5), String.format("%.2f", atlag), String.format("%.2f", atlag_k)});
                    }
                    if(minden.isSelected())
                    {
                        for(int szamlalo = 0; szamlalo < adatok.size();szamlalo++)
                        {
                            String[] darabol = adatok.get(szamlalo).split(";");
                            modell.addRow(new Object[]{darabol[0], darabol[1], darabol[2], darabol[3], darabol[4]});
                        }
                    }
                    if(felett.isSelected())
                    {
                        for(int szamlalo = 0; szamlalo < adatok.size();szamlalo++)
                        {
                            String[] darabol = adatok.get(szamlalo).split(";");
                            String lebego = darabol[4].replace(",", ".");
                            try
                            {
                                if(Float.parseFloat(lebego) >= 12.5)
                                {
                                    modell.addRow(new Object[]{darabol[0], darabol[1], darabol[2], darabol[3], darabol[4]});
                                }
                            }
                            catch (Exception e1)
                            {
                                
                            }
                        }
                    }
                    if(alatt.isSelected())
                    {
                        for(int szamlalo = 0; szamlalo < adatok.size();szamlalo++)
                        {
                            String[] darabol = adatok.get(szamlalo).split(";");
                            String lebego = darabol[4].replace(",", ".");
                            try
                            {
                                if(Float.parseFloat(lebego) < 12.5)
                                {
                                    modell.addRow(new Object[]{darabol[0], darabol[1], darabol[2], darabol[3], darabol[4]});
                                }
                            }
                            catch (Exception e1)
                            {
                                
                            }
                        }
                    }
                    final TableColumnModel columnModel = table.getColumnModel();
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
                    //table.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
                    //table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());
                    table.setModel(modell);
                }
                Foablak.frame.setCursor(null);
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
    }
}
