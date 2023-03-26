package blackjack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardTests {

    @Test
    public void returnsCorrectCardValue() {
        assertEquals(2, new Card("2S").getValue());
        assertEquals(10, new Card("10H").getValue());
        assertEquals(10, new Card("JD").getValue());
        assertEquals(10, new Card("qS").getValue());
        assertEquals(11, new Card("AC").getValue());
    }

    @Test
    public void detectsErrorWhenIncorrectCardValue() {
        Card card = new Card("88H");
        assertTrue(card.isError());
        Card card1 = new Card("1S");
        assertTrue(card1.isError());
        Card card2 = new Card("11H");
        assertTrue(card2.isError());

    }

    @Test
    public void detectsErrorWhenCardLengthOver3() {
        Card card = new Card("100C");
        assertTrue(card.isError());

        Card card1 = new Card("89lH");
        assertTrue(card1.isError());
    }

    @Test
    public void detectsErrorWhenLengthIs1ButNotUnknownCard() {
        Card card = new Card("1");
        assertTrue(card.isError());

        Card card1 = new Card("j");
        assertTrue(card1.isError());
    }

    @Test
    public void detectsErrorWhenIncorrectSuit() {
        Card card = new Card("kJ");
        assertTrue(card.isError());

        Card card1 = new Card("10P");
        assertTrue(card1.isError());
    }

    @Test
    public void detectsErrorWhenLengthIs3ButValueNot10() {
        Card card = new Card("11D");
        assertTrue(card.isError());

        Card card1 = new Card("08S");
        assertTrue(card1.isError());
    }

    @Test
    public void detectsErrorWhenWrongValueLetter() {
        Card card = new Card("SD");
        assertTrue(card.isError());

        Card card1 = new Card("HH");
        assertTrue(card1.isError());
    }

    @Test
    public void detectsErrorWhenRankValueIncorrect() {
        Card card = new Card("11Q");
        assertTrue(card.isError());

        Card card1 = new Card("1S");
        assertTrue(card1.isError());

        Card card2 = new Card("23H");
        assertTrue(card2.isError());
    }

}
