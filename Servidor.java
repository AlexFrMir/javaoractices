import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(40000);
        Socket conection = server.accept();
        DataOutputStream output = new DataOutputStream(conection.getOutputStream());
        DataInputStream input = new DataInputStream(conection.getInputStream());
        
        int n = input.readInt();
        System.out.println(n);

        double x = input.readDouble();
        System.out.println(x);

        byte[] buffer = new byte[4];
        input.read(buffer,0,4);
        System.out.println(new String(buffer,"UTF-8"));

        output.write("HOLA".getBytes());

        byte[] a = new byte[5*8];
        input.read(a,0,5*8);

        ByteBuffer b = ByteBuffer.wrap(a);
        for(int i = 0; i < 5; i++) {
            System.out.println(b.getDouble());
        }


        output.close();
        input.close();
        conection.close();
    }
}
