/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diplom;

import java.util.List;

/**
 *
 * @author osboxes
 */
public class Algorithm2 {

    public PointDim[] DN;
    public PointDim m;
    public float delta;
    public boolean stop;
//    public static long a0 = 0;
//    public static long a1 = 0;

    public Algorithm2(List<PointDim> PointSet) {
        DN = new PointDim[2];
        m = PointSet.get(0);
        delta = 0;
        stop = false;
    }

    public PointDim[] Iteration(List<PointDim> PointSet, float epsilon) {
        while (!stop) {
            PointDim[] pq = DoubleNormal(m, PointSet);
            float newDelta = Base.Distance(pq[0], pq[1]);
            if (newDelta > delta + epsilon) {
                delta = newDelta;
                DN = pq;
                PointDim pqCenter = new PointDim();
                for (int i = 0; i < Base.dimension; ++i) {
                    pqCenter.X[i] = (pq[0].X[i] + pq[1].X[i]) / 2;
                }
                m = FPscan(pqCenter, PointSet);
                if (Base.Distance(pq[0], pq[1]) + epsilon > Base.Distance(m, pqCenter) * 2.0) {
                    stop = true;
                }
            } else {
                stop = true;
            }
        }
        return DN;
    }

    private PointDim[] DoubleNormal(PointDim p, List<PointDim> PointSet) {
        PointDim[] DN = new PointDim[2];
        float delta = 0;
        boolean deltaIncrease = true;
        while (deltaIncrease) {
            deltaIncrease = false;
            PointSet.remove(p);
            PointDim q = FPscan(p, PointSet);
            float newDelta = Base.Distance(p, q);
            if (newDelta > delta) {
                deltaIncrease = true;
                delta = newDelta;
                DN[0] = p;
                DN[1] = q;
                p = q;
            }
        }
        return DN;
    }

    private PointDim FPscan(PointDim p, List<PointDim> PointSet) {
//        System.out.println("FPscan");
//        a1 = 0;
//        a0 = System.currentTimeMillis();
        int q = 0;
        float delta = -1;
        for (int i = 0; i < PointSet.size(); ++i) {
            float newDelta = Base.Distance(p, PointSet.get(i));
            if (newDelta > delta) {
                delta = newDelta;
                q = i;
            }
        }
//        a1 += System.currentTimeMillis() - a0;
//        System.out.println("a1 --------- " + a1);
        return PointSet.get(q);
    }
}
