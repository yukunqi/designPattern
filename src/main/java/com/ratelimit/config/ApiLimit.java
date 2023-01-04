package com.ratelimit.config;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 15:18
 **/
public class ApiLimit {
    /**
     * default time unit is 1 sec
     */
    private static final int DEFAULT_TIME_UNIT = 1;
    /**
     * rate limit url
     */
    private final String url;
    /**
     * rate limit value
     */
    private final int limit;
    /**
     * time range for url limit count
     */
    //优化点 这里不要赋予默认值 而是在构造函数去赋值 既然有DEFAULT_TIME_UNIT 就有自定义的时间粒度
    private final int unit;

    public ApiLimit(String url, int limit) {
        this(url,limit,DEFAULT_TIME_UNIT);
    }

    public ApiLimit(String url, int limit, int unit) {
        this.url = url;
        this.limit = limit;
        this.unit = unit;
    }

    public String getUrl() {
        return url;
    }

    public int getLimit() {
        return limit;
    }

    public int getUnit() {
        return unit;
    }
}
