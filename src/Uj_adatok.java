import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class Uj_adatok extends JPanel 
{
    private JTextField datum;
    private JTextField veas_nr;
    private JTextField avm_nr;
    private JTextField pcb_nr;
    private JTextField pcb_verzio;
    private JTextField vegso;
    private JTextField product;
    private JTextField stencil;
    private JTextField fa10;
    private JTextField fa21;
    private JTextField fa22;
    private JTextField fa23;
    private JTextField u4000;
    private JTextField u4002;
    private Db_iro sql_iras;
    private JTextField tipus;
    private JTextField pcn;
    private JTextField remark;

    /**
     * Create the panel.
     */
    public Uj_adatok() 
    {
        
        JLabel lblNewLabel = new JLabel("Új adat felvitele");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblNewLabel_1 = new JLabel("INTRODUCTION INTO THE MRP");
        
        JLabel lblNewLabel_2 = new JLabel("VEAS PART NUMBER");
        
        JLabel lblNewLabel_3 = new JLabel("AVM PART NUMBER");
        
        JLabel lblNewLabel_4 = new JLabel("PCB PART NR.");
        
        JLabel lblNewLabel_5 = new JLabel("PCB VERSION");
        
        JLabel lblNewLabel_6 = new JLabel("FINAL PROD. PART NR.");
        
        JLabel lblNewLabel_7 = new JLabel("IN PRODUCTION");
        
        JLabel lblNewLabel_8 = new JLabel("NEW REFLOW STENCIL");
        
        datum = new JTextField();
        datum.setColumns(10);
        
        veas_nr = new JTextField();
        veas_nr.setText("");
        veas_nr.setColumns(10);
        
        avm_nr = new JTextField();
        avm_nr.setText("");
        avm_nr.setColumns(10);
        
        pcb_nr = new JTextField();
        pcb_nr.setText("");
        pcb_nr.setColumns(10);
        
        pcb_verzio = new JTextField();
        pcb_verzio.setText("");
        pcb_verzio.setColumns(10);
        
        vegso = new JTextField();
        vegso.setColumns(10);
        
        product = new JTextField();
        product.setColumns(10);
        
        stencil = new JTextField();
        stencil.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("FA10");
        
        fa10 = new JTextField();
        fa10.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("FA21");
        
        fa21 = new JTextField();
        fa21.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("FA22");
        
        fa22 = new JTextField();
        fa22.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("FA23");
        
        fa23 = new JTextField();
        fa23.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("U4000");
        
        u4000 = new JTextField();
        u4000.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("U4002");
        
        u4002 = new JTextField();
        u4002.setColumns(10);
        
        JButton mentes = new JButton("Mentés");
        mentes.addActionListener(new Adat_mentes());
        
        JLabel lblNewLabel_15 = new JLabel("Típus");
        
        tipus = new JTextField();
        tipus.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("PCN");
        
        pcn = new JTextField();
        pcn.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("REMARK");
        
        remark = new JTextField();
        remark.setColumns(10);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(486)
                    .addComponent(lblNewLabel)
                    .addContainerGap(532, Short.MAX_VALUE))
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(48)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel_1)
                        .addComponent(datum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_9)
                        .addComponent(fa10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel_2)
                        .addComponent(veas_nr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_10)
                        .addComponent(fa21, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel_3)
                        .addComponent(avm_nr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_11)
                        .addComponent(fa22, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblNewLabel_15)
                            .addGap(18)
                            .addComponent(tipus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(535))
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                            .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(mentes)
                                .addContainerGap())
                            .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                            .addComponent(lblNewLabel_4)
                                            .addComponent(pcb_nr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNewLabel_12))
                                        .addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                            .addComponent(pcb_verzio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNewLabel_5)
                                            .addComponent(lblNewLabel_13))
                                        .addGap(18)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                            .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                    .addComponent(vegso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblNewLabel_6))
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                    .addGroup(groupLayout.createSequentialGroup()
                                                        .addGap(18)
                                                        .addComponent(lblNewLabel_7))
                                                    .addGroup(groupLayout.createSequentialGroup()
                                                        .addGap(17)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                            .addComponent(lblNewLabel_16)
                                                            .addComponent(product, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(pcn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                            .addComponent(lblNewLabel_14)))
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(fa23, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(u4000, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18)
                                        .addComponent(u4002, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(150)))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(lblNewLabel_8)
                                    .addComponent(stencil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNewLabel_17)
                                    .addComponent(remark, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(120)))))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_15)
                        .addComponent(tipus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(23)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_1)
                        .addComponent(lblNewLabel_2)
                        .addComponent(lblNewLabel_3)
                        .addComponent(lblNewLabel_4)
                        .addComponent(lblNewLabel_5)
                        .addComponent(lblNewLabel_6)
                        .addComponent(lblNewLabel_7)
                        .addComponent(lblNewLabel_8))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(datum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(veas_nr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(avm_nr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pcb_nr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pcb_verzio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(vegso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(product, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(stencil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(78)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_9)
                        .addComponent(lblNewLabel_10)
                        .addComponent(lblNewLabel_11)
                        .addComponent(lblNewLabel_12)
                        .addComponent(lblNewLabel_13)
                        .addComponent(lblNewLabel_14)
                        .addComponent(lblNewLabel_16)
                        .addComponent(lblNewLabel_17))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(fa10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(fa21, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(fa22, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(fa23, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(u4000, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(u4002, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pcn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(remark, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(81)
                    .addComponent(mentes)
                    .addContainerGap(200, Short.MAX_VALUE))
        );
        setBackground(Foablak.hatter_szine);
        setLayout(groupLayout);

    }
    
    class Adat_mentes implements ActionListener                                                                                      //beallít gomb megnyomáskor hívódik meg
    {
        public void actionPerformed(ActionEvent e)
         {
           sql_iras = new Db_iro();
           sql_iras.iro_muszaki(tipus.getText(), datum.getText(), veas_nr.getText(), avm_nr.getText(), pcb_nr.getText(), pcb_verzio.getText(), vegso.getText(), product.getText(), stencil.getText(),
                                   fa10.getText(), fa21.getText(), fa22.getText(), fa23.getText(), u4000.getText(), u4002.getText(), pcn.getText(), remark.getText());
         }
    }
}
