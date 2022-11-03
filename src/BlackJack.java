//
// Look for comments that are prefaced by the following character sequence: //***
// These comments will be your INSTRUCTIONS concerning the code that you need to write.
//
// NOTE ABOUT INSTRUCTIONS: variable names and method names referenced in the instructions
//    are placed in quotes for emphasis only. When using variable names and methods in your
//    code, you will not place them in quotes.
//
// Please remove all INSTRUCTION COMMENTS before submitting your
//    project to Moodle.
//
// ALL code that you are instructed to write MUST BE COMMENTED by you.
// Commenting your code is a significant part of your grade on this assignment.
//

import java.util.*;

// Representation of a game of blackjack
public class BlackJack extends CardGame {

    final String DEALER_NAME = "Dealer";
    final int DEALER_HIT_STAY_THRESHOLD = 16;

    private final BlackJackPlayer player = new BlackJackPlayer();
    private final BlackJackPlayer dealer = new BlackJackPlayer(DEALER_NAME);

    enum Winner {DEALER, PLAYER, TIE}
    private Winner winner;

    // GETTERS:
    public BlackJackPlayer getPlayer() {
        return player;
    }
    public BlackJackPlayer getDealer() {
        return dealer;
    }
    public BlackJack.Winner getWinner() {
        return winner;
    }

    // SETTERS:
    private void setWinner(Winner winner) {
        this.winner = winner;
    }

    // Deals a card to the player and increments the number of hits
    public void dealCardWithHit(Player p) {
        dealCard(p);

        BlackJackPlayer bjp;

        bjp = (BlackJackPlayer) p;

        int hits = bjp.getNumberHits();
        bjp.setNumberHits(++hits);
    }

    // Compares conditions of the game in order to state the winner
    public void determineOutcome(Player p, Player d) {
        BlackJackPlayer bjp = (BlackJackPlayer) p;
        BlackJackPlayer bjd = (BlackJackPlayer) d;

        // Checks if either the player or dealer is bust
        // If not, checks if whose score is higher than the other
        if (bjp.isBust()) {
            setWinner(Winner.DEALER);
        } else if (bjd.isBust()) {
            setWinner(Winner.PLAYER);
        } else if (bjd.getCurrentScore() > bjp.getCurrentScore()) {
            setWinner(Winner.DEALER);
        } else if (bjp.getCurrentScore() > bjd.getCurrentScore()) {
            setWinner(Winner.PLAYER);
        } else {
            setWinner(Winner.TIE);
        }
    }

    // Creates a BlackJack object with a specified number of cards
    public BlackJack(int numCards) {
        super(numCards);
   }

    public static class BlackJackPlayer extends Player {
        final int BUST_SCORE = 21;

        private int numberHits;
        private boolean bust;

        // GETTERS:
        public int getNumberHits() {
            return numberHits;
        }
        public boolean isBust() {
            return bust;
        }

        // SETTERS:
        private void setNumberHits(int numberHits) {
            this.numberHits = numberHits;
        }
        private void setBust(boolean bust) {
            this.bust = bust;
        }

        public boolean checkBust() {
            boolean bust = false;

            if (getCurrentScore() > BUST_SCORE) {
                bust = true;
                setBust(bust);
            }
            return bust;
        }

        // Creates and empty BlackJackPlayer object
        public BlackJackPlayer(){

        }

        // Creates a BlackJackPlayer object with a specified name
        public BlackJackPlayer(String name) {
            super(name);
        }
    }
}