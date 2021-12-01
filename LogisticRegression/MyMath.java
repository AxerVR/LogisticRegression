package helper;

public class MyMath {

    public static double[][] matrixTranspose(double[][] matrix){
        double[][] transpose = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                transpose[j][i] = matrix[i][j];
            }
        }

        return transpose;
    }

    public static double[][] matrixMult(double[][] A, double[][] B){
        double[][] result = new double[A.length][B[0].length];

        if(A[0].length != B.length) return result; //Cols in A must be same rows in B in order for A * B.

        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B[0].length; j++){
                for(int k = 0; k < A[0].length; k++){
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    public static double[][] matrixIdentity(int n){
        double[][] identity = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                identity[i][j] = 0;
            }
            identity[i][i] = 1;
        }
        return identity;
    }

    public static double[][] matrixInverse(double[][] A){
        double[][] identity = matrixIdentity(A.length);

        if (A.length != A[0].length) return identity; //must be an n by n matrix

        double pivote;
        double aux;

        for (int i = 0; i < A.length; i++) {
            pivote = A[i][i];

            for (int j = 0; j < A.length; j++) {
                A[i][j] = A[i][j] / pivote;
                identity[i][j] = identity[i][j] / pivote;
            }

            for (int j = 0; j < A.length; j++) {
                if (i != j){
                    aux = A[j][i];

                    for (int k = 0; k < A.length; k++) {
                        A[j][k] = A[j][k] - aux * A[i][k];
                        identity[j][k] = identity[j][k] - aux * identity[i][k];
                    }
                }
            }
        }

        return identity;
    }

    public static double sigmoid(int position, double[] weights, double[][] x) {
        double result = weights[0];

        for (int i = 1; i < x[0].length; i++) {

            result += weights[i] * x[i] [position];

        }

        return result;
    }
}
