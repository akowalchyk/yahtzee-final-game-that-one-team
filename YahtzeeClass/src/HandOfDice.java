import java.util.ArrayList;
import java.util.Collections;

/**
 Class HandOfDice: acts as a HandOfDice object, has-a relationship with class Die
 *
 * Attributes: int handSize
 *             ArrayList</Die>hand
 * publicMethods: initialHand()
 *           printHand()
 *           rollNewHand()
 *           sortHand()
 */
public class HandOfDice {

    //size of hand
    private final int handSize = Rules.getHandSize();

    //hand of Dice
    private ArrayList<Die> hand = new ArrayList<Die>();

    /**
     Constructor for HandOfDice Class
     *
     */
    public HandOfDice() {
        setInitialHand();
    }

    /**
     Getter for hand attribute
     *
     * @return hand: current hand
     */
    public ArrayList<Die> getHand() {
        return hand;
    }

    /**
     Creates the initial hand of dice
     *
     */
    public void setInitialHand() {

        //adds new dice to the hand
        for (int i = 0; i < handSize; i++) {
            Die newDie = new Die(6);
            hand.add(newDie);
            hand.get(i).rollDie();
        }
    }


    /**
     gets die by passed in index
     *
     * @param index: index of wanted die in hand
     * @return die: die wanted from index
     */
    public Die getDie(int index) {
        Die die = hand.get(index);
        return die;
    }

    /**
     Prints hand of dice
     *
     */
    public void printHand() {

        //loops through the hand and prints each die
        for (int i = 0; i < handSize; i++) {
            System.out.print(hand.get(i));
        }
        System.out.println();
    }

    /**
     Rolls a new hand
     *
     */
    public void rollNewHand() {
        char ch1;
        //loops through hand and checks to see which dice to roll again
        for (int i = 0; i < handSize; i++) {
            //grabs each char in string to decide if its a y or n
            if (hand.get(i).getLocked() == false) {
                hand.get(i).rollDie();
            }
        }
        //this.sortHand();
    }

    /**
     Sorts the hand of dice in increasing order and prints the sorted hand
     */
    public void sortHand() {
                //creates a temp array of integers to be sorted
                ArrayList<Integer> temp = new ArrayList<Integer>();

                //copies over values of dice to the new array list of integers
                for (int i = 0; i < handSize; i++) {
                    temp.add(hand.get(i).getDieNum());
                }
                Collections.sort(temp);

                //sets the dice values to be in sorted order and prints the hand
                for (int i = 0; i < handSize; i++) {
                    hand.get(i).setDieNum(temp.get(i));
                }
    }
}
