package com.algorytmy.bricks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;

/**
 * @author jbielawski on 08.12.2017 <jakub.bielawski@coi.gov.pl>
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoadMatrix {
    private Matrix matrix;
    private boolean ok = false;

    void setMatrix(String matrixRules) {
        String[] sizeAndPoints = matrixRules.split("_");
        matrix = new Matrix(Integer.parseInt(sizeAndPoints[0]));

        for (int i = 1; i < sizeAndPoints.length; i++) {
            int x = Integer.parseInt(String.valueOf(sizeAndPoints[i].charAt(0)));
            int y = Integer.parseInt(String.valueOf(sizeAndPoints[i].charAt(2)));

            Point p = new Point(x, y);
            matrix.setValue(p, 'X');
        }
        ok = true;
    }

}
