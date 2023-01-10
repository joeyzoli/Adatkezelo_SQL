
public class User_Pass 
{
   private String[] Adri = {"kukac", "beton"};
   
   public Boolean getPass(String pass)
   {
       Boolean igen;
       if(pass.equals(Adri[1]))
       {
           igen = true;
       }
       else
       {
           igen = false;
       }
       return igen;
   }
}
