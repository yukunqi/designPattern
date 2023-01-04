package com.state.designPattern;

import com.state.MarioState;

/**
 * @description: 状态模式 -> 将各个具体状态通过各自的实现类来完成SmallMario、SuperMario
 * 运用了 访问者模式 -> v各个状态类实现MarioLifeCycle来定义对数据对象 StateDesignMarioStateMachine的操作 解耦了数据和操作两者的关系
 *
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


    protected void transferState(MarioLifeCycle next){//using protected keyword in order to restrict unknown function invoke
        this.currentState = next;
    }

    protected void addScore(int score){//using protected keyword in order to restrict unknown function invoke
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
