import java.util.*;

/**
 * Write a description of class Game here.
 * 
 */
public class Game {
    private int level;
    private int blinks;
    // original challenge
    private ArrayList<Integer> challenge = new ArrayList<>();
    // player input challenge
    private ArrayList<Integer> playerInput = new ArrayList<>();
    private Random random;
    private int difficulty;

    /**
     * Constructor for objects of class Game
     */
    public Game() {
        newGame();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int d) {
        difficulty = d;
    }

    /**
     * clear the current challenge sequence and previous player input Generates
     * random numbers from 1-4 and puts them in random order incrementing the
     * sequence by 1 each round.
     */
    public void challenge() {
        challenge.clear();
        playerInput.clear();
        random = new Random();
        for (int i = 0; i < blinks; i++) {
            challenge.add(random.nextInt(3 * difficulty) + 1);
        }
    }

    /**
     * Checks to see if the player has won
     * 
     * @return 0 if lost, 1 if won, and 2 if no boundaries have been met
     */
    public int win() {
        if (level < 0) {
            return 0;
        } else if (level == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    public void setLevel(int i) {
        level = i;
    }

    /**
     * Returns the original challenge sequence
     * 
     * @return the ArrayList challenge sequence
     */
    public ArrayList<Integer> returnOriginalchallenge() {
        return challenge;
    }

    /**
     * Add an integer to the playerInput arrayList
     * 
     * @param an int to add to the playerInput arrayList
     */
    public void playerchallenge(int i) {
        playerInput.add(i);
    }

    /**
     * Sets the variables back to default
     */
    public void newGame() {
        blinks();
        level = 0;
    }

    /**
     * Checks to see the player has finished entering the challenge and calls
     * another method to see if they have leveld
     * 
     * @return true if finished, false if not finished
     */
    public boolean finished() {
        if (playerInput.size() == challenge.size()) {
            level();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to see the player has entered the challenge correctly
     * 
     * @return true if correct, and false if incorrect
     */
    private boolean compareList() {
        return challenge.toString().contentEquals(playerInput.toString()) ? true : false;
    }

    /**
     * If the player has gotten the challenge right, add one to the level, and call
     * the other methods to make a new challenge and to check if the blinks needs to
     * be upped. Else, take one away from the level and check if the blinks needs to
     * be lowered.
     */
    private void level() {
        if (compareList()) {
            level++;
            challenge();
            blinks();
        } else {
            level--;
            blinks();
        }
    }

    /**
     * Returns the current level
     * 
     * @return integer level
     */
    public int returnlevel() {
        return level;
    }

    /**
     * if level is a certain number, change the blinks to reflect it. If it is lower
     * than 3, set the blinks to 3 if it is 3, set blinks to 4 if level is 6, set
     * blinks to 5 if level is 9, set blinks to 6
     */
    private void blinks() {
        switch (difficulty) {
        case 1:
            if (level <= 3) {
                blinks = 3;
            }
            if (level > 3) {
                blinks = 4;
            }
            if (level > 5) {
                blinks = 5;
            }
            if (level > 7) {
                blinks = 6;
            }
            break;
        case 2:
            if (level <= 3) {
                blinks = 4;
            }
            if (level > 3) {
                blinks = 6;
            }
            if (level > 5) {
                blinks = 7;
            }
            if (level > 6) {
                blinks = 10;
            }
            break;
        case 3:
            if (level <= 3) {
                blinks = 5;
            }
            if (level > 3) {
                blinks = 7;
            }
            if (level > 5) {
                blinks = 9;
            }
            if (level > 7) {
                blinks = 11;
            }
            if (level > 8) {
                blinks = 14;
            }
            break;
        case 4:
            if (level <= 3) {
                blinks = 6;
            }
            if (level > 3) {
                blinks = 8;
            }
            if (level > 5) {
                blinks = 10;
            }
            if (level > 7) {
                blinks = 15;
            }
            if (level > 9) {
                blinks = 20;
            }
            break;
        default:
            blinks = 1;
            break;
        }
        /*
         * if(difficulty == 1) { if (level <= 3) { blinks = 3; } if(level > 3) { blinks
         * = 4; }
         * 
         * if(level > 5) { blinks = 5; }
         * 
         * if(level > 7) { blinks = 6; } }
         * 
         * if(difficulty == 2) { if (level <= 3) { blinks = 4; } if(level > 3) { blinks
         * = 6; }
         * 
         * if(level > 5) { blinks = 7; }
         * 
         * if(level > 6) { blinks = 10; } } if(difficulty == 3) { if (level <= 3) {
         * blinks = 5; } if(level > 3) { blinks = 7; }
         * 
         * if(level > 5) { blinks = 9; }
         * 
         * if(level > 7) { blinks = 11; } if(level > 8) { blinks = 14; } } if(difficulty
         * == 4) { if (level <= 3) { blinks = 6; } if(level > 3) { blinks = 8; }
         * 
         * if(level > 5) { blinks = 10; }
         * 
         * if(level > 7) { blinks = 15; } if(level > 9) { blinks = 20; } }
         */
    }
}
