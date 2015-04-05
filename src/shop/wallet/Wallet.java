package shop.wallet;

/**
 * 
 * @author Sierra, Nathan, Tom
 *
 */
public abstract class Wallet {

    /**
     * Returns the current player unit value.
     * 
     * @return
     */
    abstract double getBalance ();

    /**
     * Takes in a value to add to the player unit value.
     * 
     * @param amount
     */
    abstract void deposit (double amount);

    /**
     * Takes in an amount which it then subtracts from the player unit value.
     * 
     * @param amount
     */
    abstract void withdraw (double amount);
}
