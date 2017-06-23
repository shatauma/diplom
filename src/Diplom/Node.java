/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diplom;

import static Diplom.Algorithm1.b0;
import static Diplom.Algorithm1.b1;
import static Diplom.Algorithm1.b2;
import static Diplom.Algorithm1.b3;
import static Diplom.Algorithm1.b4;
import static Diplom.Algorithm1.b5;
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
            //if (PointSet.size() < 100) {//this is kostill for faster 0.5 second
            for (PointDim point : PointSet) {
                CheckBoundingValues(point);
            }
            /*} else {
                int bla = 0;
                for (PointDim point : PointSet) {
                    ++bla;
                    if (bla == 10) {
                        bla = 0;
                        CheckBoundingValues(point);
                    }
                }
            }*/
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

    public void AddPoint(PointDim point) {
        PointSet.add(point);
    }

    public Node SplitSelf() {
        b0 = System.currentTimeMillis();
        Node NewNode = new Node();
        ArrayList<PointDim> NewPointSet = new ArrayList<>(PointSet.size());
        NewNode.PointSet = new ArrayList<>(PointSet.size());
        float middle;
        int maxSideIndex = 0;
        b1 += System.currentTimeMillis() - b0;
        for (int i = 1; i < Base.dimension; ++i) {
            if (this.right.X[maxSideIndex] - this.left.X[maxSideIndex]
                    < this.right.X[i] - this.left.X[i]) {
                maxSideIndex = i;
            }
        }
        middle = (right.X[maxSideIndex] + left.X[maxSideIndex]) / 2;
        b2 += System.currentTimeMillis() - b0;
        for (PointDim point : PointSet) {
            if (point.X[maxSideIndex] > middle) {
                NewNode.AddPoint(point);
            } else {
                NewPointSet.add(point);
            }
        }
        b3 += System.currentTimeMillis() - b0;
        PointSet = NewPointSet;
        b4 += System.currentTimeMillis() - b0;
        this.UpdateBoundingValues();
        NewNode.UpdateBoundingValues();
        b5 += System.currentTimeMillis() - b0;
        return NewNode;
    }

    public void Clone(Node node) {
        this.PointSet = node.PointSet;
        this.left = node.left;
        this.right = node.right;
        this.Pairs = node.Pairs;
    }
}
