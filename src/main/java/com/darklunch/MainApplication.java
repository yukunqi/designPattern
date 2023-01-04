package com.darklunch;

import com.darklunch.core.DarkLunch;
import com.darklunch.core.IDarkFeature;
import com.darklunch.custom.UserDefinedDarkFeature;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 14:45
 **/
public class MainApplication {

    public static void main(String[] args) {
        DarkLunch darkLunch = new DarkLunch(3);

        UserDefinedDarkFeature userDefinedDarkFeature = new UserDefinedDarkFeature();
        darkLunch.addProgrammedDarkFeature("odd-even",userDefinedDarkFeature);

        IDarkFeature newApiGetUserById = darkLunch.getDarkFeature("call_newapi_getUserById");
        boolean dark = newApiGetUserById.dark(893L);
        System.out.println(dark);


        IDarkFeature oddEvenDarkFeature = darkLunch.getDarkFeature("odd-even");
        boolean dark2 = oddEvenDarkFeature.dark(481L);
        System.out.println(dark2);

    }
}
