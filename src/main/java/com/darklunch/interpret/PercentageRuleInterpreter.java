package com.darklunch.interpret;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 16:23
 **/
public class PercentageRuleInterpreter implements RuleInterpreter<Integer>{

    private final String context;
    private static final String PERCENT_PREFIX = "%";

    public PercentageRuleInterpreter(String context) {
        this.context = context;
    }

    @Override
    public Integer interpret() {
        String percentage = context.substring(PERCENT_PREFIX.length());
        return Integer.parseInt(percentage);
    }
}
