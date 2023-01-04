package com.darklunch.parser;

import com.darklunch.config.DarkConfig;

import java.io.InputStream;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 13:01
 **/
public interface DarkConfigParser {

    DarkConfig parse(InputStream inputStream);
}
