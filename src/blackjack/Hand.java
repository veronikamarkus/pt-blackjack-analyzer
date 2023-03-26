package blackjack;

import java.util.ArrayList;
import java.util.Objects;

public class Hand implements Comparable<Hand> {
    private final ArrayList<Card> cards = new ArrayList<>();
    private int value;

    public void add(Card card) {
        cards.add(card);
        value += card.getValue();
    }

    public boolean dealerCanHit() {
        return value < 17;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isBust(){
        return value > 21;
    }

    public int getHandValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Hand other)) {
            return false;
        }

        return Objects.equals(value, other.getHandValue());
    }

    @Override
    public int compareTo(Hand other) {
        return Integer.compare(value, other.getHandValue());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Card values: ");

        int size = cards.size();
        for (int i = 0; i < size - 1; i++) {
            result.append(String.format("%d, ", cards.get(i).getValue()));
        }
        result.append(String.format("%d. Total value: %d.", cards.get(size - 1).getValue(), getHandValue()));

        return result.toString();
    }
}
