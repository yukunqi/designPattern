package com.darklunch.parser;

import com.darklunch.config.DarkConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 13:06
 **/
public class YamlDarkConfigParser implements DarkConfigParser{

    private Yaml yaml = new Yaml();

    public YamlDarkConfigParser() {
    }

    @Override
    public DarkConfig parse(InputStream inputStream) {
        return yaml.loadAs(inputStream, DarkConfig.class);
    }
}
