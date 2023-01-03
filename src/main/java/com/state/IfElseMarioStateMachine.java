package com.state;

/**
 * @description: 分支逻辑法 按照状态机图使用if else逻辑来完成简单的状态流转
 * @author: KunQi Yu
 * @date: 2023-01-03 23:32
 **/
public class IfElseMarioStateMachine {
    private MarioState currentState;
    private int score;


    public IfElseMarioStateMachine() {
        this.currentState = MarioState.SMALL;
        this.score = 0;
    }

    public void obtainMushRoom() {
        if (this.currentState == MarioState.SMALL){
            this.currentState = MarioState.SUPER;
            this.score += 100;
        }
    }

    public void obtainCape() {
        if (this.currentState == MarioState.SMALL || this.currentState == MarioState.SUPER){
            this.currentState = MarioState.CAPE;
            this.score+=200;
        }
    }

    public void obtainFireFlower() {
        if (this.currentState == MarioState.SMALL || this.currentState == MarioState.SUPER){
            this.currentState = MarioState.FIRE;
            this.score+=300;
        }
    }

    public void meetMonster() {
        if (this.currentState == MarioState.SMALL){
            //mario are dead when it is small and meet monster
            this.score = 0;
            this.currentState = null;
        }else if(this.currentState == MarioState.SUPER){
            this.currentState = MarioState.SMALL;
            this.score-=100;
        }else if(this.currentState == MarioState.FIRE){
            this.currentState = MarioState.SMALL;
            this.score-=300;
        }else if(this.currentState == MarioState.CAPE){
            this.currentState = MarioState.SMALL;
            this.score-=200;
        }
    }

    public int getScore() {
        return this.score;
    }

    public MarioState getCurrentState() {
        return this.currentState;
    }



}

