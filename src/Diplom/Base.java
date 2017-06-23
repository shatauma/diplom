/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diplom;

import java.util.List;
import java.util.Random;

/**
 *
 * @author osboxes
 */
public class Base {

    public final static int dimension = 5;
    public final static int seed = 123;
    public final static int pointsCount = 1000000;
    public final static float epsilon = 1e-4f;
    
    public static float Distance(PointDim p, PointDim q) {
        float d = 0.0f;
        for (int i = 0; i < dimension; ++i) {
            d += (p.X[i] - q.X[i]) * (p.X[i] - q.X[i]);
        }
        return (float) Math.sqrt(d);
    }

    public static void CreateQube(List<PointDim> PointSet) {
        Random random = new Random(seed);
        for (int i = 0; i < pointsCount; ++i) {
            PointDim TryPoint;
            float[] TryX = new float[dimension];
            for (int j = 0; j < dimension; ++j) {
                TryX[j] = random.nextFloat();
            }
            TryPoint = new PointDim(TryX);
            PointSet.add(TryPoint);
        }
    }

    public static void CreateEllipse(List<PointDim> PointSet) {
        Random random = new Random(seed);
        PointDim C1 = new PointDim(0.3f);
        PointDim C2 = new PointDim(0.7f);

        for (int i = 0; i < pointsCount; ++i) {
            PointDim TryPoint;
            float[] TryX = new float[dimension];
            do {
                for (int j = 0; j < dimension; ++j) {
                    TryX[j] = random.nextFloat();
                }
                TryPoint = new PointDim(TryX);
            } while (Distance(TryPoint, C1) + Distance(TryPoint, C2) > 1);
            PointSet.add(TryPoint);
        }
    }
}
