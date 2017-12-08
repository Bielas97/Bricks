package com.algorytmy.bricks;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        LoadMatrix matrix = new LoadMatrix();
        matrix.setMatrix("7_2x3_4x5_2x4_3x2_3x3");
        System.out.println(matrix.getMatrix());


    }
}
