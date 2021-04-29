import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 Class Scorecard: The Yahtzee GUI, creates a game of yahtzee
 *
 * Attributes: private JFrame mainFrame;
 *              private JButton rollDiceB;
 *              private JButton showScorecard;
 *              HandOfDice newHand;
 *              private HandView handView;
 *              private Scorecard scorecard;
 *              private  ScorecardView scorecardView;
 *              private JPanel labelPanel;
 *              private JPanel scoreOptionsPanel;
 *              private ArrayList<DieView> buttons;
 *              private int clicked;
 * publicMethods: initialHand()
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
public class YahtzeeGUI {

    private JFrame mainFrame;
    private JButton rollDiceB;
    private JButton showScorecard;
    private HandOfDice newHand;
    private HandView handView;
    private Scorecard scorecard;
    private  ScorecardView scorecardView;
    private JPanel labelPanel;
    private JPanel scoreOptionsPanel;
    private JPanel scoresPanel;
    private ArrayList<DieView> buttons;
    private JPanel intructionPanel;
    private int clicked;
    private int playerNumber;
    private Boolean isVisible;

    /**
     Constructor for YahtzeeGUI
     */
    public YahtzeeGUI(int playerNum) throws IOException {
        playerNumber = playerNum;
        mainFrame=new JFrame("Yahtzee");
        rollDiceB = new JButton("Roll Dice");
        showScorecard = new JButton("Show Scorecard");
        newHand = new HandOfDice();
        handView = new HandView(newHand);
        scorecard = new Scorecard();
        scorecardView = new ScorecardView(scorecard);
        labelPanel = new JPanel();
        scoreOptionsPanel = new JPanel();
        scoresPanel = new JPanel();
        intructionPanel = new JPanel();
        clicked = 0;
        setIsVisible(false);
    }

    public void setIsVisible(Boolean visible) {
        isVisible = visible;
        if (isVisible == true) {
            mainFrame.setVisible(true);
        } else {
            mainFrame.setVisible(false);
        }
    }

    /**
     Plays a GUI game of Yahtzee
     */
    public void playGame() throws IOException {

        //setting up main frame of Yahtzee
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(2000,2000);
        mainFrame.setLayout(null);
        scorecardView.setCurrGame(this);

        addInstructions();

        //setting up GUI hand of dice
        buttons = handView.getHandView();
        for (int i = 0; i < buttons.size(); i++) {
            mainFrame.add(buttons.get(i));
        }

        //setting up Roll Dice Button and initially rolling dice
        rollDiceB.setBounds(700,100,150,50);
        rollGUIDice();
        rollDiceB.addActionListener(this::rollDiceClicked);
        mainFrame.add(rollDiceB);

        //Setting up LabelPanel and ScoreOptionsPanel
        setLabelPanel();
        setScoreOptionsPanel();
        setScoresPanel();

        //Setting up show scorecard button
        showScorecard.setBounds(700,200,150,50);
        showScorecard.addActionListener(this::scorecardClicked);
        //mainFrame.add(showScorecard);


    }

    /**
     Adds instructions for the Yahtzee Game to the frame
     */
    private void addInstructions() {

        //creating block of text
        JPanel intructionPanel = new JPanel();
        JLabel step1 = new JLabel(
                "Step #1: Click Dice you want to keep. " +
                        "Then roll remaining dice.");

        JLabel step2 = new JLabel("Step #2: When you would like to keep your " +
                "dice, ");
        JLabel step22 = new JLabel("click on a scoring option under " +
                "possible scores.");
        JLabel step3 = new JLabel("Step #3: After you click a scoring option, " +
                "the next turn will");
        JLabel step33 = new JLabel("automatically begin and you " +
                "can roll your next dice.");
        JLabel playerLabel = new JLabel("Player #" + Integer.toString(playerNumber));


        //setting size of instructions
        step1.setFont(new Font("Arial", Font.PLAIN, 20));
        step2.setFont(new Font("Arial", Font.PLAIN, 20));
        step22.setFont(new Font("Arial", Font.PLAIN, 20));
        step3.setFont(new Font("Arial", Font.PLAIN, 20));
        step33.setFont(new Font("Arial", Font.PLAIN, 20));
        playerLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        //setting up instruction panel
        intructionPanel.setSize(700,700);
        intructionPanel.setLocation(0,220);
        intructionPanel.setLayout(new BoxLayout(intructionPanel, BoxLayout.Y_AXIS));
        intructionPanel.add(step1);
        intructionPanel.add(step2);
        intructionPanel.add(step22);
        intructionPanel.add(step3);
        intructionPanel.add(step33);
        intructionPanel.add(playerLabel);

        //adding instruction panel to main frame
        mainFrame.add(intructionPanel);
    }

    /**
     Rolls GUI Dice
     */
    private void rollGUIDice() {
        //gets upper and lower scores
        ArrayList<Integer> upperScores = scorecard.printUpperScoreOptions(newHand.getHand());
        ArrayList<Integer> lowerScores = scorecard.printLowerScoreOptions(newHand.getHand());

        //changes upper score options and lower score options
        scorecardView.changeUpperViewOptions(upperScores);
        scorecardView.changeLowerViewOptions(lowerScores);
        scorecardView.setLowerScores(lowerScores);
        scorecardView.setUpperScores(upperScores);
    }

    /**
     Creates itial label panel
     *
     */
    private void setLabelPanel() {

        //creating panel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setLocation(1000,80);
        labelPanel.setSize(250,1000);

        //creating label for label panel
        JLabel labelLabel = new JLabel("Possible Scores");
        labelLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        labelPanel.add(labelLabel);

        //transforming labels from scorecard into buttons
        ArrayList<JButton> upperLabelButtons = scorecardView.getUpperViewLabels();
        ArrayList<JButton> lowerLabelButtons = scorecardView.getLowerViewLabels();

        //looping through buttons and adding them to the panel
        for (int i = 0; i < upperLabelButtons.size(); i++) {
            labelPanel.add(upperLabelButtons.get(i));
        }
        for (int i = 0; i < lowerLabelButtons.size(); i++) {
            labelPanel.add(lowerLabelButtons.get(i));
        }
        mainFrame.add(labelPanel);
    }

    /**
     Creates initial scoreOptionsPanel
     */
    private void setScoreOptionsPanel() {

        //creating scoreOptionsPanel
        scoreOptionsPanel.setLayout(new BoxLayout(scoreOptionsPanel, BoxLayout.Y_AXIS));
        scoreOptionsPanel.setLocation(900,80);
        scoreOptionsPanel.setSize(200,1000);

        //creating scoreOptionsPanel Label
        JLabel optionsLabel = new JLabel("Scores");
        optionsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        scoreOptionsPanel.add(optionsLabel);
        ArrayList<JLabel> upperLabelOptions = scorecardView.getUpperViewOptions();
        ArrayList<JLabel> lowerLabelOptions= scorecardView.getLowerViewOptions();

        //looping through scoring options and adding button to panel
        for (int i = 0; i < upperLabelOptions.size(); i++) {
            scoreOptionsPanel.add(upperLabelOptions.get(i));
        }
        for (int i = 0; i < lowerLabelOptions.size(); i++) {
            scoreOptionsPanel.add(lowerLabelOptions.get(i));
        }
        mainFrame.add(scoreOptionsPanel);
    }

    public void setScoresPanel() {
        //sets initial frame and panel

        JPanel scoresPanel = new JPanel();
        JLabel label = new JLabel("Scorecard");
        label.setFont(new Font("Arial", Font.PLAIN,  40));
        scoresPanel.add(label);
        scoresPanel.setSize(2000, 2000);
        scoresPanel.setLocation(1300, 80);
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));

        ArrayList<JLabel> upperScoreViews = scorecardView.getUpperScoreViews();
        ArrayList<JLabel> lowerScoreViews = scorecardView.getLowerScoreViews();

        //printing upper section
        JLabel label1 = new JLabel("Upper Section");
        label1.setFont(new Font("Arial", Font.PLAIN,  20));
        JLabel label2 = new JLabel("---------------------");
        label2.setFont(new Font("Arial", Font.PLAIN,  20));
        JLabel label3 = new JLabel("---------------------");
        label3.setFont(new Font("Arial", Font.PLAIN,  20));
        scoresPanel.add(label1);
        scoresPanel.add(label2);
        scoresPanel.add(label3);
        for (int i = 0; i < upperScoreViews.size(); i++) {
            scoresPanel.add(upperScoreViews.get(i));
        }

        JLabel label4 = new JLabel("---------------------");
        label4.setFont(new Font("Arial", Font.PLAIN,  20));
        scoresPanel.add(label4);

        JLabel label5 = new JLabel("---------------------");
        label5.setFont(new Font("Arial", Font.PLAIN,  20));
        scoresPanel.add(label5);

        //printing lower section

        JLabel label6 = new JLabel("Lower Section");
        label6.setFont(new Font("Arial", Font.PLAIN,  20));
        scoresPanel.add(label6);

        JLabel label7 = new JLabel("---------------------");
        label7.setFont(new Font("Arial", Font.PLAIN,  20));
        scoresPanel.add(label7);

        for (int i = 0; i < lowerScoreViews.size(); i++) {
            scoresPanel.add(lowerScoreViews.get(i));
        }

        mainFrame.add(scoresPanel);

    }

    /**
     Resets the current hand to its original non-selected state
     */
    public void resetHand() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isSelected()) {
                Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
                buttons.get(i).setSelected(false);
                buttons.get(i).setEnabled(true);
                buttons.get(i).setBorder(blackLine);
                buttons.get(i).getDie().unlock();
            }
        }
    }

    /**
     Resets the Roll Dice Button to it's original state
     */
    public void resetRollDiceB() {
        rollDiceB.setEnabled(true);
        rollDiceB.doClick();
        clicked = 0;
        rollDiceB.setEnabled(true);
        rollDiceB.setLabel("Roll Dice");
    }

    /**
     Creates the label for the endGame
     */
    public JLabel viewEndGame(int total) {
        JLabel label = new JLabel("Your Final Score is: " + total);
        label.setFont(new Font("Arial", Font.PLAIN,  20));
        return label;
    }

    /**
     Detects when show scorecard button is clicked and shows the scorecard
     *
     * @param e: action event
     */
    public void scorecardClicked(ActionEvent e) {
        scorecardView.printGUIScoreCard();
    }

    /**
     Detects when the Roll Dice Button is clicked and performs various operations
     *
     * @param e: action event
     */
    public void rollDiceClicked(ActionEvent e) {
        try {

            //automatically adds one to clicked, rolls new hand, and changes the
            //hand view
            newHand.rollNewHand();
            handView.changeHandView(newHand);
            clicked+=1;

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        //checks if no max rolls is met and disables roll button
        if (clicked == Rules.getRolls()-1) {
            ((JButton)e.getSource()).setEnabled( false );
            ((JButton)e.getSource()).setLabel("no more rolls");
        }

        //checks if all scoring options have been selected and performs endgame
        if (scorecardView.checkEndgame() == true) {
            int total = scorecard.endGame();
            JLabel finalScore = viewEndGame(total);
            JPanel finalPanel = new JPanel();
            finalPanel.setLocation(10,0);
            finalPanel.setSize(2000,2000);
            finalPanel.add(finalScore);
            mainFrame.add(finalPanel);
            showScorecard.doClick();

        }

        //rolls GUI dice
        rollGUIDice();

    }

}
