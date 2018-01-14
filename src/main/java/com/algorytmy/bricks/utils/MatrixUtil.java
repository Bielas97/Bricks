package com.algorytmy.bricks.utils;

import com.algorytmy.bricks.Matrix;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * @author jbielawski on 11.12.2017 <jakub.bielawski@wawasoft.com>
 *     klasa pomocnicza do wykonywania operacji na planszy gry
 */
@Data
@AllArgsConstructor
public class MatrixUtil {
    private Matrix matrix;

    /**
     * sprawdza czy punkty p1, p2 mieszcza sie w obszarze planszy, jesli nie - rzuca wyjatkiem
     * @param p1
     * @param p2
     */
    public void isOutOfBoundsException(Point p1, Point p2) {
        if ((p1.x < 0 || p1.y < 0 || p1.x >= matrix.getMatrixSize() || p1.y >= matrix.getMatrixSize()) ||
                p2.x < 0 || p2.y < 0 || p2.x >= matrix.getMatrixSize() || p2.y >= matrix.getMatrixSize()) {
            System.out.println("wyjatek is out of bound");
            throw new IndexOutOfBoundsException("poza plansze wyszedles");
        }
    }

    /**
     * sprawdza czy wspolrzedne x, y (x - wiersz, y - kolumna) są wolne
     * @param x
     * @param y
     * @return
     */
    public boolean isFree(int x, int y) {
        if (x < 0 || y < 0 || x >= matrix.getMatrixSize() || y >= matrix.getMatrixSize()) {
            //throw new IndexOutOfBoundsException("poza plansze wyszedles");
            return false;
        }
        return matrix.getMatrix()[x][y].equals('0');
    }

    /**
     * zwraca mniejszego x (mniejszy wiersz) sposrod dwoch punktow
     * @param p1
     * @param p2
     * @return
     */
    public int getSmallerX(Point p1, Point p2) {
        if (p1.x < p2.x) return p1.x;
        return p2.x;
    }

    /**
     * zwraca mniejszego y (mniejsza kolumna) sposrod dwoch punktow
     * @param p1
     * @param p2
     * @return
     */
    public int getSmallerY(Point p1, Point p2) {
        if (p1.y < p2.y) return p1.y;
        return p2.y;
    }

}
