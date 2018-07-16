package com.lansir.coolweather.util;

import java.util.HashMap;
import java.util.Map;

import static com.meelive.ingkee.base.utils.text.StringUtils.isEmpty;

public class HttpsUtils {
    private static final String SERVICE_INFO = "serviceinfo/info";

    /**
     * 如果是https并且开关打开，需要拼接参数&ast=1
     */
    public static Map<String, String> getAstParamMap(String url) {
        if (isEmpty(url)) {
            return null;
        }
        // TODO-wei: 21/12/2017
//        if(url.contains(SERVICE_INFO) && ServiceInfoManager.getInstance().isSwitch(UserManager.ins().isLogin(), ServiceInfoSwitchKey.AST_SWITCH)){
//            Map<String, String> map = new HashMap<>();
//            if(url.contains(SERVICE_INFO) && ServiceInfoManager.getInstance().isSwitch(UserManager.ins().isLogin(), ServiceInfoSwitchKey.AST_SWITCH)){
//                map.put("ast", "1");
//                return map;
//            }
//        }
        if (isHttpsRequest(url)) {
            Map<String, String> map = new HashMap<>();
            map.put("ast", "1");
            return map;
        }
        return null;
    }

    /**
     * 如果是https并且开关打开，需要拼接参数&ast=1
     * <p>
     * AST_SWITCH开关只对serviceinfo做拼接ast=1
     * <p>
     * 判断是https需要拼接参数&ast=1,
     */
    public static String getAstParam(String url) {
        if (isEmpty(url)) {
            return url;
        }
//        if(url.contains(SERVICE_INFO) && ServiceInfoManager.getInstance().isSwitch(UserManager.ins().isLogin(), ServiceInfoSwitchKey.AST_SWITCH)){
//            return url + "&ast=1";
//        }
        if (isHttpsRequest(url)) {
            return url + "&ast=1";
        }
        return url;
    }

    static boolean isHttpsRequest(String url) {
        return !isEmpty(url) && url.toLowerCase().startsWith("https");
    }
}
