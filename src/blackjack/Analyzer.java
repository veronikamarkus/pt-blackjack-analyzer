package blackjack;

import java.io.*;
import java.util.*;

public class Analyzer {

    private final ArrayList<Turn> allTurns = new ArrayList<>();
    private final Map<Integer, Session> sessionMap = new HashMap<>();

    private final ArrayList<String> errors = new ArrayList<>();

    public Analyzer() {
        main();
    }

    public void main() {

        addTurnsFromFile();
        addSessions();

        for (Session session: sessionMap.values()) {
            session.findError();
        }

        addErrors();

        try {
            resultToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void addTurnsFromFile() {

        try {
            Scanner scanner = new Scanner(new File("src/blackjack/resources/game_data.txt"));

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (line.equals(" , , , , ,") || line.equalsIgnoreCase("ERROR")){
                    continue;
                }
                allTurns.add(new Turn(line));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void resultToFile() throws IOException {
        File result = new File("analyzer_results.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(result));
        for (String error: errors) {
            writer.write(error + "\r\n");
        }
        writer.close();
    }

    private void addSessions() {

        for (Turn turn: allTurns){
            int sessionId = turn.getSessionId();

            if (!sessionMap.containsKey(sessionId)) {
                sessionMap.put(sessionId, new Session(sessionId));
            }
            sessionMap.get(sessionId).addTurn(turn);
            }
    }

    public void addErrors() {
        List<Session> list = new ArrayList<Session>(sessionMap.values());
        Collections.sort(list);

        for (Session session: list) {
            errors.addAll(session.getSessionErrors());
        }
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public ArrayList<Turn> getAllTurns() {
        return allTurns;
    }

    public Map<Integer, Session> getSessionMap() {
        return sessionMap;
    }
}
