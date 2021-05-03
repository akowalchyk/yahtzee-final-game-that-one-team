import java.util.*;

/**
 Class Scorecard: scorecard object for the YahtzeeGame class
 *
 * publicMethods: printScorecard()
 *                fillScoreCard()
 *                endGame()
 *                printLowerScoreOptions(hand)
 *                printUpperScoreOptions(hand)
 *                getLowerLabels()
 *                getUpperLabels()
 *                getLowerScores()
 *                getUpperScores()
 *
 *
 * privateMethods: upper_score_check()
 *                  maxOfAKindFound()
 *                  totalAllDice()
 *                  maxStraightFound()
 *                  fullHouseFound()
 *                  setValidScores()
 *                  setUpperLabels()
 *                  setLowerLabels()
 *                  setUpperScores()
 *                  setLowerScores()
 *                  computeBonus()
 *
 *
 * @author Adam Kowalchyk
 * @version v4.0 03/23/21
 * @see "No Borrowed Code"
 */
public class Scorecard {
    private final int MAX_DIE_NUM = Rules.getSides();
    private final int MIN_DIE_NUM = 1;
    private final int MIN_DIE_POS = 0;
    private final int MAX_DIE_POS = Rules.getHandSize()-1;
    private final int UPPER_HEIGHT = MAX_DIE_NUM + 3;
    private final int LOWER_HEIGHT = 8;
    private final int BONUS_MULTIPLIER = 3;
    private final int UPPER_TOTAL_INDEX = MAX_DIE_NUM;
    private final int LOWER_TOTAL_INDEX = LOWER_HEIGHT - 1;

    private ArrayList<Integer> upperScores = new ArrayList<Integer>();
    private ArrayList<Integer> lowerScores = new ArrayList<Integer>();
    private ArrayList<String> upperLabels = new ArrayList<String>();
    private ArrayList<String> lowerLabels = new ArrayList<String>();
    private ArrayList<String> validScores = new ArrayList<String>();
    private int upperTotal;
    private int lowerTotal;

    /**
     Constructor for Scorecard class
     *
     */
    public Scorecard() {
        setUpperLabels();
        setLowerScores();
        setUpperScores();
        setLowerLabels();
        setValidScores();
        upperTotal = 0;
        lowerTotal = 0;
    }

    public int getUpperTotal() { return upperTotal; }

    public int getLowerTotal() { return lowerTotal; }

    /**
     Sets valid scores into a list of strings
     *
     */
    private void setValidScores() {

        //loops through each die num adds it as a string value to validScores
        for(int i = MIN_DIE_NUM; i <= MAX_DIE_NUM; i++) {
            validScores.add(Integer.toString(i) + "'s");
        }
        validScores.add("Min of a kind");
        validScores.add("Max of a kind");
        validScores.add("Full House");
        validScores.add("Sm. Straight");
        validScores.add("Lg. Straight");
        validScores.add("Yahtzee");
        validScores.add("Chance");
    }

    /**
     Sets upper section labels
     *
     */
    private void setUpperLabels() {

        //loops through each die num adds it as a string value to labels
        for(int i = MIN_DIE_NUM; i <= MAX_DIE_NUM; i++) {
            upperLabels.add(Integer.toString(i) + "'s");
        }

        upperLabels.add("Total Score");
        upperLabels.add("Bonus");
        upperLabels.add("Total of Upper Section");

    }

    /**
     Getter for lowerLabels attribute
     *
     * @return lowerLabels: lower labels attribute
     */
    public ArrayList<String> getLowerLabels() {
        return lowerLabels;
    }

    /**
     Getter for upperLabels attribute
     *
     * @return upperLabels: upper labels attribute
     */
    public ArrayList<String> getUpperLabels() {
        return upperLabels;
    }

    /**
     Getter for upperScores attribute
     *
     * @return upperScores: upper scores attribute
     */
    public ArrayList<Integer> getUpperScores() {
        return upperScores;
    }

    /**
     Getter for lowerScores attribute
     *
     * @return lowerScores: lower scores attribute
     */
    public ArrayList<Integer> getLowerScores() {
        return lowerScores;
    }

    /**
     Sets lower section labels
     *
     */
    private void setLowerLabels() {
        lowerLabels.add("Min of a kind");
        lowerLabels.add("Max of a kind");
        lowerLabels.add("Full House");
        lowerLabels.add("Sm. Straight");
        lowerLabels.add("Lg. Straight");
        lowerLabels.add("Yahtzee");
        lowerLabels.add("Chance");
        lowerLabels.add("Total of Lower Half");
    }

    /**
     Sets initial upper scores to null
     *
     */
    private void setUpperScores() {
        for (int i = 1; i <= MAX_DIE_NUM; i++) {
            upperScores.add(null);
        }
        upperScores.add(0);
        upperScores.add(null);
        upperScores.add(null);

    }

    /**
     Sets initial lower scores to null
     *
     */
    private void setLowerScores() {
        for (int i = 0; i < LOWER_HEIGHT -1; i++) {
            lowerScores.add(null);
        }
        lowerScores.add(0);
    }

    /**
     Fills the current scorecard after each turn and returns a Boolean whether there are no more scoring
     options to pick
     *
     * @param upper: list of possible upper scores to have the user pick from
     * @param lower: list of possible lower scores to have the user pick from
     * @param scoreOption: score option chosen by user
     * @return boolean telling whether there are no more scoring options to pick
     */
    public Boolean fillScoreCard(ArrayList<Integer> upper, ArrayList<Integer> lower, String scoreOption) {
        Boolean noMoreOptions = false;

        //checks for upper level selection
        if (upperLabels.contains(scoreOption)) {

            //sets score in upperScores
            upperScores.set(upperLabels.indexOf(scoreOption), upper.get(upperLabels.indexOf(scoreOption)));

            //sets upper total in upperScores and saves to upperTotal variable
            upperScores.set(UPPER_TOTAL_INDEX, upperTotal += upper.get(upperLabels.indexOf(scoreOption)));
            upperTotal = upperScores.get(UPPER_TOTAL_INDEX);

        //checks for lower level selection
        } else if(lowerLabels.contains(scoreOption)) {

            //sets score in lowerScores
            lowerScores.set(lowerLabels.indexOf(scoreOption), lower.get(lowerLabels.indexOf(scoreOption)));

            //sets lower total in lowerScores and saves to lowerTotal variable
            lowerScores.set(LOWER_TOTAL_INDEX, lowerTotal += lower.get(lowerLabels.indexOf(scoreOption)));
            lowerTotal = lowerScores.get(LOWER_TOTAL_INDEX);
        }

        //checks if Bonus is made in upper section
        if (upperScores.get(UPPER_TOTAL_INDEX) >= computeBonus()) {
            upperScores.set(upperLabels.indexOf("Bonus"), 35);
        }

        //removes selected scoring option from validScores
        validScores.remove(scoreOption);

        //checks for no more valid scores to be made and if so, calls endGame to end the game
        if (validScores.isEmpty()) {
            noMoreOptions = true;
            endGame();
        }
        return noMoreOptions;
    }

    /**
     prints the current score card
     *
     */
    public void printScoreCard(){

        //printing upper section
        System.out.println("Upper Section");
        System.out.println("---------------------");
        System.out.println("Upper Bonus = " + computeBonus());
        for(int i = 0; i < upperLabels.size(); i++){
            System.out.print(upperLabels.get(i) + " | "  + upperScores.get(i));
            System.out.println();
        }

        //printing lower section
        System.out.println();
        System.out.println("Lower Section");
        System.out.println("---------------------");
        for(int i = 0; i < lowerLabels.size(); i++){
            System.out.print(lowerLabels.get(i) + " | " + lowerScores.get(i));
            System.out.println();
        }
        System.out.println();
    }

    /**
     Computes Upper bonus for set Rules
     * @return bonus: (int) bonus score needed for upper score bonus
     */
    public int computeBonus() {
        int bonus = 0;
        for(int i = MIN_DIE_NUM; i <= MAX_DIE_NUM; i++){
            bonus+=(3*i);
        }
        return bonus;
    }

    /**
     Calculates and Displays End Game for Scorecard
     *
     */
    public int endGame() {

        // gets upper total and lower total
        int total = 0;

        int upper_tot = upperScores.get(UPPER_TOTAL_INDEX);
        int lower_tot = lowerScores.get(LOWER_TOTAL_INDEX);
        int upper_bon = 0;

        // checks for bonus
        if (upperScores.get(upperLabels.indexOf("Bonus")) != null) {
            upper_tot += upperScores.get(upperLabels.indexOf("Bonus"));
        }

        // sets new upper height
        upperScores.set(UPPER_HEIGHT - 1, upper_tot);

        // prints final scorecard and total score
        System.out.println("Thank You for playing Yahtzee, here is your final Scorecard");
        System.out.println();
        printScoreCard();
        System.out.println("Your Final Score is: " + (upper_tot + lower_tot));
        total = upper_tot + lower_tot;
        return total;
    }

    /**
     Prints score options for user
     *
     * @param hand: current hand to be analyzed for scores
     * @return currLowerScores: (ArrayList<Integer>) current lower scores for the turn
     */
    public ArrayList<Integer> printLowerScoreOptions(ArrayList<Die> hand) {
        ArrayList<Integer> curr_lower_scores = new ArrayList<Integer>();
        int maxKindNum = Rules.getHandSize()-1;
        int minKindNum = Rules.getHandSize()-2;
        //prints score options for upper portion of card

        //prints score for min of a kind
        if (maxOfAKindFound(hand) >= minKindNum) {
            //System.out.print("Score " + totalAllDice(hand) + " on the ");
            //System.out.println("min of a Kind line");
            curr_lower_scores.add(totalAllDice(hand));
        }
        else {
            //System.out.println("Score 0 on the min of a Kind line");
            curr_lower_scores.add(0);
        }


        //prints score for max of a kind
        if (maxOfAKindFound(hand) >= maxKindNum)
        {
            //System.out.print("Score " + totalAllDice(hand) + " on the ");
            System.out.println("max of a Kind line");
            //curr_lower_scores.add(totalAllDice(hand));
        }
        else {
            //System.out.println("Score 0 on the max of a Kind line");
            curr_lower_scores.add(0);
        }

        //prints score for fullHouse
        if (fullHouseFound(hand)) {
            //System.out.println("Score 25 on the Full House line");
            curr_lower_scores.add(25);
        } else {
            //System.out.println("Score 0 on the Full House line");
            curr_lower_scores.add(0);
        }

        //prints score for small straight
        if (maxStraightFound(hand) >= 4) {
            //System.out.println("Score 30 on the Small Straight line");
            curr_lower_scores.add(30);
        } else {
            //System.out.println("Score 0 on the Small Straight line");
            curr_lower_scores.add(0);
        }

        //prints score for large straight
        if (maxStraightFound(hand) >= 5) {
            //System.out.println("Score 40 on the Large Straight line");
            curr_lower_scores.add(40);
        } else {
            //System.out.println("Score 0 on the Large Straight line");
            curr_lower_scores.add(0);
        }

        //prints score for Yahtzee
        if (maxOfAKindFound(hand) >= 5) {
            //System.out.println("Score 50 on the Yahtzee line");
            curr_lower_scores.add(50);
        }
        else {
            //System.out.println("Score 0 on the Yahtzee line");
            curr_lower_scores.add(0);
        }

        //prints score for Chance line
        //System.out.print("Score " + totalAllDice(hand) + " on the ");
        curr_lower_scores.add(totalAllDice(hand));
        //System.out.println("Chance line");
        //System.out.println();

        return curr_lower_scores;
    }

    /**
     Prints Scores for the Upper Scorecard and returns a list of all dieValue Scores
     *
     * @param hand: current hand to be analyzed for scores
     * @return curr_upper_scores: list of upper scores for each die value
     */
    public ArrayList<Integer> printUpperScoreOptions(ArrayList<Die> hand) {

        ArrayList<Integer> curr_upper_scores = new ArrayList<Integer>();
        int score = 0;

        //Loops through all dieValues and gets the count in the hand
        for (int dieValue = MIN_DIE_NUM; dieValue <= MAX_DIE_NUM; dieValue++) {
            int currentCount = 0;
            for (int diePosition = MIN_DIE_POS; diePosition <= MAX_DIE_POS; diePosition++) {
                if (hand.get(diePosition).getDieNum() == dieValue) {
                    currentCount++;
                }
            }

            //gets the score of the dieNUm, prints it out, and adds it to the list of scores
            score = dieValue * currentCount;
            //System.out.print("Score " + score + " on the ");
            //System.out.println(dieValue + " line");
            curr_upper_scores.add(score);
        }
        return curr_upper_scores;
    }

    /**
     Identifies whether a 'min of a kind' or 'max of a kind' is found and returns largest count of die
     *
     * @param hand: current hand to be analyzed for scores
     * @return maxCount: (int) max count of DieNum
     */
    private int maxOfAKindFound(ArrayList<Die> hand) {

        //largest count of same die
        int maxCount = 0;
        int currentCount;

        //loops though each die number
        for (int dieValue = MIN_DIE_NUM; dieValue <= MAX_DIE_NUM; dieValue++) {
            currentCount = 0;
            //loops though each position in hand and counts number of die
            for (int diePosition = MIN_DIE_POS; diePosition <= MAX_DIE_POS; diePosition++) {
                if (hand.get(diePosition).getDieNum() == dieValue) {
                    currentCount++;
                }
            }
            //checks for new maxCount
            if (currentCount > maxCount) {
                maxCount = currentCount;
            }
        }
        return maxCount;
    }

    /**
     Counts the total of all dice in hand
     *
     * @param hand: current hand to be analyzed for scores
     * @return total: (int) total of all dice
     */
    private int totalAllDice(ArrayList<Die> hand) {
        int total = 0;

        //counts total of all die
        for (int diePosition = MIN_DIE_POS; diePosition <= MAX_DIE_POS; diePosition++)
        {
            total += hand.get(diePosition).getDieNum();
        }
        return total;
    }

    /**
     Identifies if a small straight or large straight is found and returns length of straight
     *
     * @param hand: current hand to be analyzed for scores
     * @return maxLength: (int) max length of the straight
     */
    public int maxStraightFound(ArrayList<Die> hand) {

        ArrayList<Integer> newHand = new ArrayList<Integer>();
        for (int i = 0; i < hand.size(); i++){
            newHand.add(hand.get(i).getDieNum());
        }
        Collections.sort(newHand);
        //max length of straight to be found
        int maxLength = 1;
        int curLength = 1;

        //loops through each count of straight
        for(int counter = 0; counter < 4; counter++) {

            //checks if next die num is one more than current die num and adds length of straight
            if (newHand.get(counter)+ 1 == newHand.get(counter + 1)) {
                curLength++;

                //else reverts back to length of 1
            } else if (newHand.get(counter) + 1 < newHand.get(counter + 1)) {
                curLength = 1;
            }

            //checks for new max straight
            if (curLength > maxLength) {
                maxLength = curLength;
            }
        }
        System.out.println(maxLength);
        return maxLength;
    }

    /**
     Determines whether a fullHouse is found
     *
     * @param hand: current hand to be analyzed for scores
     * @return boolean telling whether fullHouse is found
     */
    private Boolean fullHouseFound(ArrayList<Die> hand) {

        //boolean for whether full house is found
        Boolean foundFH = false;

        //boolean for whether 3 of kind is found
        Boolean found3K = false;

        //boolean for whether 2 of a kind is found
        Boolean found2K = false;

        int currentCount;

        //loops through each die value
        for (int dieValue = MIN_DIE_NUM; dieValue <= MAX_DIE_NUM; dieValue++) {
            currentCount = 0;

            //loops through each die position on die
            for (int diePosition = MIN_DIE_POS; diePosition <= MAX_DIE_POS; diePosition++) {

                //checks if die value is found and adds it to the count
                if (hand.get(diePosition).getDieNum() == dieValue) {
                    currentCount++;
                }
            }

            //checks for pair of 2
            if (currentCount == 2) {
                found2K = true;
            }

            //checks for pair of 3
            if (currentCount == 3) {
                found3K = true;
            }
        }

        //determines if full house is found
        if (found2K && found3K) {
            foundFH = true;
        }
        return foundFH;
    }
}
