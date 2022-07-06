import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote {
    public int[][] multiplica_matrices(int N,int[][] A,int[][] B) throws RemoteException;
    public int[][] transpon(int N,int[][] m) throws RemoteException;
    public void printing_matrix(int N,int[][] m) throws RemoteException;
    public void acomoda_matriz(int N,int[][] C,int[][] c,int renglon,int columna) throws RemoteException;
    public long checsum(int N,int[][] m) throws RemoteException;
    public int[][] separa_matriz(int N,int[][] A,int inicio) throws RemoteException;
}
