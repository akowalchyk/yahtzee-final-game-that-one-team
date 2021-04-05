import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.*;

/**
 Class Rules: Acts as the Rules object for a game of yahtzee.
 *
 * Attributes: int rolls
 *             int handSize
 *             int sides
 *             YahtzeeGUI game;
 *             JFrame f;
 * publicMethods: setGUIRules()
 *                getSides()
 *                getHandSize()
 *                getRolls()
 *
 * @author Adam Kowalchyk
 * @version v3.0 03/23/21
 * @see "No Borrowed Code"
 */
public class Rules {

    //attributes are 'global' and can be used throughout class
    private static int ROLLS;
    private static int HAND_SIZE;
    private static int SIDES;


    private YahtzeeGUI game;
    private JFrame f;

    /**
     Constructor for Rules class, sets initial rules
     */
    public Rules() throws IOException {
        setGuiRules();
    }

    /**
     Setter for the ROLLS attribute
     *
     * @param rolls: rolls attribute
     */
    private void setRolls(int rolls) {
        ROLLS = rolls;
    }

    /**
     Setter for the HAND_SIZE attribute
     *
     * @param handSize: HAND_SIZE attribute
     */
    private void setHandSize(int handSize) {
        HAND_SIZE = handSize;
    }

    /**
     Setter for the SIDES attribute
     *
     * @param sides: sides attribute
     */
    private void setSIDES(int sides) {
        SIDES = sides;
    }

    /**
     Getter for the SIDES attribute
     *
     * @return SIDES
     */
    public static int getSides() {
        return SIDES;
    }

    /**
     Getter for the HAND_SIZE attribute
     *
     * @return HAND_SIZE
     */
    public static int getHandSize() {
        return HAND_SIZE;
    }

    /**
     Getter for the ROLLS attribute
     *
     * @return ROLLS
     */
    public static int getRolls() {
        return ROLLS;
    }


    /**
     Sets rules for the current game of yahtzee
     *
     */
    public void setGuiRules() {

        // create a new frame
        f = new JFrame("Welcome To Yahtzee");

        // sets layout of frame
        f.setLayout(new FlowLayout());

        // arrays for options of # of sides on a die, # of dice in a hand,
        // and number of rolls allowed
        Integer diceOptions[] = {6, 8, 12};
        Integer handOptions[] = {5, 6, 7};
        Integer rollOptions[] = {3,4,5,6,7,8,9,10};

        // creates comboBox for each option of rules
        JComboBox diceBox = new JComboBox(diceOptions);
        JComboBox handBox = new JComboBox(handOptions);
        JComboBox rollBox = new JComboBox(rollOptions);

        // sets up dice combo box
        JLabel diceOptionsLabel = new JLabel("select # of sides on a die");
        diceBox.setEditable(true);
        diceBox.addItemListener(this::diceBoxChanged);
        setSIDES(diceOptions[0]);

        // sets up hand options box
        JLabel handOptionsLabel = new JLabel("select # of dice in hand");
        handBox.setEditable(true);
        handBox.addItemListener(this::handBoxChanged);
        setHandSize(handOptions[0]);

        // sets up rolls option box
        JLabel rollOptionsLabel = new JLabel("select # of rolls per turn");
        rollBox.setEditable(true);
        rollBox.addItemListener(this::rollBoxChanged);
        setRolls(rollOptions[0]);

        // create a new panel for dice options
        JPanel diceOptionsPanel = new JPanel();
        diceOptionsPanel.add(diceOptionsLabel);
        diceOptionsPanel.add(diceBox);

        //creates a new panel for hand options
        JPanel handOptionsPanel = new JPanel();
        handOptionsPanel.add(handOptionsLabel);
        handOptionsPanel.add(handBox);

        //creates a new panel for roll options
        JPanel rollOptionsPanel = new JPanel();
        rollOptionsPanel.add(rollOptionsLabel);
        rollOptionsPanel.add(rollBox);

        //creates a new button to allow user to submit options
        JButton submitOptionsButton = new JButton("Submit Options");
        submitOptionsButton.addActionListener(this::optionsSetButton);


        // adds panels and submit button to frame
        f.add(diceOptionsPanel);
        f.add(handOptionsPanel);
        f.add(rollOptionsPanel);
        f.add(submitOptionsButton);

        //sets size of frame and creates visibility
        f.setSize(400, 300);
        f.setVisible(true);
    }

    /**
     Detects when the DiceBox is changed and changes SIDES to sides selected by user
     *
     * @param e: item event
     */
    public void diceBoxChanged(ItemEvent e) {
        Integer num = (Integer) ((JComboBox)e.getSource()).getSelectedItem();
        setSIDES(num);
    }

    /**
     Detects when the HandBox is changed and changes HAND_SIZE
     to hand size selected by user
     *
     * @param e: item event
     */
    public void handBoxChanged(ItemEvent e) {
        Integer num = (Integer) ((JComboBox)e.getSource()).getSelectedItem();
        setHandSize(num);
        System.out.println(HAND_SIZE);
    }

    /**
     Detects when the rollBox is changed and changes ROLLS
     to # of rolls selected by user
     *
     * @param e: item event
     */
    public void rollBoxChanged(ItemEvent e) {
        Integer num = (Integer) ((JComboBox)e.getSource()).getSelectedItem();
        setRolls(num);
        System.out.println(ROLLS);
    }

    /**
     Detects when set options button is pressed, creates a new game, and closes
     the options window
     *
     * @param e: action event
     */
    public void optionsSetButton(ActionEvent e) {

        //closes window
        f.dispose();
        try {
            //plays new game of yahtzee
            game = new YahtzeeGUI();
            game.playGame();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    };

}
