
import java.util.Random;

/**
 Class Dice: acts as a Dice object, used as an object in HandOfDice
 *
 * Attributes: int sides
 *             int dieNum
 *             boolean locked
 * publicMethods: rollDie()
 *                toString()
 *
 * @author Adam Kowalchyk
 * @version v2.0 03/06/21
 * @see "No Borrowed Code"
 */
public class Die {

    //number of sides on die
    private int sides = Rules.getSides();
    //value of die
    private int dieNum;

    //tells weather die is locked for rolling
    private Boolean locked;

    /**
     Constructor for the Dice Class
     *
     * @param dieNumParam: parameter for the dieNum attribute
     */
    public Die(int dieNumParam) {

        dieNum = dieNumParam;
        locked = false;
    }

    /**
     Getter for the dieNum attribute
     *
     * @return dieNum
     */
    public int getDieNum() {
        return dieNum;
    }

    /**
     Setter for the dieNum attribute
     * @param dieNum:  new die num to be set to dieNum param
     */
    public void setDieNum(int dieNum) {
        this.dieNum = dieNum;
    }

    /**
     rolls the die - assigns dieNum to random integer
     */
    public void rollDie() {
        Random rand = new Random();
        setDieNum(rand.nextInt(sides) + 1);
    }

    /**
     Unlocks die
     */
    public void unlock() {
        locked = false;
    }

    /**
     Locks die
     */
    public void lock() {
        locked = true;
    }

    /**
     Getter for the locked attribute
     *
     * @return dieNum
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     prints the dieNum of the die
     *
     * @return string value of die
     */
    public String toString() {
        return String.valueOf(getDieNum());
    }
}
