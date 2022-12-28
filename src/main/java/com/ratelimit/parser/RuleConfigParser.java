package com.ratelimit.parser;

import com.ratelimit.config.RuleConfig;

import java.io.InputStream;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 22:18
 **/
public interface RuleConfigParser {
    /**
     * parse ruleConfig from inputStream
     * @param in
     * @return
     */
    RuleConfig parse(InputStream in);

    /**
     * parse ruleConfig from context
     * @param context
     * @return
     */
    RuleConfig parse(String context);
}
