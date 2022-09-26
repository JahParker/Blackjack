/**
 * An abstract class that will be used for card games
 */
public abstract class CardGameParker {

    /**
     * Gives a new card to the player
     * @param p Player object
     */
    public void dealCard(Player p) {
        p.addCard2Hand(new Card(1));
    }

    /**
     * Randomizes the order of the deck
     */
    public void shuffleDeck() {

    }

    /**
     * Creates a card game object with a specified number of cards
     * @param numCards The number of cards in the deck
     */
    public CardGameParker(int numCards) {

    }

    /**
     * Decides who is the winner of the card game
     * @param p The player that will be the user
     * @param d The player that will be the dealer/CPU
     */
    public abstract void determineOutcome (Player p, Player d);

    /**
     * A representation of a card in a deck
     */
    public class Card {
        // FIELDS:

        // The rank of a card
        private String rank;
        // The suit of a card
        private String suit;
        // The score of a card
        private int score;

        // GETTERS AND SETTERS:

        public String getRank() {
            return rank;
        }
        private void setRank(String rank) {
            this.rank = rank;
        }

        public String getSuit() {
            return suit;
        }
        private void setSuit(String suit) {
            this.suit = suit;
        }

        public int getScore() {
            return score;
        }
        private void setScore(int score) {
            this.score = score;
        }

        /**
         * Unknown for now
         *
         * @param numCards
         */
        public Card(int numCards) {

        }

        @Override
        public String toString() {
            return "\t" + rank + " of " + suit;
        }
    }

    /**
     * Represents a player in the game
     */
    public static class Player {
        // FIELDS:

        // Name of this player
        private String name;
        // Score of this player
        private int currentScore;

        // GETTERS AND SETTERS:

        public String getName() {
            return name;
        }
        private void setName(String name) {
            this.name = name;
        }

        public int getCurrentScore() {
            return currentScore;
        }
        private void setCurrentScore(int currentScore) {
            this.currentScore = currentScore;
        }

        public String getCard(int cardIndex) {
            return "";
        }

        // CONSTRUCTORS:

        /**
         * Creates a player object
         */
        public Player() {

        }

        /**
         * Create a player object with a specified name
         * @param name
         */
        public Player(String name) {
            setName(name);
        }

        // METHODS:

        /**
         * Adds a new card to the players hand by updating the score
         *
         * @param c A card object
         */
        public void addCard2Hand(Card c) {
            setCurrentScore(22);
        }

        /**
         * Outputs the hand of the player to the console
         */
        public void displayFormattedHand() {

        }
    }
}