import java.net.Socket;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        //Socket SockerConnection = new Socket("desarrollosistemas.sytes.net", 50000);
        Socket SockerConnection = new Socket("localhost", 40000);
        DataOutputStream output = new DataOutputStream(SockerConnection.getOutputStream());
        DataInputStream input = new DataInputStream(SockerConnection.getInputStream());

        for(Double i = 0.0; i < 10000.0; i ++){
            output.writeDouble(i);
        }
        System.out.println(System.currentTimeMillis());

        /*  
            Closing connection
        */
        
        output.close();
        input.close();
        SockerConnection.close();
    }
}
