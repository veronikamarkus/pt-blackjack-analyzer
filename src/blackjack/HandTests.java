package blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class HandTests {

    @Test
    public void addsCardsAndValueIsCorrect(){
        Hand hand = new Hand();
        hand.add(new Card("10D"));
        hand.add(new Card("JD"));
        hand.add(new Card("3s"));
        hand.add(new Card("AH"));
        assertEquals(hand.getHandValue(), 34);

        Hand hand1 =  new Hand();
        hand1.add(new Card("?"));
        hand1.add(new Card("aD"));
        assertEquals(hand1.getHandValue(), 11);
    }

    @Test
    public void comparesHandsByTotalValueCorrectly() {
        Hand hand = new Hand();
        hand.add(new Card("10D"));
        hand.add(new Card("JD"));
        hand.add(new Card("3s"));
        hand.add(new Card("AH"));

        Hand hand1 =  new Hand();
        hand1.add(new Card("?"));
        hand1.add(new Card("aD"));

        Hand hand2 = new Hand();
        hand2.add(new Card("9d"));
        hand2.add(new Card("2S"));

        assertEquals(hand.compareTo(hand1), 1);
        assertEquals(hand1.compareTo(hand), -1);
        assertEquals(hand1.compareTo(hand2), 0);
    }

    @Test
    public void dealerCannotHitWhen17andMore() {
        Hand hand = new Hand();
        hand.add(new Card("1D"));
        hand.add(new Card("2s"));
        hand.add(new Card("3s"));
        assertTrue(hand.dealerCanHit());


        Hand hand1 = new Hand();
        hand1.add(new Card("9D"));
        hand1.add(new Card("AS"));

        assertFalse(hand1.dealerCanHit());
    }

    @Test
    public void isBustWhenOver21() {
        Hand hand = new Hand();
        hand.add(new Card("9D"));
        hand.add(new Card("As"));
        hand.add(new Card("2s"));

        assertTrue(hand.isBust());

        Hand hand1 =  new Hand();
        hand1.add(new Card("5H"));
        hand1.add(new Card("JD"));

        assertFalse(hand1.isBust());
    }

}
