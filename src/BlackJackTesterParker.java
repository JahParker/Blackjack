/** @author Jah're Parker
 * CSC 251; Mr. Ilkenhons
 * U3: Project 1
 * The purpose of this project is to use OOP to create a project that replicates BlackJack.
 * This version of the game will allow the player to have a choice between hitting and staying.
 * Hitting will show the player their and will give them the option to keep hitting until stated otherwise.
 * Staying will show the dealer's second card, their current score, how many times that hit
 * alongside their hand and score.
 * Additionally, the program will show the outcome and how many times the player has hit and then end the game
 */

import java.util.Scanner;

public class BlackJackTesterParker {
    public static void main(String[] args) {
        // CONSTANTS:

        final int CARDS_IN_DECK = 52;
        final int FIRST_CARD_IN_HAND = 0;
        final int SECOND_CARD_IN_HAND = 52;
        final String CHOICE_HIT = "H";
        final String CHOICE_STAY = "S";
        final int NUMBER_OF_SHUFFLES = 52;

        // FIELDS:

        boolean playerHit;
        boolean validChoice;
        String choice;
        Scanner input = new Scanner(System.in);

        System.out.println("\nWelcome to Black-Jack\n");

        BlackJackParker game1 = new BlackJackParker(CARDS_IN_DECK);
        BlackJackParker.BlackJackPlayer player = game1.getPlayer();
        BlackJackParker.BlackJackPlayer dealer = game1.getDealer();

        // Shuffles the deck of cards a set number of times
        for (int i = 0; i < NUMBER_OF_SHUFFLES; i++) {
            game1.shuffleDeck();
        }

        // A card is given to the player and dealer
        game1.dealCard(player);
        game1.dealCard(dealer);

        System.out.println("\nThe " + dealer.getName() + "'s first card is: ");
        System.out.println(dealer.getCard(FIRST_CARD_IN_HAND));
        System.out.println("\nThe " + dealer.getName() + "'s second card is face-down.");

        // A card is given to the player and dealer
        game1.dealCard(player);
        game1.dealCard(dealer);

        showCurrentHand(player);
        showCurrentScore(player);

        // Checks to see if the player busts before giving them the chance to hit
        if (player.checkBust()) {
            playerHit = false;
        } else {
            playerHit = true;
        }

        // Loops as long as the player decides to hit
        while (playerHit) {
            // Loops until either 'h' or 's' is given to the input
            do {
                System.out.print("\nWould you like to Hit(H) or Stay(S): ");
                choice = input.nextLine();
                choice = choice.toUpperCase();

                // Validates the response from the player
                if (!choice.equals(CHOICE_HIT) && !choice.equals(CHOICE_STAY)) {
                    validChoice = false;
                } else {
                    validChoice = true;
                }
            } while (!validChoice);

            // Checks to see if the input is a hit
            // If true, give a new card to the player and show their new hand/score
            if (choice.equals(CHOICE_HIT)) {
                game1.dealCardWithHit(player);
                showCurrentHand(player);
                showCurrentScore(player);

                // If the player score exceeds 21, stop loop after done with this iteration
                if (player.checkBust()) {
                    playerHit = false;
                } else {
                    playerHit = true;
                }

            } else {
                playerHit = false;
            }
        }

        // Checks is player is bust or not
        if (!player.isBust()) {
            System.out.println("\nThe " + dealer.getName() + "'s second card is: ");
            System.out.println(dealer.getCard(SECOND_CARD_IN_HAND));
            showCurrentScore(dealer);

            // The dealer will keep hitting until the condition is met
            while (dealer.getCurrentScore() <= game1.DEALER_HIT_STAY_THRESHOLD) {
                game1.dealCardWithHit(dealer);
            }
        }

        // Shows information about the dealer by stating the number of hits, its hand, and score
        // It will then call determineOutcome in order to assign the winner
        dealer.checkBust();
        System.out.print("\nAfter 'Hitting' " + dealer.getNumberHits() + " time(s),");
        showCurrentHand(dealer);
        showCurrentScore(dealer);
        game1.determineOutcome(player, dealer);

        // Win, lose, and tie messages
        if (game1.getWinner() == BlackJackParker.Winner.DEALER)
            System.out.println("\nUnfortunately " + player.getName() + ", the " + dealer.getName() + " won this hand.");
        else if (game1.getWinner() == BlackJackParker.Winner.PLAYER)
            System.out.println("\n" + player.getName() + ", you have won this hand!");
        else
            System.out.println("\n" + player.getName() + ", you and the " + dealer.getName() + " have tied on this hand.");

        System.out.println("\n" + player.getName() + ", you 'Hit' " + player.getNumberHits() + " time(s).");
        System.out.println("\nThank you for playing!\n");
    }

    /**
     * Shows the hand of the selected player
     * @param p Player object
     */
    static void showCurrentHand(BlackJackParker.BlackJackPlayer p) {
        System.out.println("\n" + p.getName() + "'s current hand is:");
        p.displayFormattedHand();
    }

    /**
     * Shows the score of the selected player
     * @param p Player object
     */
    static void showCurrentScore(BlackJackParker.BlackJackPlayer p) {
        System.out.println("\n" + p.getName() + "'s current score is:");
        System.out.println(p.getCurrentScore());
    }
}