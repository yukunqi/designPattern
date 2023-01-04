package com.darklunch.core;

import com.darklunch.config.DarkRuleConfig;
import com.darklunch.interpret.PercentageRuleInterpreter;
import com.darklunch.interpret.RangeRuleInterpreter;
import com.darklunch.interpret.ValueRuleInterpreter;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 12:29
 **/
public class DarkFeature implements IDarkFeature{
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
                    PercentageRuleInterpreter percentageRuleInterpreter = new PercentageRuleInterpreter(rule);
                    this.percentage = percentageRuleInterpreter.interpret();
                }else if (rule.contains("-")){
                    RangeRuleInterpreter rangeRuleInterpreter = new RangeRuleInterpreter(rule);
                    this.rangeSet.add(rangeRuleInterpreter.interpret());
                }else{
                    ValueRuleInterpreter valueRuleInterpreter = new ValueRuleInterpreter(rule);
                    this.rangeSet.add(valueRuleInterpreter.interpret());
                }
            }catch (Exception e){
                throw new IllegalArgumentException("rule parse failed error line was: " + rule);
            }

        }
    }


    public String getKey() {
        return key;
    }

    @Override
    public boolean dark(Long darkTarget){
        boolean contain = this.rangeSet.contains(darkTarget);
        if (contain){
            return true;
        }

        long reminder = darkTarget % 100;
        return reminder >= 0 && reminder < this.percentage;
    }

    @Override
    public boolean enabled() {
        return this.enabled;
    }

    @Override
    public boolean dark(String darkTarget) {
        long d = Long.parseLong(darkTarget);
        return this.dark(d);
    }

}
