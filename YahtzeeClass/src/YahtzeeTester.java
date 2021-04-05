import java.io.IOException;
/**
 * This program plays a round of GUI Yahtzee based on rules the user inputs
 *
 * History:
 * 02/01/21: initial project was made
 * 02/17/21: Rules class is added as well as other minor changes made to
 * other classes in order to better implement Rules class
 * 03/06/21: Scoreboard class was updated to better serve a full game of Yahtzee
 *           YahtzeeGame class updated to implement new full game of Yahtzee
 *           Minor changes made to the Rules class to better handle user input
 * 03/23/21: Yahtzee GUI was created to allow for a Graphical User interfaced Yahtzee
 *           GUI classes to include DieView, HandView, and ScorecardView are added
 *           Rules Class changed to implement a drop down box control
 *           The classed Die, HandOfDice, and Scorecard were updated to better
 *              implement GUI style of project
 *
 * CPSC 224-01, Spring 2021
 * Programming Assignment #4
 * No sources to cite.
 *
 * @author Adam Kowalchyk
 * @version v4.0 03/23/21
 * @see "No Borrowed Code"
 */

/**
 Class YahtzeeTester: tests the Yahtzee project with main()
 */
public class YahtzeeTester {

    /**
     main: creates a new Yahtzee game and executes a round
     * @param args: args for main
     * @throws IOException: exception for user input
     */
    public static void main(String[] args) throws IOException {

        //creates new Yahtzee game and plays a round
        Rules rules = new Rules();
    }
}
