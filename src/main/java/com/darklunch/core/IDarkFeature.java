package com.darklunch.core;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 15:36
 **/
public interface IDarkFeature {
    boolean enabled();

    boolean dark(String darkTarget);

    boolean dark(Long darkTarget);
}
