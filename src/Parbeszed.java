import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class Parbeszed extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jelszo;
	private static final String jelszavam = "polip13";
	private JTextField mezo = new JTextField(5);
	private Torlo torles;
	private Foablak foablak;
	
	Parbeszed()
	{
		JButton gomb = new JButton("Ok");
		gomb.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jelszo = mezo.getText();
				if(jelszavam.equals(jelszo))
				{
					torles = new Torlo();
					foablak.setContentPane(torles);
					pack();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Helytelen jelszó", "Hiba üzenet", 2);
				}
				setVisible(false);
			}
		});
		add(new JLabel("Név: "));
		add(mezo);
		add(gomb);
		setLayout(new FlowLayout());
		pack();
	}
	
	public void ellenorzo()
	{
		setModal(true);
		setVisible(true);
	}

}