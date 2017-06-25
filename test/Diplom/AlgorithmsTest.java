/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diplom;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author osboxes
 */
public class AlgorithmsTest {

    List<PointDim> PointSet = new ArrayList<>();
    Algorithm1 Algorithm1;
    Algorithm2 Algorithm2;
    long time = 0;

    public AlgorithmsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("Initialize Dimensions: " + Base.dimension + " Points count: " + Base.pointsCount);
        //System.out.println("Started Initialize");
        //System.out.println(System.currentTimeMillis());
        PointSet = new ArrayList<>();
        Base.CreateEllipse(PointSet);
        //System.out.println(System.currentTimeMillis());
        Algorithm1 = new Algorithm1(PointSet);
        Algorithm2 = new Algorithm2(PointSet);
        //System.out.println(System.currentTimeMillis());
        //System.out.println("Finished Initialize");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Algorithm1.
     */
    @Test
    public void testAlgorithm1() {
        System.out.println("Started Algorythm 1 Till end  " + System.currentTimeMillis());
        time = System.currentTimeMillis();
        Algorithm1.TillEnd();
        time = System.currentTimeMillis() - time;
        System.out.println("Time = " + time / 1000.0f);
        System.out.println("Finished Algorythm 1 Till end " + System.currentTimeMillis() + " { (" + Algorithm1.Tree.get(0).PointSet.get(0).X[0] + ", " + Algorithm1.Tree.get(0).PointSet.get(0).X[1] + "), (" + Algorithm1.Tree.get(0).Pairs.iterator().next().PointSet.get(0).X[0] + ", " + Algorithm1.Tree.get(0).Pairs.iterator().next().PointSet.get(0).X[1] + ") }");
        System.out.println("Distance = " + Base.Distance(Algorithm1.Tree.get(0).PointSet.get(0), Algorithm1.Tree.get(0).Pairs.iterator().next().PointSet.get(0)));
    }

    /**
     * Test of Algorithm2.
     */
    @Test
    public void testAlgorithm2() {
        System.out.println("Started Algorythm 2 Till end  " + System.currentTimeMillis());
        time = System.currentTimeMillis();
        PointDim[] DN = Algorithm2.Iteration(PointSet, 0.0f);
        time = System.currentTimeMillis() - time;
        System.out.println("Time = " + time / 1000.0f);
        System.out.println("Finished Algorythm 2 Till end " + System.currentTimeMillis() + " { (" + DN[0].X[0] + ", " + DN[0].X[1] + "), (" + DN[1].X[0] + ", " + DN[1].X[1] + ") }");
        System.out.println("Distance = " + Base.Distance(DN[0], DN[1]));
    }

    /**
     * Test of Algorithm3.
     */
    @Ignore
    @Test
    public void testAlgorithm3() {
        System.out.println("Started Algorythm 3 Till end  " + System.currentTimeMillis());
        time = System.currentTimeMillis();
        PointDim[] DN = Algorithm3.Iteration(PointSet);
        time = System.currentTimeMillis() - time;
        System.out.println("Time = " + time / 1000.0f);
        System.out.println("Finished Algorythm 3 Till end " + System.currentTimeMillis() + " { (" + DN[0].X[0] + ", " + DN[0].X[1] + "), (" + DN[1].X[0] + ", " + DN[1].X[1] + ") }");
        System.out.println("Distance = " + Base.Distance(DN[0], DN[1]));
    }

    /**
     * Test of Algorithm4.
     */
    @Ignore
    @Test
    public void testAlgorithm4() {
        System.out.println("Started Algorythm 3 Till end  " + System.currentTimeMillis());
        time = System.currentTimeMillis();
        PointDim[] DN = Algorithm4.Iteration(PointSet);
        time = System.currentTimeMillis() - time;
        System.out.println("Time = " + time / 1000.0f);
        System.out.println("Finished Algorythm 3 Till end " + System.currentTimeMillis() + " { (" + DN[0].X[0] + ", " + DN[0].X[1] + "), (" + DN[1].X[0] + ", " + DN[1].X[1] + ") }");
        System.out.println("Distance = " + Base.Distance(DN[0], DN[1]));
    }

}
