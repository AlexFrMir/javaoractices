import java.net.Socket;
import java.io.*;
class Cliente{
    public static void main(String[] args) throws Exception {
        //Socket conexion = new Socket("desarrollosistemas.sytes.net", 50000);
        Socket conexion = new Socket("sisdis.sytes.net", 20020);
        DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
        DataInputStream entrada = new DataInputStream(conexion.getInputStream());

        salida.writeDouble(2);
        salida.writeDouble(4);
        salida.writeDouble(76);
        salida.writeInt(12);
        
        double x = entrada.readDouble();
        System.out.println(x);

        /*  
            Close
        */
        salida.close();
        entrada.close();
        conexion.close();
    }
}