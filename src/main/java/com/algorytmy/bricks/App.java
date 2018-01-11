package com.algorytmy.bricks;

import com.algorytmy.bricks.algorytm.FindCoordinates;
import com.algorytmy.bricks.utils.LoadMatrix;

import java.awt.*;
import java.util.Scanner;

/**
 * Pomysl jest taki zeby stawiac bloczki tak zeby blokowac jak najwiecej ruchow przeciwnika
 */
public class App {
    public static void main(String[] args) {

        //LoadMatrix matrix = new LoadMatrix();
        //matrix.setMatrix("3_0x1_1x1_1x2_2x1_2x2");
        //ALgorytm
        //System.out.println(matrix.getMatrix());
        /*if(matrix.isOk()){
            System.out.println("OK");
        }*/
        //FindCoordinates findCoordinates = new FindCoordinates(matrix.getMatrix());
        /*Point p1 = new Point(1,1);
        Point p2 = new Point(1,2);
        System.out.println(findCoordinates.countBlockedMoves(p1, p2));
        findCoordinates.findVerticalCoordinates();*/
        //System.out.println("odpowiedz    "+Arrays.toString(findCoordinates.findHorizontalCoordinates()));
        //System.out.println(Arrays.toString(findCoordinates.findAnswer()));


        game();
    }

    private static void game() {
        Scanner scan = new Scanner(System.in);
        String rules = scan.nextLine();
        LoadMatrix matrix = new LoadMatrix();
        matrix.setMatrix(rules);
        System.out.println("OK");

        FindCoordinates findCoordinates = new FindCoordinates(matrix.getMatrix());

        String regex = "\\dx\\d\\_\\dx\\d";
        String start = scan.nextLine();
        String stop;
        if (start.equals("START") || start.equals("Start") || start.equals("start")) {
            String end = "STOP";
            String opponentMove;
            do {
                putMyPoints(matrix.getMatrix(), findCoordinates);
                //dzialanie
                System.out.println(matrix.getMatrix());
                opponentMove = scan.nextLine();
                putOpponentPoints(opponentMove, matrix.getMatrix());
                //dzialanie
                System.out.println(matrix.getMatrix());
            } while (!opponentMove.matches(regex));

        }

        /*String oppMove = scan.nextLine();
        putOpponentPoints(oppMove, matrix.getMatrix());
        System.out.println(matrix.getMatrix());*/

    }

    private static void putOpponentPoints(String points, Matrix matrix) {
        //1x2_2x3
        String[] parsedPoints = points.split("_");
        int P1row = Integer.parseInt(String.valueOf(parsedPoints[0].charAt(0)));
        int P1column = Integer.parseInt(String.valueOf(parsedPoints[0].charAt(2)));
        int P2row = Integer.parseInt(String.valueOf(parsedPoints[1].charAt(0)));
        int P2column = Integer.parseInt(String.valueOf(parsedPoints[1].charAt(2)));

        Point p1 = new Point(P1row, P1column);
        Point p2 = new Point(P2row, P2column);

        matrix.setValue(p1, 'X');
        matrix.setValue(p2, 'X');
    }

    private static void putMyPoints(Matrix matrix, FindCoordinates findCoordinates) {
        Point[] points = findCoordinates.findAnswer();
        matrix.setValue(points[0], 'X');
        matrix.setValue(points[1], 'X');
    }
}

