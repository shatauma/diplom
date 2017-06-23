using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Diagnostics;

namespace Diplom
{
    public class Node
    {
        public List<PointDim> PointSet = new List<PointDim>();
        public float[] left = new float[Base.DIMENSION];
        public float[] right = new float[Base.DIMENSION];
        public HashSet<Node> Pairs = new HashSet<Node>();
        public Node()
        {
        }
        public Node(List<PointDim> PointSet)
        {
            this.PointSet = PointSet;
        }
        public Node(float[] left, float[] right)
        {
            this.left = left;
            this.right = right;
        }
        public void ResetBoundingValues()
        {
            if (!this.IsEmpty())
            {
                for (int i = 0; i < Base.DIMENSION; ++i)
                {
                    this.left[i] = 1;
                    this.right[i] = 0;
                }
                foreach (PointDim point in PointSet)
                {
                    for (int i = 0; i < Base.DIMENSION; ++i)
                    {
                        if (this.left[i] > point.X[i])
                        {
                            this.left[i] = point.X[i];
                        }
                        if (this.right[i] < point.X[i])
                        {
                            this.right[i] = point.X[i];
                        }
                    }
                }
            }
        }
        public bool OnlyOneElement()
        {
            return (PointSet.Count == 1);
        }
        public bool IsEmpty()
        {
            return (PointSet.Count == 0);
        }
        public void AddPoint(PointDim point)
        {
            PointSet.Add(point);
        }
        public Node SplitSelf()
        {
            Node NewNode = new Node();
            List<PointDim> NewPointSet = new List<PointDim>();
            float middle;
            int maxSideIndex = 0;
            for (int i = 1; i < Base.DIMENSION; ++i)
            {
                if (this.right[maxSideIndex] - this.left[maxSideIndex] <
                    this.right[i] - this.left[i])
                {
                    maxSideIndex = i;
                }
            }
            middle = (right[maxSideIndex] + left[maxSideIndex]) / 2;
            foreach (PointDim point in PointSet)
            {
                if (point.X[maxSideIndex] > middle)
                {
                    NewNode.AddPoint(point);
                }
                else
                {
                    NewPointSet.Add(point);
                }
            }
            PointSet = NewPointSet;
            this.ResetBoundingValues();
            NewNode.ResetBoundingValues();
            return NewNode;
        }
        public void Clone(Node node)
        {
            this.PointSet = node.PointSet;
            this.left = node.left;
            this.right = node.right;
            this.Pairs = node.Pairs;
        }
        public PointDim GetCenter()
        {
            PointDim Center = new PointDim();
            for (int i = 0; i < Base.DIMENSION; ++i)
            {
                Center.X[i] = (left[i] + right[i]) / 2;
            }
            return Center;
        }
        public PointDim GetLeft()
        {
            PointDim VeryLeft = new PointDim();
            for (int i = 0; i < Base.DIMENSION; ++i)
            {
                VeryLeft.X[i] = left[i];
            }
            return VeryLeft;
        }
    }
}
