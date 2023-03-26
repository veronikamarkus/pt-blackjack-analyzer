package blackjack;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TurnTests {

    @Test
    public void createsCorrectHand() {
        String data = "58,80,287654321,P Hit,KH-?,QD-2D";
        Turn turn = new Turn(data);

        Hand dealersHand = new Hand();
        dealersHand.add(new Card("KH"));
        dealersHand.add(new Card("?"));

        assertEquals(turn.getDealerHand(), dealersHand);
    }

    @Test
    public void detectsTwoUnknownCards() {
        String data = "58,80,287654321,P Hit,?-?,QD-2D";
        Turn turn = new Turn(data);

        assertFalse(turn.canDoTheAction());
    }

    @Test
    public void detectsWhenDealerCannotHit() {
        String data = "58,80,287654321,D Hit,AS-8H,QD-2D";
        Turn turn = new Turn(data);

        assertFalse(turn.canDoTheAction());
    }

    @Test
    public void detectsWhenPlayerCannotHit() {
        String data = "58,80,287654321,P Hit,AS-8H,AS-9D";
        Turn turn = new Turn(data);

        assertFalse(turn.canDoTheAction());

        String data1 = "58,80,287654321,P Hit,AS-8H,AS-9D-3S";
        Turn turn1 = new Turn(data1);

        assertFalse(turn1.canDoTheAction());
    }

    @Test
    public void DealerCannotShowCardWhenPlayerIsBustOr21() {
        String data = "6,1,987654321,D Show,KH-?,9D-4D-9H";
        Turn turn = new Turn(data);

        assertFalse(turn.canDoTheAction());
    }

    @Test
    public void PlayerCannotStandWhenPlayerIsBustOr21() {
        String data = "6,1,987654321,P Stand,KH-?,9D-AD-9H";
        Turn turn = new Turn(data);

        assertFalse(turn.canDoTheAction());
    }

    @Test
    public void PlayerCannotWinWhenPlayerIsBust() {
        String data = "6,1,987654321,P Win,KH-AS,9D-5D-9H";
        Turn turn = new Turn(data);

        assertFalse(turn.canDoTheAction());
    }

    @Test
    public void PlayerCannotWinWhenDealerHasMore() {
        String data = "6,1,987654321,P Win,KH-AS,5D-9H-6H";
        Turn turn = new Turn(data);

        assertFalse(turn.canDoTheAction());
    }

    @Test
    public void PlayerCannotWinWhenDealerHandLessThan17() {
        String data = "6,1,987654321,P Win,QH-6S,AD-9H";
        Turn turn = new Turn(data);

        assertFalse(turn.canDoTheAction());
    }

    @Test
    public void PlayerWinsWhenEqual() {
        String data = "6,1,987654321,P Win,QH-7S,JS-7D";
        Turn turn = new Turn(data);

        assertTrue(turn.canDoTheAction());
    }

    @Test
    public void PlayerWinsWhenPlayerHandHasMore() {
        String data = "6,1,987654321,P Win,QH-8S,JS-7D-4S";
        Turn turn = new Turn(data);

        assertTrue(turn.canDoTheAction());
    }

    @Test
    public void PlayerCannotLooseWhenDealerIsBust() {
        String data = "6,1,987654321,P Lose,QH-8S-5H,JS-2D-4S";
        Turn turn = new Turn(data);

        assertFalse(turn.canDoTheAction());
    }

    @Test
    public void PlayerCannotLoseWhenHasMore() {
        String data = "6,1,987654321,P Lose,9H-7S-2H,JS-2D-8S";
        Turn turn = new Turn(data);

        assertFalse(turn.canDoTheAction());
    }

    @Test
    public void createsCorrectTurnFromDataString() {
        String data = "102,999,987654321,D Hit,2S-8D,kS-1C-2C";

        Turn turn = new Turn(data);

        assertEquals(turn.getTimeStamp(), 102);
        assertEquals(turn.getSessionId(), 999);
        assertEquals(turn.getPlayerId(), 987654321);
        assertEquals(turn.getAction(), "D Hit");

        Hand dealersHand = new Hand();
        dealersHand.add(new Card("2S"));
        dealersHand.add(new Card("8D"));

        assertEquals(turn.getDealerHand(), dealersHand);

        Hand playersHand = new Hand();
        playersHand.add(new Card("kS"));
        playersHand.add(new Card("1C"));
        playersHand.add(new Card("2C"));

        assertEquals(turn.getPlayerHand(), playersHand);

    }

}
