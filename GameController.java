package memoapp.memo_final;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private List<Card> flippedCards;
    private boolean result;

    public GameController() {
        this.flippedCards = new ArrayList<>();
        this.result = false;
    }

    public void handleCardClick(Card card) {

        flippedCards.add(card);

        // Check for a match if two cards are flipped
        if (flippedCards.size() == 2) {
            checkMatch();
        }
    }

    public List<Card> getCardsFlipped(){
        return flippedCards;
    }

    public int getFlippedCards(){
        return flippedCards.size();
    }

    public void clearFlippedCards(){flippedCards.clear();}

    private void checkMatch() {
        Card card1 = flippedCards.get(0);
        Card card2 = flippedCards.get(1);

        if (card1.getCardID() == card2.getCardID()) {
            card1.setMatched(true);
            card2.setMatched(true);
            result = true;

        } else {
            card1.flip(); // Flip back if not matched
            card2.flip();
        }
    }

    public boolean getResult(){
        return result;
    }

    public void resetResult(){
        result=false;
    }
}