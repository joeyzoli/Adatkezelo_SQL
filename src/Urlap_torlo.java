import javax.swing.JTextField;

public class Urlap_torlo 
{
	/*
	 * Ez az osztály csak alaphelyzetbe állítja az űrlapot mentés után, hogy ne keljen kitörölni az előző adatot
	 * paraméterként az ürlapelemeket várja
	 */
	public void urlaptorles(JTextField datum, JTextField muszak, JTextField felajanlott, JTextField mintanagysag, JTextField pcb, JTextField pozicio, JTextField hibaszam, JTextField sor)
	{
		datum.setText("");
		muszak.setText("");
		felajanlott.setText("0");
		mintanagysag.setText("0");
		pcb.setText("");
		pozicio.setText("");
		hibaszam.setText("0");
		sor.setText("-");
	}

}
