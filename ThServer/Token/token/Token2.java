import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Token2 {
    static DataInputStream input;
    static DataOutputStream output;
    static boolean beginning = true;
    static String ip;
    static int nodo;
    static long r;
    static int n=0;
    static int nodes = 4;
    static long [] Mint = new long[100];
    static boolean [] Bbool = new boolean[100];
    static ReentrantLock lock = new ReentrantLock();

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

    public static long Read(int position){
        return(Mint[position]);
    }

    public static void Write(long position, long value){
        Mint[position] = value;
        Bbool[position] = True;
    }
    
    public static void Unlock(){
        for (int i = 0; i < nodes; i++) {
            
        }


        lock.unlock();

    }

    public static void Lock(){
        lock.lock();
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
        /*
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
        }   */

        while(n<100){
            if (nodo == 0){
                if(beginning == true){
                    beginning = false;
                    
                    Lock();
                    r=1;
                    Write(n, r);
                    Unlock();
                }
            }
            n++;
        }
        output.close();
        input.close();
        Sconnection.close();
    }
}
