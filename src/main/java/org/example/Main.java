package org.example;

public class Main {
    public static void main(String[] args) {
        StateMachine machine = new StateMachine();
        String[] inputs = {"abcTESTabc", "abcTES", "TTEST", "TEST", "test", ""};
        for (String input : inputs) {
            System.out.println("\"" + input + "\" -> " + machine.runStateMachine(input));
        }
    }
}