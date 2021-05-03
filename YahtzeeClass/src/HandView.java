import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

/**
 Class HandView: acts as the GUI view of a die
 *
 * Attributes: ArrayList<DieView> buttons
 *
 * publicMethods: changeHandView()
 *                getHandView()
 *
 *
 * @author Adam Kowalchyk
 * @version v1.0 03/23/21
 * @see "No Borrowed Code"
 */
public class HandView {

    //stores buttons of DieViews
    ArrayList<DieView> buttons = new ArrayList<DieView>();

    /**
     Constructor for HandView
     *
     */
    public HandView(HandOfDice hand) throws IOException {

        //loops through passed in hand and creates new hand of dice view
        for (int i = 0; i < hand.getHand().size(); i++) {
            DieView dieView = new DieView();
            dieView.setDieView(hand.getDie(i));

            //multiplication used for dieViewSpacing
            dieView.setBounds(i * 100, 100, 100, 100);

            buttons.add(dieView);
        }
    }

    /**
     Changes current hand view by passed in hand
     *
     * @param hand: passed in hand to change handView
     */
    public void changeHandView(HandOfDice hand) throws IOException {
        int dieNum = 0;
        for (int i = 0; i < hand.getHand().size(); i++) {
            buttons.get(i).setDieView(hand.getDie(i));
        }
    }

    /**
        Getter for buttons attribute
     *
     * @return buttons: handView Buttons
     */
    public ArrayList<DieView> getHandView() {
        return buttons;
    }

}
