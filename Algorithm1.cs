using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Diagnostics;

namespace Diplom
{
    public class Algorithm1
    {
        public List<Node> Tree = new List<Node>();
        public float delta = 0.0f;
        public float maxDelta = 2.0f;
        public int IterationCount = 0;
        public Algorithm1(List<PointDim> PointSet)
        {
            Debug.WriteLine("Algorithm1.Constructor()");
            Tree.Add(new Node(new List<PointDim>(PointSet)));
            Tree[0].ResetBoundingValues();
            Tree[0].Pairs.Add(Tree[0]);
            int maxSideIndex = 0;
            for (int i = 1; i < Base.DIMENSION; ++i)
            {
                if (Tree[0].right[maxSideIndex] - Tree[0].left[maxSideIndex] <
                    Tree[0].right[i] - Tree[0].left[i])
                {
                    maxSideIndex = i;
                }
            }
            delta = Tree[0].right[maxSideIndex] - Tree[0].left[maxSideIndex];
            Debug.WriteLine("Algorithm1.Constructor() : Tree.Count=" + Tree.Count + " delta=" + delta);
        }
        public void Iteration()
        {
            ++IterationCount;
            maxDelta = 0.0f;
            //Debug.WriteLine("Tree.Count()=" + Tree.Count());
            Debug.WriteLine(DateTime.Now.ToString("hh.mm.ss.ffffff") + "- 1-------------------------------");
            SplitNodes();
            Debug.WriteLine(DateTime.Now.ToString("hh.mm.ss.ffffff") + "- 2-------------------------------");
            RemovePairsNodes();
            Debug.WriteLine(DateTime.Now.ToString("hh.mm.ss.ffffff") + "- 3-------------------------------");
            //Debug.WriteLine("Tree.Count()=" + Tree.Count());
        }
        private void SplitNodes()
        {
            int TreeCount = Tree.Count;
            for (int i = 0; i < TreeCount; ++i)
            {
                if (!Tree[i].OnlyOneElement())
                {
                    Tree.Add(Tree[i].SplitSelf());
                    if (Tree[Tree.Count - 1].IsEmpty())
                    {// if new Node is empty remove it
                        Tree.RemoveAt(Tree.Count - 1);
                    }
                    else if (Tree[i].IsEmpty())
                    {// if old Node become empty clone new node to old
                        Tree[i].Clone(Tree[Tree.Count - 1]);
                        Tree.RemoveAt(Tree.Count - 1);
                    }
                    else
                    {// copy pairs to new node
                        Tree[Tree.Count - 1].Pairs = new HashSet<Node>(Tree[i].Pairs);
                        foreach (Node pair in Tree[Tree.Count - 1].Pairs)
                        {
                            pair.Pairs.Add(Tree[Tree.Count - 1]);
                        }
                        Tree[Tree.Count - 1].Pairs.Add(Tree[Tree.Count - 1]);
                    }
                }
            }
        }
        private void RemovePairsNodes()
        {
            // Go over PairSet finding new delta
            foreach (Node node in Tree)
            {
                foreach (Node pair in node.Pairs)
                {
                    float newDelta = this.MinDistance(node, pair);
                    if (newDelta > this.delta)
                    {
                        delta = newDelta;
                    }
                }
            }
            //Remove all pairs which can`t be maximum
            foreach (Node node in Tree)
            {
                var NodePairs = node.Pairs.ToArray();
                foreach (Node pair in NodePairs)
                {
                    float MaxPairDistance = this.MaxDistance(node, pair);
                    if (MaxPairDistance < this.delta)
                    {
                        pair.Pairs.Remove(node);
                        node.Pairs.Remove(pair);
                    }
                    else if (MaxPairDistance > maxDelta)
                    {
                        maxDelta = MaxPairDistance;
                    }
                }
            }
            //Remove from tree nodes without any pairs
            var EmptyNodes = Tree.ToArray();
            foreach (Node EmptyNode in EmptyNodes)
            {
                if (EmptyNode.Pairs.Count == 0)
                {
                    Tree.Remove(EmptyNode);
                }
            }
        }
        private float MaxDistance(Node u, Node v)
        {
            PointDim C1 = u.GetCenter();
            PointDim C2 = v.GetCenter();
            PointDim Left1 = u.GetLeft();
            PointDim Left2 = v.GetLeft();
            float result = Base.Distance(C1, C2) + Base.Distance(Left1, C1) + Base.Distance(Left2, C2);
            return result;
        }
        private float MaxDistance(Pair pair)
        {
            return MaxDistance(pair.one, pair.two);
        }
        private float MinDistance(Node u, Node v)
        {
            return Base.Distance(u.PointSet.ElementAt(0), v.PointSet.ElementAt(0));
        }
        private float MinDistance(Pair pair)
        {
            return MinDistance(pair.one, pair.two);
        }
    }
}
