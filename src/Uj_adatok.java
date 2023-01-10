import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
    private JTextField pcn;
    private JTextField remark;
    private JComboBox<String> tipus;

    /**
     * Create the panel.
     */
    public Uj_adatok() 
    {
        
        JLabel lblNewLabel = new JLabel("Új adat felvitele");
        lblNewLabel.setBounds(486, 11, 92, 17);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblNewLabel_1 = new JLabel("INTRODUCTION INTO THE MRP");
        lblNewLabel_1.setBounds(48, 89, 152, 14);
        
        JLabel lblNewLabel_2 = new JLabel("VEAS PART NUMBER");
        lblNewLabel_2.setBounds(218, 89, 98, 14);
        
        JLabel lblNewLabel_3 = new JLabel("AVM PART NUMBER");
        lblNewLabel_3.setBounds(334, 89, 94, 14);
        
        JLabel lblNewLabel_4 = new JLabel("PCB PART NR.");
        lblNewLabel_4.setBounds(446, 89, 69, 14);
        
        JLabel lblNewLabel_5 = new JLabel("PCB VERSION");
        lblNewLabel_5.setBounds(557, 89, 66, 14);
        
        JLabel lblNewLabel_6 = new JLabel("FINAL PROD. PART NR.");
        lblNewLabel_6.setBounds(661, 89, 114, 14);
        
        JLabel lblNewLabel_7 = new JLabel("IN PRODUCTION");
        lblNewLabel_7.setBounds(793, 89, 81, 14);
        
        JLabel lblNewLabel_8 = new JLabel("NEW REFLOW STENCIL");
        lblNewLabel_8.setBounds(896, 89, 112, 14);
        
        datum = new JTextField();
        datum.setBounds(48, 121, 86, 20);
        datum.setColumns(10);
        
        veas_nr = new JTextField();
        veas_nr.setBounds(218, 121, 86, 20);
        veas_nr.setText("");
        veas_nr.setColumns(10);
        
        avm_nr = new JTextField();
        avm_nr.setBounds(334, 121, 86, 20);
        avm_nr.setText("");
        avm_nr.setColumns(10);
        
        pcb_nr = new JTextField();
        pcb_nr.setBounds(446, 121, 86, 20);
        pcb_nr.setText("");
        pcb_nr.setColumns(10);
        
        pcb_verzio = new JTextField();
        pcb_verzio.setBounds(557, 121, 86, 20);
        pcb_verzio.setText("");
        pcb_verzio.setColumns(10);
        
        vegso = new JTextField();
        vegso.setBounds(661, 121, 86, 20);
        vegso.setColumns(10);
        
        product = new JTextField();
        product.setBounds(792, 121, 86, 20);
        product.setColumns(10);
        
        stencil = new JTextField();
        stencil.setBounds(896, 121, 86, 20);
        stencil.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("FA10");
        lblNewLabel_9.setBounds(48, 219, 25, 14);
        
        fa10 = new JTextField();
        fa10.setBounds(48, 251, 86, 20);
        fa10.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("FA21");
        lblNewLabel_10.setBounds(218, 219, 25, 14);
        
        fa21 = new JTextField();
        fa21.setBounds(218, 251, 86, 20);
        fa21.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("FA22");
        lblNewLabel_11.setBounds(334, 219, 25, 14);
        
        fa22 = new JTextField();
        fa22.setBounds(334, 251, 86, 20);
        fa22.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("FA23");
        lblNewLabel_12.setBounds(446, 219, 25, 14);
        
        fa23 = new JTextField();
        fa23.setBounds(446, 251, 86, 20);
        fa23.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("U4000");
        lblNewLabel_13.setBounds(557, 219, 31, 14);
        
        u4000 = new JTextField();
        u4000.setBounds(538, 251, 86, 20);
        u4000.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("U4002");
        lblNewLabel_14.setBounds(661, 219, 31, 14);
        
        u4002 = new JTextField();
        u4002.setBounds(642, 251, 86, 20);
        u4002.setColumns(10);
        
        JButton mentes = new JButton("Mentés");
        mentes.setBounds(446, 352, 67, 23);
        mentes.addActionListener(new Adat_mentes());
        
        JLabel lblNewLabel_15 = new JLabel("Típus");
        lblNewLabel_15.setBounds(446, 49, 25, 14);
        
        JLabel lblNewLabel_16 = new JLabel("PCN");
        lblNewLabel_16.setBounds(792, 219, 20, 14);
        
        pcn = new JTextField();
        pcn.setBounds(792, 251, 86, 20);
        pcn.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("REMARK");
        lblNewLabel_17.setBounds(896, 219, 41, 14);
        
        remark = new JTextField();
        remark.setBounds(896, 251, 86, 20);
        remark.setColumns(10);
        setBackground(Foablak.hatter_szine);
        setLayout(null);
        add(lblNewLabel);
        add(lblNewLabel_1);
        add(datum);
        add(lblNewLabel_9);
        add(fa10);
        add(lblNewLabel_2);
        add(veas_nr);
        add(lblNewLabel_10);
        add(fa21);
        add(lblNewLabel_3);
        add(avm_nr);
        add(lblNewLabel_11);
        add(fa22);
        add(lblNewLabel_15);
        add(mentes);
        add(lblNewLabel_4);
        add(pcb_nr);
        add(lblNewLabel_12);
        add(pcb_verzio);
        add(lblNewLabel_5);
        add(lblNewLabel_13);
        add(vegso);
        add(lblNewLabel_6);
        add(lblNewLabel_7);
        add(lblNewLabel_16);
        add(product);
        add(pcn);
        add(lblNewLabel_14);
        add(fa23);
        add(u4000);
        add(u4002);
        add(lblNewLabel_8);
        add(stencil);
        add(lblNewLabel_17);
        add(remark);
        
        String[] tipusok = {"FR1200AX", "FB7530", "FB7530AX"};
        tipus = new JComboBox<String>(tipusok);
        tipus.setBounds(486, 45, 137, 22);
        add(tipus);

    }
    
    class Adat_mentes implements ActionListener                                                                                      //beallít gomb megnyomáskor hívódik meg
    {
        public void actionPerformed(ActionEvent e)
         {
           sql_iras = new Db_iro();
           sql_iras.iro_muszaki(String.valueOf(tipus.getSelectedItem()), datum.getText(), veas_nr.getText(), avm_nr.getText(), pcb_nr.getText(), pcb_verzio.getText(), vegso.getText(), product.getText(), stencil.getText(),
                                   fa10.getText(), fa21.getText(), fa22.getText(), fa23.getText(), u4000.getText(), u4002.getText(), pcn.getText(), remark.getText());
         }
    }
}
