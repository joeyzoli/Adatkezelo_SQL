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
	private ArrayList<String> dinamikustomb = new ArrayList<String>();							//változó amiben tárolom a beolvasott adatokat
	
	private void beolvas_darabol(String hely)													//függvény ami a darabolandó Stringeket tartalmazó fájlokat olvassa be 
	{
		dinamikustomb.clear();																	//tömblista ürítése
		adatok = new File(hely);				
		String[] adathalmaz;
		String koztes;
		try (BufferedReader beolvaso = new BufferedReader(new FileReader(adatok, StandardCharsets.UTF_8))) 
		{
			String buffer = beolvaso.readLine();
				
			while((buffer = beolvaso.readLine()) != null)										//addig fut amíg nem üres a sor, vagyis a fájl végéig
			{
				adathalmaz = buffer.split(",");													//string darabolása a vessző melett
					
				if(adathalmaz.length == 1)														//ha nincsen benne vessző és csak 1 elemű a tömb
				{
					dinamikustomb.add(adathalmaz[0]);											//tömb feltöltése
				}
				else if(adathalmaz.length == 2)													//2 elemű tömb esetén történik
				{
					koztes = adathalmaz[0] + "-" + adathalmaz[1];
					dinamikustomb.add(koztes);
				}
				else																			//ha egyik feltétel sem állt meg akkor fut le
				{
					koztes = adathalmaz[0] + " - " + adathalmaz[1] + " - " + adathalmaz[2] + " - " + adathalmaz[3];
					dinamikustomb.add(koztes);	
				}
			}
		}			
		catch(Exception e)							//kivételkezelés
		{
			System.out.println(e);
			String hibauzenet2 = e.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
		}
	}
	
	private void beolvas_sima(String hely)						//függvény ami a soronként 1 adatot tartalmazó fájlokat olvassa be
	{
		dinamikustomb.clear();									//tömb ürítése
		adatok = new File(hely);
			
		try (BufferedReader beolvaso = new BufferedReader(new FileReader(adatok, StandardCharsets.UTF_8))) 						//beolvasás UTF8 kódolással
		{
			String buffer;
				
			while((buffer = beolvaso.readLine()) != null)										//ciklus addig fut amíg a végére nem ér a fájlnak
			{
				dinamikustomb.add(buffer);														//tömb feltöltése
			}
		}
		catch(Exception e)																		//kivételkezelés
		{
			System.out.println(e);
			String hibauzenet2 = e.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
		}
	}
	
	public void futtat_darabol(String hely)														//lefuttatja a privát függvényt
	{
		beolvas_darabol(hely);
	}
	
	public void futtat_sima(String hely)														//lefuttatja a privát függvényt
	{
		beolvas_sima(hely);
	}
	
	public ArrayList<String> getdinamikustomb()													//visszaadja a függvények álltal feltöltött tömböt
	{
		return dinamikustomb;
	}
	
	

}
