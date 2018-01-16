package com.algorytmy.bricks;

import lombok.Setter;

import java.awt.*;

/**
 * @author jbielawski on 08.12.2017 <jakub.bielawski@wawasoft.com>
 *     klasa zawierajaca wlasciwosci planszy do gry
 */
public class Matrix {
    @Setter
    private Character[][] matrix;
    private int matrixSize;

    public Matrix(int size) {
        this.matrixSize = size;
        this.matrix = new Character[size][size];
        clear();
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public Character[][] getMatrix() {
        return matrix;
    }

    void clear() {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                setValue(new Point(i, j), '0');
            }
        }
    }

    public void setValue(Point p, char value) {
        if (value < '0') {
            throw new IllegalArgumentException("Lower than ASCII('0') ");
        }
        matrix[p.y][p.x] = value;
    }

    public void setValue(int row, int column, char value) {
        if (value < '0') {
            throw new IllegalArgumentException("Lower than ASCII('0') ");
        }
        matrix[row][column] = value;
    }

    private String matrixSizeToString() {
        return String.valueOf(matrixSize) + "x" + String.valueOf(matrixSize);
    }

    @Override
    public String toString() {

        StringBuilder ret = new StringBuilder(matrixSizeToString());
        for (int i = 0; i < matrixSize; i++) {

            ret.append("\n");

            for (int j = 0; j < matrixSize; j++) {
                ret.append(String.valueOf(matrix[i][j]));
            }

        }

        return ret.toString();

    }
}
