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
    public float[] left = new float[Base.dimension];
    public float[] right = new float[Base.dimension];
    public ArrayList<Node> Pairs = new ArrayList<>();

    public Node() {
    }

    public Node(ArrayList<PointDim> PointSet) {
        this.PointSet = PointSet;
    }

    public Node(float[] left, float[] right) {
        this.left = left;
        this.right = right;
    }

    public void ResetBoundingValues() {
        if (!this.IsEmpty()) {
            for (int i = 0; i < Base.dimension; ++i) {
                this.left[i] = 1;
                this.right[i] = 0;
            }
            for (PointDim point : PointSet) {
                for (int i = 0; i < Base.dimension; ++i) {
                    if (this.left[i] > point.X[i]) {
                        this.left[i] = point.X[i];
                    }
                    if (this.right[i] < point.X[i]) {
                        this.right[i] = point.X[i];
                    }
                }
            }
        }
    }

    public boolean OnlyOneElement() {
        return PointSet.size() == 1;
    }

    public boolean IsEmpty() {
        return PointSet.isEmpty();
    }

    public void AddPoint(PointDim point) {
        PointSet.add(point);
    }

    public Node SplitSelf() {
        Node NewNode = new Node();
        ArrayList<PointDim> NewPointSet = new ArrayList<>();
        float middle;
        int maxSideIndex = 0;
        for (int i = 1; i < Base.dimension; ++i) {
            if (this.right[maxSideIndex] - this.left[maxSideIndex]
                    < this.right[i] - this.left[i]) {
                maxSideIndex = i;
            }
        }
        middle = (right[maxSideIndex] + left[maxSideIndex]) / 2;
        for (PointDim point : PointSet) {
            if (point.X[maxSideIndex] > middle) {
                NewNode.AddPoint(point);
            } else {
                NewPointSet.add(point);
            }
        }
        PointSet = NewPointSet;
        this.ResetBoundingValues();
        NewNode.ResetBoundingValues();
        return NewNode;
    }

    public void Clone(Node node) {
        this.PointSet = node.PointSet;
        this.left = node.left;
        this.right = node.right;
        this.Pairs = node.Pairs;
    }

    public PointDim GetCenter() {
        PointDim Center = new PointDim();
        for (int i = 0; i < Base.dimension; ++i) {
            Center.X[i] = (left[i] + right[i]) / 2;
        }
        return Center;
    }

    public PointDim GetLeft() {
        PointDim VeryLeft = new PointDim();
        for (int i = 0; i < Base.dimension; ++i) {
            VeryLeft.X[i] = left[i];
        }
        return VeryLeft;
    }
}
