package com.ulceredge.slotmachine;

import java.util.Arrays;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;

public class Slotmachine {

    private final int MIN_COST = 3;
    private final int NUM_REELS = 3;
    private final int NUM_FRUITS = 3;

    private final String[] FRUITS = {"apple", "banana", "citrus"};

    // pre calculated reward miltiplicators
    private final double[] REWARD_MULT = {3.3, 5, 6.7};

    private String message;

    @Autowired
    Account account;

    public Slotmachine() {
        this.message = "Slot machine is initialized.";
    }

    /**
     * Starts the game with a given rules. If {@code increaseStake} is greater
     * than 0, the stake will be increased.
     */
    public boolean spin(final int increaseStake) {
        int stake = MIN_COST + increaseStake;

        // check if the stake is correct for this machine
        if (stake < MIN_COST) {
            this.message = "Wrong stake value: " + stake + " credits!\n";
            return false;
        }

        // check if the balance has enough credits to play
        if (stake > account.getBalance()) {
            this.message = "You have not enough money on your balance to play!\n";
            return false;
        }

        // create an array of random ints with range 0 - NUM_FRUITS and length NUM_REELS
        int[] reelsInt = new Random().ints(NUM_REELS, 0, NUM_FRUITS).toArray();

        // map ints to an appropriate String values from FRUITS[]
        this.message = "The slot machine shows:"
                + Arrays.stream(reelsInt).mapToObj(i -> FRUITS[i]).reduce("", (s, c) -> s + " " + c) + ".\n";

        // check if all the ints are of the same value by reducing duplicates
        if (Arrays.stream(reelsInt).distinct().count() == 1) {

            // calculate the reward (the reward multiplicator ist taken from REWARD_MULT[])
            int reward = (int) Math.round(REWARD_MULT[reelsInt[0]] * stake);

            this.message += "You won! Your reward is " + reward + " credits!\n";

            // replenish account with the calculated reward minus game cost
            account.deposit(reward - stake);
        } else {
            this.message += "You lose!\n";

            // withdraw the game cost from account
            account.deposit(-stake);
        }
        this.message += "Your actual balance is " + account.getBalance() + " credits.\n";
        return true;
    }

    /**
     * Returns the last message of the slot machine.
     */
    public String getMessage() {
        return this.message;
    }
}
