package com.darklunch.core;

import com.darklunch.config.DarkConfig;
import com.darklunch.datasource.ClassPathDarkConfigDataSource;
import com.darklunch.datasource.DarkConfigDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 12:29
 **/
public class DarkLunch {

    private static final Logger log = LoggerFactory.getLogger(DarkLunch.class);
    private DarkRule darkRule;
    private DarkConfigDataSource darkConfigDataSource;
    private ScheduledExecutorService executor;

    public DarkLunch(int ruleUpdateTimeInterval) {
        darkConfigDataSource = new ClassPathDarkConfigDataSource();
        this.loadDarkRule();
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.executor.scheduleAtFixedRate(()->{
            log.info("updating darkConfig");
            loadDarkRule();
            log.info("update darkConfig finished");
        },ruleUpdateTimeInterval, ruleUpdateTimeInterval, TimeUnit.SECONDS);
    }

    private void loadDarkRule(){
        DarkConfig darkConfig = darkConfigDataSource.load();
        this.darkRule = new DarkRule(darkConfig);
    }

    public void addProgrammedDarkFeature(String key,IDarkFeature iDarkFeature){
        this.darkRule.addProgrammedDarkFeature(key,iDarkFeature);
    }

    public void removeProgrammedDarkFeature(String key){
        this.darkRule.removeProgrammedDarkFeature(key);
    }

    public IDarkFeature getDarkFeature(String darkFeatureKey){
        return this.darkRule.get(darkFeatureKey);
    }
}
