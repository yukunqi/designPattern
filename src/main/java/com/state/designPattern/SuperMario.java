package com.state.designPattern;

import com.state.MarioState;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 10:12
 **/
public class SuperMario implements MarioLifeCycle {

    private static class SingletonHolder{
        private static final SuperMario SUPER_MARIO = new SuperMario();
    }

    @Override
    public void obtainMushRoom(StateDesignMarioStateMachine marioStateMachine) {

    }

    @Override
    public void obtainCape(StateDesignMarioStateMachine marioStateMachine) {
        marioStateMachine.transferState(CapeMario.getSingletonInstance());
        marioStateMachine.addScore(200);
    }

    @Override
    public void obtainFireFlower(StateDesignMarioStateMachine marioStateMachine) {
        marioStateMachine.transferState(FireFlowerMario.getSingletonInstance());
        marioStateMachine.addScore(300);
    }

    @Override
    public void meetMonster(StateDesignMarioStateMachine marioStateMachine) {
        marioStateMachine.transferState(SmallMario.getSingletonInstance());
        marioStateMachine.addScore(-100);
    }

    @Override
    public MarioState getState() {
        return MarioState.SUPER;
    }


    public static MarioLifeCycle getSingletonInstance() {
        return SingletonHolder.SUPER_MARIO;
    }
}
