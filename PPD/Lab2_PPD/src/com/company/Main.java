package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        int n, m, threadNo;
        System.out.println("Introduceti nr de linii: ");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        System.out.println("Introduceti nr de coloane: ");
        in = new Scanner(System.in);
        m = in.nextInt();

        System.out.println("Introduceti nr de threaduri: ");
        in = new Scanner(System.in);
        threadNo = in.nextInt();

        int[][] mat1 = new int[n][m];
        int[][] mat2 = new int[n][m];
        int[][] result = new int[n][m];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                result[i][j]=0;
        Matrice matrice1 = new Matrice(n,m, "matrice.txt");
        Matrice matrice2 = new Matrice(n,m, "matrice2.txt");

        matrice1.genereazaMatrice(mat1);
        matrice2.genereazaMatrice(mat2);

        matrice1.citesteMatrice(mat1);
        matrice2.citesteMatrice(mat2);

        long start = System.nanoTime();
        parProd(threadNo, mat1, mat2, result, n, m);
        long end = System.nanoTime();

        //matrice1.inmultire(mat1, mat2, result);

        long time = end-start;
        System.out.println("Timp: "+time);
        System.out.println();
        matrice1.afiseazaMatrice(mat1);
        System.out.println();
        matrice2.afiseazaMatrice(mat2);
        System.out.println();
        matrice2.afiseazaMatrice(result);
//        start = System.nanoTime();
//        matrice1.adunaMatrici(mat1, mat2, result);
//        end = System.nanoTime();

       // System.out.println("time seq: "+(end-start));


//        int[][] t= new int[n][m];
//        t = matrice1.transpusa(mat1);
//        matrice1.afiseazaMatrice(mat1);
//        System.out.println();
//        matrice1.afiseazaMatrice(t);
    }

    private static void parSum(int threadNo, int[][] first, int[][] second, int[][] result, int n, int m) {
        Thread[] threads = new Thread[threadNo];
        int MAX = n*m;
        int chunk = MAX/threadNo;
        int start = 0;
        int rest = MAX % threadNo;
        for(int i=0;i<threadNo;++i) {
            int end = start + chunk;
            if (rest>0) {
                end++;
                rest--;
            }
            SumCustomThread t = new SumCustomThread(first,second,result,start,end,n,m);
            start = end;
            threads[i] = t;
            t.start();
        }
        for(Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void parProd(int threadNo, int[][] first, int[][] second, int[][] result, int n, int m) {
        Thread[] threads = new Thread[threadNo];
        int MAX = n*m;
        int chunk = MAX/threadNo;
        int start = 0;
        int rest = MAX % threadNo;
        for(int i=0;i<threadNo;++i) {
            int end = start + chunk;
            if (rest>0) {
                end++;
                rest--;
            }
            ProdCustomThread t = new ProdCustomThread(first,second,result,start,end,n,m);
            start = end;
            threads[i] = t;
            t.start();
        }
        for(Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
