/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulceredge.slotmachine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class TestRandomReelsGenerator {

    public TestRandomReelsGenerator() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {       
        RandomReelsGenerator reelsGen = new RandomReelsGenerator(4, 8);
        
        assertEquals(reelsGen.getResult().length, 4);
        for (int reel : reelsGen.getResult()) {
            assertEquals(reel, 0);
        }
        
        RandomReelsGenerator reelsGenError1 = new RandomReelsGenerator(4, 8);
    }
    
    @Test
    public void testSpin() {
        
    }
}
