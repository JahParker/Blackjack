//
// This program is given to you. There are no comments or code for you to write.
//

import java.util.Scanner;

public class BlackJackTester {

    public static void main(String[] args) {
        System.out.println();
        final int CARDS_IN_DECK = 52;
        final int FIRST_CARD_IN_HAND = 0;
        final int SECOND_CARD_IN_HAND = 1;
        final String CHOICE_HIT = "H";
        final String CHOICE_STAY = "S";
        final int NUMBER_OF_SHUFFLES = 5;

        boolean playerHit;
        boolean validChoice;
        String choice;

        Scanner input = new Scanner(System.in);
        System.out.println("\nWelcome to Black-Jack\n");

        BlackJack game1 = new BlackJack(CARDS_IN_DECK);
        BlackJack.BlackJackPlayer player = game1.getPlayer();
        BlackJack.BlackJackPlayer dealer = game1.getDealer();

        // Shuffles the deck of cards 5 times
        for (int s = 1; s <= NUMBER_OF_SHUFFLES; s++) {
            game1.shuffleDeck();
        }

        // Deals the first card to both the player and the dealer
        game1.dealCard(player);
        game1.dealCard(dealer);

        System.out.println("\nThe " + dealer.getName() + "'s first card is: ");
        System.out.println(dealer.getCard(FIRST_CARD_IN_HAND));
        System.out.println("\nThe " + dealer.getName() + "'s second card is face-down.");

        // Deals the second card to both the player and the dealer
        game1.dealCard(player);
        game1.dealCard(dealer);

        showCurrentHand(player);
        showCurrentScore(player);

        // Skips the following loop if player's score is over 21
        if (player.checkBust()) {
            playerHit = false;
        }
        else {
            playerHit = true;
        }

        // This loop continues until the player decides to stop hitting
        while (playerHit) {

            do {
                System.out.print("\nWould you like to Hit(H) or Stay(S): ");
                choice = input.nextLine().toUpperCase();

                // Checks if the input is equal to 'H', 'h', 'S', or 's', otherwise the loop repeats
                if (!choice.equals(CHOICE_HIT) && !choice.equals(CHOICE_STAY)) {
                    validChoice = false;
                }
                else {
                    validChoice = true;
                }

            } while (!validChoice);

            // Checks if player decide to hit and determines whether they bust or not
            // If the player decides to stay or is bust, the loop ends
            if (choice.equals(CHOICE_HIT)) {
                game1.dealCardWithHit(player);

                showCurrentHand(player);
                showCurrentScore(player);

                if (player.checkBust()) {
                    playerHit = false;
                }
                else {
                    playerHit = true;
                }
            }
            else {
                playerHit = false;
            }
        }

        // Shows the dealer's second card and draws a card while it's score is less than or equal to 16
        if (!player.isBust()) {
            System.out.println("\nThe " + dealer.getName() + "'s second card is: ");
            System.out.println(dealer.getCard(SECOND_CARD_IN_HAND));

            showCurrentScore(dealer);

            while (dealer.getCurrentScore() <= game1.DEALER_HIT_STAY_THRESHOLD){
                game1.dealCardWithHit(dealer);
            }
        }

        dealer.checkBust();

        System.out.print("\nAfter 'Hitting' " + dealer.getNumberHits() + " time(s),");
        showCurrentHand(dealer);
        showCurrentScore(dealer);

        game1.determineOutcome(player, dealer);

        if (game1.getWinner() == BlackJack.Winner.DEALER)
            System.out.println("\nUnfortunately " + player.getName() + ", the " + dealer.getName() + " won this hand.");
        else if (game1.getWinner() == BlackJack.Winner.PLAYER)
            System.out.println("\n" + player.getName() + ", you have won this hand!");
        else
            System.out.println("\n" + player.getName() + ", you and the " + dealer.getName() + " have tied on this hand.");

        System.out.println("\n" + player.getName() + ", you 'Hit' " + player.getNumberHits() + " time(s).");
        System.out.println("\nThank you for playing!\n");

        input.close();
    }

    static void showCurrentHand(BlackJack.BlackJackPlayer p) {
        System.out.println("\n" + p.getName() + "'s current hand is:");
        p.displayFormattedHand();
    }

    static void showCurrentScore(BlackJack.BlackJackPlayer p) {
        System.out.print("\n" + p.getName() + "'s current score is: ");
        System.out.println(p.getCurrentScore());
    }
}