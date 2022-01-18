package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileReader {

    private Map<String, String> answers = new HashMap<>();
    private String rightAnswers;

    public void getAnswers(Path path) {
        List<String> lines = readFile(path);
        getRightAnswers(lines);
        storeCompetitorsAnswers(lines);
    }

    private List<String> readFile(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can't read file!", ioe);
        }
        return lines;
    }

    private void getRightAnswers(List<String> lines) {
        rightAnswers = lines.get(0);
        lines.remove(lines.get(0));
    }

    private void storeCompetitorsAnswers(List<String> lines) {
        for (String actual : lines) {
            String[] parts = actual.split(" ");
            if (answers.containsKey(parts[0])) {
                answers.put(parts[0], answers.get(parts[0]) + parts[1]);
            } else {
                answers.put(parts[0], parts[1]);
            }
        }
    }

    public boolean isRightAnswer(String competitor, int numberOfQuestion) {
        validateParameters(competitor, numberOfQuestion);
        for (Map.Entry<String, String> actual : answers.entrySet()) {
            if (competitor.equals(actual.getKey())) {
                return rightAnswers.substring(numberOfQuestion - 1, numberOfQuestion).
                        equals(actual.getValue().
                                substring(numberOfQuestion - 1, numberOfQuestion));
            }
        }
        throw new IllegalArgumentException("No such competitor: " + competitor + "!");
    }

    private void validateParameters(String competitor, int numberOfQuestion) {
        if (competitor == null || competitor.isBlank()) {
            throw new IllegalArgumentException("Competitor can't be empty!");
        }
        if (numberOfQuestion <= 0 || numberOfQuestion > rightAnswers.length()) {
            throw new IllegalArgumentException(numberOfQuestion + " is invalid value as number of question!");
        }
    }

    public String getWinner() {
        Map<String, Integer> pointsOfUser = createMapOfPointsPerCompetitors();
        String winner = "";
        int maxPoints = 0;
        for (Map.Entry<String, Integer> actual : pointsOfUser.entrySet()) {
            if (actual.getValue() > maxPoints) {
                maxPoints = actual.getValue();
                winner = actual.getKey();
            }
        }
        return winner;
    }

    private Map<String, Integer> createMapOfPointsPerCompetitors() {
        Map<String, Integer> pointsPerCompetitors = new TreeMap<>();
        for (Map.Entry<String, String> actual : answers.entrySet()) {
            int points = 0;
            for (int i = 1; i <= rightAnswers.length(); i++) {
                points += addPointsForRightAnswer(actual, i);
                points -= subtractPointsForWrongAnswer(actual, i);
            }
            pointsPerCompetitors.put(actual.getKey(), points);
        }
        return pointsPerCompetitors;
    }

    private int addPointsForRightAnswer(Map.Entry<String, String> actual, int i) {
        if (isRightAnswer(actual.getKey(), i)) {
            return i;
        }
        return 0;
    }

    private int subtractPointsForWrongAnswer(Map.Entry<String, String> actual, int i) {
        if (!isRightAnswer(actual.getKey(), i) && actual.getValue().charAt(i - 1) != 'X') {
            return 2;
        }
        return 0;
    }

    public Map<String, String> getAnswers() {
        return Map.copyOf(answers);
    }
}
