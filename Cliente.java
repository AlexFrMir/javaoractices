import java.net.Socket;
import java.io.*;
class Cliente{
    public static void main(String[] args) throws Exception {
        //Socket conexion = new Socket("desarrollosistemas.sytes.net", 50000);
        Socket conexion = new Socket("sisdis.sytes.net", 20020);
        DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
        DataInputStream entrada = new DataInputStream(conexion.getInputStream());

        salida.writeInt(123);
        salida.writeDouble(1234567890.1234567890);
        salida.write("Hola".getBytes());
        
        byte[] buffer = new byte[4];
        entrada.read(buffer,0,4);
        System.out.println(new String(buffer,"UTF-8"));

        /*  
            Close
        */
        salida.close();
        entrada.close();
        conexion.close();
    }
}