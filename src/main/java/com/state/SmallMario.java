package com.state;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 10:03
 **/
public class SmallMario implements MarioLifeCycle{

    private static class SingletonHolder{
        private static final SmallMario SMALL_MARIO = new SmallMario();
    }

    @Override
    public void obtainMushRoom(StateDesignMarioStateMachine marioStateMachine) {
        marioStateMachine.transferState(SuperMario.getSingletonInstance());
        marioStateMachine.addScore(100);
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
        marioStateMachine.transferState(null);
        int score = marioStateMachine.getScore();
        marioStateMachine.addScore(-score);
    }

    @Override
    public MarioState getState() {
        return MarioState.SMALL;
    }


    public static MarioLifeCycle getSingletonInstance() {
        return SingletonHolder.SMALL_MARIO;
    }
}
