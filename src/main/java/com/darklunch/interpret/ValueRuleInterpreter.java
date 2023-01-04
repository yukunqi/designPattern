package com.darklunch.interpret;

import com.google.common.collect.Range;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-29 16:31
 **/
public class ValueRuleInterpreter implements RuleInterpreter<Range<Long>> {

    private final String value;

    public ValueRuleInterpreter(String value) {
        this.value = value;
    }

    @Override
    public Range<Long> interpret() {
        long v = Long.parseLong(value);
        return Range.closed(v,v);
    }
}
