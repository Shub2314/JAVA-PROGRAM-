import java.net.*;
public class InstanceMethodOfInet 
{
    public static void main(String[] args) throws UnknownHostException 
    {
        InetAddress addr1 =InetAddress.getByName("www.google.com");
        InetAddress addr2 =InetAddress.getByName("www.irctc.co.in");
       
        //equals methood
        System.out.println("both address are equal :"+addr1.equals(addr2));

        //getHostAddress method
        System.out.println("Host address :"+addr1.getHostAddress());

        //getHostName method
        System.out.println("Host name :"+addr1.getHostName());

        //toString method
        System.out.println("convert into string :"+addr1.toString());
    }
}