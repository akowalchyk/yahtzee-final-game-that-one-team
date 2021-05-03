import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

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
    private static int ROLLS = 3;
    private static int HAND_SIZE = 5;
    private static int SIDES = 6;
    private static int NUM_PLayers;
    private static ArrayList<YahtzeeGUI> playerScreens = new ArrayList<YahtzeeGUI>();
    public static int screenCount;
    private static int winnerScore;
    private static int playerNumber;
    private static ArrayList<Integer> playerTotals = new ArrayList<>();


    private YahtzeeGUI game;
    private JFrame f;
    private JTextField t;
    private ArrayList<JTextField> names = new ArrayList<JTextField>();
    private static ArrayList<String> namesArray = new ArrayList<String>();
    private JFrame playerNameFrame;

    /**
     Constructor for Rules class, sets initial rules
     */
    public Rules() throws IOException {
        setGuiRules();
        screenCount = NUM_PLayers;
    }

    public static int getNUM_PLayers() { return NUM_PLayers; }

    public static int getWinnerScore() { return winnerScore; }

    public static int getPlayerNumber() { return playerNumber; }

    public static int getScreenCount() { return screenCount; }


    public static void finalScore() {

        System.out.println("name array " + namesArray);

        int individualScore;

        for (int i = 0; i < playerScreens.size(); i++) {
            individualScore = playerScreens.get(i).getScorecard().getLowerTotal() + playerScreens.get(i).getScorecard().getUpperTotal();
            playerTotals.add(individualScore);
        }

        int n = playerTotals.size();

        System.out.println("player scores " + playerTotals);
        System.out.println(("palyer aname " + namesArray));

        for(int i=0; i<n-1; i++){
            int max_indx = i;
            for (int j = i+1; j < n; j++) {
                if (playerTotals.get(j) > playerTotals.get(i))
                    max_indx = j;
            }
            int temp = playerTotals.get(max_indx);
            String temp1 = namesArray.get(max_indx);
            playerTotals.set(max_indx,playerTotals.get(i));

            namesArray.set(max_indx, namesArray.get(i));
            playerTotals.set(i, temp);
            namesArray.set(i, temp1);

        }
        System.out.println("player scores " + playerTotals);
        System.out.println(("palyer aname " + namesArray));
    }

    public static ArrayList<Integer> getPlayerTotals() {
        return playerTotals;
    }

    public static ArrayList<String> getNamesArray() {
        return namesArray;
    }

    public static  ArrayList<YahtzeeGUI> getPlayerScreens() {
        return playerScreens;
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
     Setter for the NUM_PLayers attribute
     *
     * @param numPlayers: rolls attribute
     */
    private void setNumPlayers(int numPlayers) {
        NUM_PLayers = numPlayers;
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
     Getter for the NUM_Players attribute
     *
     * @return NUM_PLayers
     */
    public static int getNumPlayers() {
        return NUM_PLayers;
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
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Exit the program if the frame is closed by the user

        // arrays for options of # of sides on a die, # of dice in a hand,
        // and number of rolls allowed
        Integer numPlayers[] = {1,2,3,4,5,6};

        JComboBox playersBox = new JComboBox(numPlayers);


        // sets up rolls option box
        JLabel playersOptionsLabel = new JLabel("select # of players");
        playersBox.setEditable(true);
        playersBox.addItemListener(this::numPlayersChanged);
        setNumPlayers(numPlayers[0]);

        // create a new panel for dice options
        JPanel playerOptionsPanel = new JPanel();
        playerOptionsPanel.add(playersOptionsLabel);
        playerOptionsPanel.add(playersBox);


        //creates a new button to allow user to submit options
        JButton submitOptionsButton = new JButton("Submit Options");
        submitOptionsButton.addActionListener(this::optionsSetButton);


        // adds panels and submit button to frame
        f.add(playerOptionsPanel);
        f.add(submitOptionsButton);

        //sets size of frame and creates visibility
        f.setSize(400, 300);
        f.setVisible(true);
    }

    private void setPlayerNames() {
        playerNameFrame = new JFrame("Name Your Players!");
        playerNameFrame.setLayout(new GridBagLayout());
        playerNameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l;

        Container cp = playerNameFrame.getContentPane();
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(2,2,2,2);
        c.anchor = GridBagConstraints.EAST;

        for(int i = 0; i < NUM_PLayers; i++)
            playerNameFrame.add(l = new JLabel("Player " + (i + 1) + ": ", SwingConstants.RIGHT), c);

        c.gridx = 1;
        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 1;
        c.gridy = GridBagConstraints.RELATIVE;

        for (int i = 0; i < NUM_PLayers; i++)
            names.add(t = new JTextField(35));

        for (JTextField tField : names)
            playerNameFrame.add(tField, c);

        c.weightx = 0.0;

        c.fill = GridBagConstraints.NONE;

        JButton submitPlayerNameButton = new JButton("OK!");
        submitPlayerNameButton.addActionListener(this::nameSetButton);
        playerNameFrame.add(submitPlayerNameButton);

        playerNameFrame.pack();

        playerNameFrame.setVisible(true);

    }





    public void numPlayersChanged(ItemEvent e) {
        Integer num = (Integer) ((JComboBox)e.getSource()).getSelectedItem();
        setNumPlayers(num);
        System.out.println(NUM_PLayers);
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
            setPlayerNames();
        }
        finally {

        }
    };

    public void nameSetButton(ActionEvent e) {
        
        for (JTextField tField : names)
            namesArray.add(tField.getText().trim());

        playerNameFrame.dispose();
        try {
            System.out.println("Num Players: " + NUM_PLayers);
            for (int i = 0; i < NUM_PLayers; i++) {
                game = new YahtzeeGUI((i+1), namesArray.get(i));
                playerScreens.add(game);
                playerScreens.get(i).playGame();
            }
            playerScreens.get(0).setIsVisible(true);
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    };

    public static void setCurrentScreen() {
        int currScreen = screenCount % NUM_PLayers;
        System.out.println("Curr Screen" + currScreen);
        playerScreens.get(currScreen).setIsVisible(true);
        ++screenCount;
    }

}
