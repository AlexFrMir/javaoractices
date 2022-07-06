import java.net.Socket;
import java.io.*;
class Cliente3{
    public static void main(String[] args) throws Exception {
        //Socket conexion = new Socket("desarrollosistemas.sytes.net", 50000);
        Socket conexion = new Socket("sisdis.sytes.net", 50000);
        DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
        DataInputStream entrada = new DataInputStream(conexion.getInputStream());
        long t1 = 1632744934;
        System.out.println(t1);
        long t2 = entrada.readLong();
        System.out.println(t2);
        long t3 = entrada.readLong();
        System.out.println(t3);
        long t4 = t3+3;
        System.out.println(t4);
        System.out.println(t4-t1);
        System.out.println(t3-t2);
        System.out.println((38-15)/2);
        
        

        /*  
            Close
        */
        salida.close();
        entrada.close();
        conexion.close();
    }
}