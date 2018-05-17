package lab;

public class Matrix {
    private int M;
    private int N;
    private int[][] data;

    public Matrix(int M, int N) {
        if(N == 0 && M == 0) throw new IllegalArgumentException("Wrong matrix");
        this.M = M;
        this.N = N;
        data = new int[M][N];
    }

    public Matrix(int[][]data){
        if(!matrixIsValid(data)) throw new IllegalArgumentException("Wrong matrix");
        M = data.length;
        N = data[0].length;
        this.data = new int[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                this.data[i][j] = data[i][j];
    }

    public int getM() {return M;}
    public int getN() {return N;}
    public int[][] getMatrix() {return data;}

    public String toString(){
        String aString = "";
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                if(j == N - 1){
                    aString += data[i][j] + "\n";
                }else aString += data[i][j] + " ";
            }
        }
        return aString;
    }

    @Override
    public boolean equals(Object B) {
        if(B == null) return false;
        if(B == this) return true;
        if(B instanceof Matrix){
            Matrix A = this;
            Matrix C = (Matrix)B;
            if (C.M != A.M || C.N != A.N) return false;
            for (int i = 0; i < M; i++)
                for (int j = 0; j < N; j++)
                    if (A.data[i][j] != C.data[i][j]) return false;
            return true;
        }return false;
    }

    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

//    public static double[] multiply(double[][] a, double[] x) {
//        int m = a.length;
//        int n = a[0].length;
//        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
//        double[] y = new double[m];
//        for (int i = 0; i < m; i++)
//            for (int j = 0; j < n; j++)
//                y[i] += a[i][j] * x[j];
//        return y;
//    }

    private static boolean matrixIsValid(int[][] matrix){
        if(matrix == null || matrix[0].length < 2) return false;
        int m = matrix.length;
        int temp = matrix[0].length;
        for (int i = 0; i < m; i++){
            int n = matrix[i].length;
            for (int j = 0; j < n; j++){
                if(temp != n) return false;
                temp = n;
            }
        }
        return true;
    }
}
