package com.algorytmy.bricks.algorytm;

import com.algorytmy.bricks.Matrix;
import com.algorytmy.bricks.utils.MatrixUtil;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jbielawski on 11.12.2017 <jakub.bielawski@coi.gov.pl>
 */
@Data
public class FindCoordinates {
    private Matrix matrix;

    public FindCoordinates(Matrix matrix) {
        this.matrix = matrix;
    }

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

    private Point[] findVerticalCoordinates() {
        Point[] points = new Point[2];

        int idx = 0;
        Point[] oneBlock;
        List<Point[]> blocks = new ArrayList<Point[]>();
        for (int i = 0; i < (findAllVerticalPoints().size() / 2); i++) {
            oneBlock = new Point[2];
            oneBlock[0] = findAllVerticalPoints().get(idx);
            oneBlock[1] = findAllVerticalPoints().get(idx + 1);
            blocks.add(oneBlock);
            idx = idx + 2;
        }

        int maxBlockedMoves = 1;
        for (int i = 0; i < blocks.size(); i++) {
            if (countBlockedMoves(blocks.get(i)[0], blocks.get(i)[1]) > maxBlockedMoves) {
                maxBlockedMoves = countBlockedMoves(blocks.get(i)[0], blocks.get(i)[1]);
                points[0] = blocks.get(i)[0];
                points[1] = blocks.get(i)[1];
            }
        }

        return points;
    }

    private Point[] findHorizontalCoordinates() {
        Point[] points = new Point[2];

        int idx = 0;
        Point[] oneBlock;
        List<Point[]> blocks = new ArrayList<Point[]>();
        for (int i = 0; i < (findAllHorizontalPoints().size() / 2); i++) {
            oneBlock = new Point[2];
            oneBlock[0] = findAllHorizontalPoints().get(idx);
            oneBlock[1] = findAllHorizontalPoints().get(idx + 1);
            blocks.add(oneBlock);
            idx = idx + 2;
        }

        List<Point[]> sameBlockedMovesBlocks = new ArrayList<Point[]>();
        Point[] sameBlockedMovesPoints;

        int maxBlockedMoves = 1;
        for (int i = 0; i < blocks.size(); i++) {
            if (countBlockedMoves(blocks.get(i)[0], blocks.get(i)[1]) > maxBlockedMoves) {
                maxBlockedMoves = countBlockedMoves(blocks.get(i)[0], blocks.get(i)[1]);
                points[0] = blocks.get(i)[0];
                points[1] = blocks.get(i)[1];
            }
        }

        return points;
    }

    private int countFreePlacesInARow(int row) {
        int result = 0;
        MatrixUtil matrixUtil = new MatrixUtil(matrix);
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            if (matrixUtil.isFree(row, i)) {
                result++;
            }
        }
        return result;
    }

    private int countFreePlacesInAColumn(int column) {
        int result = 0;
        MatrixUtil matrixUtil = new MatrixUtil(matrix);
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            if (matrixUtil.isFree(i, column)) {
                result++;
            }
        }
        return result;
    }

    public Point[] findAnswer() {
        Point[] answer = new Point[2];
        MatrixUtil matrixUtil = new MatrixUtil(matrix);

        //jesli w wierszu lub kolumnie zostaly tylko dwa punkty obok siebie wolne to zapelnij je
        for (int i = 0; i < matrix.getMatrixSize() - 1; i++) {
            for (int j = 0; j < matrix.getMatrixSize() - 1; j++) {
                if (countFreePlacesInARow(i) == 2 && matrixUtil.isFree(i, j) && matrixUtil.isFree(i, j + 1)) {
                    answer[0] = new Point(i, j);
                    answer[1] = new Point(i, j + 1);
                } else if (countFreePlacesInAColumn(j) == 2 && matrixUtil.isFree(i, j) && matrixUtil.isFree(i + 1, j)) {
                    answer[0] = new Point(i, j);
                    answer[1] = new Point(i + 1, j);
                }
            }
        }

        if (answer[0] == null && answer[1] == null) {

            Point pointHorizontal0 = null;
            Point pointHorizontal1 = null;
            if (findHorizontalCoordinates()[0] != null && findHorizontalCoordinates()[1] != null) {
                pointHorizontal0 = new Point(findHorizontalCoordinates()[0].x, findHorizontalCoordinates()[0].y);
                pointHorizontal1 = new Point(findHorizontalCoordinates()[1].x, findHorizontalCoordinates()[1].y);
            }
            Point pointVertical0 = null;
            Point pointVertical1 = null;
            if (findVerticalCoordinates()[0] != null && findVerticalCoordinates()[1] != null) {
                pointVertical0 = new Point(findVerticalCoordinates()[0].x, findVerticalCoordinates()[0].y);
                pointVertical1 = new Point(findVerticalCoordinates()[1].x, findVerticalCoordinates()[1].y);
            }

            if (pointHorizontal0 == null || pointHorizontal1 == null) {
                answer[0] = pointVertical0;
                answer[1] = pointVertical1;
            } else if (pointVertical0 == null || pointVertical1 == null) {
                answer[0] = pointHorizontal0;
                answer[1] = pointHorizontal1;
            } else if (countBlockedMoves(pointHorizontal0, pointHorizontal1) == countBlockedMoves(pointVertical0, pointVertical1)) {
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
        return answer;
    }

    private int countBlockedMoves(Point p1, Point p2) {
        int blockedMoves = 1;
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
