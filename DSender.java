import java.net.*;
public class DSender {
    public static void main(String[] args) throws Exception {
        DatagramSocket Ds = new DatagramSocket();
        String str = "WEL-COME to java programminng";
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        DatagramPacket Dp = new DatagramPacket(str.getBytes(), str.length(), ip, 3000); // Correct port
        Ds.send(Dp);
        Ds.close();
    }
}
