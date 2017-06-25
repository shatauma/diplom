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

    public final static int dimension = 3;
    public final static int seed = 123;
    public final static int pointsCount = 100000;
    public final static float epsilon = 1e-4f;
    public final static int threadCount = 3;

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

    public static void CreateSphere(List<PointDim> PointSet) {
        Random random = new Random(seed);
        PointDim C1 = new PointDim(0.5f);

        for (int i = 0; i < pointsCount; ++i) {
            PointDim TryPoint;
            float[] TryX = new float[dimension];
            do {
                for (int j = 0; j < dimension; ++j) {
                    TryX[j] = random.nextFloat();
                }
                TryPoint = new PointDim(TryX);
            } while (Distance(TryPoint, C1) > 0.5);
            PointSet.add(TryPoint);
        }
    }

    public static void CreateEllipse(List<PointDim> PointSet) {
        Random random = new Random(seed);
        PointDim C1 = new PointDim(0.3f);
        PointDim C2 = new PointDim(0.7f);

        for (int i = 0; i < pointsCount; ++i) {
            PointDim TryPoint;
//            if (i % 100000 == 0) {
//                System.out.println(i);
//            }
            float[] TryX = new float[dimension];
            do {
                for (int j = 0; j < dimension; ++j) {
                    TryX[j] = random.nextFloat() * 0.8f + 0.1f;
                }
                TryPoint = new PointDim(TryX);
            } while (Distance(TryPoint, C1) + Distance(TryPoint, C2) > 1);
            PointSet.add(TryPoint);
        }
    }

    public static void CreateImpViewA(List<PointDim> PointSet) {
        Random random = new Random(seed);
        if (dimension != 3) {
            System.out.println("Fatal : Dimension != 3 : Creating Cube...");
            CreateQube(PointSet);
            return;
        }

        for (int i = 0; i < pointsCount; ++i) {
            PointDim TryPoint;
            float[] TryX = new float[dimension];
            do {
                for (int j = 0; j < dimension; ++j) {
                    TryX[j] = random.nextFloat();
                }
                TryPoint = new PointDim(TryX);
            } while (((TryPoint.X[0] - 0.5) * (TryPoint.X[0] - 0.5)
                    + (TryPoint.X[1] - 0.5) * (TryPoint.X[1] - 0.5)
                    + (TryPoint.X[2] - 0.5) * (TryPoint.X[2] - 0.5)) < 0.45);
            PointSet.add(TryPoint);
        }
    }

    public static void CreateImpViewD(List<PointDim> PointSet) {
        Random random = new Random(seed);
        if (dimension != 3) {
            System.out.println("Fatal : Dimension != 3 : Creating Cube...");
            CreateQube(PointSet);
            return;
        }

        for (int i = 0; i < pointsCount; ++i) {
            PointDim TryPoint;
            float[] TryX = new float[dimension];
            do {
                for (int j = 0; j < dimension; ++j) {
                    TryX[j] = random.nextFloat();
                }
                TryPoint = new PointDim(TryX);
            } while (((1 / (TryPoint.X[0] - 0.5)) * (1 / (TryPoint.X[0] - 0.5))
                    + (1 / (TryPoint.X[1] - 0.5)) * (1 / (TryPoint.X[1] - 0.5))
                    + (1 / (TryPoint.X[2] - 0.5)) * (1 / (TryPoint.X[2] - 0.5)))
                    < 48);
            PointSet.add(TryPoint);
        }
    }

    public static void CreateImpViewF(List<PointDim> PointSet) {
        Random random = new Random(seed);
        if (dimension != 3) {
            System.out.println("Fatal : Dimension != 3 : Creating Cube...");
            CreateQube(PointSet);
            return;
        }

        for (int i = 0; i < pointsCount; ++i) {
            PointDim TryPoint;
            float[] TryX = new float[dimension];
            do {
                for (int j = 0; j < dimension; ++j) {
                    TryX[j] = random.nextFloat() * 2f - 1f;
                }
                TryPoint = new PointDim(TryX);
            } while (Math.sin(3 * Math.atan(TryPoint.X[1] / TryPoint.X[0])
                    + (TryPoint.X[2] * 2) + (TryPoint.X[0] > 0 ? 1 : 0)
                    * Math.PI) < 0.75);
            for (int j = 0; j < Base.dimension; ++j) {
                TryPoint.X[j] = TryPoint.X[j] / 2f + 0.5f;
            }
            PointSet.add(TryPoint);
        }
    }

    public static void CreateImpViewStarWars(List<PointDim> PointSet) {
        Random random = new Random(seed);
        if (dimension != 3) {
            System.out.println("Fatal : Dimension != 3 : Creating Cube...");
            CreateQube(PointSet);
            return;
        }

        for (int i = 0; i < pointsCount; ++i) {
            PointDim TryPoint;
            float[] TryX = new float[dimension];
            do {
                for (int j = 0; j < dimension; ++j) {
                    TryX[j] = random.nextFloat() * 2 - 1.0f;
                }
                TryPoint = new PointDim(TryX);
            } while (!(((TryPoint.X[0] * TryPoint.X[0] + TryPoint.X[1]
                    * TryPoint.X[1] + TryPoint.X[2] * TryPoint.X[2]) < 0.2)
                    || (((TryPoint.X[1] * TryPoint.X[1] + TryPoint.X[2]
                    * TryPoint.X[2]) < 0.08) && (TryPoint.X[0] < 0.4)
                    && (TryPoint.X[0] > 0)) || ((TryPoint.X[0] * TryPoint.X[0]
                    + 4 * TryPoint.X[1] * TryPoint.X[1]) < ((1
                    - Math.abs(TryPoint.X[2])) * 0.12))
                    || ((Math.abs(TryPoint.X[2]) < 0.95)
                    && (Math.abs(TryPoint.X[2]) > 0.9)
                    && ((Math.abs(TryPoint.X[0]) + Math.abs(TryPoint.X[1])
                    * 0.3) < 1)) || ((Math.abs(TryPoint.X[2]) < 1)
                    && (Math.abs(TryPoint.X[2]) > 0.89))
                    && ((Math.abs(TryPoint.X[0]) < 0.7)
                    && (Math.abs(TryPoint.X[1]) > 0.9)
                    || (Math.abs(TryPoint.X[1]) < 0.035)
                    || (TryPoint.X[0] > (TryPoint.X[1] * 0.7 - 0.05))
                    && (TryPoint.X[0] < (TryPoint.X[1] * 0.7 + 0.05))
                    || (-TryPoint.X[0] > (TryPoint.X[1] * 0.7 - 0.05))
                    && (-TryPoint.X[0] < (TryPoint.X[1] * 0.7 + 0.05))
                    || (((Math.abs(TryPoint.X[0]) + Math.abs(TryPoint.X[1])
                    * 0.3) < 1.05) && ((Math.abs(TryPoint.X[0])
                    + Math.abs(TryPoint.X[1]) * 0.3) > 0.95)))));
            for (int j = 0; j < Base.dimension; ++j) {
                TryPoint.X[j] = TryPoint.X[j] / 2.0f + 0.5f;
            }
            PointSet.add(TryPoint);
        }
    }
}
