import java.util.*;

/**
 * Logic for the game, sequences, levels, comparing inputs and sequences.
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
     * Creates a newGame.
     */
    public Game() {
        newGame();
    }
    /**
    *Returns the current difficulty of the game
     */
    public int getDifficulty() {
        return difficulty;
    }
    /**
    sets the difficulty to the player chosen level
     */
    public void setDifficulty(int d) {
        difficulty = d;
    }

    /**
     * clears the challenge, clears the player inputs, creates a random challenge by choosing a random button
     using the difficulty to ensure new challenge is within the limits of the difficulty. 
     Repeats for each blink required until challenge is completed.
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
     * Player win checker,
     * 
     * returns 0 if they have lost, 1 if they have won.
     */
    public int win() {
        if (level < 0) {
            return 0;
        } else if (level == 10) {
            return 1;
        } else {
            return 2;
        }
    }
    /**
    Sets the current level of the game
     */
    public void setLevel(int i) {
        level = i;
    }

    /**
     * Returns the challenge sequence
     *
     */
    public ArrayList<Integer> returnOriginalchallenge() {
        return challenge;
    }

    /**
     * Adds the chosen button to the players sequence, to compare to the challenged array.
     */
    public void playerchallenge(int i) {
        playerInput.add(i);
    }

    /**
     * Sets level back to 0.
     */
    public void newGame() {
        blinks();
        level = 0;
    }

    /**
     * Checks to see the player has finished entering the challenge and calls
     * another method to see if they have leveld
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
     */
    private boolean compareList() {
        return challenge.toString().contentEquals(playerInput.toString()) ? true : false;
    }

    /**
     * Increases the level if the player was correct, decrease level if they got it wrong.
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

    /*
    *returns current level
     */
    public int returnlevel() {
        return level;
    }

    /**
     * Switch amount of blinks based on level, blinks increment amount and default blinks are based on difficulty.
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
