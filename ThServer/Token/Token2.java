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
    static long [] Mint;
    static boolean [] Bbool;
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

    public static int Read(int [] M)[
        returns 0;
    ]

    public static void main(String[] args) throws Exception{
        Mint = new long[100];
        Bbool = new boolean[100];
        if (args.length != 2) {
            System.err.println("Se debe pasar como parametros el numero del nodo y la IP del siguiente nodo en el anillo");
            System.exit(1);
        }        

        nodo = Integer.valueOf(args[0]);
        ip = args[1];
        //Second Algorithm

        Worker worker = new Worker();
        Socket  Sconnection = null;
        worker.start();
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
                    beginning = false;
                    token = 1;
                }else{

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
