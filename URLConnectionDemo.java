import java.net.*;
import java.io.*;
public class URLConnectionDemo {
    public static void main(String[] args) throws IOException
    {
        try {
            URL U = new URL("https://www.tpointtech.com/java-tutorial");
            URLConnection conn = U.openConnection();
            conn.connect();
            System.out.println("Connection established");
            InputStream stream=conn.getInputStream();
                 int i;
            while ((i=stream.read()) !=-1)
            {
                System.out.print((char)i);
            }      
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
}
