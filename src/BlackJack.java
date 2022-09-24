// Please remove all INSTRUCTION COMMENTS before submitting your
//    project to Moodle.
//
// ALL code that you are instructed to write MUST BE COMMENTED by you.
// Commenting your code is a significant part of your grade on this assignment.

public class BlackJack extends CardGame {
    enum Winner {DEALER, PLAYER, TIE}
    private Winner winner;
    static final int DEALER_HIT_STAY_THRESHOLD = 16;
    static final String DEALER_NAME = "Dealer";
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



    public void dealCardWithHit(Player p) {
        dealCard(p);
    }

    @Override
    public void determineOutcome(Player p, Player d) {

    }

    public BlackJack(int numCards) {
        super(numCards);
    }

    public static class BlackJackPlayer extends Player {
        static final int BUST_SCORE = 21;
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

        public boolean checkBust() {
            boolean bust = false;
            return bust;
        }

        public BlackJackPlayer() {

        }

        public BlackJackPlayer(String name) {
            super(name);
        }
    }
}