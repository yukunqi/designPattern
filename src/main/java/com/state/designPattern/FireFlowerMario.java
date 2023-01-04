package com.state.designPattern;

import com.state.MarioState;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 10:24
 **/
public class FireFlowerMario implements MarioLifeCycle {

    private static class SingletonHolder{
        private static final FireFlowerMario FIRE_FLOWER_MARIO = new FireFlowerMario();
    }

    public static FireFlowerMario getSingletonInstance(){
        return SingletonHolder.FIRE_FLOWER_MARIO;
    }

    @Override
    public void obtainMushRoom(StateDesignMarioStateMachine marioStateMachine) {

    }

    @Override
    public void obtainCape(StateDesignMarioStateMachine marioStateMachine) {

    }

    @Override
    public void obtainFireFlower(StateDesignMarioStateMachine marioStateMachine) {

    }

    @Override
    public void meetMonster(StateDesignMarioStateMachine marioStateMachine) {
        marioStateMachine.transferState(SmallMario.getSingletonInstance());
        marioStateMachine.addScore(-300);
    }

    @Override
    public MarioState getState() {
        return MarioState.FIRE;
    }
}
