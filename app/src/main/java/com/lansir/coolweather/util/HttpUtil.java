package com.lansir.coolweather.util;

import com.lansir.coolweather.InkeDefaultURLBuilder;
import com.lansir.coolweather.MyApplication;
import com.lansir.coolweather.gson.Weather;
import com.meelive.ingkee.network.builder.URLBuilder;
import com.meelive.ingkee.network.cache.CacheType;
import com.meelive.ingkee.network.http.HttpWorker;
import com.meelive.ingkee.network.http.param.ParamEntity;
import com.meelive.ingkee.network.http.responser.RspInkeDefault;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import rx.Observable;

public class HttpUtil {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static Observable<RspInkeDefault<Weather>> reqWeatherInfo(String address) {
        ReqWeatherParam param = new ReqWeatherParam();
        param.requestUrl(address);
        RspInkeDefault<Weather> inkeDefault = new RspInkeDefault<>(Weather.class);
        return HttpWorker.getInstace(MyApplication.getContext()).get(param, inkeDefault, null, CacheType.NO_CACHE);
    }

    @URLBuilder.Path(url="http://guolin.tech/api/weather?cityid=CN101190401&key=bc0418b57b2d4918819d3974ac1285d9", builder = InkeDefaultURLBuilder.class)
    private static class ReqWeatherParam extends ParamEntity {

    }
}
