package blackjack;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private int value;
    public boolean error;

    private boolean unknown;

    private final String cardString;

    public Card(String card) {
        cardString = card.toUpperCase();
        error = isError(card);
    }

    public boolean isError(String card) {

        if (card.length() == 1) {
            return isNotUnknown(card);
        }

        if (!isCorrectSuit(card)) {
            return true;
        }

        if (card.length() > 3) {
            return true;
        }

        if (card.length() == 3 ) {
            return isValueNotTen(card);
        }

        Character cardValue = Character.toUpperCase(card.charAt(0));

        if (Character.isAlphabetic(cardValue)) {
            return isWrongLetter(cardValue);
        }

        if (isRankCorrect(Integer.parseInt(String.valueOf(cardValue)))){
            value = Integer.parseInt(String.valueOf(cardValue));
            return false;
        }

        return true;
    }

    private boolean isNotUnknown(String card) {
        if (card.equals("?")) {
            value = 0;
            unknown = true;
            return false;
        }
        return true;
    }

    private boolean isValueNotTen(String card) {
        if (!card.startsWith("10")) {
            return true;
        } else {
            value = 10;
            return false;
        }
    }

    private boolean isWrongLetter(Character cardValue) {
        if ("KQJ".contains(String.valueOf(cardValue))) {
            value = 10;
            return false;
        }
        if ("A".contains(String.valueOf(cardValue))) {
            value = 11;
            return false;
        } else {
            return true;
        }
    }

    private boolean isCorrectSuit(String card) {
        return "SHCD".contains(String.valueOf(card.charAt(card.length() - 1)).toUpperCase());
    }

    private boolean isRankCorrect(int value) {
        return 1 < value && value < 11;
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(value, other.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card other)) {
            return false;
        }

        return Objects.equals(value, other.getValue());
    }

    @Override
    public String toString() {
        return "Card value is %d".formatted(value);
    }

    public int getValue() {
        return this.value;
    }

    public boolean isError() {
        return error;
    }

    public boolean isUnknown() {
        return unknown;
    }

    public String getCardString() {
        return cardString;
    }
}
