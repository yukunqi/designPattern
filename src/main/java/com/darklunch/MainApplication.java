package com.darklunch;

import com.darklunch.core.DarkFeature;
import com.darklunch.core.DarkLunch;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 14:45
 **/
public class MainApplication {

    public static void main(String[] args) {
        DarkLunch darkLunch = new DarkLunch(3);
        DarkFeature newApiGetUserById = darkLunch.getDarkFeature("call_newapi_getUserById");
        boolean dark = newApiGetUserById.dark(893);
        System.out.println(dark);
    }
}
