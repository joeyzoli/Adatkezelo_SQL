import javax.swing.JOptionPane;

public class ComboBox 
{
	/*
	 * Ez az osztály a beolvasott fájlokból elkészíti a combobox-ok tartalmát, vagyis egy String tömböt
	 * 
	 */
	private String[] combobox_tomb;
	private Adat_beolvaso beolvas;
	private final String vt_azon = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\vt_lista.csv";
	private final String projekt = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Projektek.csv";
	private final String hiba_helye = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\hiba_helye.csv";
	private final String ellenorok = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ellenőrök.csv";
	private final String hibakodok = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Hibakód.csv";
	
	private void listakeszito_vt()
	{
		try
		{
			beolvas = new Adat_beolvaso();
			beolvas.beolvas_darabol(vt_azon);
			combobox_tomb = new String[beolvas.getdinamikustomb().size()];
			
			for(int szamlalo = 0; szamlalo < beolvas.getdinamikustomb().size(); szamlalo++)
			{
				combobox_tomb[szamlalo] = beolvas.getdinamikustomb().get(szamlalo);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			String hibauzenet2 = ex.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
		}
	}
	
	private void listakeszito_projekt()
	{
		try
		{
			beolvas = new Adat_beolvaso();
			beolvas.beolvas_sima(projekt);
			combobox_tomb = new String[beolvas.getdinamikustomb().size()];
			
			for(int szamlalo = 0; szamlalo < beolvas.getdinamikustomb().size(); szamlalo++)
			{
				combobox_tomb[szamlalo] = beolvas.getdinamikustomb().get(szamlalo);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			String hibauzenet2 = ex.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
		}
	}
	
	private void listakeszito_hiba_helye()
	{
		try
		{
			beolvas = new Adat_beolvaso();
			beolvas.beolvas_sima(hiba_helye);
			combobox_tomb = new String[beolvas.getdinamikustomb().size()];
			
			for(int szamlalo = 0; szamlalo < beolvas.getdinamikustomb().size(); szamlalo++)
			{
				combobox_tomb[szamlalo] = beolvas.getdinamikustomb().get(szamlalo);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			String hibauzenet2 = ex.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
		}
	}
	
	private void listakeszito_ellenorok()
	{
		try
		{
			beolvas = new Adat_beolvaso();
			beolvas.beolvas_sima(ellenorok);
			combobox_tomb = new String[beolvas.getdinamikustomb().size()];
			
			for(int szamlalo = 0; szamlalo < beolvas.getdinamikustomb().size(); szamlalo++)
			{
				combobox_tomb[szamlalo] = beolvas.getdinamikustomb().get(szamlalo);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			String hibauzenet2 = ex.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
		}
	}
	
	private void listakeszito_hibakodok()
	{
		try
		{
			beolvas = new Adat_beolvaso();
			beolvas.beolvas_darabol(hibakodok);
			combobox_tomb = new String[beolvas.getdinamikustomb().size()];
			
			for(int szamlalo = 0; szamlalo < beolvas.getdinamikustomb().size(); szamlalo++)
			{
				combobox_tomb[szamlalo] = beolvas.getdinamikustomb().get(szamlalo);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			String hibauzenet2 = ex.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
		}
	}
	
	public String[] getCombobox()
	{
		listakeszito_vt();
		return combobox_tomb;
	}
	
	public String[] getCombobox_projekt()
	{
		listakeszito_projekt();
		return combobox_tomb;
	}
	
	public String[] getCombobox_hiba()
	{
		listakeszito_hiba_helye();
		return combobox_tomb;
	}
	
	public String[] getCombobox_ellenorok()
	{
		listakeszito_ellenorok();
		return combobox_tomb;
	}
	
	public String[] getCombobox_hibakodok()
	{
		listakeszito_hibakodok();
		return combobox_tomb;
	}

}
