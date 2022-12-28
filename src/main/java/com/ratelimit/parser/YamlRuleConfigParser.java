package com.ratelimit.parser;

import com.ratelimit.config.ApiLimit;
import com.ratelimit.config.AppIdLimit;
import com.ratelimit.config.RuleConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 22:21
 **/
public class YamlRuleConfigParser implements RuleConfigParser{

    private Yaml yaml = new Yaml();

    @Override
    public RuleConfig parse(InputStream in) {

        Map<String,Object> configMap = yaml.load(in);
        Map<String,Object> root = (Map<String, Object>) configMap.get("configs");
        Map<String,AppIdLimit> appIdLimitLinkedHashMap = new LinkedHashMap<>();
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

        return new RuleConfig(appIdLimitLinkedHashMap);
    }

    @Override
    public RuleConfig parse(String context) {
        return null;
    }
}
