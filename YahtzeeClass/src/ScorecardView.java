import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 Class Scorecard: acts as the GUI Scorecard view for the scorecard class
 *
 * Attributes: private ArrayList<JButton> upperViewLabels
 *             private ArrayList<JButton> lowerViewLabels
 *             private ArrayList<JLabel> upperViewOptions
 *             private ArrayList<JLabel> lowerViewOptions
 *             private ArrayList<Integer> upperScores
 *             private ArrayList<Integer> lowerScores
 *             private YahtzeeGUI currGame;
 * publicMethods:
 *           initialHand
 *           changeUpperViewOptions
 *           changeLowerViewOptions
 *           getUpperViewLabels
 *           getLowerViewLabels
 *           getUpperViewOptions
 *           getLowerViewOptions
 *           printGUIScorecard
 *           setUpperScores
 *           setLowerScores
 *           checkEndGame
 *
 * @author Adam Kowalchyk
 * @version v1.0 03/23/21
 * @see "No Borrowed Code"
 *
 */
public class ScorecardView extends Scorecard {
    Scorecard scorecard;

    // all attributes act the same as scorecard attributes
    private ArrayList<JButton> upperViewLabels = new ArrayList<JButton>();
    private ArrayList<JButton> lowerViewLabels = new ArrayList<JButton>();
    private ArrayList<JLabel> upperViewOptions = new ArrayList<JLabel>();
    private ArrayList<JLabel> lowerViewOptions = new ArrayList<JLabel>();
    private ArrayList<Integer> upperScores = new ArrayList<>();
    private ArrayList<Integer> lowerScores = new ArrayList<>();
    private YahtzeeGUI currGame;

    /**
     Constructor for Scorecard View
     *
     */
    public ScorecardView(Scorecard newScorecard) {
        scorecard = newScorecard;
        setUpperViewLabels();
        setLowerViewLabels();
        setUpperViewOptions();
        setLowerViewOptions();
    }

    /**
     Sets the scorecards current game
     *
     * @param currGame: currGame being played
     */
    public void setCurrGame(YahtzeeGUI currGame) {
        this.currGame = currGame;
    }

    /**
     Sets the lower view labels of the GUI
     *
     */
    private void setLowerViewLabels() {

        ArrayList<String> lowerLabels = scorecard.getLowerLabels();

        //loops through lower labels of scorecard and creates a button for each
        //label then adds it to the list
        for (int i = 0; i < lowerLabels.size()-1; i++) {
            JButton button = new JButton(lowerLabels.get(i));
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            lowerViewLabels.add(button);
            lowerViewLabels.get(i).addActionListener(this::optionClicked);
        }
    }

    /**
     Sets the upper view labels of the GUI
     *
     */
    private void setUpperViewLabels() {
        ArrayList<String> upperLabels = scorecard.getUpperLabels();

        //loops through upper labels of scorecard and creates a button for each
        //label then adds it to the list
        for (int i = 0; i < Rules.getSides(); i++) {
            JButton button = new JButton(upperLabels.get(i));
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            upperViewLabels.add(button);
            upperViewLabels.get(i).addActionListener(this::optionClicked);
        }
    }

    /**
     Sets the upper view options of the GUI
     *
     */
    private void setUpperViewOptions() {

        //loops through upper options of scorecard and sets the initial option
        //to blank
        for (int i = 0; i < Rules.getSides(); i++) {
            JLabel label = new JLabel("-");
            label.setFont(new Font("Arial", Font.PLAIN, 39));
            upperViewOptions.add((label));
        }
    }

    /**
     Sets the lower view options of the GUI
     *
     */
    private void setLowerViewOptions() {

        //loops through lower options of scorecard and sets the initial option
        //to blank
        for (int i = 0; i < scorecard.getLowerLabels().size()-1; i++) {
            JLabel label = new JLabel("-");
            label.setFont(new Font("Arial", Font.PLAIN, 39));
            lowerViewOptions.add((label));
        }
    }

    /**
     Changes upper view options of scorecard view by passed in scores
     *
     * @param upperScores: scores calculated for the users turn
     */
    public void changeUpperViewOptions(ArrayList<Integer> upperScores) {
        for (int i = 0; i < upperScores.size(); i++) {
            upperViewOptions.get(i).setText(String.valueOf(upperScores.get(i)));
        }
    }

    /**
     Changes lower view options of scorecard view by passed in scores
     *
     * @param lowerScores: scores calculated for the users turn
     */
    public void changeLowerViewOptions(ArrayList<Integer> lowerScores) {
        for (int i = 0; i < lowerScores.size(); i++) {
            lowerViewOptions.get(i).setText(String.valueOf(lowerScores.get(i)));
        }
    }

    /**
    Getter for upper view labels
     */
    public ArrayList<JButton> getUpperViewLabels() {
        return upperViewLabels;
    }

    /**
     Getter for lower view labels
     */
    public ArrayList<JButton> getLowerViewLabels() {
        return lowerViewLabels;
    }

    /**
     Getter for lower view options
     */
    public ArrayList<JLabel> getLowerViewOptions() {
        return lowerViewOptions;
    }

    /**
     Getter for upper view options
     */
    public ArrayList<JLabel> getUpperViewOptions() {
        return upperViewOptions;
    }


    /**
     Prints the current GUI scorecard to a new frame
     *
     */
    public void printGUIScoreCard() {

        //sets initial frame and panel
        JFrame scorecardFrame = new JFrame("Scorecard");
        scorecardFrame.setSize(100,700);
        JPanel scorecardView = new JPanel();
        scorecardView.setLayout(new BoxLayout(scorecardView, BoxLayout.Y_AXIS));

        //printing upper section
        scorecardView.add(new JLabel("Upper Section"));
        scorecardView.add(new JLabel("---------------------"));
        scorecardView.add(new JLabel("Upper Bonus = " + scorecard.computeBonus()));
        for(int i = 0; i < scorecard.getUpperLabels().size(); i++){
            scorecardView.add(new JLabel(scorecard.getUpperLabels().get(i) + " | "  +
                    scorecard.getUpperScores().get(i)));
        }

        scorecardView.add(new JLabel("----------------------------"));
        scorecardView.add(new JLabel("----------------------------"));

        //printing lower section
        scorecardView.add(new JLabel("Lower Section"));
        scorecardView.add(new JLabel("---------------------"));
        for (int i = 0; i < scorecard.getLowerLabels().size(); i++) {
            scorecardView.add(new JLabel(scorecard.getLowerLabels().get(i) + " | " +
                    scorecard.getLowerScores().get(i)));
        }

        //adds panel to frame and sets to visible
        scorecardFrame.add(scorecardView);
        scorecardFrame.setVisible(true);
    }

    /**
     Detects when any of the score options have been clicked, fills the
     scorecard with the score option picked, and resets the hand
     *
     * @param e: action event
     */
    public void optionClicked(ActionEvent e) {

        //disabling option selected and getting the label to determine score
        ((JButton)e.getSource()).setEnabled( false );
        String scoreline = ((JButton)e.getSource()).getLabel();

        //filling the scorecard
        scorecard.fillScoreCard(upperScores,lowerScores,
                scoreline);

        //resetting the handView and the Roll Dice Button
        currGame.resetHand();
        currGame.resetRollDiceB();
    }

    /**
     Setter for upper scores
     *
     * @param newScores: new upper scores
     */
    public void setUpperScores(ArrayList<Integer> newScores) {
        upperScores = newScores;
    }

    /**
     Setter for lower scores
     *
     * @param newScores: new lower scores
     */
    public void setLowerScores(ArrayList<Integer> newScores) {
        lowerScores = newScores;
    }

    /**
     Checks if all scoring options have been selected
     *
     * @return endGame: false if no endGame, true if endGame
     */
    public Boolean checkEndgame() {
        Boolean endGame = false;
        int count = 0;

        //loops through upperViewLabels to check if clicked
        for (int i = 0; i < Rules.getSides(); i++) {
            if (upperViewLabels.get(i).isEnabled() == false) {
                count+=1;
            }
        }

        //loops through lowerViewLabels to check if clicked
        for (int i = 0; i < lowerViewLabels.size(); i++) {
            if (lowerViewLabels.get(i).isEnabled() == false) {
                count+=1;
            }
        }

        //checks if all options have been clicked
        if (count == (Rules.getSides() +lowerViewLabels.size())) {
            endGame = true;
        }

        return endGame;
    }


}
