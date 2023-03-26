package blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Session implements Comparable<Session>{
    private int id;
    private ArrayList<Turn> turns = new ArrayList<>();

    private ArrayList<String> errors = new ArrayList<>();

    public Session(int id) {
        this.id = id;
    }

    public void addTurn(Turn turn) {
        turns.add(turn);
    }

    public void findError() {
        Collections.sort(turns);
        for (Turn turn: turns) {

            if (turn.isTurnError()) {
                errors.add(turn.getTurnString());
                break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public ArrayList<Turn> getTurns() {
        return turns;
    }

    public ArrayList<String> getSessionErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "Session ID: " + id;
    }

    @Override
    public int compareTo(Session other) {
        return Integer.compare(id, other.getId());
    }
}
