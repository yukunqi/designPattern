package com.ratelimit.datasource;

import com.ratelimit.config.RuleConfig;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 21:32
 **/
public interface RuleConfigDataSource {
    /**
     * load RuleConfig
     * @return
     */
    RuleConfig load();
}
