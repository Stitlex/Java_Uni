import org.example.State;
import org.example.StateMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateMachineTest {

    private StateMachine stateMachine;

    @BeforeEach
    public void init() {
        stateMachine = new StateMachine();
    }

    @ParameterizedTest
    @CsvSource({
            "'', S",
            "T, ONE",
            "TE, TWO",
            "TES, THREE",
            "TEST, F",
            "A, S",
            "TA, S",
            "TEA, S",
            "TESA, S",
            "TESTA, F",
            "abcTESTabc, F",
            "abcTES, THREE",
            "abcTEaSaTabc, S",
            "test, S"
    })
    public void stateMachineParametrizedTest(String input, State expected) {
        assertEquals(expected, stateMachine.runStateMachine(input));
    }


    @ParameterizedTest
    @CsvSource({
            "TTEST, F",
            "TTTEST, F",
            "TETEST, F",
            "abcTTESTabc, F",
            "TESTEST, F"
    })
    public void repeatedPrefixParametrizedTest(String input, State expected) {
        assertEquals(expected, stateMachine.runStateMachine(input));
    }
}