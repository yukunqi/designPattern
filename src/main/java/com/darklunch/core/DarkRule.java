package com.darklunch.core;

import com.darklunch.config.DarkConfig;
import com.darklunch.config.DarkRuleConfig;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 12:29
 **/
public class DarkRule {

    private Map<String,DarkFeature> darkFeatureMap = new LinkedHashMap<>();


    public DarkRule(DarkConfig darkConfig) {
        this.loadConfig(darkConfig);
    }


    private void loadConfig(DarkConfig darkConfig){
        List<DarkRuleConfig> ruleConfigList = darkConfig.getFeatures();

        for (DarkRuleConfig darkRuleConfig : ruleConfigList) {
            DarkFeature darkFeature = new DarkFeature(darkRuleConfig);
            this.darkFeatureMap.put(darkRuleConfig.getKey(), darkFeature);
        }

    }

    public DarkFeature get(String key){
        return this.darkFeatureMap.get(key);
    }
}
