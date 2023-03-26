package blackjack;

import java.util.HashSet;
import java.util.Set;

public class Turn implements Comparable<Turn> {
    private String turnString;
    private int timeStamp;
    private int sessionId;
    private int playerId;
    private String action;
    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();
    private boolean turnError;

    public Turn(String turn) {
        initialize(turn);
    }

    private void initialize(String turn) {
        turnString = turn;

        String[] split = turn.split(",");

        timeStamp = Integer.parseInt(split[0]);
        sessionId = Integer.parseInt(split[1]);
        playerId = Integer.parseInt(split[2]);
        action = split[3];

        createDealerHand(split[4]);
        createPlayerHand(split[5]);

        if (!turnError) {
            turnError = !canDoTheAction();
        }
    }

    private void createDealerHand(String hand) {
        createHand(hand, dealerHand);
    }

    private void createPlayerHand(String hand) {
        createHand(hand, playerHand);
    }

    private void createHand(String handString, Hand hand) {
        String[] cards = handString.split("-");

        for (String card: cards) {
            Card newCard = new Card(card);

            if (newCard.isError(card)) {
                turnError = true;
            } else {
                hand.add(new Card(card));
            }
        }
    }

    public boolean canDoTheAction(){
        if (isSameCards() || isTwoUnknownCards()) {
            return false;
        }

        switch (action) {
            case "P Joined" -> {
                return !isTwoUnknownCards();
            }
            case "D Hit" -> {
                return dealerHand.dealerCanHit();
            }
            case "P Hit" -> {
                return playerHand.getHandValue() < 20;
            }
            case "D Show", "P Stand" -> {
                return !playerHand.isBust() && playerHand.getHandValue() != 21;
            }
            case "P Win" -> {
                int comparison = playerHand.compareTo(dealerHand);
                return (!playerHand.isBust() && (comparison > 0 || comparison == 0) && dealerHand.getHandValue() >= 17);
            }
            case "P Lose" -> {
                return (!dealerHand.isBust() && !playerHand.isBust() && dealerHand.compareTo(playerHand) > 0);
            }
        }
        return true;
    }

    private boolean isTwoUnknownCards() {
        int count = 0;

        for (Card card: dealerHand.getCards()) {
            if (card.isUnknown()) {
                count++;
            }
        }
        return count == 2;
    }

    private boolean isSameCards() {
        Set<String> set = new HashSet<>();

        int count = 0;

        for (Card card: dealerHand.getCards()) {
            set.add(card.getCardString());
            count++;
        }

        for (Card card: playerHand.getCards()) {
            set.add(card.getCardString());
            count++;
        }

        return set.size() != count;
    }

    @Override
    public String toString() {
        return String.format("TimeStamp: %d, Game Session ID: %d, Player ID: %d, Action: %s, Dealer Hand " +
                "value: %d, PlayerHand value: %d.", timeStamp, sessionId, playerId, action, dealerHand.getHandValue(),
                playerHand.getHandValue());
    }

    public String getTurnString() {
        return turnString;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public int getSessionId() {
        return sessionId;
    }

    public boolean isTurnError() {
        return turnError;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getAction() {
        return action;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    @Override
    public int compareTo(Turn other) {
        return Integer.compare(timeStamp, other.getTimeStamp());
    }
}
