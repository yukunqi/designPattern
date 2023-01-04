package com.darklunch.custom;

import com.darklunch.core.IDarkFeature;

/**
 * @description: odd-even dark feature
 * @author: KunQi Yu
 * @date: 2022-12-29 15:52
 **/
public class UserDefinedDarkFeature implements IDarkFeature {


    @Override
    public boolean enabled() {
        return true;
    }

    @Override
    public boolean dark(String darkTarget) {
        long d = Long.parseLong(darkTarget);
        return this.dark(d);
    }

    @Override
    public boolean dark(Long darkTarget) {
        return darkTarget % 2 == 0;
    }
}
