import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.tennis.Scoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoringTest {
    private Scoring score = new Scoring();
    @BeforeEach
    void initScore(){
        score = new Scoring();
    }

    @Test
    void game_withInvalidScoreString_shouldThrowException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->score.computeSequence("\nAAAABC"));
        assertEquals("Only A and B are permitted", exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, ()->score.computeSequence("aabbAA"));
        assertEquals("Only A and B are permitted", exception.getMessage());
    }
    @Test
    void game_withBlankScoreString_shouldThrowException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->score.computeSequence("     "));
        assertEquals("Only A and B are permitted", exception.getMessage());
    }
    @Test
    void game_withNullScore_shouldThrowException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->score.computeSequence(null));
        assertEquals("Score must have at least one point", exception.getMessage());
    }
    @Test
    void game_withEmptyScore_shouldThrowException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->score.computeSequence(""));
        assertEquals("Score must have at least one point", exception.getMessage());
    }
    @Test
    void testWhiteGamePlayerA(){
        String stringScore = "AAAA";
        String expectedScore = """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 40 / Player B : 0
                Player A wins the game
                """;
        String finalScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, finalScore);
    }

    @Test
    void testExcededWhiteGamePlayerA(){
        String stringScore = "AAAAAA";
        String expectedScore = """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 40 / Player B : 0
                Player A wins the game
                """;
        String finalScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, finalScore);
    }

    @Test
    void testWonByPlayerA(){
        String stringScore = "AABBAA";
        String expectedScore = """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Player A wins the game
                """;
        String finalScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, finalScore);
    }
    @Test
    void testWonByPlayerAAfterAdvantage(){
        String stringScore = "AABBABAA";
        String expectedScore = """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Deuce
                Advantage Player A 
                Player A wins the game
                """;
        String finalScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, finalScore);
    }

    @Test
    void testWonByPlayerAfterDeuce(){
        String stringScore = "AABBABABAA";
        String expectedScore = """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Deuce
                Advantage Player A
                Deuce
                Advantage Player A
                Player A wins the game
                """;
        String finalScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, finalScore);
    }


    @Test
    void testWhiteGamePlayerB(){
        String stringScore = "BBBB";
        String expectedScore = """
                Player A : 0 / Player B : 15
                Player A : 0 / Player B : 30
                Player A : 0 / Player B : 40
                Player B wins the game
                """;
        String finalScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, finalScore);
    }

    @Test
    void testExcededWhiteGamePlayerB(){
        String stringScore = "BBBBBBB";
        String expectedScore = """
                Player A : 0 / Player B : 15
                Player A : 0 / Player B : 30
                Player A : 0 / Player B : 40
                Player B wins the game
                """;
        String finalScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, finalScore);
    }

    @Test
    void testWonByPlayerB(){
        String stringScore = "AABBBB";
        String expectedScore = """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 30 / Player B : 40
                Player B wins the game
                """;
        String finalScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, finalScore);
    }

    @Test
    void testWonByPlayerBAfterAdvantage(){
        String stringScore = "AABBABBB";
        String expectedScore = """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Deuce
                Advantage Player B
                Player B wins the game
                """;
        String finalScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, finalScore);
    }

    @Test
    void testWonByPlayerBAfterMultipleDeuces(){
        String stringScore = "AABBABABBAABBAABABBB";
        String expectedScore = """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Deuce
                Advantage Player A
                Deuce
                Advantage Player B
                Deuce
                Advantage Player A
                Deuce
                Advantage Player B
                Deuce
                Advantage Player A
                Deuce
                Advantage Player A
                Deuce
                Advantage Player B
                Player B wins the game
                """;

        String finalScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, finalScore);
    }

    @Test
    void testDeuce(){
        String stringScore = "AAABBBABABABABBA";
        String expectedScore = """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 40 / Player B : 0
                Player A : 40 / Player B : 15
                Player A : 40 / Player B : 30
                Deuce
                Advantage Player A
                Deuce
                Advantage Player A
                Deuce
                Advantage Player A
                Deuce
                Advantage Player A
                Deuce
                Advantage Player B
                Deuce
                """;
        String deuceScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, deuceScore);
    }

    @Test
    void testAdvantage(){
        String stringScore = "BBAABAB";
        String expectedScore = """
                Player A : 0 / Player B : 15
                Player A : 0 / Player B : 30
                Player A : 15 / Player B : 30
                Player A : 30 / Player B : 30
                Player A : 30 / Player B : 40
                Deuce
                Advantage Player B
                """;
        String adScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, adScore);

    }

    @Test
    void testUnfinishedAABBA(){
        String stringScore = "AAB";
        String expectedScore = """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 30 / Player B : 15
                """;
        String unfinishedScore = score.computeSequence(stringScore);
        assertEquals(expectedScore, unfinishedScore);
    }

}
