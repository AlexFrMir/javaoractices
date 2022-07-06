import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Token {
    static DataInputStream input;
    static DataOutputStream output;
    static boolean beginning = true;
    static String ip;
    static int nodo;
    static long token;

    static class Worker extends Thread{
        public void run(){
            //First Algorithm
            try {
                ServerSocket Servidor = new ServerSocket(20000+nodo);
                Socket Sconnection = Servidor.accept();
                
                input = new DataInputStream(Sconnection.getInputStream());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        if (args.length != 2) {
            System.err.println("Se debe pasar como parametros el numero del nodo y la IP del siguiente nodo en el anillo");
            System.exit(1);
        }        

        nodo = Integer.valueOf(args[0]);
        ip = args[1];
        //Second Algorithm

        Worker worker = new Worker();
        worker.start();
         
        Socket  Sconnection = null;
        for( ; ; ){
            try {
                Sconnection = new Socket(ip, 20000+(nodo+1)%4);
                System.out.println( 20000+(nodo+1)%4);
                break;
            } catch (Exception e) {
                //TODO: handle exception
                Thread.sleep(500);
            }
        }
        output = new DataOutputStream(Sconnection.getOutputStream());
        worker.join();
        for( ; ; ){
            if (nodo == 0) {
                if (beginning==true) {
                    System.setProperty("javax.net.ssl.keyStore", "keystore_servidor.jks");
                    System.setProperty("javax.net.ssl.keyStorePassword", "1234567");
                    beginning = false;
                    token = 1;
                }else{

                    System.setProperty("javax.net.ssl.keyStore", "keystore_cliente.jks");
                    System.setProperty("javax.net.ssl.keyStorePassword", "1234567");
                    token = input.readLong();
                    token += 1;
                    System.out.println(nodo);
                    System.out.println(token);
                }
            }else{
                token = input.readLong();
                token += 1;
                System.out.println(nodo);
                System.out.println(token);
            }
            if(nodo == 0 && token >=100){
                break;
            }
            output.writeLong(token);
        }   
        output.close();
        input.close();
        Sconnection.close();
    }
}
