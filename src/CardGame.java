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

// Template to assist with the creation of card games
public abstract class CardGame {

    // Suits in a deck of cards
    final String SPADES = "Spades";
    final String HEARTS = "Hearts";
    final String DIAMONDS = "Diamonds";
    final String CLUBS = "Clubs";


    final String[] suits = {SPADES, HEARTS, DIAMONDS, CLUBS};

    // Number position of special cards in a deck
    final int ACE_NUM = 0;
    final int JACK_NUM = 10;
    final int QUEEN_NUM = 11;
    final int KING_NUM = 12;

    // Names of the special cards
    final String ACE_STR = "Ace";
    final String JACK_STR = "Jack";
    final String QUEEN_STR = "Queen";
    final String KING_STR = "King";

    // Values of the special cards
    final int ACE_SCORE = 11;
    final int JACK_SCORE = 10;
    final int QUEEN_SCORE = 10;
    final int KING_SCORE = 10;

    private Map<Integer, String> rankMap = new HashMap<>();
    private Map<String, Integer> scoreMap = new HashMap<>();

    private int numberOfCardsInDeck;
    private int cardsInSuit;
    private int nextCard = 0;

    // Holds the deck of cards in a card game
    private final ArrayList<Card> deck = new ArrayList<>();

    // GETTERS:
    public Map<Integer, String> getRankMap() {
        return Collections.unmodifiableMap(rankMap);
    }

    public int getNumberOfCardsInDeck() {
        return numberOfCardsInDeck;
    }

    public int getCardsInSuit() {
        return cardsInSuit;
    }

    public int getNextCard() {
        return nextCard;
    }

    public List<Card> getDeck() { return Collections.unmodifiableList(deck); }

    // SETTERS:
    private void setRankMap(Map<Integer, String> rankMap) {
        this.rankMap = rankMap;
    }
    private void setScoreMap(Map<String, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }

    private void setNumberOfCardsInDeck(int numberOfCardsInDeck) {
        this.numberOfCardsInDeck = numberOfCardsInDeck;
    }

    private void setCardsInSuit(int cardsInSuit) {
        this.cardsInSuit = cardsInSuit;
    }

    private void setNextCard(int nextCard) {
        this.nextCard = nextCard;
    }

    // Creates an empty deck and populates it with Card objects assigned to a number
    private void setDeck() {
        int cardsInDeck = getNumberOfCardsInDeck();

        deck.clear();
        for (int i = 0; i < cardsInDeck; i++) {
            deck.add(new Card(i));
        }
    }

    // Adds a card to the players hand
    public void dealCard(Player p) {
        // Finds the next card in "deck" and create a Card object
        int nextCardIndex = getNextCard();
        Card nextCard = getDeck().get(nextCardIndex);

        // Adds Card object to the player's hand and sets the next card in the deck
        p.addCard2Hand(nextCard);
        setNextCard(++nextCardIndex); //Pre-increments to update value before passing
    }

    // Randomizes the order of cards in the deck
    public void shuffleDeck() {
        int cardsInDeck = getNumberOfCardsInDeck();
        int index;
        Card temp;

        // Grabs a value at a random position within cardsInDeck and inserts it at "i"
        // The value at "index" is replaced with the value previously at "i"
        for (int i = 0; i < cardsInDeck; i++) {
            index = (int) (Math.random() * cardsInDeck); //
            temp = deck.get(i);
            deck.set(i, deck.get(index));
            deck.set(index, temp);
        }
    }

    // Creates a CardGame object with a set amount of cards
    public CardGame(int numCards) {
        setNumberOfCardsInDeck(numCards);
        setCardsInSuit(numCards / suits.length);

        Map<Integer, String> rankMap = new HashMap<>();
        rankMap.put(ACE_NUM, ACE_STR);
        rankMap.put(JACK_NUM, JACK_STR);
        rankMap.put(QUEEN_NUM, QUEEN_STR);
        rankMap.put(KING_NUM, KING_STR);

        this.setRankMap(rankMap);

        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put(ACE_STR, ACE_SCORE);
        scoreMap.put(JACK_STR, JACK_SCORE);
        scoreMap.put(QUEEN_STR, QUEEN_SCORE);
        scoreMap.put(KING_STR, KING_SCORE);

        this.setScoreMap(scoreMap);

        setDeck();
    }

    public class Card {
        private String rank;
        private String suit;
        private int score;

        // Getters:
        public String getRank() {
            return rank;
        }
        public String getSuit() {
            return suit;
        }
        public int getScore() {
            return score;
        }

        // Setters:
        private void setRank(String rank) {
            this.rank = rank;
        }
        private void setSuit(String suit) {
            this.suit = suit;
        }
        private void setScore(int score) { this.score = score; }

        // Creates a card object with a specific card number
        public Card(int cardNum) {
            // Calculates the rank of the card number
            int rankNum = cardNum % cardsInSuit;

            // Checks the rank of the given card based on rankNum
            if (rankMap.containsKey(rankNum)) {
                // Sets the string value from rankMap into rank
                setRank(rankMap.get(rankNum));
            } else {
                // Sets the string value of rankNum + 1 into rank
                setRank(String.valueOf(rankNum + 1));
            }

            // Calculates the rank number to help add the correct number of cards in a suit
            // Example: cardNum = 1 will be a spade (1/12) = 0
            //          cardNum = 8 will be a diamond (40/12) = 3
            setSuit(suits[cardNum / cardsInSuit]);


            // Checks the score based on the rank of the current card number
            if (scoreMap.containsKey(getRank())) {
                // Sets the integer value of a string in scoreMap into score
                setScore(scoreMap.get(getRank()));
            } else {
                // Sets the rank of the current card number into score
                setScore(rankNum + 1);
            }
        }

        // What is returned when printing a CardGame object
        @Override
        public String toString() {
            return "\t" + rank + " of " + suit;
        }
    }

    public static class Player {
        private String name;
        private int currentScore;
        // Represents the hand of a player by holding Card objects
        private final ArrayList<Card> hand = new ArrayList<>();

        // GETTERS:
        public String getName() {
            return name;
        }
        public int getCurrentScore() {
            return currentScore;
        }
        public List<Card> getHand() {
            return Collections.unmodifiableList(hand);
        }
        public Card getCard(int cardIndex) {
            return hand.get(cardIndex);
        }

        // SETTERS:
        private void setName(String name) {
            this.name = name;
        }
        private void setCurrentScore(int currentScore) {
            this.currentScore = currentScore;
        }

        // Adds a card to the players hand and updates the score
        public void addCard2Hand(Card c) {
            hand.add(c);
            setCurrentScore(getCurrentScore() + c.getScore());
        }

        // Prints the current hand of the player
        public void displayFormattedHand() {
            for (int i = 0; i < hand.size(); i++) {
                System.out.println(hand.get(i).toString());
            }
        }

        // Creates a player object with a name inputted my the user
        public Player() {
            Scanner input = new Scanner(System.in);

            System.out.print("Please enter your name: ");
            String playerName = input.nextLine();

            setName(playerName);
        }

        // Creates a player object with a preassigned name
        public Player(String name) {
            setName(name);
        }
    }

    // Used by subclasses to ensure there is a way to determine the winner
    public abstract void determineOutcome(Player p, Player d);
}