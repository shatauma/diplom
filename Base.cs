using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Diagnostics;

namespace Diplom
{
    public static class Base
    {
        public const int DIMENSION = 2;
        const int SEED = 123;
        public const int POINTS_COUNT = 1000000;
        public const float epsilon = 1e-4f;
        public static float Distance(PointDim p, PointDim q)
        {
            float d = 0.0f;
            for (int i = 0; i < DIMENSION; ++i)
            {
                d += (p.X[i] - q.X[i]) * (p.X[i] - q.X[i]);
            }
            return (float)Math.Sqrt(d);
        }
        public static void CreateRandomPoints(List<PointDim> PointSet)
        {
            CreateEllipse(PointSet);
        }
        private static void CreateQube(List<PointDim> PointSet)
        {
            Random random = new Random(SEED);
            for (int i = 0; i < POINTS_COUNT; ++i)
            {
                PointDim TryPoint;
                float[] TryX = new float[DIMENSION];
                for (int j = 0; j < DIMENSION; ++j)
                {
                    TryX[j] = (float)random.NextDouble();
                }
                TryPoint = new PointDim(TryX);
                PointSet.Add(TryPoint);
            }
        }
        private static void CreateEllipse(List<PointDim> PointSet)
        {
            Random random = new Random(SEED);
            PointDim C1 = new PointDim(0.3f);
            PointDim C2 = new PointDim(0.7f);

            for (int i = 0; i < POINTS_COUNT; ++i)
            {
                PointDim TryPoint;
                float[] TryX = new float[DIMENSION];
                do
                {
                    for (int j = 0; j < DIMENSION; ++j)
                    {
                        TryX[j] = (float)random.NextDouble();
                    }
                    TryPoint = new PointDim(TryX);
                } while (Distance(TryPoint, C1) + Distance(TryPoint, C2) > 1);
                PointSet.Add(TryPoint);
            }
        }
    }
    public struct PointMy
    {
        public float X;
        public float Y;
        public PointMy(float X, float Y)
        {
            this.X = X;
            this.Y = Y;
        }
    }//use only for two dimencions
    public class PointDim
    {
        public float[] X;
        public PointDim(float[] X)
        {
            this.X = X;
        }
        public PointDim(float X)
        {
            this.X = new float[Base.DIMENSION];
            for (int i = 0; i < Base.DIMENSION; ++i)
            {
                this.X[i] = X;
            }
        }
        public PointDim()
        {
            this.X = new float[Base.DIMENSION];
        }
    }//use for more then two dimencions
    public struct Pair
    {
        public Node one;
        public Node two;
        public Pair(Node one, Node two)
        {
            this.one = one;
            this.two = two;
        }
    }
}
