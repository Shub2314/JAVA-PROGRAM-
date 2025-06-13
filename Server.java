import java.io.*;
import java.net.*;
public class Server 
{
    public static void main(String[] args) throws IOException 
    {
        ServerSocket ss = new ServerSocket(2314);
        Socket s = ss.accept();
        System.out.println("Client connected");
        System.out.println("Hello !!  Client ");
    }
}
