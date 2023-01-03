package com.state;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 00:04
 **/
public enum MarioEvent {
    /**
     *
     */
    GOT_MUSHROOM(0),
    GOT_CAPE(1),
    GOT_FIRE(2),
    MET_MONSTER(3);

    private int value;

    private MarioEvent(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
