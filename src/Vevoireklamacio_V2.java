import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Vevoireklamacio_V2 extends JPanel {

    /**
     * Create the panel.
     */
    public Vevoireklamacio_V2() {
        
        JPanel panel = new JPanel();        
        JPanel btnPanel = new JPanel();
        Cmrt_adatok cmrt = new Cmrt_adatok();
        Fajl_atiras fajlok = new Fajl_atiras();
        Ellenori_lapok lapok = new Ellenori_lapok();
        CSV csv = new CSV();
        Techem_OQC techem = new Techem_OQC();
        Telecom_utolso utolsofolyamat = new Telecom_utolso();
        IFS_archive archive = new IFS_archive();
        Tracy_utolso utolsotracy = new Tracy_utolso();
        Gepes_ellenorok ellenorok = new Gepes_ellenorok();
        JRadioButton btn1 = new JRadioButton("D0");
        JRadioButton btn2 = new JRadioButton("D1");
        JRadioButton btn3 = new JRadioButton("D2");
        JRadioButton btn4 = new JRadioButton("D3");
        JRadioButton btn5 = new JRadioButton("D4");
        JRadioButton btn6 = new JRadioButton("D5");
        JRadioButton btn7 = new JRadioButton("D6");
        JRadioButton btn8 = new JRadioButton("D7");
        JRadioButton btn9 = new JRadioButton("D8");
        ButtonGroup csoport = new ButtonGroup();
        csoport.add(btn1);
        csoport.add(btn2);
        csoport.add(btn3);
        csoport.add(btn4);
        csoport.add(btn5);
        csoport.add(btn6);
        csoport.add(btn7);
        csoport.add(btn8);
        csoport.add(btn9);
        CardLayout cardLayout = new CardLayout();
        panel.setLayout(cardLayout);
        panel.add(cmrt, "link1");
        panel.add(fajlok, "link2");
        panel.add(lapok, "link3");
        panel.add(csv, "link4");
        panel.add(techem, "link5");
        panel.add(utolsofolyamat, "link6");
        panel.add(archive, "link7");
        panel.add(utolsotracy, "link8");
        panel.add(ellenorok, "link9");
        btn1.addActionListener(e -> cardLayout.show(panel, "link1"));
        btn2.addActionListener(e -> cardLayout.show(panel, "link2"));
        btn3.addActionListener(e -> cardLayout.show(panel, "link3"));
        btn4.addActionListener(e -> cardLayout.show(panel, "link4"));
        btn5.addActionListener(e -> cardLayout.show(panel, "link5"));
        btn6.addActionListener(e -> cardLayout.show(panel, "link6"));
        btn7.addActionListener(e -> cardLayout.show(panel, "link7"));
        btn8.addActionListener(e -> cardLayout.show(panel, "link8"));
        btn9.addActionListener(e -> cardLayout.show(panel, "link9"));
        btnPanel.add(btn1);
        btnPanel.add(btn2);
        btnPanel.add(btn3);
        btnPanel.add(btn4);
        btnPanel.add(btn5);
        btnPanel.add(btn6);
        btnPanel.add(btn7);
        btnPanel.add(btn8);
        btnPanel.add(btn9);
        setLayout(new BorderLayout(0, 0));
        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.NORTH);
        
        Foablak.frame.setContentPane(this);

    }

}
