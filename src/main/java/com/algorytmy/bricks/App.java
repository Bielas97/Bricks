package com.algorytmy.bricks;

import com.algorytmy.bricks.algorytm.FindCoordinates;
import com.algorytmy.bricks.utils.LoadMatrix;

import java.awt.*;

/**
 * Pomysl jest taki zeby stawiac bloczki tak zeby blokowac jak najwiecej ruchow przeciwnika
 */
public class App {
    public static void main(String[] args) {

        LoadMatrix matrix = new LoadMatrix();
        matrix.setMatrix("5");
        //ALgorytm
        System.out.println(matrix.getMatrix());
        /*if(matrix.isOk()){
            System.out.println("OK");
        }*/
        FindCoordinates findCoordinates = new FindCoordinates(matrix.getMatrix());
        Point p1 = new Point(2,2);
        Point p2 = new Point(2,3);
        System.out.println(findCoordinates.countBlockedMoves(p1, p2));



    }
}

