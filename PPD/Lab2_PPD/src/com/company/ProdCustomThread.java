package com.company;

public class ProdCustomThread extends Thread {

    private int first[][], second[][];
    private int result[][];
    private int startingPoint;
    private int endingPoint;
    private int n, m;

    public ProdCustomThread(int first[][], int second[][], int result[][], int startingPoint, int endingPoint, int n, int m) {
        this.first = first;
        this.second = second;
        this.result = result;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.n = n;
        this.m = m;
    }

    @Override
    public void run() {
        int s;
        for(int k=startingPoint; k<endingPoint; ++k) {
            {//result[k/m][k%m] = first[k/m][k%m]+second[k/m][k%m];
                //result[k/m][k%m]=0;
                for(int h=0;h<m;h++)
                    result[k/m][k%m]+=first[k/m][h]*second[h][k%m]; //k=i*m+j;
            }
        }
    }

}
