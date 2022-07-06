
public class ClientRMI {
    statuc int N=9;
    public static void main(String[] args) throws Exception{
        int[][] A = new int[N][N];
        int[][] B = new int[N][N];
        int[][] C = new int[N][N];
        String nodo1 = "rmi://localhost/nodo1";
        String nodo2 = "rmi://localhost/nodo2";
        String nodo3 = "rmi://localhost/nodo3";
        InterfaceRMI r1 = (InterfaceRMI)Naming.lookup(nodo1);
        InterfaceRMI r2 = (InterfaceRMI)Naming.lookup(nodo2);
        InterfaceRMI r3 = (InterfaceRMI)Naming.lookup(nodo3);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = 3 * i + j;
                B[i][j] = i - 4 * j; 
                C[i][j] = 0;
            }
        }
        B = r1.transpon(N, B); 
        int[][]A1 = r1.separa_matriz(N,A,0);
        int[][]A2 = r2.separa_matriz(N,A,N/3);
        int[][]A3 = r3.separa_matriz(N,A,2*N/3);
        int[][]B1 = r1.separa_matriz(N,B,0);
        int[][]B2 = r2.separa_matriz(N,B,N/3);
        int[][]B3 = r3.separa_matriz(N,B,2*N/3);
        
        r1.printing_matrix(N, A1);
        r1.printing_matrix(N, A2);
        r1.printing_matrix(N, A3);
        r1.printing_matrix(N, B1);
        r1.printing_matrix(N, B2);
        r1.printing_matrix(N, B3);

        int[][] C1= r1.multiplica_matrices(N,A1,B1);
        int[][] C2= r1.multiplica_matrices(N,A1,B2);
        int[][] C3= r1.multiplica_matrices(N,A1,B3);
        int[][] C4= r2.multiplica_matrices(N,A2,B1);
        int[][] C5= r2.multiplica_matrices(N,A2,B2);
        int[][] C6= r2.multiplica_matrices(N,A2,B3);
        int[][] C7= r3.multiplica_matrices(N,A3,B1);
        int[][] C8= r3.multiplica_matrices(N,A3,B2);
        int[][] C9= r3.multiplica_matrices(N,A3,B3);
        

        r1.acomoda_matriz(N,C,C1,0,0);
        r1.acomoda_matriz(N,C,C2,0,N/3);
        r1.acomoda_matriz(N,C,C3,0,2*N/3);
        r2.acomoda_matriz(N,C,C4,N/3,0);
        r2.acomoda_matriz(N,C,C5,N/3,N/3);
        r2.acomoda_matriz(N,C,C6,N/3,2*N/3);
        r3.acomoda_matriz(N,C,C7,2*N/3,0);
        r3.acomoda_matriz(N,C,C8,2*N/3,N/3);
        r3.acomoda_matriz(N,C,C9,2*N/3,2*N/3);

        
        if(N==9){
            System.out.println(r1.printing_matrix(N, A));
            System.out.println(r2.printing_matrix(N, B));
            System.out.println(r3.printing_matrix(N, C));
            System.out.println(r1.checsum(N, C));
        }
        if(N==3000){
            System.out.println(r1.checsum(N, C));
        }

    }
}
