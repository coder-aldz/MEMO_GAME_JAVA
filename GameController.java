import java.util.ArrayList;
import java.util.List;

/**
 * GameController manages the logic for handling card clicks
 * and determining matches in a card-matching game.
 */
public class GameController {
    // List to keep track of currently flipped cards
    private final List<Card> flippedCards = new ArrayList<>();
    // Flag to indicate if the last pair of cards matched
    private boolean result;

    /**
     * Handles a card click by adding the card to the flipped list
     * and checking for a match if two cards are flipped.
     *
     * @param card The card that was clicked.
     */
    public void handleCardClick(Card card) {
        flippedCards.add(card);

        // Check for a match if two cards are flipped
        if (flippedCards.size() == 2) {
            checkMatch();
        }
    }

    /**
     * Returns a copy of the currently flipped cards.
     *
     * @return A list of flipped cards.
     */
    public List<Card> getFlippedCards() {
        return new ArrayList<>(flippedCards); // Return a copy to protect encapsulation
    }

    /**
     * Clears the list of flipped cards.
     */
    public void clearFlippedCards() {
        flippedCards.clear();
    }

    /**
     * Returns the number of cards currently flipped.
     *
     * @return The count of flipped cards.
     */
    public int getFlippedCardCount() {
        return flippedCards.size();
    }

    /**
     * Private helper method to check if two flipped cards match.
     * If they match, sets the cards as matched and updates the result flag.
     * If they don't match, flips the cards back and clears the flipped list.
     */
    private void checkMatch() {

        Card card1 = flippedCards.get(0);
        Card card2 = flippedCards.get(1);

        if (card1.getCardID() == card2.getCardID()) {
            // If cards match, set them as matched and update the result
            card1.setMatched(true);
            card2.setMatched(true);
            result = true;
        } else {
            // If cards don't match, flip them back
            card1.flip();
            card2.flip();
        }

        // Clear the flipped cards after checking
        clearFlippedCards();
    }

    /**
     * Returns the result of the last match check.
     *
     * @return True if the last pair matched, otherwise false.
     */
    public boolean hasMatchResult() {
        return result;
    }

    /**
     * Resets the result flag to false.
     */
    public void resetMatchResult() {
        result = false;
    }
}
