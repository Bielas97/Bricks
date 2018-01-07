package com.algorytmy.bricks.algorytm;

import com.algorytmy.bricks.Matrix;
import com.algorytmy.bricks.utils.MatrixUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * @author jbielawski on 11.12.2017 <jakub.bielawski@coi.gov.pl>
 */
@AllArgsConstructor
@Data
public class FindCoordinates {
    private Matrix matrix;

    public int[] coordinates() {
        int[] xy = new int[2];

        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            for (int j = 0; j < matrix.getMatrixSize(); j++) {


            }
        }
        return xy;
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
