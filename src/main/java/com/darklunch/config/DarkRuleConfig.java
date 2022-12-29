package com.darklunch.config;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 12:35
 **/
public class DarkRuleConfig {
    private String key;
    private boolean enabled;
    private String rule;


    public String getKey() {
        return key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
