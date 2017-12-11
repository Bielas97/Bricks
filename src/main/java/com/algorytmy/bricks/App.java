package com.algorytmy.bricks;

import com.algorytmy.bricks.utils.LoadMatrix;

/**
 * Pomysl jest taki zeby stawiac bloczki tak zeby blokowac jak najwiecej ruchow przeciwnika
 */
public class App {
    public static void main(String[] args) {

        LoadMatrix matrix = new LoadMatrix();
        matrix.setMatrix("7_2x3_4x5_2x4_3x2_3x3");
        //ALgorytm
        System.out.println(matrix.getMatrix());
        if(matrix.isOk()){
            System.out.println("OK");
        }


    }
}

