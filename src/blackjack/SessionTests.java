package blackjack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class SessionTests {

    @Test
    public void addsTurns() {
        Session session = new Session(999);
        String data = "102,999,987654321,P Win,2S-8D-8S,kS-2C-8C";
        String data1 = "102,999,987654321,P Lose,2S-8D-8S,kS-3C";

        assertTrue(session.getTurns().isEmpty());

        session.addTurn(new Turn(data));

        assertEquals(1, session.getTurns().size());

        session.addTurn(new Turn(data1));

        assertEquals(2, session.getTurns().size());

    }

    @Test
    public void findsErrors() {
        Session session = new Session(999);
        String data = "102,999,987654321,P Win,2S-8D-8S,kS-2C-7C";
        String data1 = "102,999,987654321,P Lose,2S-8D-8S,kS-3C-8H";

        session.addTurn(new Turn(data));
        session.addTurn(new Turn(data1));
        session.findError();

        assertEquals(1, session.getSessionErrors().size());

    }

    @Test
    public void returnsCorrectId() {
        Session session = new Session(999);

        assertEquals(session.getId(), 999);
    }

}
