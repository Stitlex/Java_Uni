package org.example;

public class StateMachine {

    private static final char T = 'T';
    private static final char E = 'E';
    private static final char S = 'S';

    public State runStateMachine(String input) {
        State state = State.S;
        for (int i = 0; i < input.length(); i++) {
            state = nextState(state, input.charAt(i));
            if (state == State.F) {
                break;
            }
        }
        return state;
    }

    State nextState(State state, char c) {
        return switch (state) {
            case S -> c == T ? State.ONE : State.S;
            case ONE -> c == E ? State.TWO : c == T ? State.ONE : State.S;
            case TWO -> c == S ? State.THREE : c == T ? State.ONE : State.S;
            case THREE -> c == T ? State.F : State.S;
            case F -> State.F;
        };
    }
}