package com.algorytmy.bricks;

import com.algorytmy.bricks.algorytm.FindCoordinates;
import com.algorytmy.bricks.utils.BST;
import com.algorytmy.bricks.utils.LoadMatrix;
import com.algorytmy.bricks.utils.MatrixUtil;

import java.awt.*;
import java.util.Scanner;

/**
 * Mój algorytm opiera sie o blokowaniu przeciwnikowi jak najwiecej ruchow
 * przykladowo jesli mamy plansze
 *
 *      0   0   0   0
 *      0   0   0   0
 *      0   0   0   0
 *      0   0   0   0
 *
 * i ustawimy odpowiednio nasz bloczek
 *
 *      0   0   0   0
 *      0   X   X   0
 *      0   0   0   0
 *      0   0   0   0
 *
 * to zablokowalismy przeciwnikowi 7 ruchow przedstawionych ponizej
 *
 *      0   -   -   0
 *      -   -   -   -
 *      0   -   -   0
 *      0   0   0   0
 *
 */
public class App {

    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        game();
    }

    /**
     * nakladka na rozgrywke
     * @throws Exception
     */
    private static void game() throws Exception {
        Scanner scan = new Scanner(System.in);
        String rules = scan.nextLine();
        LoadMatrix matrix = new LoadMatrix();
        matrix.setMatrix(rules);
        System.out.println("OK");

        BST binarySearchTree = new BST();
        FindCoordinates findCoordinates = new FindCoordinates(matrix.getMatrix(), binarySearchTree);

        String regex = "\\dx\\d\\_\\dx\\d";
        String start = scan.nextLine();
        String opponentMove;
        if (start.equalsIgnoreCase("start")) {
            do {
                putMyPoints(matrix.getMatrix(), findCoordinates);
                opponentMove = scan.nextLine();
                putOpponentPoints(opponentMove, matrix.getMatrix());
            } while (opponentMove.matches(regex));
        } else if (start.matches(regex)) {
            opponentMove = start;
            do {
                putOpponentPoints(opponentMove, matrix.getMatrix());
                putMyPoints(matrix.getMatrix(), findCoordinates);
                opponentMove = scan.nextLine();
            } while (opponentMove.matches(regex));
        }
    }

    /**
     * kladzie bloczek przeciwnika na plansze
     * @param points
     * @param matrix
     * @throws Exception
     */
    private static void putOpponentPoints(String points, Matrix matrix) throws Exception {
        MatrixUtil matrixUtil = new MatrixUtil(matrix);
        String[] parsedPoints = points.split("_");
        int P1row = Integer.parseInt(String.valueOf(parsedPoints[0].charAt(0)));
        int P1column = Integer.parseInt(String.valueOf(parsedPoints[0].charAt(2)));
        int P2row = Integer.parseInt(String.valueOf(parsedPoints[1].charAt(0)));
        int P2column = Integer.parseInt(String.valueOf(parsedPoints[1].charAt(2)));

        Point p1 = new Point(P1row, P1column);
        Point p2 = new Point(P2row, P2column);

        if (matrixUtil.isFree(p1.x, p1.y) && matrixUtil.isFree(p2.x, p2.y)) {
            matrix.setValue(p1, 'X');
            matrix.setValue(p2, 'X');
        } else {
            throw new Exception("klocek lub punkt zostal juz postawiony na tym miejscu wczesniej, przeciwnik napisal zly program");
        }
    }

    /**
     * kladzie moj bloczek na plansze i wyswietla go na konsoli
     * @param matrix
     * @param findCoordinates
     * @throws Exception
     */
    private static void putMyPoints(Matrix matrix, FindCoordinates findCoordinates) throws Exception {
        Point[] points = findCoordinates.findAnswer();
        matrix.setValue(points[0], 'X');
        matrix.setValue(points[1], 'X');
        System.out.println(points[0].x + "x" + points[0].y + "_" + points[1].x + "x" + points[1].y);
    }
}

