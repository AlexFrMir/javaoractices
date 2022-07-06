import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import javax.sound.sampled.Port;
import javax.swing.Spring;

public class coord  {
    static Object obj =  new Object();
    static String[] Hosts;
    static int[] Ports;
    static int num_nodes;
    static int node;    
    static int current_coord;
    //  Classes 

    static class Worker extends Thread{
        Socket Sconnection;
        Worker(Socket connection){
            this.Sconnection = connection;
        }
        public void run() {
            try {
                DataOutputStream Soutput = new DataOutputStream(Sconnection.getOutputStream());
                DataInputStream Sinput = new DataInputStream(Sconnection.getInputStream());
                String message = Sinput.readUTF();
                
                if(message == "ELECCION"){
                    Soutput.writeUTF("OK");
                    node_election(node);
                }else if(message == "COORDINADOR"){
                    current_coord = Sinput.readInt();
                }

                System.out.println("Thread Worker is running");
                Sconnection.close();
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }
    
    static class Server extends Thread{
        public void run(){
            try {
                ServerSocket SSocket = new ServerSocket(Ports[node]);
                while (true) {
                    Socket Sconnection = SSocket.accept();
                    Worker w = new Worker(Sconnection);
                    w.start();
                    w.join();
                }
            } catch (Exception e) {
                
            }
        }
    }

    
    //  Functions
/*
    static void send_message(long logic_time,String Host, int Port) throws IOException{
        Socket sclient = null;
        for( ; ;){
            try {
                sclient = new Socket(Host,Port);
                break;
            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        DataOutputStream SOutput = new DataOutputStream(sclient.getOutputStream());
        SOutput.writeLong(logic_time);
        SOutput.close();
        sclient.close();
    }
*/

    static String send_message_election(String Host, int Port) throws IOException{
        Socket sclient = new Socket(Host,Port);
        DataOutputStream soutput;
        DataInputStream sintput;
        String getdata;
        try{
            if(sclient.isConnected()){
                soutput = new DataOutputStream(sclient.getOutputStream());
                sintput = new DataInputStream(sclient.getInputStream());
                soutput.writeUTF("ELECCION");
                getdata = sintput.readUTF();
                soutput.close();
                sintput.close();
                return(getdata);
            }else{
                return "";                
            }
        } catch (Exception e) {
            //TODO: handle exception
            
        }finally{
            sclient.close();
        }
        return "";
    }

    static void send_message_coordinator(String Host, int Port) throws IOException{
        Socket sclient= new Socket(Host,Port);
        DataOutputStream soutput;
        DataInputStream sintput;
        String getdata;
        try {
            
            if(sclient.isConnected()){
                soutput = new DataOutputStream(sclient.getOutputStream());
                soutput.writeUTF("COORDINADOR");
                soutput.writeInt(node);
                soutput.close();
                sclient.close();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }finally{
            sclient.close();
        }
    }

    static void node_election(int nodo){

    }
    //  Main

    public static void main(String[] args) throws Exception{
        String[] parts;
        node = Integer.parseInt(args[0]);
        num_nodes = args.length-1;
        Hosts = new String[num_nodes];
        Ports = new int[num_nodes];
        for (int i = 1; i < args.length; i++) {
            parts = args[i].split(":");
            Hosts[i-1] = parts[0];
            Ports[i-1] = Integer.parseInt(parts[1]);
        }
        // barrera
        
        Thread.sleep(3000);
        if(node == 7){
            node_election(4);
        }
        Server serve = new Server();
        serve.start();
        serve.join();
    }
}
