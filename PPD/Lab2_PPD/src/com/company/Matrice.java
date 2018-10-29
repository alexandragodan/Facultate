package com.company;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Matrice {

    private int m, n;
    private String fileName;
    //private int[][] matrice;

    public Matrice(int n, int m, String fileName) {
        this.m = m;
        this.n = n;
        this.fileName = fileName;
        //genereazaMatrice(mat);
        //this.matrice = mat;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public void genereazaMatrice(int[][] mat) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for(int i=0; i< n; i++) {
            for (int j = 0; j < m; j++) {
                int random = ThreadLocalRandom.current().nextInt(0, 10);
                writer.write(Integer.toString(random) + " ");
            }
            writer.write("\n");
        }
        writer.write("\n");
        writer.close();
    }


    public void citesteMatrice(int[][] mat) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
        for(int i = 0; i < n; ++i)
        {
            for(int j = 0; j < m; ++j)
            {
                if(input.hasNextInt())
                {
                    mat[i][j] = input.nextInt();
                }
            }
        }
    }

    public void afiseazaMatrice(int[][] mat) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("rezultat.txt"));
        for(int i=0; i< n; i++)
        {
            for(int j=0; j< m; j++)
            {
                System.out.print(mat[i][j] + " ");
                writer.write(mat[i][j]);
            }
            System.out.println();
            writer.write("\n");
        }
        writer.close();
    }

    public void adunaMatrici(int[][] m1, int[][]m2, int[][] rez)
    {
        for(int i =0; i<n; i++)
            for(int j=0; j<m; j++)
            {
                rez[i][j] = m1[i][j]+m2[i][j];
            }
    }

    public int[][] transpusa(int[][] x)
    {
        int[][] t = new int[n][m];

        for(int i = 0; i < this.n; i++)
            for(int j = 0; j < this.m; j++)
                t[i][j] = x[j][i];
        return t;
    }

    void inmultire(int[][] x, int[][] y, int[][] z) /* in acest caz putem returna matricea
					prin tipul rezultat, pentru matricea este un pointer (o adresa) */
    {
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
            {
                z[i][j]=0;
                for(int k=0;k<n;k++)
                    z[i][j]+=x[i][k]*y[k][j];
            }
    }

}
