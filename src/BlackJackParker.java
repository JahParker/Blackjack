/**
 * Represents the BlackJack card game
 */
public class BlackJackParker extends CardGameParker {
    final int DEALER_HIT_STAY_THRESHOLD = 16;
    static final String DEALER_NAME = "Dealer";
    enum Winner {DEALER, PLAYER, TIE}

    private Winner winner;
    private BlackJackPlayer player = new BlackJackPlayer();
    private BlackJackPlayer dealer = new BlackJackPlayer(DEALER_NAME);

    public Winner getWinner() {
        return winner;
    }
    private void setWinner(Winner winner) {
        this.winner = winner;
    }

    public BlackJackPlayer getPlayer() {
        return player;
    }

    public BlackJackPlayer getDealer() {
        return dealer;
    }

    /**
     * Deals a new card to the player when they decide to hit
     * @param p Player object
     */
    public void dealCardWithHit(Player p) {
        dealCard(p);
    }

    /**
     * Decides who has won the game
     * @param p The player that will be the user
     * @param d The player that will be the dealer/CPU
     */
    @Override
    public void determineOutcome(Player p, Player d) {

    }

    /**
     * Creates a BlackJackParker object with the specified number of cards
     * @param numCards
     */
    public BlackJackParker(int numCards) {
        super(numCards);
    }

    /**
     * Represents a black jack player
     */
    public static class BlackJackPlayer extends Player {
        // FIELDS:

        static final int BUST_SCORE = 21;

        // The number of hits that the player takes
        private int numberHits;
        private boolean bust;

        public int getNumberHits() {
            return numberHits;
        }
        private void setNumberHits(int numberHits) {
            this.numberHits = numberHits;
        }

        public boolean isBust() {
            return bust;
        }
        private void setBust(boolean bust) {
            this.bust = bust;
        }

        /**
         * Sets bust to false
         * @return bust Will be changed to false  in this method
         */
        public boolean checkBust() {
            boolean bust = false;
            return bust;
        }

        /**
         * Creates a BlackJackPlayer object
         */
        public BlackJackPlayer() {

        }

        /**
         * Creates a BlackJackPlayer object with the specified name
         * @param name Name of the player
         */
        public BlackJackPlayer(String name) {
            super(name);
        }
    }
}