import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLServerSocketFactory;

public class SeverSSL {
    public static void main(String[] args) throws Exception{
        System.setProperty("javax.net.ssl.keyStore", "keystore_servidor.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "1234567");

        SSLServerSocketFactory socket_Factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket server_socket = socket_Factory.createServerSocket(50000);
        Socket Sconnection = server_socket.accept();
        DataOutputStream Soutput = new DataOutputStream(Sconnection.getOutputStream());
        DataInputStream Sinput = new DataInputStream(Sconnection.getInputStream());
        
        double x = Sinput.readDouble();
        System.out.println(x);
        
        Soutput.close();
        Sinput.close();
        Sconnection.close();

    }
}
