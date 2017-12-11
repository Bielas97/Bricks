package com.algorytmy.bricks.utils;

import com.algorytmy.bricks.Matrix;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jbielawski on 11.12.2017 <jakub.bielawski@coi.gov.pl>
 */
@Data
@AllArgsConstructor
public class MatrixUtil {
    private Matrix matrix;

    public Matrix cloneMatrix() {
        Matrix cloned = new Matrix(matrix.getMatrixSize());
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            for (int j = 0; j < matrix.getMatrixSize(); j++) {
                cloned.getMatrix()[i][j] = matrix.getMatrix()[i][j];
            }
        }
        return cloned;
    }

    public boolean isFree(int x, int y) {
        if (x < 0 || y < 0 || x >= matrix.getMatrixSize() || y >= matrix.getMatrixSize()) {
            throw new IndexOutOfBoundsException("poza plansze wyszedles");
        }
        return matrix.getMatrix()[x][y].equals('0');
    }

    public boolean isCorner(int x, int y) {
        if (x < 0 || y < 0 || x >= matrix.getMatrixSize() || y >= matrix.getMatrixSize()) {
            throw new IndexOutOfBoundsException("poza plansze wyszedles");
        }
        return (x == 0 && y == 0) || (x == 0 && y == matrix.getMatrixSize() - 1) || (x == matrix.getMatrixSize() - 1 && y == 0) || (x == matrix.getMatrixSize() - 1 && y == matrix.getMatrixSize() - 1);
    }

}
