package com.ratelimit.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 15:17
 **/
public class RuleConfig {
    private Map<String,AppIdLimit> appIdLimitLinkedHashMap = new LinkedHashMap<>();

    public RuleConfig() {
        this.load();
    }

    public Map<String, AppIdLimit> getAppIdLimitLinkedHashMap() {
        return appIdLimitLinkedHashMap;
    }

    private void load(){
        Yaml yaml = new Yaml();
        InputStream rateLimitConfigInputStream = this.getClass().getResourceAsStream("/rate-limit.yaml");
        Map<String,Object> configMap = yaml.load(rateLimitConfigInputStream);
        Map<String,Object> root = (Map<String, Object>) configMap.get("configs");

        for (Map.Entry<String,Object> app : root.entrySet()) {
            String appId = app.getKey();
            Map<String,Object> apis = (Map<String, Object>) app.getValue();
            AppIdLimit appIdLimit = new AppIdLimit(appId);

            for (Map.Entry<String,Object> api: apis.entrySet()) {
                String url = api.getKey();
                Map<String,Object> value = (Map<String, Object>) api.getValue();

                Integer limit = (Integer) value.get("limit");
                Integer unit = (Integer) value.get("unit");
                ApiLimit apiLimit;
                if (unit != null){
                     apiLimit = new ApiLimit(url,limit,unit);
                }else {
                     apiLimit = new ApiLimit(url,limit);
                }

                appIdLimit.addApiLimit(url,apiLimit);
            }

            appIdLimitLinkedHashMap.put(appId,appIdLimit);
        }
    }
}
