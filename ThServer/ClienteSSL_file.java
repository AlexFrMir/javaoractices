import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

public class ClienteSSL {
    public static void main(String[] args) throws Exception{
        System.setProperty("javax.net.ssl.keyStore", "keystore_cliente.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "1234567");
        SSLSocketFactory client = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket Sconnection = client.createSocket("localhost",50000);
        DataOutputStream Soutput = new DataOutputStream(Sconnection.getOutputStream());
        DataInputStream Sinput = new DataInputStream(Sconnection.getInputStream());
        Soutput.writeDouble(123456789.12345679);
        Soutput.close();
        Sinput.close();
        Sconnection.close();
    }
}
