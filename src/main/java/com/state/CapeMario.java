package com.state;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 10:22
 **/
public class CapeMario implements MarioLifeCycle{

    private static class SingletonHolder{
        private static final CapeMario CAPE_MARIO = new CapeMario();
    }

    public static CapeMario getSingletonInstance(){
        return SingletonHolder.CAPE_MARIO;
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
        marioStateMachine.addScore(-200);
    }

    @Override
    public MarioState getState() {
        return MarioState.CAPE;
    }
}
