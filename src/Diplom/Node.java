/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diplom;

import java.util.ArrayList;

/**
 *
 * @author osboxes
 */
public class Node {

    public ArrayList<PointDim> PointSet = new ArrayList<>();
    public PointDim left = new PointDim();
    public PointDim right = new PointDim();
    public ArrayList<Node> Pairs = new ArrayList<>();

    public Node() {
    }

    public Node(ArrayList<PointDim> PointSet) {
        this.PointSet = PointSet;
    }

    public Node(PointDim left, PointDim right) {
        this.left = left;
        this.right = right;
    }

    public void UpdateBoundingValues() {
        if (!this.IsEmpty()) {
            ResetBoundingValues();
            if (PointSet.size() < 100) {//this is kostill for faster 0.5 second
                for (PointDim point : PointSet) {
                    CheckBoundingValues(point);
                }
            } else {
                int blaStep;
                if (PointSet.size() > 1000) {
                    if (PointSet.size() > 10000) {
                        if (PointSet.size() > 100000) {
                            blaStep = 10000;
                        } else {
                            blaStep = 1000;
                        }
                    } else {
                        blaStep = 100;
                    }
                } else {
                    blaStep = 10;
                }
                for (int bla = 0; bla < PointSet.size(); bla += blaStep) {
                    CheckBoundingValues(PointSet.get(bla));
                }
            }
        }
    }

    private void ResetBoundingValues() {
        for (int i = 0; i < Base.dimension; ++i) {
            this.left.X[i] = 1;
            this.right.X[i] = 0;
        }
    }

    private void CheckBoundingValues(PointDim point) {
        for (int i = 0; i < Base.dimension; ++i) {
            if (this.left.X[i] > point.X[i]) {
                this.left.X[i] = point.X[i];
            }
            if (this.right.X[i] < point.X[i]) {
                this.right.X[i] = point.X[i];
            }
        }
    }

    public boolean OnlyOneElement() {
        return PointSet.size() == 1;
    }

    public boolean IsEmpty() {
        return PointSet.isEmpty();
    }

    public Node SplitSelf() {
//        d0 = System.currentTimeMillis();
        Node NewNode = new Node();
        ArrayList<PointDim> NewPointSet = new ArrayList<>(PointSet.size() >> 1);
        NewNode.PointSet = new ArrayList<>(PointSet.size() >> 1);
        float middle;
        int maxSideIndex = 0;
        for (int i = 1; i < Base.dimension; ++i) {
            if (this.right.X[maxSideIndex] - this.left.X[maxSideIndex]
                    < this.right.X[i] - this.left.X[i]) {
                maxSideIndex = i;
            }
        }
        middle = (right.X[maxSideIndex] + left.X[maxSideIndex]) / 2f;
//        d1 += System.currentTimeMillis() - d0;
        for (PointDim point : PointSet) {
            if (point.X[maxSideIndex] > middle) {
                NewNode.PointSet.add(point);
            } else {
                NewPointSet.add(point);
            }
        }
        PointSet = NewPointSet;
//        d2 += System.currentTimeMillis() - d0;
        this.UpdateBoundingValues();
        NewNode.UpdateBoundingValues();
//        d3 += System.currentTimeMillis() - d0;
        return NewNode;
    }

    public void Clone(Node node) {
        this.PointSet = node.PointSet;
        this.left = node.left;
        this.right = node.right;
        this.Pairs = node.Pairs;
    }
}
