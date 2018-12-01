import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class GameFrame extends GUI
{
    public GameFrame() {
        // creates the frame for the grid
        gameFrame = new JFrame("Remember Remember");

        // Creates the panel used for 4 JButtons that will flash
        JPanel p = new JPanel(new GridLayout(1, 3));
        if (difficulty == 2) {
            p = new JPanel(new GridLayout(2, 3));
        } else if (difficulty == 3) {
            p = new JPanel(new GridLayout(3, 3));
        } else if (difficulty == 4) {
            p = new JPanel(new GridLayout(4, 3));
        }
        // Creates the panel used for 2 JButtons for options
        JPanel pane = new JPanel(new GridLayout(1, 2));
        Container contentPane = gameFrame.getContentPane();
        // Creates a container
        contentPane.setLayout(new BorderLayout());
        JPanel container = new JPanel(new GridLayout(1, 1));
        container.add(level);
        container.add(difficultyLabel);
        // adds the level JLabel to the content pane
        contentPane.add(container, BorderLayout.NORTH);
        // contentPane.add(level, BorderLayout.NORTH);
        // contentPane.add(difficultyLabel, BorderLayout.SOUTH);

        // Adds the JPanel into the content pane
        contentPane.add(p, BorderLayout.CENTER);

        makeMenu(gameFrame);
        
        // button 1
       // one.setContentAreaFilled(false);
        // one.setOpaque(true);
        // one.setColor(new Color(200,0,100));
        ActionListener oneListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(1);
                game.playerchallenge(1);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        one = new JButton();
        one.addActionListener(oneListener);
        one.setBackground(Color.WHITE);
        p.add(one);
        
        ActionListener twoListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(2);
                game.playerchallenge(2);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        two = new JButton();
        two.addActionListener(twoListener);
        two.setBackground(Color.WHITE);
        p.add(two);

        ActionListener threeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(3);
                game.playerchallenge(3);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        three = new JButton();
        three.addActionListener(oneListener);
        three.setBackground(Color.WHITE);
        p.add(three);

        ActionListener fourListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(4);
                game.playerchallenge(4);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        four = new JButton();
        four.addActionListener(fourListener);
        four.setBackground(Color.WHITE);
        p.add(four);

        ActionListener fiveListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(5);
                game.playerchallenge(5);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        five = new JButton();
        five.addActionListener(oneListener);
        five.setBackground(Color.WHITE);
        p.add(five);

        ActionListener sixListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(6);
                game.playerchallenge(6);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        six = new JButton();
        six.addActionListener(sixListener);
        six.setBackground(Color.WHITE);
        p.add(six);

        ActionListener sevenListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(7);
                game.playerchallenge(7);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        seven = new JButton();
        seven.addActionListener(sevenListener);
        seven.setBackground(Color.WHITE);
        p.add(seven);

        ActionListener eightListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(8);
                game.playerchallenge(8);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        eight = new JButton();
        eight.addActionListener(eightListener);
        eight.setBackground(Color.WHITE);
        p.add(eight);

        ActionListener nineListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(9);
                game.playerchallenge(9);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        nine = new JButton();
        nine.addActionListener(nineListener);
        nine.setBackground(Color.WHITE);
        p.add(nine);

        ActionListener tenListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(10);
                game.playerchallenge(10);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        ten = new JButton();
        ten.addActionListener(tenListener);
        ten.setBackground(Color.WHITE);
        p.add(ten);

        ActionListener elevenListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(11);
                game.playerchallenge(11);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        eleven = new JButton();
        eleven.addActionListener(elevenListener);
        eleven.setBackground(Color.WHITE);
        p.add(eleven);

        ActionListener twelveListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerClick(12);
                game.playerchallenge(12);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        };
        twelve = new JButton();
        twelve.addActionListener(twelveListener);
        twelve.setBackground(Color.WHITE);
        p.add(twelve);

        // adds buttons with a refence to a HashMap
        buttonList();

        // disable all the buttons in the buttonList
        for (JButton button : buttonList.values()) {
            button.setBackground(Color.WHITE);
            button.setEnabled(false);
        }

        // add the JPanel pane to the contentPane
        contentPane.add(pane, BorderLayout.SOUTH);


        ActionListener playListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.challenge();
                // make the buttons flash
                blink();
            }
        };
        play = new JButton("Play");
        play.addActionListener(playListener);
        pane.add(play);

        ActionListener quitListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        };
        JButton quit = new JButton("quit");
        quit.addActionListener(quitListener);
        pane.add(quit);


        gameFrame.setBackground(Color.BLUE);
        gameFrame.pack();
        gameFrame.setSize(350, (150 * difficulty));
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void buttonList() {
        buttonList.put(1, one);
        buttonList.put(2, two);
        buttonList.put(3, three);
        if (difficulty == 2 || difficulty == 3 || difficulty == 4) {
            buttonList.put(4, four);
            buttonList.put(5, five);
            buttonList.put(6, six);
        }
        if (difficulty == 3 || difficulty == 4) {
            buttonList.put(7, seven);
            buttonList.put(8, eight);
            buttonList.put(9, nine);
        }
        if (difficulty == 4) {
            buttonList.put(10, ten);
            buttonList.put(11, eleven);
            buttonList.put(12, twelve);
        }
    }
}
