/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diplom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author osboxes
 */
public class Algorithm1 {

    public List<Node> Tree = new ArrayList<>();
    public float delta = 0.0f;
    public float maxDelta = 2.0f;
    public int IterationCount = 0;

    public Algorithm1(List<PointDim> PointSet) {
        Tree.add(new Node(new ArrayList<>(PointSet)));
        Tree.get(0).ResetBoundingValues();
        Tree.get(0).Pairs.add(Tree.get(0));
        int maxSideIndex = 0;
        for (int i = 1; i < Base.dimension; ++i) {
            if (Tree.get(0).right[maxSideIndex] - Tree.get(0).left[maxSideIndex]
                    < Tree.get(0).right[i] - Tree.get(0).left[i]) {
                maxSideIndex = i;
            }
        }
        delta = Tree.get(0).right[maxSideIndex] - Tree.get(0).left[maxSideIndex];
    }

    public void Iteration() {
        ++IterationCount;
        maxDelta = 0.0f;
        //System.out.println("Tree.Count()=" + Tree.Count());
        //System.out.println(System.currentTimeMillis() + "- 1-------------------------------");
        SplitNodes();
        //System.out.println(System.currentTimeMillis() + "- 2-------------------------------");
        RemovePairsNodes();
        //System.out.println(System.currentTimeMillis() + "- 3-------------------------------");
        //System.out.println("Tree.Count()=" + Tree.Count());
    }

    private void SplitNodes() {
        int TreeCount = Tree.size();
        for (int i = 0; i < TreeCount; ++i) {
            if (!Tree.get(i).OnlyOneElement()) {
                Tree.add(Tree.get(i).SplitSelf());
                if (Tree.get(Tree.size() - 1).IsEmpty()) {// if new Node is empty remove it
                    Tree.remove(Tree.size() - 1);
                } else if (Tree.get(i).IsEmpty()) {// if old Node become empty clone new node to old
                    Tree.get(i).Clone(Tree.get(Tree.size() - 1));
                    Tree.remove(Tree.size() - 1);
                } else {// copy pairs to new node
                    Tree.get(Tree.size() - 1).Pairs = new HashSet<>(Tree.get(i).Pairs);
                    for (Node pair : Tree.get(Tree.size() - 1).Pairs) {
                        pair.Pairs.add(Tree.get(Tree.size() - 1));
                    }
                    Tree.get(Tree.size() - 1).Pairs.add(Tree.get(Tree.size() - 1));
                }
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
                    } else if (MaxPairDistance > maxDelta) {
                        maxDelta = MaxPairDistance;
                    }
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
        PointDim C1 = u.GetCenter();
        PointDim C2 = v.GetCenter();
        PointDim Left1 = u.GetLeft();
        PointDim Left2 = v.GetLeft();
        float result = Base.Distance(C1, C2) + Base.Distance(Left1, C1)
                + Base.Distance(Left2, C2);
        return result;
    }

    private float MaxDistance(Pair pair) {
        return MaxDistance(pair.one, pair.two);
    }

    private float MinDistance(Node u, Node v) {
        return Base.Distance(u.PointSet.get(0), v.PointSet.get(0));
    }

    private float MinDistance(Pair pair) {
        return MinDistance(pair.one, pair.two);
    }
}
