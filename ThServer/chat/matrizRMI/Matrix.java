import java.util.zip.Checksum;

public class Matrix {
    static int N = 6;


    static int[][] separa_matriz(int[][] A,int inicio){
        int[][] M = new int[N/2][N];
        for(int i = 0 ; i < N/2 ; i++ ){
            for(int j = 0 ; j < N; j++ ){
                M[i][j]=A[i + inicio][j];
            }
        }
        return M;
    }
    
    static long checsum(int[][] m){
        long s = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s += m[i][j];
            }
        }
        return s;
    }

    static int[][] multiplica_matrices(int[][] A,int[][] B){
        int[][] C = new int[N/2][N/2];
        for(int i = 0 ; i < N/2 ; i++ ){
            for(int j = 0 ; j < N/2 ; j++ ){
                for(int k = 0 ; k < N ; k++ ){
                    C[i][j] += A[i][k] * B[j][k];
                }
            }
        }
        return C;
    }

    static void acomoda_matriz(int[][] C,int[][] c,int renglon,int columna){
        for(int i = 0 ; i < N/2 ; i++ ){
            for(int j = 0 ; j < N/2 ; j++ ){
                C[i + renglon][j + columna]=c[i][j];
            }
        }
    }

    static void printing_matrix(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    static int[][] transpon(int[][] m){
        int[][] m2 = new int[N][N];
        for (int x=0; x < N; x++) {
            for (int y=0; y < N; y++) {
                m2[y][x] = m[x][y];
            }
        }
        return m2;
    }

    public static void main(String[] args) {
    int[][] A = new int[N][N];
    int[][] B = new int[N][N];
    int[][] C = new int[N][N];
    for (int i = 0; i < C.length; i++) {
        for (int j = 0; j < C.length; j++) {
            A[i][j] = 2 * i - j;
            B[i][j] = i + 2 * j; 
        }
    }
    System.out.println("A");
    printing_matrix(A);
    System.out.println("B");
    printing_matrix(B);
    B=transpon(B);
    printing_matrix(B);
    // Transponer Matriz
    int[][]A1 = separa_matriz(A,0);
    int[][]A2 = separa_matriz(A,N/2);
    int[][]B1 = separa_matriz(B,0);
    int[][]B2 = separa_matriz(B,N/2);
    System.out.println("A1");
    printing_matrix(A1);
    System.out.println("A2");
    printing_matrix(A2);
    System.out.println("B1");
    printing_matrix(B1);
    System.out.println("B2");
    printing_matrix(B2);
    
    
 
    
    int[][] C1= multiplica_matrices(A1,B1);
    int[][] C2= multiplica_matrices(A1,B2);
    int[][] C3= multiplica_matrices(A2,B1);
    int[][] C4= multiplica_matrices(A2,B2);
    System.out.println("C1");
    printing_matrix(C1);
    System.out.println("C2");
    printing_matrix(C2);
    System.out.println("C3");
    printing_matrix(C3);
    System.out.println("C4");
    printing_matrix(C4);


    acomoda_matriz(C,C1,0,0);
    acomoda_matriz(C,C2,0,N/2);
    acomoda_matriz(C,C3,N/2,0);
    acomoda_matriz(C,C4,N/2,N/2);

    printing_matrix(C);
    System.out.println(checsum(C));
    }
}
