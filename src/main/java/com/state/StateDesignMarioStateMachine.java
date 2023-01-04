package com.state;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 09:48
 **/
public class StateDesignMarioStateMachine {
    private MarioLifeCycle currentState;
    private int score;

    public StateDesignMarioStateMachine() {
        this.currentState = SmallMario.getSingletonInstance();
        this.score = 0;
    }

    protected void transferState(MarioLifeCycle next){
        this.currentState = next;
    }

    protected void addScore(int score){
        this.score+=score;
    }

    public int getScore() {
        return score;
    }


    public void obtainMushRoom() {
        this.currentState.obtainMushRoom(this);
    }


    public void obtainCape() {
        this.currentState.obtainCape(this);
    }


    public void obtainFireFlower() {
        this.currentState.obtainFireFlower(this);
    }


    public void meetMonster() {
        this.currentState.meetMonster(this);
    }


    public MarioState getState() {
        return currentState.getState();
    }
}
