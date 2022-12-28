package com.ratelimit.datasource;

import com.ratelimit.config.RuleConfig;
import com.ratelimit.parser.RuleConfigParser;
import com.ratelimit.parser.YamlRuleConfigParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 21:34
 **/

public class ClassPathFileRuleConfigDataSource implements RuleConfigDataSource {
    private static final Logger log = LoggerFactory.getLogger(ClassPathFileRuleConfigDataSource.class);
    public static final String API_LIMIT_CONFIG_NAME = "rate-limit";

    public static final String YAML_EXTENSION = "yaml";
    public static final String YML_EXTENSION = "yml";


    private static final String[] SUPPORT_EXTENSIONS = new String[]{YAML_EXTENSION, YML_EXTENSION};
    private static final Map<String, RuleConfigParser> PARSER_MAP = new HashMap<>();

    static {
        PARSER_MAP.put(YAML_EXTENSION, new YamlRuleConfigParser());
        PARSER_MAP.put(YML_EXTENSION, new YamlRuleConfigParser());
    }

    @Override
    public RuleConfig load() {
        for (String extension : SUPPORT_EXTENSIONS){
            try(InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + API_LIMIT_CONFIG_NAME + "." + extension)){
                if (resourceAsStream != null){
                    RuleConfigParser ruleConfigParser = PARSER_MAP.get(extension);
                    return ruleConfigParser.parse(resourceAsStream);
                }
            } catch (IOException e) {
                log.error("io exception",e);
            }

        }

        return null;
    }

}
