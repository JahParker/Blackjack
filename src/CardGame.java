// Please remove all INSTRUCTION COMMENTS before submitting your
//    project to Moodle.
//
// ALL code that you are instructed to write MUST BE COMMENTED by you.
// Commenting your code is a significant part of your grade on this assignment.

public abstract class CardGame {

    public void dealCard(Player p) {
        p.addCard2Hand(new Card(1));
    }

    public void shuffleDeck() {

    }

    public CardGame(int numCards) {

    }

    public abstract void determineOutcome (Player p, Player d);
    public class Card {
        private String rank;
        private String suit;
        private int score;

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

        public Card(int numCards) {

        }

        @Override
        public String toString() {
            return "\t" + rank + " of " + suit;
        }
    }

    public static class Player {
        String name;
        int currentScore;

        public String getName() {
            return name;
        }

        public int getCurrentScore() {
            return currentScore;
        }

        private void setName(String name) {
            this.name = name;
        }

        private void setCurrentScore(int currentScore) {
            this.currentScore = currentScore;
        }

        public String getCard(int cardIndex) {
            return "";
        }

        public void addCard2Hand(Card c) {
            setCurrentScore(22);
        }

        public void displayFormattedHand() {

        }

        public Player() {

        }

        public Player(String name) {
            setName(name);
        }
    }
}