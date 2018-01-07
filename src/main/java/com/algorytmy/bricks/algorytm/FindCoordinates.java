package com.algorytmy.bricks.algorytm;

import com.algorytmy.bricks.Matrix;
import com.algorytmy.bricks.utils.MatrixUtil;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    public Point[] findVerticalCoordinates() {
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

    public Point[] findHorizontalCoordinates() {
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

    public int countBlockedMoves(Point p1, Point p2) {
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
