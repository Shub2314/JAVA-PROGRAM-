import java.net.*;
public class URL_Demo 
{
    public static void main(String[] args) 
    {
        try 
        {
            URL U=new URL("https://www.example.com443/path/to/resource");
            System.out.println("full :"+U);
            System.out.println("protocal :"+U.getProtocol());
            System.out.println("port :"+U.getPort());
            System.out.println("path :"+U.getPath());
            System.out.println("Host :"+U.getHost());
        } 
        catch (Exception e) 
        {
               System.out.println("Error "+e.getMessage());
        }
    }
}
