package com.darklunch.interpret;

import com.google.common.collect.Range;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 16:24
 **/
public class RangeRuleInterpreter implements RuleInterpreter<Range<Long>>{

    private final String context;
    private static final String SPLIT_SEPARATOR = "-";

    public RangeRuleInterpreter(String context) {
        this.context = context;
    }

    @Override
    public Range<Long> interpret() {
        String[] startAndEnd = this.context.split(SPLIT_SEPARATOR);
        if (startAndEnd.length != 2){
            throw new IllegalArgumentException("rule parse failed argument not correct");
        }

        long start = Long.parseLong(startAndEnd[0]);
        long end = Long.parseLong(startAndEnd[1]);

        return Range.closed(start,end);
    }
}
