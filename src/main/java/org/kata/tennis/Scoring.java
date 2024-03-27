package org.kata.tennis;

import java.util.Map;

public class Scoring {
    private boolean finished;
    private static Map<Integer, String> scoreMap = Map.of(0, "0", 1, "15", 2, "30", 3, "40");
    private static final int MIN_POINT_FOR_DEUCE = 3;

    /**
     * Get score after a sequence of points
     *
     * @param score Game points sequence
     * @return String representation of the score after each won ball
     */
    public String computeSequence(String score) {
        checkScoreFormat(score);
        Player playerA = new Player("A");
        Player playerB = new Player("B");

        StringBuilder globalScore = new StringBuilder();
        for (char currentChar : score.toCharArray()) {
            if (!this.finished) {
                if (currentChar == 'A') {
                    playerA.doScore();
                    globalScore.append(computeSnapshotScore(playerA, playerB)).append('\n');
                } else {
                    playerB.doScore();
                    globalScore.append(computeSnapshotScore(playerA, playerB)).append('\n');
                }
            }

        }
        return globalScore.toString();
    }

    /**
     * Method used to get a snapshot score
     *
     * @param firstPlayer  Player A
     * @param secondPlayer Player B
     * @return String representation of the current score
     */
    private String computeSnapshotScore(Player firstPlayer, Player secondPlayer) {
        String aScore = scoreMap.get(firstPlayer.getPoints());
        String bScore = scoreMap.get(secondPlayer.getPoints());

        // Deuce
        if(isDeuce(firstPlayer, secondPlayer)){
            return Status.DEUCE.getValue();
        }
        //Regular Score => [0-40]
        if (aScore != null && bScore != null) {
            return String.format("%s : %s / %s : %s", firstPlayer,scoreMap.get(firstPlayer.getPoints()),secondPlayer, scoreMap.get(secondPlayer.getPoints()));
        }
        //A player won
        if (hasWinner(firstPlayer, secondPlayer)) {
            finished = true;
            return String.format("%s %s", getLeader(firstPlayer,secondPlayer),Status.WIN.getValue());
        }
        // A player has AD
        return String.format("%s %s",Status.AD.getValue(),getLeader(firstPlayer,secondPlayer));
    }

    private boolean hasWinner(Player a, Player b) {
        return Math.abs(a.getPoints() - b.getPoints()) > 1;
    }
    private boolean isDeuce(Player a, Player b) {
        return (a.getPoints() >= MIN_POINT_FOR_DEUCE && a.getPoints() == b.getPoints());
    }
    private Player getLeader(Player a, Player b) {
        return a.getPoints() > b.getPoints() ? a : b;
    }

    private void checkScoreFormat(String score) {
        if (score == null || score.isEmpty()) {
            throw new IllegalArgumentException("Score must have at least one point");
        }
        if (!score.matches("[AB]+")) {
            throw new IllegalArgumentException("Only A and B are permitted");
        }
    }
}
