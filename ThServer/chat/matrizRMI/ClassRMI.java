import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClassRMI extends UnicastRemoteObject implements InterfaceRMI{
    public ClassRMI() throws RemoteException{
        super();
    }

    public int[][] separa_matriz(int N, int[][] A,int inicio) throws RemoteException{
        int[][] M = new int[N/3][N];
        for(int i = 0 ; i < N/3 ; i++ ){
            for(int j = 0 ; j < N; j++ ){
                M[i][j]=A[i + inicio][j];
            }
        }
        return M;
    }
    
    public long checsum(int N,int[][] m) throws RemoteException{
        long s = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s += m[i][j];
            }
        }
        return s;
    }

    public int[][] multiplica_matrices(int N,int[][] A,int[][] B) throws RemoteException{
        int[][] C = new int[N/3][N/3];
        for(int i = 0 ; i < N/3 ; i++ ){
            for(int j = 0 ; j < N/3 ; j++ ){
                for(int k = 0 ; k < N ; k++ ){
                    C[i][j] += A[i][k] * B[j][k];
                }
            }
        }
        return C;
    }

    public void acomoda_matriz(int N,int[][] C,int[][] c,int renglon,int columna){
        for(int i = 0 ; i < N/3 ; i++ ){
            for(int j = 0 ; j < N/3 ; j++ ){
                C[i + renglon][j + columna]=c[i][j];
            }
        }
    }

    public void printing_matrix(int N,int[][] m) throws RemoteException{
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public int[][] transpon(int N,int[][] m) throws RemoteException{
        int[][] m2 = new int[N][N];
        for (int x=0; x < N; x++) {
            for (int y=0; y < N; y++) {
                m2[y][x] = m[x][y];
            }
        }
        return m2;
    }




}
