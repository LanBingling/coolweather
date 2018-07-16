package com.lansir.coolweather;

import com.lansir.coolweather.util.HttpsUtils;
import com.meelive.ingkee.base.utils.encrypt.MD5Utils;
import com.meelive.ingkee.network.builder.URLBuilder;
import com.meelive.ingkee.network.builder.URLBuilderHelper;
import com.meelive.ingkee.network.http.param.IParamEntity;

import java.lang.reflect.Field;
import java.util.Map;

public abstract class BaseInkeURLBuilder extends BaseURLBuilder {
    private String cacheKey;

    @Override
    public void parse(URLBuilder.Path path, Map<String, Field> fieldMap, IParamEntity entity) {
        super.parse(path, fieldMap, entity);

        //在转换为IP之前生成cache
        cacheKey = parseCacheKey(url, commonParamsMap, paramsMap);
    }

    /**
     * 生成Http缓存KEY
     *
     * @param url    不要使用加密后的Url，保证同参一致
     * @param params
     */
    private String parseCacheKey(String url, Map... params) {
        return MD5Utils.encode(URLBuilderHelper.ins().getUrlStr(url, params));
    }

    @Override
    public String getCacheKey() {
        return cacheKey;
    }

    @Override
    public String urlEncryption(String url) {
        // TODO-wei: 21/12/2017 这里没有使用加密
        // 加密URL
        return url;
//        return SecretUtil.ins().secretUrl(url);
    }

    @Override
    public Map<String, String> getHttpsCommonParams() {
        // TODO-wei: 21/12/2017
        return HttpsUtils.getAstParamMap(url);
    }
}

