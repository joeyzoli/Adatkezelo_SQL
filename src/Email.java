import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class Email 
{
    public void emailkuldes(String felado, String feladoemail, String cimzett, String cc, String id, String datum, String cikk, String intezkedes, String hibaleiras)
    {
        Properties props = new Properties(); //new Properties();     System.getProperties();
        
        props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
        props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
        
        Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
        try 
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(feladoemail));                         //feladó beállítása
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(cimzett));                           //címzett beállítása
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(cc));                           //címzett beállítása
            message.setSubject("Lejár a határidő!");                                            //tárgy beállítása
           
            Multipart multipart = new MimeMultipart();                                      //csatoló osztály példányosítása
           
            MimeBodyPart textPart = new MimeBodyPart();                                     //levél szövegények osztály példányosítása
            
            textPart.setText("Tisztelt " + felado + "! \n  \n 5 napon bellül lejár a határideje a vevői reklamációnál aminél Ön a felelős!"
                    + "\n Reklamáció ID: " + id
                    + "\n Reklamáció dátuma: \n " + datum
                    + "\n Reklamált cikkszám: \n " + cikk
                    + "\n Hiba leírása: \n " + hibaleiras
                    + "\n Elvégzendő feladat: \n " + intezkedes
                    + "\n Kérem minnél elöbb zárja le!");                                          //levél tartalmának csatolása
            multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                   
            message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
            
            Transport.send(message);                                                        //levél küldése

            System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
        
        }
        catch (Exception e1) 
        {
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            e1.printStackTrace();
        }
     
            
    }

}
