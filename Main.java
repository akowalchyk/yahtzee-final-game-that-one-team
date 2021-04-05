package edu.gonzaga;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.jar.JarEntry;

public class Main {

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        JFrame f=new JFrame("Yeti shall get you.");

        final JTextField tf=new JTextField();
        JButton b=new JButton("Click Here");

        // Basic non-modifiable image (can't change size & such)
        ImageIcon yetiIcon = new ImageIcon("./YetiIcon.png");
        f.setIconImage(yetiIcon.getImage());

        // The modifiable image
        BufferedImage yetiPicture;
        JLabel picLabel = new JLabel();
        try {
            yetiPicture = ImageIO.read(new File("./YetiIcon.png"));
            picLabel = new JLabel(new ImageIcon(yetiPicture));
            picLabel.setBounds(50, 130, 100, 150);
            picLabel.setVisible(false);
            //f.add(picLabel);                    // Add picture

        } catch(IOException e) {
            e.printStackTrace();
        }

        //JLabel yetiLabel = new JLabel(yetiIcon);      // Make yeti chase you!
        // Need to hook to mouseEvent tracking. :-)

        JLabel finalPicLabel = picLabel;
        Timer yetiTimer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.setIconImage(null);
                tf.setText("It's gone now. I guess we're safe");
                b.setText("Just... not again.");
                finalPicLabel.setVisible(false);
            }
        });

        JLabel finalPicLabel1 = picLabel;
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // NOTE: e contains information about *what* action was performed
                tf.setText("It's a 5 second yeti! RUN!");
                b.setText(("Why did you do that!?!"));
                f.setIconImage(yetiIcon.getImage());
                finalPicLabel1.setVisible(true);
                yetiTimer.start();                          // Start the Yeti timer
            }
        });

        // Add components to the frame (window)
        f.add(tf, BorderLayout.NORTH);
        f.add(b, BorderLayout.SOUTH);
        f.add(picLabel, BorderLayout.CENTER);
        f.setSize(400,400);
        //f.setLayout(null);
        f.setVisible(true);
    }
}
