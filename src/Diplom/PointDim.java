/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diplom;

import java.util.Arrays;

/**
 *
 * @author osboxes
 */
public class PointDim {

    public float[] X = new float[Base.dimension];

    public PointDim(float[] X) {
        this.X = X;
    }

    public PointDim(float X) {
        Arrays.fill(this.X, X);
    }

    public PointDim() {
    }
}
