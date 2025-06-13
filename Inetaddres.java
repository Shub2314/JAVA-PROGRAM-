import java.net.*;
class Inetaddres
{
public static void main(String ar[]) throws UnknownHostException
{
      InetAddress addr1 = InetAddress.getLocalHost();
      System.out.println("Inet of LocalHostis  : "+addr1);

     InetAddress addr2 =InetAddress.getByName("www.irctc.co.in");
      System.out.println("Inet of IRCTC is  : "+ addr2);

    InetAddress addr3[]=InetAddress.getAllByName("www.google.com");
         for (int i=0; i<addr3.length; i++) 
            {
                  System.out.println(addr3[i]);
            }
    }
}



