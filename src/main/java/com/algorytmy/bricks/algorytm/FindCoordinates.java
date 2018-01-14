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
 *     klasa z algorytmem
 */
@Data
@AllArgsConstructor
public class FindCoordinates {
    private Matrix matrix;
    private BST binarySearchTree;

    /**
     * znajduje wszystkie dostepne poziome ruchy
     * @return
     */
    private List<Point> findAllHorizontalPoints() {
        MatrixUtil matrixUtil = new MatrixUtil(matrix);
        List<Point> horizontalPoints = new ArrayList<Point>();
        // i wiersz
        // j kolumny
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            for (int j = 0; j < matrix.getMatrixSize() - 1; j++) {
                if (matrixUtil.isFree(i, j) && matrixUtil.isFree(i, j + 1)) {
                    Point p1 = new Point(i, j);
                    Point p2 = new Point(i, j + 1);
                    horizontalPoints.add(p1);
                    horizontalPoints.add(p2);
                }
            }
        }
        return horizontalPoints;
    }

    /**
     * znajduje wszystkie dostepne pionowe ruchy
     * @return
     */
    private List<Point> findAllVerticalPoints() {
        MatrixUtil matrixUtil = new MatrixUtil(matrix);
        List<Point> verticalPoints = new ArrayList<Point>();
        // i wiersz
        // j kolumny
        for (int i = 0; i < matrix.getMatrixSize() - 1; i++) {
            for (int j = 0; j < matrix.getMatrixSize(); j++) {
                if (matrixUtil.isFree(i, j) && matrixUtil.isFree(i + 1, j)) {
                    Point p1 = new Point(i, j);
                    Point p2 = new Point(i + 1, j);
                    verticalPoints.add(p1);
                    verticalPoints.add(p2);
                }
            }
        }
        return verticalPoints;
    }

    /**
     * znajduje optymalny pinowy ruch
     * @return
     */
    private Point[] findVerticalCoordinates() {
        Point[] points = new Point[2];

        List<Point> favp = findAllVerticalPoints();

        int idx = 0;
        Point[] oneBlock;
        List<Point[]> blocks = new ArrayList<Point[]>();
        for (int i = 0; i < (favp.size() / 2); i++) {
            oneBlock = new Point[2];
            oneBlock[0] = favp.get(idx);
            oneBlock[1] = favp.get(idx + 1);
            if (countBlockedMoves(oneBlock[0], oneBlock[1]) == 7) {
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
                if (countBlockedMoves(block[0], block[1]) > maxBlockedMoves) {
                    maxBlockedMoves = countBlockedMoves(block[0], block[1]);
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
     * @return
     */
    private Point[] findHorizontalCoordinates() {
        Point[] points = new Point[2];
        List<Point> fahp = findAllHorizontalPoints();

        int idx = 0;
        Point[] oneBlock;
        List<Point[]> blocks = new ArrayList<Point[]>();
        for (int i = 0; i < (fahp.size() / 2); i++) {
            oneBlock = new Point[2];
            oneBlock[0] = fahp.get(idx);
            oneBlock[1] = fahp.get(idx + 1);
            if (countBlockedMoves(oneBlock[0], oneBlock[1]) == 7) {
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
                if (countBlockedMoves(block[0], block[1]) > maxBlockedMoves) {
                    maxBlockedMoves = countBlockedMoves(block[0], block[1]);
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
     * @return
     * @throws Exception
     */
    public Point[] findAnswer() throws Exception {
        Point[] answer = new Point[2];
        //jesli w wierszu lub kolumnie zostaly tylko dwa punkty obok siebie wolne to zapelnij je
        //nie zrobilem tego, nie starczylo czasu

        /*int idxColumn = 0;
        int idxRow = 0;
        int idx = 0;
        while (idx < matrix.getMatrixSize() - 1) {
            if (countFreePlacesInARow(idx) == 2 && matrixUtil.isFree(idx, idxColumn) && matrixUtil.isFree(idx, idxColumn + 1)) {
                System.out.println("weszlo row");
                answer[0] = new Point(idx, idxColumn);
                answer[1] = new Point(idx, idxColumn + 1);
                return answer;
            } else {
                idxColumn++;
            }
            if (countFreePlacesInAColumn(idx) == 2 && matrixUtil.isFree(idx, idxRow) && matrixUtil.isFree(idx, idxRow + 1)) {
                System.out.println("weszlo column");
                answer[0] = new Point(idxRow, idx);
                answer[1] = new Point(idxRow + 1, idx);
                return answer;
            } else {
                idxRow++;
            }
            idx++;*/

        Point pointVertical0;
        Point pointVertical1;
        Point pointHorizontal0;
        Point pointHorizontal1;
        if (findHorizontalCoordinates() == null && findVerticalCoordinates() == null) {
            throw new Exception("brak możliwych ruchów");
        } else if (findHorizontalCoordinates() != null && findVerticalCoordinates() == null) {
            answer[0] = findHorizontalCoordinates()[0];
            answer[1] = findHorizontalCoordinates()[1];
            return answer;
        } else if (findHorizontalCoordinates() == null && findVerticalCoordinates() != null) {
            answer[0] = findVerticalCoordinates()[0];
            answer[1] = findVerticalCoordinates()[1];
            return answer;
        } else {
            pointVertical0 = findVerticalCoordinates()[0];
            pointVertical1 = findVerticalCoordinates()[1];
            pointHorizontal0 = findHorizontalCoordinates()[0];
            pointHorizontal1 = findHorizontalCoordinates()[1];
            if (countBlockedMoves(pointHorizontal0, pointHorizontal1) == countBlockedMoves(pointVertical0, pointVertical1)) {
                Random rnd = new Random();
                if (rnd.nextInt() % 2 == 0) {
                    answer[0] = pointHorizontal0;
                    answer[1] = pointHorizontal1;
                } else {
                    answer[0] = pointVertical0;
                    answer[1] = pointVertical1;
                }
            } else if (countBlockedMoves(pointHorizontal0, pointHorizontal1) > countBlockedMoves(pointVertical0, pointVertical1)) {
                answer[0] = pointHorizontal0;
                answer[1] = pointHorizontal1;
            } else {
                answer[0] = pointVertical0;
                answer[1] = pointVertical1;
            }
        }

        binarySearchTree.insert(answer);
        return answer;
    }

    /**
     * oblicza blokowane ruchy dla postawionego bloczka o punktach p1, p2
     * @param p1
     * @param p2
     * @return
     */
    private int countBlockedMoves(Point p1, Point p2) {
        int blockedMoves = 1; // zawsze ustawiajac bloczek blokujemy przynjamniej 1 ruch
        MatrixUtil matrixUtil = new MatrixUtil(matrix);

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
