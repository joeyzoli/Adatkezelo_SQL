import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JColorChooser;
import javax.swing.JButton;

public class Hatter_beallitas extends JPanel
{
	/**
	 * Ez az osztály a háttér szinének beállítására szolgál
	 */
	private static final long serialVersionUID = 1L;
	protected JColorChooser tcc;
	private Color color = Foablak.hatter_szine;
	private String hattermentes = System.getProperty("user.home") + "\\hatterszin.txt";

	/**
	 * Create the panel.
	 */
	public Hatter_beallitas() 
	{
		this.setPreferredSize(new Dimension(1100, 650));
		
		JLabel lblNewLabel = new JLabel("Háttér színének a beállítása");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton mentes = new JButton("Szín választás");
		mentes.addActionListener(new Hatter_beallit());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addGroup(groupLayout.createSequentialGroup()
		                    .addGap(406)
		                    .addComponent(lblNewLabel))
		                .addGroup(groupLayout.createSequentialGroup()
		                    .addGap(452)
		                    .addComponent(mentes)))
		            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		            .addGap(41)
		            .addComponent(lblNewLabel)
		            .addGap(84)
		            .addComponent(mentes)
		            .addContainerGap(482, Short.MAX_VALUE))
		);
		setBackground(Foablak.hatter_szine);
		setLayout(groupLayout);

	}
	
	class Hatter_beallit implements ActionListener																						//beallít gomb megnyomáskor hívódik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
		    Color initialcolor = Color.BLUE;    
		    color = JColorChooser.showDialog(new Hatter_beallitas(),"Select a color",initialcolor);    
		    Foablak.hatter_szine = color;
		    String hatterszin = String.valueOf(color.getRGB());
		    try
		    {
		    PrintWriter writer = new PrintWriter(hattermentes, "UTF-8");
		    writer.print(hatterszin);
		    writer.close();
		    }
		    catch (IOException e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
		    revalidate();
		    repaint();
		 }
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
