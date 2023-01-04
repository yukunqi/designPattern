package com.state;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-03 23:46
 **/
public class ApplicationDemo {

    private void ifElseMarioStateMachine(){
        IfElseMarioStateMachine mario = new IfElseMarioStateMachine();
        mario.obtainMushRoom();
        int score = mario.getScore();
        MarioState state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);
    }

    private void lookupTableMarioStateMachine(){
        LookupTableMarioStateMachine mario = new LookupTableMarioStateMachine();
        mario.obtainMushRoom();
        int score = mario.getScore();
        MarioState state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);
    }

    private void stateDesignMarioStateMachine(){
        StateDesignMarioStateMachine stateDesignMarioStateMachine = new StateDesignMarioStateMachine();
        stateDesignMarioStateMachine.obtainMushRoom();
        int score = stateDesignMarioStateMachine.getScore();
        MarioState state = stateDesignMarioStateMachine.getState();
        System.out.println("mario score: " + score + "; state: " + state);
    }

    public static void main(String[] args) {
        ApplicationDemo applicationDemo = new ApplicationDemo();

        applicationDemo.ifElseMarioStateMachine();
        applicationDemo.lookupTableMarioStateMachine();
        applicationDemo.stateDesignMarioStateMachine();
    }
}
