import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * GUI is the main class for this game. It builds and displays the application
 * GUI and initialises all other components.
 * 
 */
public class GUI extends JFrame{
    static final long serialVersionUID = 1;
    private JFrame frame;
    private JFrame difficultyFrame;
    public JFrame gameFrame;
    public JLabel level = new JLabel("level: 0");
    public JLabel difficultyLabel = new JLabel("Difficulty: 1");
    public Game game;
    public HashMap<Integer, JButton> buttonList = new HashMap<>();
    public JButton one;
    public JButton two;
    public JButton three;
    public JButton four;
    public JButton five;
    public JButton six;
    public JButton seven;
    public JButton eight;
    public JButton nine;
    public JButton ten;
    public JButton eleven;
    public JButton twelve;
    public JButton play;
    private JButton start;
    private int oldLevel;
    private boolean running;
    public int difficulty = 0;

    /**
     * Constructor for objects of class GUI also creates a Game object that handles
     * the game logic creates another thread to handle scoring as to not halt the
     * main thread
     */
    public static void main(String[] args) {
        GUI GUI = new GUI();
    }

    public void setDifficulty(int d) {
        game.setDifficulty(d);
        difficulty = d;

        // set difficulty 3* on each stage
    }

    public GUI() {
        game = new Game();
        running = true;
      //  setDifficulty(3);//THIS IS AN ISSUE BEING HERE
        startGame();
        new Thread(new Runnable() {
            public void run() {
                while (running) {

                    level.setText("Current Level: " + game.returnlevel());
                    level.setBackground(Color.WHITE);
                    difficultyLabel.setText("Difficulty: " + difficulty);
                    difficultyLabel.setBackground(Color.WHITE);
                    if (game.win() == 0) {
                        // if level below zero, tell user they lost
                        game.setLevel(0);
                        exit();
                    }
                    if (game.win() == 1) {
                        // if level is 10, tell user they win
                        game.setLevel(0);
                        winner();

                    }
                    if (game.win() == 2) {
                        checkLevel();
                       
                        
                        /**
                         * if(oldLevel > currentLevel) //lost a round { for (JButton button :
                         * buttonList.values()) { Color back = button.getBackground();
                         * button.setEnabled(false); button.setOpaque(true);
                         * button.setBackground(Color.BLUE); try { Thread.sleep(300); //1000
                         * milliseconds is one second. } catch(InterruptedException ex) {
                         * Thread.currentThread().interrupt(); } button.setBackground(back); } setLevel
                         * = oldLevel--; } if(oldLevel < currentLevel) //win a round { for (JButton
                         * button : buttonList.values()) { Color back = button.getBackground();
                         * button.setEnabled(false); button.setOpaque(true);
                         * button.setBackground(Color.YELLOW); try { Thread.sleep(300); //1000
                         * milliseconds is one second. } catch(InterruptedException ex) {
                         * Thread.currentThread().interrupt(); } button.setBackground(back); } setLevel
                         * = oldLevel++; }
                         * 
                         * oldLevel = setLevel; }
                         * 
                         * try { Thread.sleep(300); //1000 milliseconds is one second. }
                         * catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
                         */
                    }
                }
            }
        }).start();
    }

    private void checkLevel()
    {
        int repeat = 5;
        if(game.returnlevel() < oldLevel)
        {
            for(int i = 0; i < repeat; i++)
            {
            level.getParent().setBackground(Color.RED);
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            level.getParent().setBackground(Color.WHITE);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
        else if(game.returnlevel() > oldLevel)
        {
            for(int i = 0; i < repeat; i++)
            {
            level.getParent().setBackground(Color.GREEN);
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            level.getParent().setBackground(Color.WHITE);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        
    }
    oldLevel = game.returnlevel();
}

    /**
     * Creates a pop up dialog box to let the user know that they lost Removes all
     * the frames and goes back to the default interface
     */
    private void exit() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        play.setEnabled(false);
        // disable buttons whilst flashing
        for (JButton button : buttonList.values()) {
            button.setEnabled(false);
            button.setOpaque(true);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            button.setBackground(Color.BLACK);
            repaint();
        }

        play.setEnabled(true);
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        JOptionPane.showMessageDialog(gameFrame, "You've Lost\n", "Message", JOptionPane.INFORMATION_MESSAGE);
        for (JButton button : buttonList.values()) {
            button.setEnabled(false);
            button.setOpaque(true);
            button.setBackground(Color.WHITE);
            repaint();
        }
        int option = endGameOptions("You've Lost", "LOSER");
        if (option == 0) {
            game.newGame();
        } else if (option == 1) {
            gameFrame.setVisible(false);
            chooseDifficulty(gameFrame);
            // frame.removeAll();
            // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        } else if (option == 2 || option == -1) {
            quit();
        }
    }

    /**
     * Creates a pop up dialog box to let the user know that they win Removes all
     * the frames and goes back to the default interface
     */
    private void winner() {
        play.setEnabled(false);
        // disable buttons whilst flashing
        for (JButton button : buttonList.values()) {
            button.setEnabled(false);
            button.setOpaque(true);
            button.setBackground(Color.YELLOW);
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        play.setEnabled(true);
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        for (JButton button : buttonList.values()) {
            button.setEnabled(false);
            button.setOpaque(true);
            button.setBackground(Color.WHITE);
            repaint();
        }
        int option = endGameOptions("You've Won!", "WINNER");
        if (option == 0) {
            game.newGame();
        } else if (option == 1) {
            gameFrame.setVisible(false);
            makeDifficultyFrame();
            // frame.removeAll();
            // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        } else if (option == 2 || option == -1) {
            quit();
        }
    }

    private int endGameOptions(String message, String title) {

        Object[] options = { "Restart Game", "Change Difficulty", "Quit" };
        int n = JOptionPane.showOptionDialog(gameFrame, // parent container of JOptionPane
                message, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // do not use a
                                                                                                      // custom Icon
                options, // the titles of buttons
                options[2]);// default button title

        return n;
    }

    /**
     * Creates a dialog box about the game
     */
    private void about(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Memory Game\n", "About Memory Game", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Creates a dialog box to give the user help about the game
     */
    private void help(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Repeat the challenge shown to you \n Click play to advance", "Help",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Create the main frame's menu bar.
     * 
     * @param frame The frame that the menu bar should be added to.
     */
    public void makeMenu(JFrame frame) {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the File menu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);

        JMenuItem restart = new JMenuItem("Main Menu");
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                startGame();
            }
        });
        fileMenu.add(restart);

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });
        fileMenu.add(quit);

        // create the File menu
        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                about(frame);
            }
        });
        helpMenu.add(about);

        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                help(frame);
            }
        });
        helpMenu.add(help);
    }

    /**
     * Create the Swing frame and its content for the menu screen.
     */
    private void startGame() {
        game.newGame();
        frame = new JFrame("Remember - Main Menu");
        // Creates the panel for the 3 JButtons
        JPanel p = new JPanel(new GridLayout(1, 3));
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        // Adds the JPanel to the content Pane

        makeMenu(frame);

        contentPane.add(p, BorderLayout.CENTER);
        ActionListener game = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        };
        // button 1
        if(difficulty == 0)
        {
        start = new JButton("New Game | Select a difficulty");
        }
        else{
            start = new JButton("New Game | Difficulty: "+difficulty);
        }
        start.addActionListener(game);
        p.add(start);

        ActionListener diffListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                chooseDifficulty(frame);
            }
        };
        // button 2
        JButton chooseDifficulty = new JButton("Set Difficulty");
        chooseDifficulty.addActionListener(diffListener);
        p.add(chooseDifficulty);

        ActionListener quitListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        };
        // button 3
        JButton quit = new JButton("QUIT");
        quit.addActionListener(quitListener);
        p.add(quit);
        if(difficulty == 0)
        {
        start.setEnabled(false);
        }
        frame.setLocation(500, 400);
        frame.pack();
        frame.setSize(800, 200);
        frame.setVisible(true);
    }

    private void chooseDifficulty(JFrame frame) {
        frame.setVisible(false);
        makeDifficultyFrame();
        frame.dispose();
    }

    /**
     * Starts a clean game
     */
    private void reset() {
        frame.removeAll();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        makeFrame();
    }

    private void restart()
    {
        frame.removeAll();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        startGame();   
    }

    /**
     * Removes all frames and closes down the game
     */
    public void quit() {
        if(frame != null)
        {
        frame.dispose();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        if(gameFrame != null)
        {
        gameFrame.dispose();
        gameFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        if(difficultyFrame != null)
        {
        difficultyFrame.dispose();
        difficultyFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        return;
    }

    /**
     * Creates a swing frame and its content for the game
     */
    private void makeFrame() {
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
        one = new JButton();
       // one.setContentAreaFilled(false);
        // one.setOpaque(true);
        // one.setColor(new Color(200,0,100));
        one.setBackground(Color.WHITE);
        one.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // adds number referencing this button to an arrayList
                playerClick(1);
                game.playerchallenge(1);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        });
        p.add(one);

        // button 2
        two = new JButton();
        //two.setContentAreaFilled(false);
        two.setBackground(Color.WHITE);
        two.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // adds number referencing this button to an arrayList
                playerClick(2);
                game.playerchallenge(2);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        });
        p.add(two);

        // button 3
        three = new JButton();
        //three.setContentAreaFilled(false);
        three.setBackground(Color.WHITE);
        three.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // adds number referencing this button to an arrayList
                playerClick(3);
                game.playerchallenge(3);
                // disables buttons if user has finished entering the challenge
                buttonDisable();
            }
        });
        p.add(three);
        if (difficulty == 2 || difficulty == 3 || difficulty == 4) {
            // button 4
            four = new JButton();
          //  four.setContentAreaFilled(false);
            four.setBackground(Color.WHITE);
            four.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // adds number referencing this button to an arrayList
                    game.playerchallenge(4);
                    playerClick(4);
                    // disables buttons if user has finished entering the challenge
                    buttonDisable();
                }
            });
            p.add(four);
            // button 5
            five = new JButton();
            //five.setContentAreaFilled(false);
            five.setBackground(Color.WHITE);
            five.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // adds number referencing this button to an arrayList
                    game.playerchallenge(5);
                    playerClick(5);
                    // disables buttons if user has finished entering the challenge
                    buttonDisable();
                }
            });
            p.add(five);
            // button 6
            six = new JButton();
            //six.setContentAreaFilled(false);
            six.setBackground(Color.WHITE);
            six.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // adds number referencing this button to an arrayList
                    game.playerchallenge(6);
                    playerClick(6);
                    // disables buttons if user has finished entering the challenge
                    buttonDisable();
                }
            });
            p.add(six);

        }
        if (difficulty == 3 || difficulty == 4) {
            // button 7
            seven = new JButton();
           // seven.setContentAreaFilled(false);
            seven.setBackground(Color.WHITE);
            seven.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // adds number referencing this button to an arrayList
                    game.playerchallenge(7);
                    playerClick(7);
                    // disables buttons if user has finished entering the challenge
                    buttonDisable();
                }
            });
            p.add(seven);
            // button 8
            eight = new JButton();
           // eight.setContentAreaFilled(false);
            eight.setBackground(Color.WHITE);
            eight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // adds number referencing this button to an arrayList
                    game.playerchallenge(8);
                    playerClick(8);
                    // disables buttons if user has finished entering the challenge
                    buttonDisable();
                }
            });
            p.add(eight);
            // button 9
            nine = new JButton();
           // nine.setContentAreaFilled(false);
            nine.setBackground(Color.WHITE);
            nine.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // adds number referencing this button to an arrayList
                    game.playerchallenge(9);
                    playerClick(9);
                    // disables buttons if user has finished entering the challenge
                    buttonDisable();
                }
            });
            p.add(nine);
        }
        if (difficulty == 4) {
            // button 10
            ten = new JButton();
         //   ten.setContentAreaFilled(false);
            ten.setBackground(Color.WHITE);
            ten.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // adds number referencing this button to an arrayList
                    game.playerchallenge(10);
                    playerClick(10);
                    // disables buttons if user has finished entering the challenge
                    buttonDisable();
                }
            });
            p.add(ten);
            // button 11
            eleven = new JButton();
         //   eleven.setContentAreaFilled(false);
            eleven.setBackground(Color.WHITE);
            eleven.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // adds number referencing this button to an arrayList
                    game.playerchallenge(11);
                    playerClick(11);
                    // disables buttons if user has finished entering the challenge
                    buttonDisable();
                }
            });
            p.add(eleven);
            // button 7
            twelve = new JButton();
         //   twelve.setContentAreaFilled(false);
            twelve.setBackground(Color.WHITE);
            twelve.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // adds number referencing this button to an arrayList
                    game.playerchallenge(12);
                    playerClick(12);
                    // disables buttons if user has finished entering the challenge
                    buttonDisable();
                }
            });
            p.add(twelve);
        }

        // adds buttons with a refence to a HashMap
        buttonList();

        // disable all the buttons in the buttonList
        for (JButton button : buttonList.values()) {
            button.setBackground(Color.WHITE);
            button.setEnabled(false);
        }

        // add the JPanel pane to the contentPane
        contentPane.add(pane, BorderLayout.SOUTH);

        // JButton called play
        play = new JButton("Play");

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // initialse the random challenge in the game class
                game.challenge();
                // make the buttons flash
                blink();
            }
        });
        pane.add(play);

        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });
        pane.add(quit);

        gameFrame.setBackground(Color.BLUE);
        gameFrame.pack();
        gameFrame.setSize(350, (150 * difficulty));
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        //gameFrame = new GameFrame();
    }

    private void makeDifficultyFrame() {
        // creates the frame for the grid

        // Creates the panel used for 4 JButtons that will flash
        JPanel p = new JPanel(new GridLayout(1, 4));
        // Adds the JPanel into the content pane

        difficultyFrame = new JFrame("Choose Difficulty");

        Container contentPane = difficultyFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        // Adds the JPanel to the content Pane

        makeMenu(difficultyFrame);

        contentPane.add(p, BorderLayout.CENTER);
        ActionListener oneListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDifficulty(1);
                difficultyFrame.dispose();
                startGame();
            }
        };
        // button 1
        JButton one = new JButton("1");
        one.addActionListener(oneListener);
        p.add(one);

        ActionListener twoListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDifficulty(2);
                difficultyFrame.dispose();
                startGame();
            }
        };
        // button 1
        JButton two = new JButton("2");
        two.addActionListener(twoListener);
        p.add(two);

        ActionListener threeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDifficulty(3);
                difficultyFrame.dispose();
                startGame();
            }
        };
        // button 1
        JButton three = new JButton("3");
        three.addActionListener(threeListener);
        p.add(three);

        ActionListener fourListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDifficulty(4);
                difficultyFrame.dispose();
                startGame();
            }
        };
        // button 1
        JButton four = new JButton("4");
        four.addActionListener(fourListener);
        p.add(four);

        difficultyFrame.setLocation(frame.getX(), frame.getY());
        difficultyFrame.pack();
        difficultyFrame.setSize(800, 200);
        difficultyFrame.setVisible(true);

    }

    /**
     * disables the buttons if the user has entered the correct amount of inputs
     */
    public void buttonDisable() {
        if (game.finished()) {
            for (JButton button : buttonList.values()) {
                button.setEnabled(false);
            }
        }
            else{
                for(JButton button : buttonList.values())
                {
                    button.setEnabled(true);
                }
            }
        }

    /**
     * Adds the buttons into a hashMap with a number reference
     */
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

    /**
     * Make the buttons change colour creates another thread to handle flashing as
     * to not halt the main thread
     */
    public void blink() {
        play.setEnabled(false);
        // disable buttons whilst flashing
        for (JButton button : buttonList.values()) {
            button.setEnabled(false);
        }
        new Thread(new Runnable() {
            public void run() {
                // iterate through the original arrayList
                for (int Integer : game.returnOriginalchallenge()) {
                    // retrieve the JButton in accordance with the number in the HashMap
                    JButton currentButton = buttonList.get(Integer);
                    // assign the colour of the current button to a runningiable called back
                   // currentButton.setOpaque(true);
                    Color newColor = Color.RED.brighter().brighter();
                   currentButton.setBackground(newColor);
                    // currentButton.setBorder(new LineBorder(Color.BLACK, 10));
                    //currentButton.setContentAreaFilled(false);
                    // refresh
                    repaint();
                    // sleep for 1/2 sec
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    // set colour to original colour
                    // currentButton.setBorder(new LineBorder(Color.BLACK, 1));
                    currentButton.setBackground(Color.WHITE);
                  // currentButton.setContentAreaFilled(true);
                    // update
                    repaint();
                    // sleep for 0.3 ms to give appearance of blinking if same button comes on twice
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
                // enable buttons
                for (JButton button : buttonList.values()) {
                    button.setEnabled(true);
                }
                play.setEnabled(true);
            }

        }).start();
    }

    public void playerClick(Integer i) {
        // for (JButton button : buttonList.values()) {
        // button.setEnabled(false);
        // }
        JButton currentButton = buttonList.get(i);
        currentButton.setEnabled(false);
        new Thread(new Runnable() {
            public void run() {
                // iterate through the original arrayList
                // retrieve the JButton in accordance with the number in the HashMap
                // assign the colour of the current button to a runningiable called back
                // currentButton.setContentAreaFilled(true);
                currentButton.setOpaque(true);
                Color newColor = Color.RED.brighter().brighter();
                currentButton.setBackground(newColor);
                // currentButton.setBorder(new LineBorder(Color.BLACK, 10));
                // refresh
                repaint();
                // sleep for 1/2 sec
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                // set colour to original colour
                // currentButton.setBorder(new LineBorder(Color.BLACK, 1));
                // currentButton.setContentAreaFilled(false);
                // currentButton.setBorder(new LineBorder(Color.BLACK, 0));
                currentButton.setBackground(Color.WHITE);
                // update
                repaint();               
            }
            
        }).start();
        currentButton.setEnabled(true); 
    }
}
