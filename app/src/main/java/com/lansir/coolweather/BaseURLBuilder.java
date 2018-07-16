package com.lansir.coolweather;

import android.text.TextUtils;
import android.util.Log;

import com.meelive.ingkee.base.utils.GlobalContext;
import com.meelive.ingkee.base.utils.log.Logger;
import com.meelive.ingkee.network.builder.URLBuilder;
import com.meelive.ingkee.network.http.ParamReview;
import com.meelive.ingkee.network.http.param.IParamEntity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseURLBuilder implements URLBuilder {
    private static final String TAG = BaseURLBuilder.class.getSimpleName();

    private final String CUSTOM_URL = "requestUrl";//需要动态增加url
    private final String HEDERMAP = "headerMap";//需要动态配置header

    protected LinkedHashMap headerMap = new LinkedHashMap();
    protected Map<String, Object> paramsMap = new HashMap<>();
    protected Map<String, Object> commonParamsMap = new HashMap<>();
    protected String url = null;
    protected boolean isRetry;
    protected long cacheTimeout;//缓存过期间隔时间

    /**
     * 获得公共参数
     *
     * @return
     */
    protected abstract Map<String, String> getAtomParamsMap();

    /**
     * 获得请求头
     *
     * @param url
     * @return
     */
    protected abstract LinkedHashMap<String, String> getAtomHeaderMap(String url);

    @Override
    public void parse(Path path, Map<String, Field> fields, IParamEntity entity) {
        isRetry = path.isRetry();
        cacheTimeout = path.cacheTimeout();
        parseParams(fields, entity, paramsMap);
        /**
         * 获取高防地址
         */
        //String highPriorityUrl = KeyLinkStore.getSpecialUrl(path.urlKey());
        String highPriorityUrl = null;
        if (null != paramsMap.get(CUSTOM_URL)) {
            url = (String) paramsMap.get(CUSTOM_URL);
            paramsMap.remove(CUSTOM_URL);
        } else if (!TextUtils.isEmpty(highPriorityUrl)) {
            url = highPriorityUrl;
        } else if (!TextUtils.isEmpty(path.url())) {
            url = path.url();
        } else {
            //url = ServiceInfoManager.getInstance().getUrl(path.urlKey());
        }
        if (TextUtils.isEmpty(url)) {

            Log.e(TAG, "必须有参数url或urlKey,使用UpLoadFileParamEntity.class,key=" + path.urlKey() + ",paramName:" + entity.getClass().getName());

            // 测试环境，哎，一致不好用，暂时就不校验了
//            if (!InkeConfig.isTest() && GlobalContext.isDebug()) {
////                throw new IllegalArgumentException(
////                        "必须有参数url或urlKey,使用UpLoadFileParamEntity.class,key=" + path.urlKey() + ",paramName:" + entity.getClass().getName());
//            }

            url = "http://this_is_wrong_site_***/";
        }

        if (url.endsWith("?") && url.length() > 2) {
            url = url.substring(0, url.length() - 1);
        }

        //原子头
        Map atomicHeader = getAtomHeaderMap(url);
        if (null != atomicHeader) {
            headerMap.putAll(atomicHeader);
        }

        //自定义头
        if (null != paramsMap.get(HEDERMAP)) {
            if (paramsMap.get(HEDERMAP) instanceof LinkedHashMap) {
                headerMap.putAll((LinkedHashMap) paramsMap.get(HEDERMAP));
            } else {
                Log.e(TAG, "paramsMap headerMap must be Map,使用UpLoadFileParamEntity.class");
                if (GlobalContext.isDebug()) {
                    throw new IllegalArgumentException(
                            "paramsMap headerMap must be Map,使用UpLoadFileParamEntity.class");
                }
            }
        }
        parseCommonParams(commonParamsMap);
        ParamReview.reviewParam(url, fields, paramsMap);//合法性检查

    }


    /**
     * 解析接口参数
     *
     * @param fields
     * @param paramsMap
     */
    protected void parseParams(Map<String, Field> fields, IParamEntity entity, Map<String, Object> paramsMap) {
        if (fields != null) {
            try {
                for (Map.Entry<String, Field> entry : fields.entrySet()) {
                    Object value = null;
                    value = entry.getValue().get(entity);

                    if (value != null) {
                        paramsMap.put(entry.getKey(), value);
                        Logger.d("parse: entityMap.put" + entry.getKey() + "/value:" + value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Logger.d("parse: paramsMap.toString" + paramsMap.toString());
        }
    }

    /**
     * 增加公共参数
     *
     * @param paramsMap
     */
    public void parseCommonParams(Map<String, Object> paramsMap) {
        if (null != paramsMap) {
            Map<String, String> atomParams = getAtomParamsMap();
            if (null != atomParams) {
                paramsMap.putAll(atomParams);
            }
            Map<String, String> httpsCommonParams = getHttpsCommonParams();
            if (null != httpsCommonParams) {
                paramsMap.putAll(httpsCommonParams);
            }
        }
    }

    @Override
    public boolean isReTry() {
        return isRetry;
    }

    @Override
    public Map<String, Object> getCommonParams() {
        return commonParamsMap;
    }

    @Override
    public LinkedHashMap<String, String> getHeaderMap() {
        return headerMap;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Map<String, Object> getParams() {
        return paramsMap;
    }

    @Override
    public long getCacheTimeout() {
        return cacheTimeout;
    }
}
