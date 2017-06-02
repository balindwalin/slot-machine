/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulceredge.slotmachine;

import java.util.Random;

/**
 * Generates random values (fruits) of reels for slot machine. 
 */
public class RandomReelsGenerator {

    private final int numOfReels;
    private final int numOfFruits;
    
    private int[] reels;
    
    public RandomReelsGenerator(final int numOfReels, final int numOfFruits) {
        this.numOfReels = numOfReels;
        this.numOfFruits = numOfFruits;
        
        this.reels = new int[numOfReels];
    }
 
    public RandomReelsGenerator spin() {
        // create an array of random ints with range 0 - numOfFruits and length numOfReels
        this.reels = new Random().ints(this.numOfReels, 0, this.numOfFruits).toArray();
        return this;
    }
    
    public int[] getResult() {
        return this.reels;
    }
}
