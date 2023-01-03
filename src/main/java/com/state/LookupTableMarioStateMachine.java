package com.state;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-03 23:56
 **/
public class LookupTableMarioStateMachine {
    private MarioState currentState;
    private int score;
    /**
     * MarioState [row][col] = v -> row state with col event become v state
     */
    private MarioState[][] stateTables = new MarioState[][]{
            {MarioState.SUPER, MarioState.CAPE, MarioState.FIRE, MarioState.SMALL},
            {MarioState.SUPER, MarioState.CAPE, MarioState.FIRE, MarioState.SMALL},
            {MarioState.CAPE, MarioState.CAPE, MarioState.CAPE, MarioState.SMALL},
            {MarioState.FIRE, MarioState.FIRE, MarioState.FIRE, MarioState.SMALL}
    };

    /**
     * scoreTables [row][col] = v row state with col event get v score
     */
    private int[][] scoreTables = {
            {+100, +200, +300, +0},
            {+0, +200, +300, -100},
            {+0, +0, +0, -200},
            {+0, +0, +0, -300}
    };

    public LookupTableMarioStateMachine() {
        this.currentState = MarioState.SMALL;
        this.score = 0;
    }

    public void obtainMushRoom() {
        executeEvent(MarioEvent.GOT_MUSHROOM);
    }

    public void obtainCape() {
        executeEvent(MarioEvent.GOT_CAPE);
    }

    public void obtainFireFlower() {
        executeEvent(MarioEvent.GOT_FIRE);
    }

    public void meetMonster() {
        executeEvent(MarioEvent.MET_MONSTER);
    }

    private void executeEvent(MarioEvent marioEvent){
        int row = this.currentState.getValue();
        int col = marioEvent.getValue();

        this.currentState = stateTables[row][col];
        this.score+= scoreTables[row][col];
    }

    public int getScore() {
        return this.score;
    }

    public MarioState getCurrentState() {
        return this.currentState;
    }

}
