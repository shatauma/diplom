/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diplom;

/**
 *
 * @author osboxes
 */
public class PointDim {

    public float[] X;

    public PointDim(float[] X) {
        this.X = X;
    }

    public PointDim(float X) {
        this.X = new float[Base.dimension];
        for (int i = 0; i < Base.dimension; ++i) {
            this.X[i] = X;
        }
    }

    public PointDim() {
        this.X = new float[Base.dimension];
    }
}
