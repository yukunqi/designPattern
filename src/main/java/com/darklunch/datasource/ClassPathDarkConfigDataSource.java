package com.darklunch.datasource;

import com.darklunch.config.DarkConfig;
import com.darklunch.parser.DarkConfigParser;
import com.darklunch.parser.YamlDarkConfigParser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 13:02
 **/
public class ClassPathDarkConfigDataSource implements  DarkConfigDataSource{

    private static final String DEFAULT_FILE_NAME = "dark-rule";

    public static final String YAML_EXTENSION = "yaml";
    public static final String YML_EXTENSION = "yml";

    private static final String[] SUPPORT_EXTENSIONS = new String[]{YAML_EXTENSION,YML_EXTENSION};
    private static final Map<String, DarkConfigParser> PARSER_MAP = new HashMap<>();

    static {
        PARSER_MAP.put(YAML_EXTENSION, new YamlDarkConfigParser());
        PARSER_MAP.put(YML_EXTENSION,new YamlDarkConfigParser());
    }


    public ClassPathDarkConfigDataSource() {
    }

    @Override
    public DarkConfig load() {

        for (String extension : SUPPORT_EXTENSIONS){
            InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + DEFAULT_FILE_NAME + "." +extension);
            if (resourceAsStream != null){
                DarkConfigParser darkConfigParser = PARSER_MAP.get(extension);
                return darkConfigParser.parse(resourceAsStream);
            }

        }

        return null;
    }
}
