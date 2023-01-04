package com.darklunch.datasource;

import com.darklunch.config.DarkConfig;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 12:59
 **/
public interface DarkConfigDataSource {

    DarkConfig load();
}
