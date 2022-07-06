import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.io.IOException;
public class ClaseRMI extends UnicastRemoteObject implements InterfaceRMI{
    public ClaseRMI() throws RemoteException{
        super();
    }
    public String rcp(String command) throws RemoteException{
        String dir = "";
        String WhatToRun = "/usr/local/bin/pwd"
        if (command == "cd") {
            System.out.println(command.substring(3));
            if (command.substring(3) == "..") {
                
            }
        }
        switch (command) {
            case "cd":
                
                break;
        
            default:
                break;
        }
        return dir;
    }
    public String mayusculas(String s) throws RemoteException{
        return s.toUpperCase();
    }

    public float division(float a,float b) throws RemoteException{
        return a/b;
    }

    public float multiplicacion(float a,float b) throws RemoteException{
        return a*b;
    }

    public float resta(float a,float b) throws RemoteException{
        return a-b;
    }

    public float suma(float a,float b) throws RemoteException{
        return a+b;
    }
    
    public long checsum(int[][] m) throws RemoteException{
        long s = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                s += m[i][j];
            }
        }
        return s;
    }
}
