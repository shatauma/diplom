/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diplom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author osboxes
 */
public class Algorithm1 {

    public ArrayList<Node> Tree = new ArrayList<>();
    public float delta = 0.0f;
    public float maxDelta = 2.0f;
    public int IterationCount = 0;
    public static long a0 = 0;
    public static long a1 = 0;
    public static long a2 = 0;
    public static long a3 = 0;
    public static long b0 = 0;
    public static long b1 = 0;
    public static long b2 = 0;
    public static long b3 = 0;
    public static long b4 = 0;
    public static long b5 = 0;
    public static long c0 = 0;
    public static long c1 = 0;
    public static long c2 = 0;
    public static long c3 = 0;

    public Algorithm1(List<PointDim> PointSet) {
        Tree.add(new Node(new ArrayList<>(PointSet)));
        Tree.get(0).UpdateBoundingValues();
        Tree.get(0).Pairs.add(Tree.get(0));
        int maxSideIndex = 0;
        for (int i = 1; i < Base.dimension; ++i) {
            if (Tree.get(0).right.X[maxSideIndex] - Tree.get(0).left.X[maxSideIndex]
                    < Tree.get(0).right.X[i] - Tree.get(0).left.X[i]) {
                maxSideIndex = i;
            }
        }
        delta = Tree.get(0).right.X[maxSideIndex] - Tree.get(0).left.X[maxSideIndex];
    }

    public void TillEnd() {
        a1 = 0;
        a2 = 0;
        a3 = 0;
        b1 = 0;
        b2 = 0;
        b3 = 0;
        b4 = 0;
        b5 = 0;
        c1 = 0;
        c2 = 0;
        c3 = 0;

        boolean stop = false;
        while (!stop) {
            Iteration();
            if ((Tree.size() == 2 && Tree.get(0).PointSet.size() < 2
                    && Tree.get(1).PointSet.size() < 2) || (Tree.size() == 1
                    && Tree.get(0).PointSet.size() < 3) || (Tree.size() == 0)) {
                stop = true;
            }
        }

        System.out.println("a1 --------- " + a1);
        System.out.println("a2 --------- " + (a2 - a1));
        //System.out.println("a3 --------- " + (a3 - a2));
        System.out.println("c1 --------- " + c1);
        System.out.println("c2 --------- " + (c2 - c1));
        //System.out.println("c3 --------- " + (c3 - c2));
        System.out.println("b1 --------- " + b1);
        System.out.println("b2 --------- " + (b2 - b1));
        System.out.println("b3 --------- " + (b3 - b2));
        System.out.println("b4 --------- " + (b4 - b3));
        System.out.println("b5 --------- " + (b5 - b4));
    }

    public void Iteration() {
        ++IterationCount;
        maxDelta = 0.0f;
        //System.out.println(Tree.size());
        //System.out.println(delta);

        a0 = System.currentTimeMillis();
        SplitNodes();
        a1 += System.currentTimeMillis() - a0;
        RemovePairsNodes();
        a2 += System.currentTimeMillis() - a0;
    }

    private void SplitNodes() {
        int TreeCount = Tree.size();
        for (int i = 0; i < TreeCount; ++i) {
            if (!Tree.get(i).OnlyOneElement()) {
                c0 = System.currentTimeMillis();
                Node newNode = Tree.get(i).SplitSelf();
                c1 += System.currentTimeMillis() - c0;
                if (newNode.IsEmpty()) {// if new Node is empty remove it
                } else if (Tree.get(i).IsEmpty()) {// if old Node become empty clone new node to old
                    Tree.get(i).Clone(newNode);
                } else {// copy pairs to new node
                    newNode.Pairs = new ArrayList<>(Tree.get(i).Pairs);
                    for (Node pair : newNode.Pairs) {
                        pair.Pairs.add(newNode);
                    }
                    newNode.Pairs.add(newNode);
                    Tree.add(newNode);
                }
                c2 += System.currentTimeMillis() - c0;
            }
        }
    }

    private void RemovePairsNodes() {
        // Go over PairSet finding new delta
        for (Node node : Tree) {
            for (Node pair : node.Pairs) {
                float newDelta = this.MinDistance(node, pair);
                if (newDelta > this.delta) {
                    delta = newDelta;
                }
            }
        }
        //Remove all pairs which can`t be maximum, by just partner removing
        for (Node node : Tree) {
            Iterator<Node> pairIterator = node.Pairs.iterator();
            while (pairIterator.hasNext()) {
                Node pair = pairIterator.next();
                if (!pair.Pairs.contains(node)) {
                    pairIterator.remove();
                } else {
                    float MaxPairDistance = this.MaxDistance(node, pair);
                    if (MaxPairDistance < this.delta) {
                        pairIterator.remove();
                    }/* else if (MaxPairDistance > maxDelta) {
                        maxDelta = MaxPairDistance;
                    }*/
                }
            }
        }
        //Remove from tree nodes without any pairs
        Iterator<Node> emptyNodeIterator = Tree.iterator();
        while (emptyNodeIterator.hasNext()) {
            Node emptyNode = emptyNodeIterator.next();
            /*for (Node EmptyNode : Tree) {*/
            if (emptyNode.Pairs.isEmpty()) {
                emptyNodeIterator.remove();
            }
        }
    }

    private float MaxDistance(Node u, Node v) {
        PointDim a = new PointDim();
        PointDim b = new PointDim();
        for (int i = 0; i < Base.dimension; ++i) {
            if (u.right.X[i] < v.left.X[i]) {
                a.X[i] = u.left.X[i];
                b.X[i] = v.right.X[i];
            } else {
                a.X[i] = u.right.X[i];
                b.X[i] = v.left.X[i];
            }
        }
        return Base.Distance(a, b);
    }

    private float MaxDistance(Pair pair) {
        return MaxDistance(pair.one, pair.two);
    }

    private float MinDistance(Node u, Node v) {
        return Base.Distance(u.PointSet.get(u.PointSet.size() - 1),
                v.PointSet.get(v.PointSet.size() - 1));
    }

    private float MinDistance(Pair pair) {
        return MinDistance(pair.one, pair.two);
    }
}
