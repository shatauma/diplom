using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Diagnostics;

namespace Diplom
{
    public class Algorithm2
    {
        public PointDim[] DN;
        public PointDim m;
        public float delta;
        public bool stop;
        public Form1 form;
        public Algorithm2(List<PointDim> PointSet, Form1 form)
        {
            DN = new PointDim[2];
            m = PointSet.ElementAt(0);
            delta = 0;
            stop = false;
            this.form = form;
        }
        public PointDim[] Iteration(List<PointDim> PointSet, float epsilon)
        {
            while (!stop)
            {
                PointDim[] pq = DoubleNormal(m, PointSet);
                float newDelta = Base.Distance(pq[0], pq[1]);
                if (newDelta > delta + epsilon)
                {
                    delta = newDelta;
                    DN = pq;
                    PointDim pqCenter = new PointDim();
                    for (int i = 0; i < Base.DIMENSION; ++i)
                    {
                        pqCenter.X[i] = (pq[0].X[i] + pq[1].X[i]) / 2;
                    }
                    m = FPscan(pqCenter, PointSet);
                    if (Base.Distance(pq[0], pq[1]) + epsilon > Base.Distance(m, pqCenter) * 2.0)
                    {
                        stop = true;
                    }
                }
                else
                {
                    stop = true;
                }
            }
            return DN;
        }
        private PointDim[] DoubleNormal(PointDim p, List<PointDim> PointSet)
        {
            PointDim[] DN = new PointDim[2];
            float delta = 0;
            bool deltaIncrease = true;
            while (deltaIncrease)
            {
                deltaIncrease = false;
                PointSet.Remove(p);
                PointDim q = FPscan(p, PointSet);
                float newDelta = Base.Distance(p, q);
                if (newDelta > delta)
                {
                    deltaIncrease = true;
                    delta = newDelta;
                    DN[0] = p;
                    DN[1] = q;
                    p = q;
                    if (Base.DIMENSION == 2)
                    {
                        form.OutputDiameterPoints(DN);
                    }
                }
            }
            return DN;
        }
        private PointDim FPscan(PointDim p, List<PointDim> PointSet)
        {
            int q = 0;
            float delta = -1;
            for (int i = 0; i < PointSet.Count; ++i)
            {
                float newDelta = Base.Distance(p, PointSet[i]);
                if (newDelta > delta)
                {
                    delta = newDelta;
                    q = i;
                }
            }
            return PointSet[q];
        }
    }
}
