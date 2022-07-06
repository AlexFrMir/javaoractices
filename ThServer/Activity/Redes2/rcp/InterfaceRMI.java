import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote{
    public String mayusculas(String name) throws RemoteException;
    public String rcp(String command) throws RemoteException;
    public float suma(float a,float b) throws RemoteException;
    public float resta(float a,float b) throws RemoteException;
    public float division(float a,float b) throws RemoteException;
    public float multiplicacion(float a,float b) throws RemoteException;
    public long checsum(int[][] m) throws RemoteException;
}