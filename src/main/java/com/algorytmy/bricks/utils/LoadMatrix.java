package com.algorytmy.bricks.utils;

import com.algorytmy.bricks.Matrix;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;

/**
 * @author jbielawski on 08.12.2017 <jakub.bielawski@wawasoft.com>
 *     klasa do ladowania planszy gry
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoadMatrix {
    private Matrix matrix;
    private boolean ok = false;

    public void setMatrix(String matrixRules) {
        String[] sizeAndPoints = matrixRules.split("_");
        matrix = new Matrix(Integer.parseInt(sizeAndPoints[0]));

        String[] points;
        int x;
        int y;
        for (int i = 1; i < sizeAndPoints.length; i++) {
            points = sizeAndPoints[i].split("[xX]");
            y = Integer.parseInt(String.valueOf(points[0]));
            x = Integer.parseInt(String.valueOf(points[1]));

            Point p = new Point(x, y);
            matrix.setValue(p, 'X');
        }
        ok = true;
    }
}
