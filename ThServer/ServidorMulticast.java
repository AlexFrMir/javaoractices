import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class ServidorMulticast {
    
    static void send_message(byte[]buffer, String ip, int puerto) throws IOException{
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip_grupo = InetAddress.getByName(ip);
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, ip_grupo, puerto);
        socket.send(paquete);
        socket.close();
    }
    public static void main(String[] args) throws Exception {
        send_message("hola".getBytes(), "230.0.0.0", 50000);
        ByteBuffer b = ByteBuffer.allocate(5*8);
        b.putDouble(1.1);
        b.putDouble(1.2);
        b.putDouble(1.3);
        b.putDouble(1.4);
        b.putDouble(1.5);
        send_message(b.array(), "230.0.0.0", 50000);

        
    }
}
