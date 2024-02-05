import java.io.File;
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
            
            textPart.setText("Tisztelt " + felado + "! \n  \n Lejárt a határideje a vevői reklamációnál aminél Ön a felelős!"
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
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }
     
            
    }
    
    public void sima_emailkuldes(String felado, String feladoemail, String cimzett, String id, String datum, String cikk, String intezkedes, String hibaleiras)
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
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }
     
            
    }
    
    public void sqa_emailkuldes(String felado, String feladoemail, String cimzett, String id, String datum, String intezkedes)
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
            message.setSubject("Lejár a határidő!");                                            //tárgy beállítása
           
            Multipart multipart = new MimeMultipart();                                      //csatoló osztály példányosítása
           
            MimeBodyPart textPart = new MimeBodyPart();                                     //levél szövegények osztály példányosítása
            
            textPart.setText("Tisztelt " + felado + "! \n  \n 5 munkanapja nem történt semmi a reklamációnál amit Ön inditott!"
                    + "\n Reklamáció ID: " + id
                    + "\n Utolsó történés: \n " + datum                    
                    + "\n Eddig történt: \n " + intezkedes
                    + "\n Kérem minnél elöbb vegye fel a kapcsolatot az illetékessel!");                                          //levél tartalmának csatolása
            multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                   
            message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
            
            Transport.send(message);                                                        //levél küldése

            System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
        
        }
        catch (Exception e1) 
        {
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }        
    }
    
    public void retour_emailkuldes(String feladoemail, String cimzett, String vevo, File excel)
    {
        Properties props = new Properties(); //new Properties();     System.getProperties();
        
        props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
        props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
        Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
        try 
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(feladoemail));                                  //feladó beállítása
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(cimzett));                                                //címzett beállítása
            message.setSubject("Beragadt retour "+ vevo);                                              //tárgy beállítása
           
            Multipart multipart = new MimeMultipart();                                          //csatoló osztály példányosítása
           
            MimeBodyPart textPart = new MimeBodyPart();                                         //levél szövegények osztály példányosítása
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(excel);
            multipart.addBodyPart(attachmentPart);
            textPart.setText("Sziasztok! \n  \n"
                    + "A beragadt retourok a csatolt excelben vannak!!");                                          //levél tartalmának csatolása
            multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                   
            message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
            
            Transport.send(message);                                                        //levél küldése

            System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
        
        }
        catch (Exception e1) 
        {
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }        
    }
    
    public void hibauzenet(String feladoemail, String hibauzenet)
    {
        if(System.getProperty("user.name").equals("toth.zoltan")) {}
        else
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
                    InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));                           //címzett beállítása
                message.setSubject("Hibaüzenet");                                            //tárgy beállítása
               
                Multipart multipart = new MimeMultipart();                                      //csatoló osztály példányosítása
               
                MimeBodyPart textPart = new MimeBodyPart();                                     //levél szövegények osztály példányosítása
                
                textPart.setText(hibauzenet);                                          //levél tartalmának csatolása
                multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                       
                message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
                Foablak.frame.setCursor(null);                                                  //egér mutató alaphelyzetbe állítása
                Transport.send(message);                                                        //levél küldése
    
                System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
            
            }
            catch (Exception e1) 
            {
                String hibauzenet2 = e1.toString();
                //Email hibakuldes = new Email();
                //hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet2);
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                e1.printStackTrace();
                Foablak.frame.setCursor(null);          //egér mutató alaphelyzetbe állítása
            }
        }
            
    }
    
    public void ellenori_email(String feladoemail, String cimzett1, String cimzett2, String cc1, String cc2, String dolgozo, String terület, String datum, String muszak)
    {
        Properties props = new Properties(); //new Properties();     System.getProperties();
        
        props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
        props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
        
        Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
        try 
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(feladoemail));                          //feladó beállítása
            message.addRecipients(Message.RecipientType.TO,
                InternetAddress.parse(cimzett1+","+cimzett2));                                       //címzett beállítása
            message.addRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(cc1+","+cc2));                                       //címzett beállítása
            message.setSubject("Nem töltött ellenőri papírt");                                            //tárgy beállítása
           
            Multipart multipart = new MimeMultipart();                                      //csatoló osztály példányosítása
           
            MimeBodyPart textPart = new MimeBodyPart();                                     //levél szövegények osztály példányosítása
            
            textPart.setText("Sziasztok! \n \n"
                    + dolgozo +" nem töltött papírt a/az "+ terület + " "+ datum +" a/az "+ muszak +" műszakban");                                          //levél tartalmának csatolása
            multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                   
            message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
            
            Transport.send(message);                                                        //levél küldése

            System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
        
        }
        catch (Exception e1) 
        {
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }        
    }
    
    public void zarolas_email(String feladoemail, String cimzettek, String projekt, String cikk, String db, String ok, String ki, String datum, String muszak)
    {
        Properties props = new Properties(); //new Properties();     System.getProperties();
        
        props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
        props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
        
        Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
        try 
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(feladoemail));                          //feladó beállítása
            message.addRecipients(Message.RecipientType.TO,
                InternetAddress.parse(cimzettek));                                       //címzett beállítása
            message.setSubject("Zárolás");                                            //tárgy beállítása
           
            Multipart multipart = new MimeMultipart();                                      //csatoló osztály példányosítása
           
            MimeBodyPart textPart = new MimeBodyPart();                                     //levél szövegények osztály példányosítása
            
            textPart.setText("Sziasztok! \n \n"
                    + "Zárolva lett az alábbi tétel: \n"
                    + "Projekt: "+ projekt+ "\n"
                    + "Cikkszám: "+ cikk + "\n"
                    + "Zárolt db: "+ db +"\n"
                    + "Zárolás oka: "+ ok +"\n"
                    + "Zárolta: "+ ki +"\n"
                    + "Dátum: "+ datum +"\n"
                    + "Műszak: "+ muszak+"\n"
                    + "A zárolás technikusi beavatkozást igényel!");                                          //levél tartalmának csatolása
            multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                   
            message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
            
            Transport.send(message);                                                        //levél küldése

            System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
        
        }
        catch (Exception e1) 
        {
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }        
    }
    
    public void mindenes_email(String feladoemail, String cimzettek, String cc, String targy, String tartalom)
    {
        Properties props =  System.getProperties(); //new Properties();     System.getProperties();
        
        props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
        props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
        
        Session session = Session.getDefaultInstance(props);                               //session létrehozűsa a megadott paraméterekkel            Session.getInstance(props, null);  
        try 
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(feladoemail));                              //feladó beállítása
            message.addRecipients(Message.RecipientType.TO,
                InternetAddress.parse(cimzettek));                                          //címzett beállítása
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(cc));
            message.setSubject(targy);                                                      //tárgy beállítása
           
            //Multipart multipart = new MimeMultipart();                                      //csatoló osztály példányosítása
           
            //MimeBodyPart textPart = new MimeBodyPart();                                     //levél szövegények osztály példányosítása
            
            //textPart.setText(tartalom);                                                     //levél tartalmának csatolása
            //multipart.addBodyPart(textPart);                                                //csatolmány osztály           
                   
            //message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
            message.setText(tartalom);
            
            Transport.send(message);                                                        //levél küldése

            System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
        
        }
        catch (Exception e1) 
        {
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }        
    }

}
