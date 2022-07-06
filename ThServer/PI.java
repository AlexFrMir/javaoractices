import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PI {
    static Object obj = new Object();
    static float pi = 0;
    static class Worker extends Thread{
        Socket Sconnection;
        Worker(Socket Sconnection){
            this.Sconnection = Sconnection;
        }
        public void run(){
            //Algoritmo1
            try{
                DataOutputStream Soutput = new DataOutputStream(Sconnection.getOutputStream());
                DataInputStream input = new DataInputStream(Sconnection.getInputStream());
                float suma = input.readFloat();
                synchronized(obj){
                    pi = suma + pi;
                }
                Soutput.close();
                input.close();
                Sconnection.close();
            }catch(Exception e){
                //
            }
        }
    }
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Uso:");
            System.err.println("JAva Pi <nodo>");
            System.exit(0);
        }
        int nodo = Integer.valueOf(args[0]);
        if (nodo==0) {
            //algoritmo2
            ServerSocket Servidor = new ServerSocket(40000);
            Worker Workers[] = new Worker[4];
            System.out.println("Server");
            for (int i = 0; i < Workers.length; i++) {
                Socket connection = Servidor.accept();
                Worker w = new Worker(connection);
                Workers[i] = w;
                Workers[i].start();
            }
            for (int i = 0; i < Workers.length; i++) {
                Workers[i].join();
            }
            System.out.println(pi);
        } else {
            //algoritmo3
            Socket connection = null;
            for( ; ; ){
                try {
                    connection = new Socket("localhost", 40000);
                    break;
                } catch (Exception e) {
                    System.out.println("Connection Error");
                    Thread.sleep(100);
                }
            }
            DataOutputStream output = new DataOutputStream(connection.getOutputStream());
            DataInputStream input = new DataInputStream(connection.getInputStream());
            float suma = 0;
            for (int i = 0; i < 1000000; i++) {
                suma = 4/(8*(float)i+2*((float)nodo-2)+3)+suma;
            }
            suma = nodo%2==0?-suma:suma;
            output.writeFloat(suma);
            output.close();
            input.close();
            connection.close();
        }
    }
}
