package com.darklunch.core;

import com.darklunch.config.DarkRuleConfig;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 12:29
 **/
public class DarkFeature {
    private String key;
    private boolean enabled;
    private Integer percentage;
    private RangeSet<Long> rangeSet = TreeRangeSet.create();

    private static final String PERCENT_PREFIX = "%";

    public DarkFeature(DarkRuleConfig darkRuleConfig) {
        this.enabled = darkRuleConfig.isEnabled();
        this.key = darkRuleConfig.getKey();
        this.parseDarkRule(darkRuleConfig.getRule());
    }

    private void parseDarkRule(String ruleString){
        String [] rules = ruleString.split(",");
        for(String rule: rules) {
            try {
                if (rule.startsWith(PERCENT_PREFIX)){
                    String percentage = rule.substring(PERCENT_PREFIX.length());
                    this.percentage = Integer.parseInt(percentage);
                }else if (rule.contains("-")){
                    String[] startAndEnd = rule.split("-");
                    if (startAndEnd.length != 2){
                        throw new IllegalArgumentException("rule parse failed argument not correct");
                    }

                    long start = Long.parseLong(startAndEnd[0]);
                    long end = Long.parseLong(startAndEnd[1]);
                    this.rangeSet.add(Range.closed(start,end));
                }else{
                    long number = Long.parseLong(rule);
                    this.rangeSet.add(Range.closed(number,number));
                }
            }catch (Exception e){
                throw new IllegalArgumentException("rule parse failed error line was: " + rule);
            }

        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getKey() {
        return key;
    }

    public boolean dark(long darkTarget){
        boolean contain = this.rangeSet.contains(darkTarget);
        if (contain){
            return true;
        }

        long reminder = darkTarget % 100;
        return reminder >= 0 && reminder < this.percentage;
    }
}
