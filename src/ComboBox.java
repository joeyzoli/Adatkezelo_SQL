import java.awt.Cursor;

import javax.swing.JOptionPane;

public class ComboBox 
{
	/*
	 * Ez az osztály a beolvasott fájlokból elkészíti a combobox-ok tartalmát, vagyis egy String tömböt
	 * 
	 */
	private String[] combobox_tomb;
	private Adat_beolvaso beolvas;
	static final String vt_azon = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\vt_lista.csv";
	static final String projekt = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Projektek.csv";
	static final String hiba_helye = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\hiba_helye.csv";
	static final String ellenorok = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ellenőrök.csv";
	static final String hibakodok = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Hibakód.csv";
	static final String proglove = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Proglove_termekek.csv";
	static final String folyamat = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Folyamat.csv";
	static final String vevoi_hibaok = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Vevői_hibaokok.csv";
	static final String vevoi_hibaok2 = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Vevői_hibaokok2.csv";
	static final String proglove_rovid = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Proglove_termekek_rovid.csv";
	static final String loxone = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Loxone_termekek.csv";
	static final String loxone_rovid = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Loxone_termekek_rovid.csv";
	static final String vevoi_projekt = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Vevői projektek.csv";
	static final String vevoi_cikk = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Vevői cikkszámok.csv";
	static final String vevoi_proglove = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Vevői proglove.csv";
	
	private void listakeszito(String hely)																						//VT-azonosítókat dolgozza fel
	{
		try
		{
		    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			beolvas = new Adat_beolvaso();																				//példányosítás
			beolvas.futtat_darabol(hely);																			//osztály függvényének meghívása
			combobox_tomb = new String[beolvas.getdinamikustomb().size()];												//tömb méretének meghatározása
			
			for(int szamlalo = 0; szamlalo < beolvas.getdinamikustomb().size(); szamlalo++)								//for cuklus az ArrayList elemeinek átadása egy String tömbnek
			{
				combobox_tomb[szamlalo] = beolvas.getdinamikustomb().get(szamlalo);										//tömb elemeinek feltöltése
			}
			Foablak.frame.setCursor(null);
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			String hibauzenet2 = ex.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
		}
	}
	
	private void listakeszito2(String hely)                                                                                     //VT-azonosítókat dolgozza fel
    {
        try
        {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            beolvas = new Adat_beolvaso();                                                                              //példányosítás
            beolvas.futtat_sima(hely);                                                                           //osztály függvényének meghívása
            combobox_tomb = new String[beolvas.getdinamikustomb().size()];                                              //tömb méretének meghatározása
            
            for(int szamlalo = 0; szamlalo < beolvas.getdinamikustomb().size(); szamlalo++)                             //for cuklus az ArrayList elemeinek átadása egy String tömbnek
            {
                combobox_tomb[szamlalo] = beolvas.getdinamikustomb().get(szamlalo);                                     //tömb elemeinek feltöltése
            }
            Foablak.frame.setCursor(null);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            String hibauzenet2 = ex.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        }
    }
	
	
	
	public String[] getCombobox(String hol)														//lefuttaja a függvényt és visszaadja a tomböt
	{
		listakeszito(hol);
		return combobox_tomb;
	}
	
	public String[] getCombobox2(String hol)                                                        //lefuttaja a függvényt és visszaadja a tomböt
    {
        listakeszito2(hol);
        return combobox_tomb;
    }
	
	

}
