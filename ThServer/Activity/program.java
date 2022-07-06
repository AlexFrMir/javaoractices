import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.sampled.Port;
import javax.swing.Spring;

public class program {
    static Object obj =  new Object();
    static String[] Hosts;
    static int[] Ports;
    static int num_nodes;
    static int node;    
    static long logic_clock;

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

    static class Clock extends Thread {
        public void run() {
            for( ; ; ){
                synchronized(obj){
                    System.out.println(logic_clock);
                    try {
                        switch (node) {
                            case 0:
                                logic_clock +=4;
                                break;
                            case 1:
                                logic_clock +=5;
                                break;

                            case 2:
                                logic_clock +=6;
                                break;
                        
                            default:
                                break;
                        }
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    try {
                        sleep(1000);
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                } 
            }

        }
    }
    
    //  Functions

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

        Server serve = new Server();
        serve.start();
        for (int i = 0; i < num_nodes; i++) {
            if(i!=node){
                send_message(-1, Hosts[i], Ports[i]);
            }
        }

        Clock clock = new Clock();
        clock.start();
        serve.join();
    }
}
