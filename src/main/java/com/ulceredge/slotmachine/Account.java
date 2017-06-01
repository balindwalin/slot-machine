package com.ulceredge.slotmachine;

public class Account {

    private int balance = 0;
    private static final Account instance = new Account();

    private String message;

    private Account() {
    }

    /**
     * Returns the instance of the singelton account.
     */
    public static Account getInstance() {
        return instance;
    }

    /**
     * Deposits the given amount of credits to the balance. The amount can be
     * negative.
     */
    public boolean deposit(final int amount) {
        // check if the balance is greater than 0 after deposition
        if (this.balance + amount < 0) {
            this.message = "Unacceptable deposit: " + amount + " credits. Your account's balance is " + this.balance + " credits.\n";
            return false;
        }
        balance += amount;
        this.message = "You successfully deposited " + amount + " credits. Now your account's balance is " + this.balance + " credits.\n";
        return true;
    }

    /**
     * Returns the value of the account's balance.
     *
     * @return int amount of credits on the balance
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Returns the last message of the account.
     */
    public String getMessage() {
        return this.message;
    }
}
