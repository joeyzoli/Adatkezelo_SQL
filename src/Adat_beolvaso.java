import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Adat_beolvaso 
{
	/*
	 *	Ez az osztály a különböző combobox elemeket tartalmazó fájlokat olvassa ba 
	 */
	
	private File adatok;
	private ArrayList<String> dinamikustomb = new ArrayList<String>();					//változó amiben tárolom a beolvasott adatokat
	
	public void beolvas_darabol(String hely)
	{
		dinamikustomb.clear();														//tömblista ürítése
		adatok = new File(hely);				
		String[] adathalmaz;
		String koztes;
		try (BufferedReader beolvaso = new BufferedReader(new FileReader(adatok, StandardCharsets.UTF_8))) 
		{
			String buffer = beolvaso.readLine();
				
			while((buffer = beolvaso.readLine()) != null)							//addig fut amíg nem üres a sor, vagyis a fájl végéig
			{
				adathalmaz = buffer.split(",");
					
				if(adathalmaz.length == 1)
				{
					koztes = adathalmaz[0];
					dinamikustomb.add(koztes);	
				}
				else if(adathalmaz.length == 2)
				{
					koztes = adathalmaz[0] + "-" + adathalmaz[1];
					dinamikustomb.add(koztes);
				}
				else
				{
					koztes = adathalmaz[0] + " - " + adathalmaz[1] + " - " + adathalmaz[2] + " - " + adathalmaz[3];
					dinamikustomb.add(koztes);	
				}
			}
		}			
		catch(Exception e)
		{
			System.out.println(e);
			String hibauzenet2 = e.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
		}
	}
	
	public void beolvas_sima(String hely)
	{
		dinamikustomb.clear();
		adatok = new File(hely);
			
		try (BufferedReader beolvaso = new BufferedReader(new FileReader(adatok, StandardCharsets.UTF_8))) 
		{
			String buffer;
				
			while((buffer = beolvaso.readLine()) != null)
			{
				dinamikustomb.add(buffer);	
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			String hibauzenet2 = e.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
		}
	}
	
	public ArrayList<String> getdinamikustomb()
	{
		return dinamikustomb;
	}

}
