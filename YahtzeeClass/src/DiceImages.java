import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 Class DiceImages: Fetches images for a die
 *
 * publicMethods: getDieImage()
 *
 * @author Adam Kowalchyk
 * @version v1.0 03/23/21
 * @see "No Borrowed Code"
 */
public class DiceImages {

    /**
     Creates an item Listener to current Die View button, toggles state of die
     from pressed to not pressed
     *
     * @param die: passed in die to find image for
     * @return image1: image found for die
     */
    public Image getDieImage(int die) throws IOException {
        String toread = "";

        //checks for 1 digit number or to digit number
        if (die <= 9) {
            toread = "D6-0" + String.valueOf(die) + ".png";
        } else {
            toread = "D6-" + String.valueOf(die) + ".png";
        }

        String fileName = "media/" + toread;

        //returns new image
        File file = new File(fileName);
        Image image1 = ImageIO.read(file);
        return image1;
    }
}
