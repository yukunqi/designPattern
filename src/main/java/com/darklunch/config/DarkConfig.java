package com.darklunch.config;

import java.util.List;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 12:28
 **/
public class DarkConfig {
    private List<DarkRuleConfig> features;

    public DarkConfig() {
    }

    public List<DarkRuleConfig> getFeatures() {
        return features;
    }

    public void setFeatures(List<DarkRuleConfig> features) {
        this.features = features;
    }
}
