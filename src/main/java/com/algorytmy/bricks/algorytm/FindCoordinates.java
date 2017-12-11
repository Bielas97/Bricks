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

    private int countBlockedMoves(Point p1, Point p2) {
        int blockedMoves = 1;
        MatrixUtil matrixUtil = new MatrixUtil(matrix);
        Matrix cloned = matrixUtil.cloneMatrix();

        if (p1.x == 0 && p2.x == 1 && p1.y == 0 && p2.y == 0) { //czyli jestesmy w gornym lewym rogu i bloczek jest polozony poziomo
            if (matrixUtil.isFree(0, 1)) {// x=0 y=1
                blockedMoves++;
            }
            if (matrixUtil.isFree(1, 1)) { //x=1 y=1
                blockedMoves++;
            }
            if (matrixUtil.isFree(2, 0)) { //x=2 y=0
                blockedMoves++;
            }
        } else if (p1.x == 0 && p2.x == 0 && p1.y == 0 && p2.y == 1) { //czyli jestesmy w gornym lewym rogu i bloczek jest polozony pionowo
            if (matrixUtil.isFree(1, 0)) {// x=1 y=0
                blockedMoves++;
            }
            if (matrixUtil.isFree(1, 1)) { //x=1 y=1
                blockedMoves++;
            }
            if (matrixUtil.isFree(0, 2)) { //x=0 y=2
                blockedMoves++;
            }
        } else if (p1.x == 0 && p2.x == 1 && p1.y == matrix.getMatrixSize() - 1 && p2.y == matrix.getMatrixSize() - 1) { //czyli jestesmy w dolnym lewym rogu i bloczek jest polozony poziomo
            if (matrixUtil.isFree(0, matrix.getMatrixSize() - 2)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(1, matrix.getMatrixSize() - 2)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(2, matrix.getMatrixSize() - 1)) {
                blockedMoves++;
            }
        } else if (p1.x == 0 && p2.x == 0 && p1.y == matrix.getMatrixSize() - 1 && p2.y == matrix.getMatrixSize() - 2) { //czyli jestesmy w gornym lewym rogu i bloczek jest polozony pionowo
            if (matrixUtil.isFree(0, matrix.getMatrixSize() - 3)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(1, matrix.getMatrixSize() - 2)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(1, matrix.getMatrixSize() - 1)) {
                blockedMoves++;
            }
        } else if (p1.x == matrix.getMatrixSize() - 1 && p2.x == matrix.getMatrixSize() - 1 && p1.y == 0 && p2.y == 1) { //czyli jestesmy w gornym prawym rogu i bloczek jest polozony pionowo
            if (matrixUtil.isFree(matrix.getMatrixSize() - 2, 0)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(matrix.getMatrixSize() - 2, 1)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(matrix.getMatrixSize() - 1, 2)) {
                blockedMoves++;
            }
        } else if (p1.x == matrix.getMatrixSize() - 1 && p2.x == matrix.getMatrixSize() - 1 && p1.y == 0 && p2.y == 1) { //czyli jestesmy w gornym prawym rogu i bloczek jest polozony poziomo
            if (matrixUtil.isFree(matrix.getMatrixSize() - 3, 0)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(matrix.getMatrixSize() - 1, 1)) {
                blockedMoves++;
            }
            if (matrixUtil.isFree(matrix.getMatrixSize() - 2, 1)) {
                blockedMoves++;
            }
        }

        //TODO reszta cornerow!!! - prawy dolny rog zostal

        return blockedMoves;
    }
}
