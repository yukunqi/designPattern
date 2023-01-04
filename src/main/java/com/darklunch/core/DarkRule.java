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

    private Map<String,IDarkFeature> programmedDarkFeatures = new LinkedHashMap<>();

    private Map<String,IDarkFeature> darkFeatureMap = new LinkedHashMap<>();


    public DarkRule(DarkConfig darkConfig) {
        this.loadConfig(darkConfig);
    }

    public void addProgrammedDarkFeature(String featureKey,IDarkFeature iDarkFeature){
        if (darkFeatureMap.containsKey(featureKey)){
            throw new IllegalArgumentException("config features had the same key please use another one");
        }

        programmedDarkFeatures.put(featureKey,iDarkFeature);
    }

    public void removeProgrammedDarkFeature(String featureKey){
        programmedDarkFeatures.remove(featureKey);
    }


    private void loadConfig(DarkConfig darkConfig){
        List<DarkRuleConfig> ruleConfigList = darkConfig.getFeatures();

        for (DarkRuleConfig darkRuleConfig : ruleConfigList) {
            DarkFeature darkFeature = new DarkFeature(darkRuleConfig);
            this.darkFeatureMap.put(darkRuleConfig.getKey(), darkFeature);
        }

    }

    public IDarkFeature get(String key){
        IDarkFeature iDarkFeature = this.programmedDarkFeatures.get(key);
        if (iDarkFeature != null){
            return iDarkFeature;
        }

        return this.darkFeatureMap.get(key);
    }
}
