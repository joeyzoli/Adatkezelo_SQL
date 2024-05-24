import javax.swing.JTextArea;
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
	
	public void urlaptorles_proglove(JTextField felajanlott, JTextField mintanagysag, JTextField pcb, JTextArea pozicio, JTextField hiba, JTextField jo, JTextField sum, JTextField hibas, JTextField arany)
    {
        felajanlott.setText("0");
        mintanagysag.setText("0");
        pcb.setText("");
        pozicio.setText("");
        hiba.setText("");
        jo.setText("");
        sum.setText("");
        hibas.setText("");
        arany.setText("");
    }
	
	public void urlaptorles_veoi(JTextField datum, JTextField reklamaltdb, JTextArea hibaleiras, JTextField gyartas, JTextField rma, JTextField hibaoka, JTextField hibaoka2)
    {
        datum.setText("");
        reklamaltdb.setText("");
        hibaleiras.setText("");
        gyartas.setText("");
        rma.setText("");
        hibaoka.setText("");
        hibaoka2.setText("");
    }
	
	public void urlaptorles_retour(JTextField datum, JTextField beerkezett, JTextField elteres, JTextField rma, JTextField megjegyzes, JTextField hovamezo, JTextField hovadatum, JTextField hovafelelos, JTextField tesztdatum,
	        JTextField tesztfelelos, JTextField vegdatum, JTextField vegfelelos, JTextField rakdatum, JTextField rakdb, JTextField selejt, JTextField vevoirma)
    {
        datum.setText("");
        beerkezett.setText("");
        elteres.setText("");     
        rma.setText("");
        megjegyzes.setText("");
        hovamezo.setText("");
        hovadatum.setText("");
        hovafelelos.setText("");
        tesztdatum.setText("");
        tesztfelelos.setText("");
        vegdatum.setText("");
        vegfelelos.setText("");
        rakdatum.setText("");
        rakdb.setText("");
        selejt.setText("");
        vevoirma.setText("");
    }

}
