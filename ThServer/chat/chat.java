import java.io.Console;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Scanner;


public class chat {
    DatagramSocket socket;
    DatagramPacket paquete;
    static String name;
    static String mensaje;

    static Object obj = new Object();
    static int port;
    static class Worker extends Thread {   

        public void run(){
            byte[] a;
            // TODO Auto-generated method stub
            // En un ciclo infinito se recibiran los mensajes enviados al
            // grupo 230.0.00 a travez del puerto 40000 y se desplegara n en la pantalla 
            for( ; ;){
                try {
                        InetAddress ip_grupo = InetAddress.getByName("230.0.0.0");
                        MulticastSocket socket = new MulticastSocket(40000);
                        socket.joinGroup(ip_grupo);
                        a = recibe_mensaje_multicast(socket, 255);
                        System.out.println(new String(a,"UTF-8"));
                        socket.leaveGroup(ip_grupo);
                        socket.close();  
                      
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        }
    }

    static byte[] recibe_mensaje_multicast(MulticastSocket socket,int longitud_mensaje) throws IOException{
        byte[] buffer = new byte[longitud_mensaje];
        DatagramPacket paquete = new DatagramPacket(buffer,buffer.length);
        socket.receive(paquete);
        return paquete.getData();
    }

    static void envia_mensaje_multicast(byte[] buffer,String ip,int puerto) throws IOException{
        DatagramSocket socket = new DatagramSocket();
        socket.send(new DatagramPacket(buffer,buffer.length,InetAddress.getByName(ip),puerto));
        socket.close();
        
    }

    public static void main(String[] args) throws Exception {
        new Worker().start();
        name =args[0];
        Scanner teclado= new Scanner(System.in);
        String texto;
        // EN un ciclo infinito se leera cada mensaje del teclado y se enviata el mensaje
        // al grupo 230.0.0.0 a travez del puerto 40000
        
        for( ; ; ){
            System.out.print("Ingrese el mensaje a enviar:");
            synchronized(obj){
                texto = teclado.nextLine();
                mensaje = name+':'+texto;
                envia_mensaje_multicast(mensaje.getBytes(), "230.0.0.0", 40000);
            }
            Thread.sleep(100);
        }
    }
}
