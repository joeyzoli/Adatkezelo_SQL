import javax.swing.JTextField;

public class Urlap_torlo 
{
	/*
	 * Ez az osztály csak alaphelyzetbe állítja az űrlapot mentés után, hogy ne keljen kitörölni az előző adatot
	 * paraméterként az ürlapelemeket várja
	 */
	public void urlaptorles(JTextField felajanlott, JTextField mintanagysag, JTextField pcb, JTextField pozicio, JTextField hibaszam, JTextField sor)
	{
		felajanlott.setText("0");
		mintanagysag.setText("0");
		pcb.setText("");
		pozicio.setText("");
		hibaszam.setText("0");
		sor.setText("-");
	}
	
	public void urlaptorles_proglove(JTextField felajanlott, JTextField mintanagysag, JTextField pcb, JTextField pozicio, JTextField hiba, JTextField jo)
    {
        felajanlott.setText("0");
        mintanagysag.setText("0");
        pcb.setText("");
        pozicio.setText("");
        hiba.setText("");
        jo.setText("");
    }

}
