/* Importing Files */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/* Class Server */

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(40000);
        Socket SocketConnection = server.accept();
        DataOutputStream output = new DataOutputStream(SocketConnection.getOutputStream());
        DataInputStream input = new DataInputStream(SocketConnection.getInputStream());
        
        double x;
        //System.out.println(x);
        for(int i = 0; i < 10000; i++){
            x = input.readDouble();
        }
        System.out.println(System.currentTimeMillis());
        /*  
            Closing Connection
        */

        output.close();
        input.close();
        SocketConnection.close();
    }
}
