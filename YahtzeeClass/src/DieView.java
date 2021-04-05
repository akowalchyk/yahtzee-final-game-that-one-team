import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.border.Border;

/**
 Class DieView: acts as the GUI view of a die
 *
 * Attributes: Die die;
 *             DiceImages image;
 *             Border redLine;
 *             Border blackLine;
 * publicMethods: getDie()
 *                setDieView()
 *
 *
 * @author Adam Kowalchyk
 * @version v4.0 03/23/21
 * @see "No Borrowed Code"
 */
public class DieView extends JToggleButton {

    private boolean locked = false;
    private Die die;
    private DiceImages image = new DiceImages();
    private Border redLine =  BorderFactory.createLineBorder(Color.RED);
    private Border blackLine =  BorderFactory.createLineBorder(Color.BLACK);

    /**
     Constructor for the DieView Class
     *
     */
    public DieView() throws IOException {
        setSize(40,40);
        Image dieImage = image.getDieImage(1);
        setIcon(new ImageIcon(dieImage));
        addItemListener(buttonPressed);
        setBorder(blackLine);
    }

    /**
     Getter for the die attribute
     *
     * @return die
     */
    public Die getDie() {
        return die;
    }

    /**
     Sets the new die view to passed in die
     *
     * @return die
     */
    public void setDieView(Die newDice) throws IOException {
        die = newDice;
        Image dieImage = image.getDieImage(newDice.getDieNum());
        setIcon(new ImageIcon(dieImage));
    }

    /**
     Creates an item Listener to current Die View button, toggles state of die
     from pressed to not pressed
     *
     * @param itemEvent: passed in event for listening
     */
    ItemListener buttonPressed = new ItemListener() {
        public void itemStateChanged(ItemEvent itemEvent) {
            Border redLine =  BorderFactory.createLineBorder(Color.RED);

            int state = itemEvent.getStateChange();

            // if button is selected, lock the die and change border
            if (state == ItemEvent.SELECTED) {
                setBorder(redLine);
                die.lock();
                System.out.println("Selected");

            //if item is deselected, unlock the die and change border
            } else {
                setBorder(blackLine);
                die.unlock();
                System.out.println("Deselected");
            }
        }
    };
}
