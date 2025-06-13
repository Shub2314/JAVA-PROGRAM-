import java.net.*;
public class DReceiver 
{
    public static void main(String[] args) throws Exception
    {
        DatagramSocket Ds=new DatagramSocket(3000); 
        byte[] buf=new byte[1040];
        DatagramPacket Dp=new DatagramPacket(buf,1040);
        Ds.receive(Dp);
        String str=new String(Dp.getData(),0,Dp.getLength());
        System.out.println("Data :"+str);
        Ds.close();
    }
}
