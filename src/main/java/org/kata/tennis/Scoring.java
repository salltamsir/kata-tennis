package org.kata.tennis;

import java.util.HashMap;
import java.util.Map;

public class Scoring {

    private static Map<Integer, String> scoreMap;

    static {
        scoreMap = new HashMap<>();
        scoreMap.put(0, "0");
        scoreMap.put(1, "15");
        scoreMap.put(2, "30");
        scoreMap.put(3, "40");
    }
    public String computeSequence(String score){
        return "";
    }
    private String computeScore(Player first, Player second) {
        return "";
    }
}
