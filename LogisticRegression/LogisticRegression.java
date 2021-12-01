package logistic.controller;

import helper.MyMath;
import java.util.Arrays;

public class LogisticRegression {
    private double[][] dataSet;
    private double[][] X, Y;
    private double[] Ws, ansClean;
    private int rows, cols;
    private final double alpha = 0.01;

    public LogisticRegression(){}

    private void XMatrix(){
        X = new double[rows][cols];

        for (int i = 0; i < rows; i++){
            for (int j = 1; j < cols; j++){
                X[i][0] = 1;
                X[i][j] = dataSet[i][j - 1];
            }
        }
    }

    private void YMatrix(){
        Y = new double[rows][1];

        for (int i = 0; i < rows; i++){
            Y[i][0] = dataSet[i][cols - 1];
        }
    }

    private void WsMatrix(){
        Ws = new double[X.length];
        ansClean = new double[X[0].length];

        for (int i = 0; i < 8000; i++) {

            double[] WsNew = new double[Ws.length];
            double sigma = 0;

            for (int j = 0; j < X.length; j++) {
                for (int k = 0; k < X[0].length; k++) {
                    sigma += ((1 / ( 1 + Math.exp( - MyMath.sigmoid(k,Ws,X))))- Y[k][0] ) * X[j][k];
                }

                WsNew[j] = Ws[j] - (alpha * sigma);
                sigma = 0;


            }
            System.arraycopy(WsNew, 0, Ws, 0, X[0].length);
        }
        System.arraycopy(Ws, 0, ansClean, 0, ansClean.length);
    }

    public void setData(double[][] iDataSet) {
        dataSet = iDataSet;

        rows = iDataSet.length;
        cols = iDataSet[0].length;

        XMatrix();
        YMatrix();
        WsMatrix();
    }

    public double classify(double[] inputs) {

        System.out.println(Arrays.toString(ansClean));

        System.out.println(Arrays.toString(inputs));

        double result = ansClean[0];
        for (int i = 1; i < ansClean.length; i++) {
            result += ansClean[i] * inputs[i-1];
        }
        return 1/(1 + Math.exp(-result));
    }

    /*public void printDataSet(){
        for (int i = 0; i < betas.length; i++){
            for (int j = 0; j < betas[0].length; j++){
                System.out.print(betas[i][j] + " | ");
            }
            System.out.println();
        }
    }*/
}