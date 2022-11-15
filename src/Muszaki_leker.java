import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Muszaki_leker extends JPanel 
{
    private JTextField tipus;
    static JTextArea eredmeny = new JTextArea();
    static JScrollPane scrollPane = new JScrollPane(eredmeny);

    /**
     * Create the panel.
     */
    public Muszaki_leker() 
    {
        
        JLabel lblNewLabel = new JLabel("Műszaki adatok lekérdezése");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel lblNewLabel_1 = new JLabel("Típus");
        
        tipus = new JTextField();
        tipus.setColumns(10);
        
        JButton lekerdez = new JButton("Lekérdez");
        lekerdez.addActionListener(new Adat_mentes());
        
        //eredmeny = new JTextArea();
        
        //scrollPane = new JScrollPane(eredmeny);
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(474)
                            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(lblNewLabel)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblNewLabel_1)
                                    .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lekerdez)
                                        .addComponent(tipus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(47)
                            .addComponent(eredmeny, GroupLayout.PREFERRED_SIZE, 937, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(99, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(51)
                    .addComponent(lblNewLabel)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(18)
                            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblNewLabel_1)
                                .addComponent(tipus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18)
                            .addComponent(lekerdez)
                            .addGap(28)
                            .addComponent(eredmeny, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(35, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(219))))
        );
        setBackground(Foablak.hatter_szine);
        setLayout(groupLayout);

    }
    
    class Adat_mentes implements ActionListener                                                                                      //beallít gomb megnyomáskor hívódik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            SQL lekerdez = new SQL();
            lekerdez.muszaki_lekerdezo(tipus.getText());
            Foablak.frame.setCursor(null);
         }
    }
}
