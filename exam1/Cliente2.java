import java.net.Socket;
import java.io.*;
class Cliente2{
    public static void main(String[] args) throws Exception {
        //Socket conexion = new Socket("desarrollosistemas.sytes.net", 50000);
        Socket conexion = new Socket("sisdis.sytes.net", 50000);
        DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
        DataInputStream entrada = new DataInputStream(conexion.getInputStream());

        salida.writeInt(39);
        salida.writeInt(53);
        salida.writeDouble(95);
        salida.writeDouble(4);
        
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