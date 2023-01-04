package com.state;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 09:48
 **/
public interface MarioLifeCycle {
    void obtainMushRoom(StateDesignMarioStateMachine marioStateMachine);

    void obtainCape(StateDesignMarioStateMachine marioStateMachine);

    void obtainFireFlower(StateDesignMarioStateMachine marioStateMachine);

    void meetMonster(StateDesignMarioStateMachine marioStateMachine);

    MarioState getState();

}
