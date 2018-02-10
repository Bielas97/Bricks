package com.algorytmy.bricks.algorytm;

import com.algorytmy.bricks.Matrix;
import com.algorytmy.bricks.utils.BST;
import com.algorytmy.bricks.utils.MatrixUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jbielawski on 11.12.2017 <jakub.bielawski@wawasoft.com>
 * klasa z algorytmem
 */
@Data
@AllArgsConstructor
public class FindCoordinates {
    private Matrix matrix;
    private BST binarySearchTree;

    /**
     * znajduje wszystkie dostepne poziome ruchy
     *
     * @return
     */
    private List<Point> findAllHorizontalPoints(MatrixUtil matrixUtil) {
        List<Point> horizontalPoints = new ArrayList<Point>();
        // i wiersz
        // j kolumny
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            for (int j = 0; j < matrix.getMatrixSize() - 1; j++) {
                if (matrixUtil.isFree(j, i) && matrixUtil.isFree(j + 1, i)) {
                    Point p1 = new Point(j, i);
                    Point p2 = new Point(j + 1, i);
                    horizontalPoints.add(p1);
                    horizontalPoints.add(p2);
                }
            }
        }
        return horizontalPoints;
    }

    /**
     * znajduje wszystkie dostepne pionowe ruchy
     *
     * @return
     */
    private List<Point> findAllVerticalPoints(MatrixUtil matrixUtil) {
        List<Point> verticalPoints = new ArrayList<Point>();
        // i wiersz
        // j kolumny
        for (int i = 0; i < matrix.getMatrixSize() - 1; i++) {
            for (int j = 0; j < matrix.getMatrixSize(); j++) {
                if (matrixUtil.isFree(j, i) && matrixUtil.isFree(j, i + 1)) {
                    Point p1 = new Point(j, i);
                    Point p2 = new Point(j, i + 1);
                    verticalPoints.add(p1);
                    verticalPoints.add(p2);
                }
            }
        }
        return verticalPoints;
    }

    /**
     * znajduje optymalny pinowy ruch
     *
     * @return
     */
    private Point[] findVerticalCoordinates(MatrixUtil matrixUtil) {
        Point[] points = new Point[2];

        List<Point> favp = findAllVerticalPoints(matrixUtil);

        int idx = 0;
        Point[] oneBlock;
        List<Point[]> blocks = new ArrayList<Point[]>();
        for (int i = 0; i < (favp.size() / 2); i++) {
            oneBlock = new Point[2];
            oneBlock[0] = favp.get(idx);
            oneBlock[1] = favp.get(idx + 1);
            if (countBlockedMoves(oneBlock[0], oneBlock[1], matrixUtil) == 7) {
                //break;
                return oneBlock;
            }
            blocks.add(oneBlock);
            idx = idx + 2;
        }
        if (blocks.size() == 1) {
            points[0] = blocks.get(0)[0];
            points[1] = blocks.get(0)[1];
        } else if (blocks.size() == 0) {
            return null;
        } else {
            int maxBlockedMoves = 1;
            for (Point[] block : blocks) {
                if (countBlockedMoves(block[0], block[1], matrixUtil) > maxBlockedMoves) {
                    maxBlockedMoves = countBlockedMoves(block[0], block[1], matrixUtil);
                    points[0] = block[0];
                    points[1] = block[1];
                }
            }
            if (maxBlockedMoves == 1) {
                points[0] = blocks.get(0)[0];
                points[1] = blocks.get(0)[1];
            }
        }
        return points;
    }

    /**
     * znajduje optymalny poziomy ruch
     *
     * @return
     */
    private Point[] findHorizontalCoordinates(MatrixUtil matrixUtil) {
        Point[] points = new Point[2];
        List<Point> fahp = findAllHorizontalPoints(matrixUtil);

        int idx = 0;
        Point[] oneBlock;
        List<Point[]> blocks = new ArrayList<Point[]>();
        for (int i = 0; i < (fahp.size() / 2); i++) {
            oneBlock = new Point[2];
            oneBlock[0] = fahp.get(idx);
            oneBlock[1] = fahp.get(idx + 1);
            if (countBlockedMoves(oneBlock[0], oneBlock[1], matrixUtil) == 7) {
                //break;
                return oneBlock;
            }
            blocks.add(oneBlock);
            idx = idx + 2;
        }

        if (blocks.size() == 1) {
            points[0] = blocks.get(0)[0];
            points[1] = blocks.get(0)[1];
        } else if (blocks.size() == 0) {
            return null;
        } else {
            int maxBlockedMoves = 1;
            for (Point[] block : blocks) {
                if (countBlockedMoves(block[0], block[1], matrixUtil) > maxBlockedMoves) {
                    maxBlockedMoves = countBlockedMoves(block[0], block[1], matrixUtil);
                    points[0] = block[0];
                    points[1] = block[1];
                }
            }
            if (maxBlockedMoves == 1) {
                points[0] = blocks.get(0)[0];
                points[1] = blocks.get(0)[1];
            }
        }
        return points;
    }

    /**
     * podaje koncowy ruch
     *
     * @return
     * @throws Exception
     */

    /**
     * oblicza wolne pola w danym wierszu
     *
     * @param row
     * @return
     */
    private int countFreePlacesInARow(int row, MatrixUtil matrixUtil) {
        int result = 0;
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            if (matrixUtil.isFree(i, row)) {
                result++;
            }
        }
        return result;
    }

    /**
     * oblicza wolne pola w danej kolumnie
     *
     * @param column
     * @return
     */
    private int countFreePlacesInAColumn(int column, MatrixUtil matrixUtil) {
        int result = 0;
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            if (matrixUtil.isFree(column, i)) {
                result++;
            }
        }
        return result;
    }

    /**
     * jesli w dowolnym wierzu lub dowolnej kolumnie zostaly tylko dwa wolne miejsca - zapelnij je!
     *
     * @return
     */
    private Point[] findLastTwoInARowOrColumn(MatrixUtil matrixUtil) {
        Point[] answer = new Point[2];
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            for (int j = 0; j < matrix.getMatrixSize(); j++) {
                if (j < matrix.getMatrixSize() - 1) {
                    if (countFreePlacesInARow(i, matrixUtil) == 2 && matrixUtil.isFree(j, i) && matrixUtil.isFree(j + 1, i)) {
                        answer[0] = new Point(j, i);
                        answer[1] = new Point(j + 1, i);
                        return answer;
                    }
                }
                if (i < matrix.getMatrixSize() - 1) {
                    if (countFreePlacesInAColumn(j, matrixUtil) == 2 && matrixUtil.isFree(j, i) && matrixUtil.isFree(j, i + 1)) {
                        answer[0] = new Point(j, i);
                        answer[1] = new Point(j, i + 1);
                        return answer;
                    }
                }
            }
        }
        return answer;
    }

    public Point[] findAnswer() throws Exception {
        Point[] answer;
        MatrixUtil matrixUtil = new MatrixUtil(matrix);

        //jesli zostaly tylko dwa wolne miejsca w dowolnym wierszu lub dowolnej kolumnie - zapelnij je!
        //ZWIEKSZA MOZLIWOSC WYGRANEJ LECZ WRAZ Z TYM ROSNIE ZNACZACA ZLOZONOSC CZASOWA!
        answer = findLastTwoInARowOrColumn(matrixUtil);

        if (answer[0] == null && answer[1] == null) {
            Point pointVertical0;
            Point pointVertical1;
            Point pointHorizontal0;
            Point pointHorizontal1;
            if (findHorizontalCoordinates(matrixUtil) == null && findVerticalCoordinates(matrixUtil) == null) {
                throw new Exception("brak możliwych ruchów");
            } else if (findHorizontalCoordinates(matrixUtil) != null && findVerticalCoordinates(matrixUtil) == null) {
                answer[0] = findHorizontalCoordinates(matrixUtil)[0];
                answer[1] = findHorizontalCoordinates(matrixUtil)[1];
                return answer;
            } else if (findHorizontalCoordinates(matrixUtil) == null && findVerticalCoordinates(matrixUtil) != null) {
                answer[0] = findVerticalCoordinates(matrixUtil)[0];
                answer[1] = findVerticalCoordinates(matrixUtil)[1];
                return answer;
            } else {
                pointVertical0 = findVerticalCoordinates(matrixUtil)[0];
                pointVertical1 = findVerticalCoordinates(matrixUtil)[1];
                pointHorizontal0 = findHorizontalCoordinates(matrixUtil)[0];
                pointHorizontal1 = findHorizontalCoordinates(matrixUtil)[1];
                if (countBlockedMoves(pointHorizontal0, pointHorizontal1, matrixUtil) == countBlockedMoves(pointVertical0, pointVertical1, matrixUtil)) {
                    Random rnd = new Random();
                    if (rnd.nextInt() % 2 == 0) {
                        answer[0] = pointHorizontal0;
                        answer[1] = pointHorizontal1;
                    } else {
                        answer[0] = pointVertical0;
                        answer[1] = pointVertical1;
                    }
                } else if (countBlockedMoves(pointHorizontal0, pointHorizontal1, matrixUtil) > countBlockedMoves(pointVertical0, pointVertical1, matrixUtil)) {
                    answer[0] = pointHorizontal0;
                    answer[1] = pointHorizontal1;
                } else {
                    answer[0] = pointVertical0;
                    answer[1] = pointVertical1;
                }
            }
        }

        binarySearchTree.insert(answer);
        return answer;
    }

    /**
     * oblicza blokowane ruchy dla postawionego bloczka o punktach p1, p2
     *
     * @param p1
     * @param p2
     * @return
     */
    private int countBlockedMoves(Point p1, Point p2, MatrixUtil matrixUtil) {
        int blockedMoves = 1; // zawsze ustawiajac bloczek blokujemy przynjamniej 1 ruch
        //MatrixUtil matrixUtil = new MatrixUtil(matrix);

        matrixUtil.isOutOfBoundsException(p1, p2);

        if (p1.y == p2.y && Math.abs(p1.x - p2.x) == 1) { //bloczek polozony w dowolnym miejscu poziomo
            if (matrixUtil.isFree(matrixUtil.getSmallerX(p1, p2) - 1, p1.y)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(matrixUtil.getSmallerX(p1, p2), p1.y + 1)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(matrixUtil.getSmallerX(p1, p2) + 1, p1.y + 1)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(matrixUtil.getSmallerX(p1, p2) + 2, p1.y)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(matrixUtil.getSmallerX(p1, p2) + 1, p1.y - 1)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(matrixUtil.getSmallerX(p1, p2), p1.y - 1)) {
                blockedMoves++;
            }
        } else if (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) { //bloczek polozony w dowolnym miejscu pionowo
            if (matrixUtil.isFree(p1.x, matrixUtil.getSmallerY(p1, p2) - 1)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(p1.x, matrixUtil.getSmallerY(p1, p2) + 2)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(p1.x - 1, matrixUtil.getSmallerY(p1, p2))) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(p1.x - 1, matrixUtil.getSmallerY(p1, p2) + 1)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(p1.x + 1, matrixUtil.getSmallerY(p1, p2) + 1)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(p1.x + 1, matrixUtil.getSmallerY(p1, p2))) {
                blockedMoves++;
            }
        }

        return blockedMoves;
    }
}
